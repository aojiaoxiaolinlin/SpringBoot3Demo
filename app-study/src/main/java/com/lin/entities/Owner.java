package com.lin.entities;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.RelationOneToOne;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 实体类。
 *
 * @author linlin
 * @since 2025-07-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("owner")
public class Owner implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    private Integer id;

    private String name;

    private Integer firstId;

    private Integer secondId;


    @RelationOneToOne(targetField = "id", selfField = "firstId")
    private Device first;

    @RelationOneToOne(targetField = "id", selfField = "secondId")
    private Device second;
}
