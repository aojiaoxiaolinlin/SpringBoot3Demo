package com.lin.utils;


import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import com.mybatisflex.core.keygen.IKeyGenerator;

public class ULIDGenerate implements IKeyGenerator {

    @Override
    public Object generate(Object entity, String keyColumn) {
        Ulid ulid = UlidCreator.getUlid();
        return ulid.toString();
    }
}
