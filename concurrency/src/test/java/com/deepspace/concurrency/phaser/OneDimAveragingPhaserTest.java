package com.deepspace.concurrency.phaser;

import com.deepspace.concurrency.ConcurrencyUtils;
import junit.framework.TestCase;

public class OneDimAveragingPhaserTest extends TestCase {
    // Number of times to repeat each test, for consistent timing results.
    private final static int nIterations = 40_000;

    /**
     * Test on large input.
     */
    public void testFuzzyBarrier() {
        final double expected = 1.05;
        final double speedup = parTestHelper(2 * 1024 * 1024, ConcurrencyUtils.getThreadPoolSize());
        String errMsg = String.format("It was expected that the fuzzy barrier parallel implementation would " +
                "run %fx faster than the barrier implementation, but it only achieved %fx speedup", expected, speedup);
        assertTrue(errMsg, speedup >= expected);
        String successMsg = String.format("Fuzzy barrier parallel implementation " +
                "ran %fx faster than the barrier implementation", speedup);
        System.out.println(successMsg);
    }
    /**
     * A helper function for tests of the two-task parallel implementation.
     *
     * @param N The size of the array to test
     * @return The speedup achieved, not all tests use this information
     */
    private double parTestHelper(final int N, final int nTasks) {
        // Create a random input
        double[] myNew = PhaserTestUtils.createArray(N, nIterations);
        double[] myVal = PhaserTestUtils.createArray(N, nIterations);
        final double[] myNewRef = PhaserTestUtils.createArray(N, nIterations);
        final double[] myValRef = PhaserTestUtils.createArray(N, nIterations);

        long barrierTotalTime = 0;
        long fuzzyTotalTime = 0;

        for (int r = 0; r < 3; r++) {
            final long barrierStartTime = System.currentTimeMillis();
            OneDimAveragingPhaser.runParallelBarrier(nIterations, myNew, myVal, N, nTasks);
            final long barrierEndTime = System.currentTimeMillis();

            final long fuzzyStartTime = System.currentTimeMillis();
            OneDimAveragingPhaser.runParallelFuzzyBarrier(nIterations, myNewRef, myValRef, N, nTasks);
            final long fuzzyEndTime = System.currentTimeMillis();

            if (nIterations % 2 == 0) {
                checkResult(myNew, myNewRef);
            } else {
                checkResult(myVal, myValRef);
            }

            barrierTotalTime += (barrierEndTime - barrierStartTime);
            fuzzyTotalTime += (fuzzyEndTime - fuzzyStartTime);
        }

        return (double)barrierTotalTime / (double)fuzzyTotalTime;
    }

    private void checkResult(final double[] ref, final double[] output) {
        for (int i = 0; i < ref.length; i++) {
            String msg = "Mismatch on output at element " + i;
            assertEquals(msg, ref[i], output[i]);
        }
    }
}
