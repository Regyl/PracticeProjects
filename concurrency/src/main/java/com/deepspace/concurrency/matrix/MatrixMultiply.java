package com.deepspace.concurrency.matrix;

import edu.rice.pcdp.PCDP;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Wrapper class for implementing matrix multiply efficiently in parallel.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MatrixMultiply {

    /**
     * Get a list of all the methods that can be used to perform matrix multiplication.
     *
     * @return method list
     */
    public static List<Consumer<MatrixMultiplicationDTO>> getAllProcessMethods() {
        return Arrays.asList(MatrixMultiply::manualMatrixMultiply, MatrixMultiply::PCDPMatrixMultiply,
                MatrixMultiply::seqMatrixMultiply);
    }

    public static void manualMatrixMultiply(MatrixMultiplicationDTO dto) {
        int N = dto.getN();
    }

    /**
     * Perform a two-dimensional matrix multiply (A x B = C) in parallel.
     */
    public static void PCDPMatrixMultiply(MatrixMultiplicationDTO dto) {
        int N = dto.getN();
        PCDP.forall2dChunked(0, N - 1, 0, N - 1, (i, j) -> {
            dto.getC()[i][j] = 0.0;
            for (int k = 0; k < N; k++) {
                dto.getC()[i][j] += dto.getA()[i][k] * dto.getB()[k][j];
            }
        });
    }

    /**
     * Perform a two-dimensional matrix multiply (A x B = C) sequentially.
     */
    public static void seqMatrixMultiply(MatrixMultiplicationDTO dto) {
        int N = dto.getN();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dto.getC()[i][j] = 0.0;
                for (int k = 0; k < N; k++) {
                    dto.getC()[i][j] += dto.getA()[i][k] * dto.getB()[k][j];
                }
            }
        }
    }
}
