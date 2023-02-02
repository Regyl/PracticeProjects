package com.deepspace.concurrency.matrix;

import com.deepspace.concurrency.ConcurrencyUtils;
import junit.framework.TestCase;

public class MatrixMultiplyTest extends TestCase {
    // Number of times to repeat each test, for consistent timing results.
    private static final int REPEATS = 20;

    private static final String EXCEPTION_MESSAGE = "It was expected that the parallel implementation would run at " +
            "least %fx faster, but it only achieved %fx speedup";

    /**
     * Tests the performance of the parallel implementation on a 512x512 matrix.
     */
    public void testPar512_x_512() {
        final int nCores = ConcurrencyUtils.getThreadPoolSize();
        double minimalExpectedSpeedup = (double)nCores * 0.6;

        double speedup = parallelTestHelper(512);

        final String errMsg = String.format(EXCEPTION_MESSAGE, minimalExpectedSpeedup, speedup);
        assertTrue(errMsg, speedup >= minimalExpectedSpeedup);
    }

    /**
     * Tests the performance of the parallel implementation on a 768x768 matrix.
     */
    public void testPar768_x_768() {
        final int nCores = ConcurrencyUtils.getThreadPoolSize();
        double minimalExpectedSpeedup = (double)nCores * 0.3;

        double speedup = parallelTestHelper(768);

        final String errMsg = String.format(EXCEPTION_MESSAGE, minimalExpectedSpeedup, speedup);
        assertTrue(errMsg, speedup >= minimalExpectedSpeedup);
    }

    /**
     * Check if there is any difference in the correct and generated outputs.
     */
    private void checkResult(final double[][] ref, final double[][] output, final int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String msg = "Error detected on cell (" + i + ", " + j + ")";
                assertEquals(msg, ref[i][j], output[i][j]);
            }
        }
    }

    /**
     * A helper function for tests of the two-task parallel implementation.
     *
     * @param N The size of the array to test
     * @return The speedup achieved, not all tests use this information
     */
    private double parallelTestHelper(int N) {
        double[][] A = MatrixUtils.createMatrix(N);
        double[][] B = MatrixUtils.createMatrix(N);
        double[][] C = new double[N][N];
        double[][] refC = new double[N][N];

        // Use a reference sequential version to compute the correct result
        MatrixMultiplicationDTO seqDto = new MatrixMultiplicationDTO(A, B, refC, N);
        MatrixMultiply.seqMatrixMultiply(seqDto);

        // Use the parallel implementation to compute the result
        MatrixMultiplicationDTO dto = new MatrixMultiplicationDTO(A, B, C, N);
        MatrixMultiply.parMatrixMultiply(dto);

        checkResult(refC, C, N);

        /*
         * Run several repeats of the sequential and parallel versions to get an accurate measurement of parallel
         * performance.
         */
        final long seqStartTime = System.currentTimeMillis();
        for (int r = 0; r < REPEATS; r++) {
            MatrixMultiply.seqMatrixMultiply(seqDto);
        }
        final long seqEndTime = System.currentTimeMillis();

        final long parStartTime = System.currentTimeMillis();
        for (int r = 0; r < REPEATS; r++) {
            MatrixMultiply.parMatrixMultiply(dto);
        }
        final long parEndTime = System.currentTimeMillis();

        final long seqTime = (seqEndTime - seqStartTime) / REPEATS;
        final long parTime = (parEndTime - parStartTime) / REPEATS;

        return (double)seqTime / (double)parTime;
    }
}
