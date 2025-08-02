package com.lin.mappers;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import com.lin.entities.User;

/**
 * 用户 映射层。
 *
 * @author linlin
 * @since 2025-07-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
