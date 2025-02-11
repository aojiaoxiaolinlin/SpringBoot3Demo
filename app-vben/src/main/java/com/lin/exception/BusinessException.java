package com.lin.exception;

import com.lin.api.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private int code;

    public BusinessException(HttpStatus httpStatus) {
        this.code = httpStatus.getStatus();

    }
}
