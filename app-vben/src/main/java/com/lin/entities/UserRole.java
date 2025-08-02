package com.lin.entities;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 *  实体类。
 *
 * @author linlin
 * @since 2025-02-07
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table("user_role")
public class UserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @Id
    private String userId;

    /**
     * 角色id
     */
    @Id
    private String roleId;

}
