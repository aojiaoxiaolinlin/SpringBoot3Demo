package com.lin.services.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lin.entities.Device;
import com.lin.mappers.DeviceMapper;
import com.lin.services.DeviceService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author linlin
 * @since 2025-07-01
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device>  implements DeviceService{

}
