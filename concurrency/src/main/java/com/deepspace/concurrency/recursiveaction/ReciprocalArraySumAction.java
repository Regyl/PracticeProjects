package com.deepspace.concurrency.recursiveaction;

import lombok.Getter;

import java.util.concurrent.RecursiveAction;

public class ReciprocalArraySumAction extends RecursiveAction {

    /**
     * Batch per action.
     */
    private final int threadThreshold;

    /**
     * Starting index for traversal done by this task.
     */
    private final int startIndexInclusive;

    /**
     * Ending index for traversal done by this task.
     */
    private final int endIndexExclusive;

    /**
     * Input array to reciprocal sum.
     */
    private final double[] input;

    /**
     * Intermediate value produced by this task.
     */
    @Getter
    private double value;

    /**
     * Constructor.
     * @param startIndexInclusive Set the starting index to begin
     *        parallel traversal at.
     * @param endIndexExclusive Set ending index for parallel traversal.
     * @param setInput Input values
     * @param threadThreshold i.e. batch size per thread
     */
    public ReciprocalArraySumAction(final int startIndexInclusive,
                           final int endIndexExclusive, final double[] setInput, int threadThreshold) {
        this.startIndexInclusive = startIndexInclusive;
        this.endIndexExclusive = endIndexExclusive;
        this.input = setInput;
        this.threadThreshold = threadThreshold;
    }


    /**
     * Constructor.
     * @param startIndexInclusive Set the starting index to begin
     *        parallel traversal at.
     * @param endIndexExclusive Set ending index for parallel traversal.
     * @param setInput Input values
     */
    public ReciprocalArraySumAction(final int startIndexInclusive,
                           final int endIndexExclusive, final double[] setInput) {
        this.startIndexInclusive = startIndexInclusive;
        this.endIndexExclusive = endIndexExclusive;
        this.input = setInput;
        this.threadThreshold = 200_000;
    }

    @Override
    protected void compute() {
        if ((endIndexExclusive - startIndexInclusive) <= threadThreshold) {
            for (int i = startIndexInclusive; i < endIndexExclusive; i++) {
                value += 1 / input[i];
            }
        } else {
            int middleIndex = (endIndexExclusive + startIndexInclusive) / 2;
            ReciprocalArraySumAction leftAction = new ReciprocalArraySumAction(startIndexInclusive, middleIndex, input);
            ReciprocalArraySumAction rightAction = new ReciprocalArraySumAction(middleIndex, endIndexExclusive, input);
            leftAction.fork();
            rightAction.compute();
            leftAction.join();
            value = leftAction.getValue() + rightAction.getValue();
        }
    }
}
