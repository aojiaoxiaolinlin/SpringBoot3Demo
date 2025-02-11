package com.lin.services.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lin.entities.UserRole;
import com.lin.mappers.UserRoleMapper;
import com.lin.services.UserRoleService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author linlin
 * @since 2025-02-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>  implements UserRoleService{

}
