package com.lin.entities.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String id;
    private String account;
    private String username;
    private String realName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private List<String> roles;
}
