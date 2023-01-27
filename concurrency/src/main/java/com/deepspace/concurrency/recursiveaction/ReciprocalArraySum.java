package com.deepspace.concurrency.recursiveaction;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ForkJoinPool;

/**
 * Class wrapping methods for implementing reciprocal array sum in parallel.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReciprocalArraySum {

    /**
     * Idk if I'm a genius or a stupid monkey, but it works.
     *
     * @param input Input array
     * @return The sum of the reciprocals of the array input
     */
    public static double parallelArraySum(final double[] input) {
        int threadThreshold = input.length / 2;
        ReciprocalArraySumAction asyncAction = new ReciprocalArraySumAction(0, input.length, input, threadThreshold);
        ForkJoinPool.commonPool().invoke(asyncAction);

        return asyncAction.getValue();
    }

    /**
     * Idk if I'm a genius or a stupid monkey but it works.
     *
     * @param input Input array
     * @param numTasks The number of tasks to create
     * @return The sum of the reciprocals of the array input
     */
    public static double parallelManyTaskArraySum(final double[] input, final int numTasks) {
        int threadThreshold = input.length / numTasks;
        ReciprocalArraySumAction asyncAction = new ReciprocalArraySumAction(0, input.length, input, threadThreshold);
        ForkJoinPool.commonPool().invoke(asyncAction);

        return asyncAction.getValue();
    }
}
