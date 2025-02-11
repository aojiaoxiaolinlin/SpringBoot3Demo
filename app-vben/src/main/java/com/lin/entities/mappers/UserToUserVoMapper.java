package com.lin.entities.mappers;

import com.lin.entities.User;
import com.lin.entities.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserToUserVoMapper {
    @Mapping(target = "roles", expression = "java( user.getRoles().stream().map(role -> role.getRoleName()).collect(java.util.stream.Collectors.toList()) )")
    UserVo convertVo(User user);
}
