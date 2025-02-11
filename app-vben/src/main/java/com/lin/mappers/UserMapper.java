package com.lin.mappers;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import com.lin.entities.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表 映射层。
 *
 * @author linlin
 * @since 2025-02-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUserRoleById(@Param("id") String id);
}
