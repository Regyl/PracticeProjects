package com.deepspace.concurrency.matrix;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * Utility class for creating input matrices.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MatrixUtils {

    private static final Random RND = new Random(System.currentTimeMillis());

    /**
     * Create a double[] of length N to use as input for the tests.
     *
     * @param N Size of the array to create
     * @return Initialized double array of length N
     */
    public static double[][] createMatrix(int N) {
        double[][] input = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                input[i][j] = RND.nextInt(100);
            }
        }

        return input;
    }
}
