package com.lin.service;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.lin.service.temp.cache.ChatRecordCache.USER_CHATS;

/**
 * @Author 霖霖
 * @Date 2025/2/14 10:00
 * @Description
 */
@Service
public class HuaWeiChatModel {
    private static final Logger log = LoggerFactory.getLogger(HuaWeiChatModel.class);
    @Resource
    private ChatModel chatModel;

    public Flux<ChatResponse> chat(String userId, String chatId, String message) {
        Map<String, Prompt> userPrompt = USER_CHATS.computeIfAbsent(userId, key -> new ConcurrentHashMap<>());

        UserMessage userMessage = new UserMessage(message);
        Prompt prompt = userPrompt.computeIfAbsent(chatId, key -> new Prompt(new ArrayList<>()));
        List<Message> tempInstructions = new ArrayList<>(prompt.getInstructions());
        tempInstructions.add(userMessage);
        ArrayList<String> tempText = new ArrayList<>();

        return chatModel
                .stream(new Prompt(tempInstructions))
                .doOnNext((item) -> {
                    if (ObjectUtils.isNotEmpty(item.getResult()) &&
                        ObjectUtils.isNotEmpty(item.getResult().getOutput())) {
                        tempText.add(item.getResult().getOutput().getText());
                    }
                }).doOnComplete(() -> {
                    if (ObjectUtils.isNotEmpty(tempText)) {
                        prompt.getInstructions().add(userMessage);
                        prompt.getInstructions().add(new AssistantMessage(String.join("", tempText)));
                    }
                }).doOnError((error) -> log.error("发生错误: {}", error.getMessage()));
    }


    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public Flux<LinkedHashMap<String, Object>> customChat() {
        WebClient webClient = WebClient
                .builder().baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

        JSONObject body = getBody();
        ParameterizedTypeReference<LinkedHashMap<String, Object>> typeReference = new ParameterizedTypeReference<>() {};
        return webClient.post().uri("/v1/chat/completions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(body)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(typeReference);
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
        body.put("stream", true);
        body.put("messages", messages);
        return body;
    }
}
