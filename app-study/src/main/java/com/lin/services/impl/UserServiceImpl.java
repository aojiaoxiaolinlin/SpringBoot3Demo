package com.lin.services.impl;

import com.lin.entities.User;
import com.lin.mappers.UserMapper;
import com.lin.services.UserService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 用户 服务层实现。
 *
 * @author linlin
 * @since 2025-07-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<User> pageRelationsQuery(Integer pageNum, Integer pageSize) {
        return userMapper.paginateWithRelations(pageNum, pageSize, QueryWrapper.create());
    }
}
