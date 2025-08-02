package com.lin.entities.vo;


import com.lin.entities.User;
import com.mybatisflex.annotation.TableRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableRef(User.class)
public class UserVo extends BaseVo {

    private String account;

    private String username;

    private String realName;

    private List<String> roles;
}
