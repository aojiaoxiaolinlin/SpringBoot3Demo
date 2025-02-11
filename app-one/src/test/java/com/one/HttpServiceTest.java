package com.one;

import com.one.service.HttpService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
public class HttpServiceTest {
    @Resource
    private HttpService httpService;

    @Test
    void testGetUserInfo() {
        Objects.requireNonNull(httpService.getUserInfo().collectList().block()).forEach(System.out::println);
    }

    @Test
    void testGetUserInfoForRestClient() {
        httpService.getUserInfoByRestClient().forEach(System.out::println);
    }

    @Test
    void testGetUserInfoForRestClientAndInterface() {
        httpService.getUserInfoByRestClientAndInterface().forEach(System.out::println);
    }
}
