package com.deepspace.concurrency.matrix;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatrixMultiplicationDTO {

    /**
     * First input matrix with dimensions NxN.
     */
    private final double[][] A;

    /**
     * Second input matrix with dimensions NxN.
     */
    private final double[][] B;

    /**
     * The output matrix.
     */
    private final double[][] C;

    /**
     * Size of each dimension of the input matrices.
     */
    private final int N;
}
