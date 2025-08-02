package com.lin.entities;

import com.mybatisflex.annotation.ColumnAlias;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 书 实体类。
 *
 * @author linlin
 * @since 2025-07-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("book")
public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    @ColumnAlias("user_id")
    private Integer authorId;

}
