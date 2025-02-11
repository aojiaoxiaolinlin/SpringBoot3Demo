package com.lin.entities.mappers;

import com.lin.entities.Role;
import com.lin.entities.vo.RoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleToRoleVoMapper {
    RoleVo convertRoleVo(Role role);
}
