package com.lin.mappers;

import com.lin.entities.Owner;
import com.lin.entities.vo.OwnerVo;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  映射层。
 *
 * @author linlin
 * @since 2025-07-01
 */
@Mapper
public interface OwnerMapper extends BaseMapper<Owner> {
   List<OwnerVo> selectOwnerDevice();
}
