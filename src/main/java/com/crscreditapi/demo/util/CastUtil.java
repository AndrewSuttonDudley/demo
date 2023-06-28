package com.crscreditapi.demo.util;

import java.util.HashMap;
import java.util.Map;

public class CastUtil {

    public static <T, V> Map<T, V> castMap(Class<? extends T> keyClass, Class<? extends V> valueClass, Map<?, ?> rawMap) {
        Map<T, V> result = new HashMap<>(rawMap.size());
        for (Object key : rawMap.keySet()) {
            result.put(keyClass.cast(key), valueClass.cast(rawMap.get(key)));
        }
        return result;
    }
}
