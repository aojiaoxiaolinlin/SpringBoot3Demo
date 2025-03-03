package com.lin.entities.dto;


import lombok.Data;

/**
 * @Author 霖霖
 * @Date 2025/2/14 22:44
 * @Description
 */
@Data
public class MessageDto {
    private String UserId;
    private String chatId;
    private String content;

}
