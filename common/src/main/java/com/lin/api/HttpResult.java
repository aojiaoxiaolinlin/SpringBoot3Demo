package com.lin.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResult<T> {
    private int code;
    private String message;
    private T data;

    private HttpResult() {
    }

    public static <U> HttpResult<U> success(U data) {
        HttpResult<U> httpResult = new HttpResult<>();
        httpResult.setCode(200);
        httpResult.setData(data);
        httpResult.setMessage("操作成功");
        return httpResult;
    }

    public static <U> HttpResult<U> error(String message) {
        HttpResult<U> httpResult = new HttpResult<>();
        httpResult.setCode(5000);
        httpResult.setMessage(message);
        return httpResult;
    }

    public HttpResult<T> message(String message) {
        this.setMessage(message);
        return this;
    }
}
