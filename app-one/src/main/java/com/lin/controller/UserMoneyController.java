package com.lin.controller;

import com.lin.service.UserMoneyService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户余额表 前端控制器
 * </p>
 *
 * @author 霖霖
 * @since 2025-06-29
 */
@RestController
@RequestMapping("userMoney")
public class UserMoneyController {

    @Resource
    private UserMoneyService userMoneyService;

    @GetMapping
    public Boolean userMoney() {
        return userMoneyService.transfer(1, 2, 1000L);
    }
}
