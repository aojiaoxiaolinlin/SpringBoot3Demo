package com.lin.services.impl;

import com.lin.services.UserService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lin.entities.User;
import com.lin.mappers.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import static com.lin.entities.table.UserTableDef.USER;

/**
 * 用户表 服务层实现。
 *
 * @author linlin
 * @since 2025-02-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.where(USER.ACCOUNT.eq(username))
                            .and(USER.PASSWORD.eq(password));
        return userMapper.selectOneWithRelationsByQueryAs(queryWrapper, User.class);
    }
}
