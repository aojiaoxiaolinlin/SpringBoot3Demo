package com.lin.entities;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 角色表 实体类。
 *
 * @author linlin
 * @since 2025-02-07
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("role")
public class Role extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ULID主键
     */
    @Id(keyType = KeyType.Generator, value = "ULID")
    private String id;

    /**
     * 角色名
     */
    private String roleName;

}
