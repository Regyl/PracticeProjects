package com.deepspace;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountUtils {

    public static double millsBetween(long startTime, long endTime) {
        return (endTime - startTime) / 1_000_000.0;
    }
}
