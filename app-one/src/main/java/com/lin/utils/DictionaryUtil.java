package com.lin.utils;

import com.lin.common.IDictionary;
import jakarta.annotation.Nonnull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryUtil {

    public static <T extends IDictionary> List<Map<String, Object>> getDictionaryList(@Nonnull Class<T> clazz) {
        return Arrays.stream(clazz.getEnumConstants())
                     .map(enumItem -> {
                         Map<String, Object> item = new HashMap<>(2);
                         item.put("key", enumItem.getKey());
                         item.put("label", enumItem.getLabel());
                         return item;
                     }).toList();
    }
}
