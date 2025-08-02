package com.lin.services;

import com.lin.entities.Owner;
import com.lin.entities.vo.OwnerVo;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 *  服务层。
 *
 * @author linlin
 * @since 2025-07-01
 */
public interface OwnerService extends IService<Owner> {
    List<OwnerVo> getOwnerDevice();

    List<Owner> getListRelations();
}
