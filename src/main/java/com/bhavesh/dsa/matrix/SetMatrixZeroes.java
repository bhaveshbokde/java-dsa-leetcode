package com.bhavesh.dsa.matrix;

/**
 * LeetCode 73 - Set Matrix Zeroes (Medium).
 *
 * <p>If a cell is 0, its entire row and column become 0 - computed in place.
 *
 * <p>The trap is obvious once stated: zeroing a row as soon as a 0 is found
 * writes new zeros, which the ongoing scan then mistakes for original ones, and
 * the whole matrix collapses. The zeros to act on must be the <em>original</em>
 * ones, so detection and application have to be separated.
 *
 * <p>Recording which rows and columns to clear in two boolean arrays costs
 * O(m + n) and is a perfectly good answer. The O(1) refinement stores those
 * same flags inside the matrix itself, in row 0 and column 0.
 *
 * <p>That overloading creates one genuine ambiguity: cell {@code (0,0)} would
 * have to flag both row 0 and column 0. A single extra boolean for the first
 * column resolves it, and the first column is then handled last - after it has
 * finished serving as storage.
 *
 * <p>Time O(m * n), auxiliary space O(1).
 */
public final class SetMatrixZeroes {

    private SetMatrixZeroes() {
    }

    public static void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Column 0 needs its own flag, because matrix[0][0] can only encode one
        // of "row 0 has a zero" and "column 0 has a zero".
        boolean firstColumnHasZero = false;

        for (int r = 0; r < rows; r++) {
            if (matrix[r][0] == 0) {
                firstColumnHasZero = true;
            }
            for (int c = 1; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }

        // Apply inwards-out, so the markers are read before being overwritten.
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 1; c--) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
            if (firstColumnHasZero) {
                matrix[r][0] = 0;
            }
        }
    }
}
