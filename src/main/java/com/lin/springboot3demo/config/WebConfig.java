package com.lin.springboot3demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 配置跨越解决GraphQL请求CORS问题
     *
     * @param registry 跨域注册
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/graphql/**")
                .allowedMethods(HttpMethod.POST.name());
        registry.addMapping("/getGraphQL/**")
                .allowedMethods(HttpMethod.GET.name());
    }
}
