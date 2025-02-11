package com.lin.services.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lin.entities.Role;
import com.lin.mappers.RoleMapper;
import com.lin.services.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务层实现。
 *
 * @author linlin
 * @since 2025-02-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>  implements RoleService{

}
