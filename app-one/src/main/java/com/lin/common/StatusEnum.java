package com.lin.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum StatusEnum implements IDictionary {
    //     待处理,处理中,处理完成
    WAITING(0, "待处理"),
    PROCESSING(1, "处理中"),
    FINISHED(2, "处理完成");

    private final String label;

    private final int key;

    @JsonValue
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    StatusEnum(int key, String label) {
        this.label = label;
        this.key = key;
    }

    @JsonCreator
    public static StatusEnum fromKey(int key) {
        return Arrays.stream(values())
                     .filter(v -> v.key == key)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("无效状态码: " + key));
    }
}
