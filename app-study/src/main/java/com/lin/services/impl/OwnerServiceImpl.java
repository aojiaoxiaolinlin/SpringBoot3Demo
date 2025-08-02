package com.lin.services.impl;

import com.lin.entities.Owner;
import com.lin.entities.vo.OwnerVo;
import com.lin.mappers.OwnerMapper;
import com.lin.services.OwnerService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务层实现。
 *
 * @author linlin
 * @since 2025-07-01
 */
@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper, Owner> implements OwnerService {

    @Resource
    private OwnerMapper ownerMapper;


    @Override
    public List<OwnerVo> getOwnerDevice() {
        return ownerMapper.selectOwnerDevice();
    }

    @Override
    public List<Owner> getListRelations() {
        return ownerMapper.selectAllWithRelations();
    }
}
