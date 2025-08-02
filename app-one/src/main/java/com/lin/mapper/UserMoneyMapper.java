package com.lin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.entities.UserMoney;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户余额表 Mapper 接口
 * </p>
 *
 * @author 霖霖
 * @since 2025-06-29
 */
@Mapper
public interface UserMoneyMapper extends BaseMapper<UserMoney> {

}
