package com.lin.entities.vo;

import com.lin.entities.Device;
import com.lin.entities.Owner;
import com.mybatisflex.annotation.TableRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableRef(Owner.class)
public class OwnerVo {
    private Integer id;

    private String name;

    private Device first;

    private Device second;

}

