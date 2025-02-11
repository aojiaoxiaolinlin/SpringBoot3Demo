package com.lin.entities.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {
    private String id;
    private String roleName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
