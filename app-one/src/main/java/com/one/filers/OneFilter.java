package com.one.filers;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

/**
 * 既能是Restful也能使GraphQl
 */
@Component
public class OneFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
                                                                                                         IOException,
                                                                                                         ServletException {
        // 只有使用过才能获取到body
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        // String s = new String(bytes, StandardCharsets.UTF_8);
        // System.out.println("请求体:" + s);
        // JSONObject jsonObject = JSONObject.parseObject(s);
        // JSONObject variables = jsonObject.getJSONObject("variables");
        // System.out.println("参数:" + variables);
        chain.doFilter(contentCachingRequestWrapper, response);
    }
}
