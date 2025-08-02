package com.lin.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.hutool.core.util.StrUtil;
import com.lin.api.HttpErrorResult;
import com.lin.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpErrorResult httpErrorResult(BusinessException e) {
        return new HttpErrorResult(e.getCode(), "出现异常", e.getMessage());
    }
}
