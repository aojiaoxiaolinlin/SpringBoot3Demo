package com.lin.entities.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
