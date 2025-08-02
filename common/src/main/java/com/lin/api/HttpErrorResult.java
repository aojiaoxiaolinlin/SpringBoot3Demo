package com.lin.api;

public record HttpErrorResult(int code, String message, String error) {
}
