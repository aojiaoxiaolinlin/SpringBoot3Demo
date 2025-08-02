package com.lin.services;

import com.lin.entities.User;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;

/**
 * 用户 服务层。
 *
 * @author linlin
 * @since 2025-07-01
 */
public interface UserService extends IService<User> {

    Page<User> pageRelationsQuery(Integer pageNum, Integer pageSize);
}
