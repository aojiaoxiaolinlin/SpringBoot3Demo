package com.lin.service.temp.cache;


import org.springframework.ai.chat.prompt.Prompt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 霖霖
 * @Date 2025/2/16 11:53
 * @Description
 */
public class ChatRecordCache {
    // {userId: {chatId:{prompt}}}
   public static final ConcurrentHashMap<String, Map<String, Prompt>> USER_CHATS = new ConcurrentHashMap<>();
}
