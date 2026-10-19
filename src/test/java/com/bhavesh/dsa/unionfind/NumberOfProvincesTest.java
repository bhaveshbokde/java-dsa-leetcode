package com.bhavesh.dsa.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberOfProvincesTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(2, NumberOfProvinces.findCircleNum(new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        assertEquals(3, NumberOfProvinces.findCircleNum(new int[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
    }

    @Test
    @DisplayName("a fully connected graph is one province")
    void fullyConnected() {
        assertEquals(1, NumberOfProvinces.findCircleNum(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    @Test
    @DisplayName("single city")
    void singleCity() {
        assertEquals(1, NumberOfProvinces.findCircleNum(new int[][] {{1}}));
    }

    @Test
    @DisplayName("empty and null inputs")
    void degenerate() {
        assertEquals(0, NumberOfProvinces.findCircleNum(new int[0][]));
        assertEquals(0, NumberOfProvinces.findCircleNum(null));
    }

    @Test
    @DisplayName("connectivity is transitive across the matrix")
    void transitiveConnection() {
        // 0-1 and 1-2 are linked, so all three form a single province.
        assertEquals(1, NumberOfProvinces.findCircleNum(new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }

    @Test
    @DisplayName("matches a flood-fill reference on randomised symmetric matrices")
    void matchesFloodFill() {
        Random random = new Random(777547L);
        for (int trial = 0; trial < 400; trial++) {
            int n = 1 + random.nextInt(8);
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                matrix[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    int linked = random.nextInt(3) == 0 ? 1 : 0;
                    matrix[i][j] = linked;
                    matrix[j][i] = linked;
                }
            }
            assertEquals(floodFillCount(matrix), NumberOfProvinces.findCircleNum(matrix),
                    () -> "mismatch for " + java.util.Arrays.deepToString(matrix));
        }
    }

    private static int floodFillCount(int[][] matrix) {
        int n = matrix.length;
        boolean[] seen = new boolean[n];
        int components = 0;
        for (int start = 0; start < n; start++) {
            if (seen[start]) {
                continue;
            }
            components++;
            java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
            stack.push(start);
            seen[start] = true;
            while (!stack.isEmpty()) {
                int at = stack.pop();
                for (int next = 0; next < n; next++) {
                    if (matrix[at][next] == 1 && !seen[next]) {
                        seen[next] = true;
                        stack.push(next);
                    }
                }
            }
        }
        return components;
    }
}
