package com.one.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class EmitterService {

    public void useEmitter(ResponseBodyEmitter responseBodyEmitter) {
        responseBodyEmitter.onCompletion(()->{
            log.info("结束");
        });
        responseBodyEmitter.onTimeout(()->{
            log.info("超时");
        });
        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    responseBodyEmitter.send("Hello ResponseEmitter, 数据:" + i);
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            responseBodyEmitter.complete();
        });
    }

    public void useEmitter(SseEmitter sseEmitter) {
        sseEmitter.onCompletion(() -> {
            log.info("结束");
        });
        sseEmitter.onTimeout(() -> {
            log.info("超时");
        });
        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    sseEmitter.send(SseEmitter.event()
                                              .name("message")  // 事件名
                                              .data("Hello SSE, 数据: " + i)  // 自动添加 "data:" 前缀
                                              .id("1")          // 事件 ID
                                              .reconnectTime(5000)); // 重连时间
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            sseEmitter.complete();
        });
    }
}
