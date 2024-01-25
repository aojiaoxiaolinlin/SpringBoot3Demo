package com.lin.springboot3demo;

import com.lin.springboot3demo.service.HttpServer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.Objects;

@SpringBootTest
class SpringBoot3DemoApplicationTests {

    @Resource
    private HttpServer httpServer;

    @Test
    void contextLoads() {
        Objects.requireNonNull(httpServer.getRequest().collectList().block()).forEach(System.out::println);
    }

    @Test
    void test() {
        Flux.generate(() -> 0, (i, sink) -> {
            if (i > 10) sink.complete();
            sink.next("数据" + i);
            return ++i;
        }).subscribe(System.out::println);
    }

}
