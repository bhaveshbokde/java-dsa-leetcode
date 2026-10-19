package com.bhavesh.dsa.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SetMatrixZeroesTest {

    @Test
    @DisplayName("LeetCode example 1")
    void example1() {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        SetMatrixZeroes.setZeroes(matrix);
        assertArrayEquals(new int[][] {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, matrix);
    }

    @Test
    @DisplayName("LeetCode example 2")
    void example2() {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        SetMatrixZeroes.setZeroes(matrix);
        assertArrayEquals(new int[][] {{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}}, matrix);
    }

    @Test
    @DisplayName("a zero in the first column is handled by its dedicated flag")
    void zeroInFirstColumn() {
        int[][] matrix = {{1, 2}, {0, 4}};
        SetMatrixZeroes.setZeroes(matrix);
        assertArrayEquals(new int[][] {{0, 2}, {0, 0}}, matrix);
    }

    @Test
    @DisplayName("a zero at (0,0) clears both the first row and the first column")
    void zeroAtOrigin() {
        int[][] matrix = {{0, 2, 3}, {4, 5, 6}};
        SetMatrixZeroes.setZeroes(matrix);
        assertArrayEquals(new int[][] {{0, 0, 0}, {0, 5, 6}}, matrix);
    }

    @Test
    @DisplayName("no zeros leaves the matrix unchanged")
    void noZeros() {
        int[][] matrix = {{1, 2}, {3, 4}};
        SetMatrixZeroes.setZeroes(matrix);
        assertArrayEquals(new int[][] {{1, 2}, {3, 4}}, matrix);
    }

    @Test
    @DisplayName("all zeros stays all zeros")
    void allZeros() {
        int[][] matrix = {{0, 0}, {0, 0}};
        SetMatrixZeroes.setZeroes(matrix);
        assertArrayEquals(new int[][] {{0, 0}, {0, 0}}, matrix);
    }

    @Test
    @DisplayName("single row, single column, 1x1, empty and null")
    void degenerate() {
        int[][] singleRow = {{1, 0, 3}};
        SetMatrixZeroes.setZeroes(singleRow);
        assertArrayEquals(new int[][] {{0, 0, 0}}, singleRow);

        int[][] singleColumn = {{1}, {0}, {3}};
        SetMatrixZeroes.setZeroes(singleColumn);
        assertArrayEquals(new int[][] {{0}, {0}, {0}}, singleColumn);

        int[][] one = {{5}};
        SetMatrixZeroes.setZeroes(one);
        assertArrayEquals(new int[][] {{5}}, one);

        SetMatrixZeroes.setZeroes(new int[0][]);
        SetMatrixZeroes.setZeroes(null);
    }

    @Test
    @DisplayName("matches an O(m*n) extra-space reference on randomised matrices")
    void matchesReference() {
        Random random = new Random(7373L);
        for (int trial = 0; trial < 600; trial++) {
            int rows = 1 + random.nextInt(6);
            int cols = 1 + random.nextInt(6);
            int[][] original = new int[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    original[r][c] = random.nextInt(4) == 0 ? 0 : 1 + random.nextInt(9);
                }
            }
            int[][] expected = reference(original);
            int[][] actual = Arrays.stream(original).map(int[]::clone).toArray(int[][]::new);
            SetMatrixZeroes.setZeroes(actual);
            assertArrayEquals(expected, actual,
                    () -> "mismatch for " + Arrays.deepToString(original));
        }
    }

    /** Records the original zero positions first, then applies them. */
    private static int[][] reference(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[] clearRow = new boolean[rows];
        boolean[] clearCol = new boolean[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    clearRow[r] = true;
                    clearCol[c] = true;
                }
            }
        }
        int[][] out = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (clearRow[r] || clearCol[c]) {
                    out[r][c] = 0;
                }
            }
        }
        return out;
    }
}
