package com.deepspace.concurrency;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConcurrencyUtils {

    public static int getThreadPoolSize() {
        return Runtime.getRuntime().availableProcessors() - 1;
    }

    public static int getIOThreadPoolSize() {
        return Runtime.getRuntime().availableProcessors() * 2 + 1;
    }
}
