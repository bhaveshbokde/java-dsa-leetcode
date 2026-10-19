package com.bhavesh.dsa.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UniquePathsTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(28, UniquePaths.uniquePaths(3, 7));
        assertEquals(3, UniquePaths.uniquePaths(3, 2));
    }

    @Test
    @DisplayName("a single row or column has exactly one path")
    void singleRowOrColumn() {
        assertEquals(1, UniquePaths.uniquePaths(1, 10));
        assertEquals(1, UniquePaths.uniquePaths(10, 1));
        assertEquals(1, UniquePaths.uniquePaths(1, 1));
    }

    @Test
    @DisplayName("the grid is symmetric in its dimensions")
    void symmetric() {
        for (int m = 1; m <= 8; m++) {
            for (int n = 1; n <= 8; n++) {
                assertEquals(UniquePaths.uniquePaths(m, n), UniquePaths.uniquePaths(n, m),
                        "paths(" + m + "," + n + ") must equal paths(" + n + "," + m + ")");
            }
        }
    }

    @Test
    @DisplayName("non-positive dimensions are rejected")
    void invalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> UniquePaths.uniquePaths(0, 3));
        assertThrows(IllegalArgumentException.class, () -> UniquePaths.uniquePaths(3, -1));
        assertThrows(IllegalArgumentException.class, () -> UniquePaths.uniquePathsByCombinatorics(0, 1));
    }

    @Test
    @DisplayName("the DP and closed-form agree across a range of grids")
    void variantsAgree() {
        for (int m = 1; m <= 17; m++) {
            for (int n = 1; n <= 17; n++) {
                assertEquals(UniquePaths.uniquePathsByCombinatorics(m, n), UniquePaths.uniquePaths(m, n),
                        "variants disagree at " + m + "x" + n);
            }
        }
    }

    @Test
    @DisplayName("a known larger value: a 10x10 grid has 48620 paths")
    void knownLargerValue() {
        assertEquals(48_620, UniquePaths.uniquePaths(10, 10));
    }

    @Test
    @DisplayName("matches an exhaustive path count on small grids")
    void matchesExhaustiveCount() {
        for (int m = 1; m <= 6; m++) {
            for (int n = 1; n <= 6; n++) {
                assertEquals(countPaths(m, n, 0, 0), UniquePaths.uniquePaths(m, n),
                        "mismatch at " + m + "x" + n);
            }
        }
    }

    private static long countPaths(int m, int n, int r, int c) {
        if (r == m - 1 && c == n - 1) {
            return 1;
        }
        long total = 0;
        if (r + 1 < m) {
            total += countPaths(m, n, r + 1, c);
        }
        if (c + 1 < n) {
            total += countPaths(m, n, r, c + 1);
        }
        return total;
    }
}
