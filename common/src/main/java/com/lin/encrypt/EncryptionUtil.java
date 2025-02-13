package com.lin.encrypt;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @Author 霖霖
 * @Date 2024/12/10 上午10:57
 * @Description 加密工具
 */
public class EncryptionUtil {

    private static final String RSA_ALGORITHM = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";

    /**
     * AES/CBC/PKCS5Padding 加密
     *
     * @param data              待加密数据
     * @param frontendPublicKey 公钥
     *
     * @return 加密对象
     */
    public static JSONObject encrypt(Object data, String frontendPublicKey) {
        RSA rsa = new RSA(RSA_ALGORITHM, null, frontendPublicKey);
        // 生成16位随机数字符串
        String iv = RandomStringUtils.secureStrong().nextAlphabetic(16);
        String aesKey = RandomStringUtils.secureStrong().nextAlphabetic(16);
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, aesKey.getBytes(), iv.getBytes());
        String jsonData = JSONObject.toJSONString(data);
        String encryptedData = aes.encryptBase64(jsonData);
        // RSA 加密 aes 密钥
        String aesKeyEncrypt = rsa.encryptBase64(aesKey, KeyType.PublicKey);
        // RSA 加密 iv
        String ivEncrypt = rsa.encryptBase64(iv, KeyType.PublicKey);
        // RSA 加签
        long timestamp = System.currentTimeMillis();
        Digester digester = SecureUtil.sha256();
        // 因为前后端签名无法相互验证，所以这里使用摘要代替签名
        String digest = digester.digestHex(jsonData + "{" + (timestamp + 30 * 60 * 1000) + "}");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", encryptedData);
        jsonObject.put("e", aesKeyEncrypt);
        jsonObject.put("k", ivEncrypt);
        jsonObject.put("s", digest);
        jsonObject.put("timestamp", timestamp);

        return jsonObject;
    }
}
