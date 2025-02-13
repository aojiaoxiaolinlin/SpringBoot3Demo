package com.lin.controller;


import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 霖霖
 * @Date 2025/2/12 09:51
 * @Description 测试
 */
@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Resource
    private AuthenticationManager authenticationManager;

    @RequestMapping("login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        log.info("login");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate.isAuthenticated()) {
            return ((User) authenticate.getPrincipal()).getUsername();
        }
        return "error";
    }

    @GetMapping("info")
    public String info() {
        return "Hello World";
    }
}
