package com.lin.springboot3demo.controller;

import com.lin.springboot3demo.entity.GithubUserInfo;
import com.lin.springboot3demo.service.HttpServer;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@RequestMapping("flux")
@RestController
@CrossOrigin
public class TestController {

    @GetMapping
    public String get1() {
        return "数据";
    }

    @GetMapping("mono")
    public Mono<String> get() {
        return Mono.just("Mono数据");
    }

    @GetMapping("flux")
    public Flux<String> getFlux() {
        return Flux.just("one", "two", "three");
    }

    @GetMapping(value = "dataStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getDataStream() {
        return Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(value = "dataStream2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getDataStream2() {
        return Flux.generate(() -> 0, (i, sink) -> {
            if (i > 10) sink.complete();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sink.next("数据" + i);
            return ++i;
        });
    }

    @GetMapping(value = "dataStream3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Multi<Integer> getDataStream3() {
        Random random = new Random();
        return Multi.createFrom().items(1, 2, 3, 4, 5)
                .onItem().call(i -> {
                    Duration delay = Duration.ofMillis(random.nextInt(1000) + 1);
                    return Uni.createFrom().nullItem().onItem().delayIt().by(delay);
                });
    }

    @Resource
    private HttpServer httpServer;

    @GetMapping("getGitHubUser")
    public Flux<GithubUserInfo> getGithubUser() {
        return httpServer.getRequest();
    }

}
