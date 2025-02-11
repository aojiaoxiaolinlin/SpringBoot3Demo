package com.lin.exception.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.hutool.core.util.StrUtil;
import com.lin.api.HttpErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public HttpErrorResult handlerNotLoginException(NotLoginException e) {
        return new HttpErrorResult(e.getCode(), "未登录", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpErrorResult httpErrorResult(MethodArgumentNotValidException e) {
        return new HttpErrorResult(400, "请求参数校验失败",
                Arrays.toString(e.getFieldErrors().stream()
                                 .map(item ->
                                         StrUtil.format("field: {}, message: {}",
                                                 item.getField(),
                                                 item.getDefaultMessage()))
                                 .toArray()));
    }
}
