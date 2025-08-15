package com.lin.encrypt.filter;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.AES;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author 霖霖
 * @Date 2024/12/4 下午11:24
 * @Description
 */
@Slf4j
public class DecryptFilter extends HttpFilter {
    private final String backendPrivateKey;

    public DecryptFilter(String backendPrivateKey) {
        this.backendPrivateKey = backendPrivateKey;
    }

    private static final String RSA_ALGORITHM = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
                                                                                                      IOException,
                                                                                                      ServletException {
        DecryptionRequestWrapper decryptionRequestWrapper = new DecryptionRequestWrapper(request);
        String bodyString = decryptionRequestWrapper.getBodyString();
        // 请求体为空，尝试从请求参数中获取，此过程并未使用，写着未删除罢了，如果请求体为空，请检查是否有请求体。
        // 标准 加密请求体格式：{"data":"加密数据","timestamp":"时间戳"}，所以从设计上来说，请求体不应该为空。
        if (bodyString == null || bodyString.isEmpty()) {
            chain.doFilter(decryptionRequestWrapper, response);
            return;
        }

        log.info("[ EncryptFilter ] >> requestUrI:{}  requestBody:{}", request.getRequestURI(), bodyString);
        JSONObject jsonObject = JSONObject.parseObject(bodyString);
        String encryptData = jsonObject.get("data").toString();
        Long timestamp = (Long) jsonObject.get("timestamp");

        String decryptData = decrypt(encryptData, request, response, timestamp);
        if (StringUtils.isEmpty(decryptData)) {
            return;
        }

        decryptionRequestWrapper.setBody(decryptData.getBytes(StandardCharsets.UTF_8));
        chain.doFilter(decryptionRequestWrapper, response);

    }


    private String decrypt(String encryptData, HttpServletRequest request, HttpServletResponse response,
                           Long timestamp) throws IOException {
        // 解密
        // 1. 获取加密后的 AES 秘钥
        String aesKeyEncrypted = request.getHeader("e");
        // 2. 获取加密后的 AES 向量
        String aesIvEncrypted = request.getHeader("i");
        // 3. 获取签名
        String sign = request.getHeader("s");
        // 4. RSA 解密 AES 秘钥和 AES 向量
        RSA rsa = new RSA(RSA_ALGORITHM, backendPrivateKey, null);
        String decryptData;
        try {
            String aesKey = rsa.decryptStr(aesKeyEncrypted, KeyType.PrivateKey);
            String aesIv = rsa.decryptStr(aesIvEncrypted, KeyType.PrivateKey);
            // 5. AES 解密数据
            AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, Base64Decoder.decode(aesKey),
                    Base64Decoder.decode(aesIv));
            decryptData = aes.decryptStr(encryptData);
        } catch (Exception e) {
            // 解密失败，不合法。
            log.error("解密失败:{}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write("非法请求参数");
            return null;
        }
        // 6.验签
        // 使用Sha256提取decryptData摘要
        long timeOut = 30 * 60 * 1000;
        String verifySign = new Digester(DigestAlgorithm.SHA256).digestHex(
                decryptData + "{" + (timestamp + timeOut) + "}");
        if (!verifySign.equals(sign)) {
            throw new RuntimeException("签名验证失败");
        }
        return decryptData;
    }
}
