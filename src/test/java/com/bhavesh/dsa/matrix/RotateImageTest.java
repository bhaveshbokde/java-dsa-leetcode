package com.bhavesh.dsa.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RotateImageTest {

    @Test
    @DisplayName("LeetCode 3x3 example")
    void threeByThree() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RotateImage.rotate(matrix);
        assertArrayEquals(new int[][] {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}}, matrix);
    }

    @Test
    @DisplayName("LeetCode 4x4 example")
    void fourByFour() {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        RotateImage.rotate(matrix);
        assertArrayEquals(new int[][] {{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}}, matrix);
    }

    @Test
    @DisplayName("1x1 and empty matrices")
    void degenerate() {
        int[][] single = {{1}};
        RotateImage.rotate(single);
        assertArrayEquals(new int[][] {{1}}, single);

        int[][] empty = {};
        RotateImage.rotate(empty);
        assertArrayEquals(new int[][] {}, empty);

        RotateImage.rotate(null);
    }

    @Test
    @DisplayName("four rotations return the original matrix")
    void fourRotationsAreIdentity() {
        Random random = new Random(48048L);
        for (int trial = 0; trial < 200; trial++) {
            int n = 1 + random.nextInt(6);
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = random.nextInt(100);
                }
            }
            int[][] original = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
            for (int turn = 0; turn < 4; turn++) {
                RotateImage.rotate(matrix);
            }
            assertArrayEquals(original, matrix, "four quarter turns must be the identity");
        }
    }

    @Test
    @DisplayName("matches a copy-into-new-matrix reference")
    void matchesReference() {
        Random random = new Random(8080L);
        for (int trial = 0; trial < 300; trial++) {
            int n = 1 + random.nextInt(6);
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = random.nextInt(50);
                }
            }
            int[][] expected = reference(matrix);
            int[][] actual = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
            RotateImage.rotate(actual);
            assertArrayEquals(expected, actual, () -> "mismatch for " + Arrays.deepToString(matrix));
        }
    }

    private static int[][] reference(int[][] matrix) {
        int n = matrix.length;
        int[][] out = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out[j][n - 1 - i] = matrix[i][j];
            }
        }
        return out;
    }
}
