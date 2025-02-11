package com.one.service;

import com.one.entities.GithubUserInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class HttpService {
    public Flux<GithubUserInfo> getUserInfo() {
        // 异步/同步API
        return WebClient.create("https://api.github.com/users")
                        .get()
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(GithubUserInfo.class);
    }

    public List<GithubUserInfo> getUserInfoByRestClient() {
        // 同步API
        RestClient restClient = RestClient.builder()
                                          .baseUrl("https://api.github.com")
                                          .build();
        ParameterizedTypeReference<List<GithubUserInfo>> parameterizedTypeReference =
                new ParameterizedTypeReference<>() {};
        return restClient.get().uri("users").accept(MediaType.APPLICATION_JSON)
                         .retrieve().body(parameterizedTypeReference);
    }

    public List<GithubUserInfo> getUserInfoByRestClientAndInterface() {
        RestClient restClient = RestClient.create("https://api.github.com");
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        UserInfo client = proxyFactory.createClient(UserInfo.class);
        return client.getUsers();

    }
}
