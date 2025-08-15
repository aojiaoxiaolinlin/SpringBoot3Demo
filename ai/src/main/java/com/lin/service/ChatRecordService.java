package com.lin.service;


import org.apache.commons.lang3.ObjectUtils;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.lin.service.temp.cache.ChatRecordCache.USER_CHATS;

/**
 * @Author 霖霖
 * @Date 2025/2/16 11:56
 * @Description
 */
@Service
public class ChatRecordService {

    public boolean deleteChatById(String userId, String id) {
        Map<String, Prompt> chats = USER_CHATS.get(userId);
        if (ObjectUtils.isEmpty(chats)) {
            return false;
        }
        chats.remove(id);
        return true;
    }
}
