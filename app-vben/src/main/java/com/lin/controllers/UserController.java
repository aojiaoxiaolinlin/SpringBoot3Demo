package com.lin.controllers;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.lin.api.HttpResult;
import com.lin.entities.User;
import com.lin.entities.mappers.UserToVoMapper;
import com.lin.entities.vo.UserVo;
import com.lin.services.UserService;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lin.entities.table.RoleTableDef.ROLE;
import static com.lin.entities.table.UserRoleTableDef.USER_ROLE;
import static com.lin.entities.table.UserTableDef.USER;

@Slf4j
@RestController
@RequestMapping("user")
@SaCheckLogin
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserToVoMapper userToVoMapper;


    @GetMapping("info")
    public HttpResult<UserVo> info() {
        String loginId = StpUtil.getLoginIdAsString();
        User user = userService.getOne(QueryWrapper.create()
                                                   .from(USER)
                                                   .join(USER_ROLE).on(USER.ID.eq(USER_ROLE.USER_ID))
                                                   .join(ROLE).on(ROLE.ID.eq(USER_ROLE.ROLE_ID))
                                                   .where(USER.ID.eq(loginId)));
        return HttpResult.success(userToVoMapper.toVo(user));
    }

}
