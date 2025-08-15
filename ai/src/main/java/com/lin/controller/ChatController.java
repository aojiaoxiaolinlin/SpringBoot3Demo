package com.lin.controller;


import com.lin.api.HttpResult;
import com.lin.entities.dto.MessageDto;
import com.lin.service.ChatRecordService;
import com.lin.service.HuaWeiChatModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@RestController
public class ChatController {
    @Resource
    private HuaWeiChatModel huaWeiChatModel;
    @Resource
    private ChatRecordService chatRecordService;

    private static final ConcurrentHashMap<String, Sinks.Many<ChatResponse>> userSinks = new ConcurrentHashMap<>();

    @RequestMapping(value = "customChat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<LinkedHashMap<String, Object>> customChat() {
        return huaWeiChatModel.customChat();
    }

    @GetMapping(value = "chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> chat(@RequestParam("userId") String userId) {
        log.info("连接:{}", userId);
        return userSinks.computeIfAbsent(userId, key ->
                                Sinks.many()
                                     .unicast()
                                     .onBackpressureBuffer())
                        .asFlux();
    }

    @PostMapping("chat")
    public HttpResult chat(@RequestBody MessageDto messageDto) {
        log.info("用户请求消息: {}", messageDto.getContent());
        Sinks.Many<ChatResponse> chatResponseMany = userSinks.get(messageDto.getUserId());
        huaWeiChatModel.chat(messageDto.getUserId(), messageDto.getChatId(), messageDto.getContent())
                       .subscribe(chatResponseMany::tryEmitNext);
        return HttpResult.success(true);
    }

    @DeleteMapping("chat/{userId}/{id}")
    public HttpResult deleteChatById(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        return HttpResult.success(chatRecordService.deleteChatById(userId, id));
    }

    @DeleteMapping("/chat/{userId}")
    public HttpResult closeSse(@PathVariable("userId") String userId) {
        cleanupUserSink(userId);
        return HttpResult.success(true);
    }

    // 清理用户 Sink
    private void cleanupUserSink(String userId) {
        Sinks.Many<ChatResponse> removed = userSinks.remove(userId);
        log.info("用户:{}离开取消连接", userId);
        if (ObjectUtils.isNotEmpty(removed)) {
            removed.tryEmitComplete();
        }
    }
}
