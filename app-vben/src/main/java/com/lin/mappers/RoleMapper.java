package com.lin.mappers;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import com.lin.entities.Role;

/**
 * 角色表 映射层。
 *
 * @author linlin
 * @since 2025-02-07
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
