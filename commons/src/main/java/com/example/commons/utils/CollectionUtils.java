package com.example.commons.utils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static <T, K> List<K> extractField(List<T> tList, Function<T, K> map) {
        return tList.stream()
                .map(map)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static <T, K> Set<K> extractFieldToSet(List<T> tList, Function<T, K> map) {
        return tList.stream()
                .map(map)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
