package com.deepspace.concurrency.phaser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PhaserTestUtils {

    public static double[] createArray(final int N, final int iterations) {
        final double[] input = new double[N + 2];
        int index = N + 1;
        while (index > 0) {
            input[index] = 1.0;
            index -= (iterations / 4);
        }
        return input;
    }
}
