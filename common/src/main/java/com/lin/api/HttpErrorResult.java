package com.lin.api;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record HttpErrorResult(int code, String message, String error) implements Serializable {
}
