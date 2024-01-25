package com.lin.springboot3demo.service;

import com.lin.springboot3demo.entity.GithubUserInfo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


import java.util.List;

@Service
public class HttpServer {

    public Flux<GithubUserInfo> getRequest() {
//        List<GithubUserInfo> userInfos = WebClient.create("https://api.github.com/users")
//                .get()
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(GithubUserInfo.class)
//                .collectList()
//                .block();
//
//        if (userInfos != null) {
//            userInfos.forEach(System.out::println);
//        }
//        else {
//            System.out.println("No users found");
//        }

        return WebClient.create("https://api.github.com/users")
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(GithubUserInfo.class);
    }
}
