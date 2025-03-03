package com.lin;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * @Author 霖霖
 * @Date 2025/2/14 10:34
 * @Description
 */
@SpringBootTest
public class AiApplicationTest {
    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Test
    void clientTest() {
        WebClient webClient = WebClient
                .builder().baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

        JSONObject body = getBody();
        JSONObject result = webClient.post().uri("/v1/chat/completions")
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .bodyValue(body)
                                     .accept(MediaType.APPLICATION_JSON)
                                     .retrieve()
                                     .bodyToMono(JSONObject.class).block();
        System.out.println(Objects.requireNonNull(result).toJSONString());
    }

    private static JSONObject getBody() {
        JSONArray messages = new JSONArray();
        JSONObject system = new JSONObject();
        system.put("role", "system");
        system.put("content", "你是很有帮助的助理。");
        messages.add(system);
        JSONObject user = new JSONObject();
        user.put("role", "user");
        user.put("content", "你好！");
        messages.add(user);
        JSONObject body = new JSONObject();
        body.put("model", "DeepSeek-R1");
        body.put("max_tokens", 4096);
        body.put("stream", false);
        body.put("messages", messages);
        return body;
    }
}
