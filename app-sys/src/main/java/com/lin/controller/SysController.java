package com.lin.controller;

import com.lin.api.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sys")
public class SysController {

    @GetMapping("hello")
    public HttpResult<String> hello() {
        return HttpResult.success("hello");
    }
}
