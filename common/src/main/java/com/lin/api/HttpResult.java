package com.lin.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class HttpResult {
    private int code;
    private String message;
    private Object data;

    private HttpResult(){}

    public static HttpResult success(Object data){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(200);
        httpResult.setData(data);
        httpResult.setMessage("操作成功");
        return httpResult;
    }

    public static HttpResult error(String message){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(5000);
        httpResult.setMessage(message);
        return httpResult;
    }

    public HttpResult message(String message){
        this.setMessage(message);
        return this;
    }
}
