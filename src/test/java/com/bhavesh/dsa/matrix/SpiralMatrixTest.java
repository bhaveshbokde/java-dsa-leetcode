package com.bhavesh.dsa.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpiralMatrixTest {

    @Test
    @DisplayName("LeetCode 3x3 example")
    void threeByThree() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertEquals(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5), SpiralMatrix.spiralOrder(matrix));
    }

    @Test
    @DisplayName("LeetCode 3x4 example")
    void threeByFour() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        assertEquals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7), SpiralMatrix.spiralOrder(matrix));
    }

    @Test
    @DisplayName("a single row is not emitted twice")
    void singleRow() {
        assertEquals(List.of(1, 2, 3), SpiralMatrix.spiralOrder(new int[][] {{1, 2, 3}}));
    }

    @Test
    @DisplayName("a single column is not emitted twice")
    void singleColumn() {
        assertEquals(List.of(1, 2, 3), SpiralMatrix.spiralOrder(new int[][] {{1}, {2}, {3}}));
    }

    @Test
    @DisplayName("1x1, empty and null")
    void degenerate() {
        assertEquals(List.of(7), SpiralMatrix.spiralOrder(new int[][] {{7}}));
        assertTrue(SpiralMatrix.spiralOrder(new int[][] {}).isEmpty());
        assertTrue(SpiralMatrix.spiralOrder(new int[][] {{}}).isEmpty());
        assertTrue(SpiralMatrix.spiralOrder(null).isEmpty());
    }

    @Test
    @DisplayName("every cell appears exactly once for randomised shapes")
    void everyCellExactlyOnce() {
        Random random = new Random(5454L);
        for (int trial = 0; trial < 400; trial++) {
            int rows = 1 + random.nextInt(6);
            int cols = 1 + random.nextInt(6);
            int[][] matrix = new int[rows][cols];
            int value = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = value++;
                }
            }
            List<Integer> spiral = SpiralMatrix.spiralOrder(matrix);
            assertEquals(rows * cols, spiral.size(),
                    () -> "wrong element count for " + rows + "x" + cols);
            List<Integer> sorted = new ArrayList<>(spiral);
            sorted.sort(null);
            List<Integer> expected = new ArrayList<>();
            for (int i = 0; i < rows * cols; i++) {
                expected.add(i);
            }
            assertEquals(expected, sorted,
                    () -> "elements missing or duplicated for " + Arrays.deepToString(matrix));
        }
    }
}
