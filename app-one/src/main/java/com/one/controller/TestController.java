package com.one.controller;

import com.lin.api.HttpResult;
import com.one.entities.GithubUserInfo;
import com.one.service.HttpService;
import com.one.service.EmitterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api")
public class TestController {

    @Resource
    private HttpService httpService;

    @Resource
    private EmitterService emitterService;

    public TestController(HttpService httpService) {
        this.httpService = httpService;
    }

    @GetMapping("test")
    public HttpResult test(@RequestParam("name") String name, @RequestParam("age") int age) {
        return HttpResult.success("Hello " + name + " " + age);
    }

    @GetMapping("test/{id}")
    public HttpResult test(@PathVariable String id) {
        log.info(Thread.currentThread().toString());
        return HttpResult.success("Hello " + id);
    }

    @GetMapping("dataStreamByFlux")
    public Flux<Integer> getDataStreamByFlux() {
        return Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(value = "dataStreamByFluxSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getDataStreamByFluxSSE() {
        return Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("githubUsersBlock")
    public List<GithubUserInfo> getGithubUserInfoBlock() {
        return httpService.getUserInfo().collectList().block();
    }

    // 前端正常接收
    @GetMapping(value = "githubUsers")
    public Flux<GithubUserInfo> getGithubUserInfo() {
        return httpService.getUserInfo();
    }

    // SSE 效果
    @GetMapping(value = "githubUsersSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<GithubUserInfo> getGithubUserInfoSSE() {
        return httpService.getUserInfo();
    }

    // 通用异步响应 加了MediaType.TEXT_EVENT_STREAM_VALUE 也不是SSE,SseEmitter 是其子类专用于SSE协议
    @GetMapping(value = "responseEmitter")
    public ResponseBodyEmitter getUserInfoByEmitter() {
        ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();
        emitterService.useEmitter(responseBodyEmitter);
        return responseBodyEmitter;
    }

    // 加不加MediaType.TEXT_EVENT_STREAM_VALUE都是一样的
    @GetMapping(value = "sse")
    public SseEmitter getUserInfoBySseEmitter() {
        SseEmitter sseEmitter = new SseEmitter();
        emitterService.useEmitter(sseEmitter);
        return sseEmitter;
    }
}
