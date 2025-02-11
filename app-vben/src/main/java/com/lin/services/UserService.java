package com.lin.services;

import com.mybatisflex.core.service.IService;
import com.lin.entities.User;

/**
 * 用户表 服务层。
 *
 * @author linlin
 * @since 2025-02-06
 */
public interface UserService extends IService<User> {

    User login(String username, String password);

}
