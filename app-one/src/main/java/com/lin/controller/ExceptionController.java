package com.lin.controller;

import com.lin.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exception")
public class ExceptionController {

    @GetMapping("test")
    public String test() {
        throw new BusinessException(400, "测试异常");
    }
}
