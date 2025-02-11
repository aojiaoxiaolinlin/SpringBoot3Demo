package com.lin.controllers;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.lin.api.HttpResult;
import com.lin.entities.User;
import com.lin.entities.dto.UserDto;
import com.lin.services.UserService;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.lin.entities.table.UserTableDef.USER;

@Slf4j
@RestController("auth")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private SaTokenConfig saTokenConfig;


    /**
     * 用户登录接口
     *
     * @param userDTo login 信息
     *
     * @return 用户信息
     */
    @PostMapping("auth/login")
    public HttpResult login(@Valid @RequestBody UserDto userDTo) {
        // 已经登录的只重置token过期时间
        if (StpUtil.isLogin()) {
            StpUtil.renewTimeout(saTokenConfig.getTimeout());
        } else {
            User user = userService.getOne(QueryWrapper.create()
                                                       .where(USER.ACCOUNT.eq(userDTo.getAccount()))
                                                       .and(USER.PASSWORD.eq(userDTo.getPassword())));
            if (user == null) {
                return HttpResult.error("用户名或密码错误!");
            }
            StpUtil.login(user.getId());
        }
        return HttpResult.success(JSONObject.of("accessToken", StpUtil.getTokenValue()));
    }
}
