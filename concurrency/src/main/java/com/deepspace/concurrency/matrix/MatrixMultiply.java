package com.deepspace.concurrency.matrix;

import edu.rice.pcdp.PCDP;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Wrapper class for implementing matrix multiply efficiently in parallel.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MatrixMultiply {

    /**
     * Perform a two-dimensional matrix multiply (A x B = C) in parallel.
     *
     * @param A An input matrix with dimensions NxN
     * @param B An input matrix with dimensions NxN
     * @param C The output matrix
     * @param N Size of each dimension of the input matrices
     */
    public static void parMatrixMultiply(final double[][] A, final double[][] B, final double[][] C, final int N) {
        PCDP.forall2dChunked(0, N - 1, 0, N - 1, (i, j) -> {
            C[i][j] = 0.0;
            for (int k = 0; k < N; k++) {
                C[i][j] += A[i][k] * B[k][j];
            }
        });
    }

    /**
     * A reference implementation of seqMatrixMultiply, in case the one in the main source file is accidentally
     * modified.
     */
    public static void seqMatrixMultiply(final double[][] A, final double[][] B, final double[][] C, final int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                C[i][j] = 0.0;
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}
