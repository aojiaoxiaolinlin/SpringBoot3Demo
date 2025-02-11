package com.lin.entities;


import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.RelationManyToMany;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 用户表 实体类。
 *
 * @author linlin
 * @since 2025-02-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("user")
public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = "ULID")
    private String id;

    /**
     * 真名
     */
    private String realName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    @RelationManyToMany(
            joinTable = "user_role",
            joinSelfColumn = "user_id",
            joinTargetColumn = "role_id"
    )
    private List<Role> roles;

}
