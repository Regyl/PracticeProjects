package com.deepspace;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountUtils {

    public static double millsBetween(long startTime, long endTime) {
        return (endTime - startTime) / 1_000_000.0;
    }

    public static long calculateAvgTime(List<Pair<Long, Long>> line) {
        return line.stream()
                .map(Pair::getLeft)
                .reduce(Long::sum)
                .orElseThrow(() -> new IllegalArgumentException("Недопустимый элемент массива")) / line.size();
    }
}
