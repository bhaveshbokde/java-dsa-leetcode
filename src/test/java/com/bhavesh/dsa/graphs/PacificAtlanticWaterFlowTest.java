package com.bhavesh.dsa.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PacificAtlanticWaterFlowTest {

    private static Set<List<Integer>> asSet(List<List<Integer>> cells) {
        return new HashSet<>(cells);
    }

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        int[][] heights = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4},
        };
        Set<List<Integer>> expected = Set.of(
                List.of(0, 4), List.of(1, 3), List.of(1, 4),
                List.of(2, 2), List.of(3, 0), List.of(3, 1), List.of(4, 0));
        assertEquals(expected, asSet(PacificAtlanticWaterFlow.pacificAtlantic(heights)));
    }

    @Test
    @DisplayName("single cell touches both oceans")
    void singleCell() {
        assertEquals(Set.of(List.of(0, 0)),
                asSet(PacificAtlanticWaterFlow.pacificAtlantic(new int[][] {{1}})));
    }

    @Test
    @DisplayName("a flat grid: every cell reaches both oceans")
    void flatGrid() {
        int[][] heights = {{1, 1}, {1, 1}};
        assertEquals(4, PacificAtlanticWaterFlow.pacificAtlantic(heights).size());
    }

    @Test
    @DisplayName("single row and single column")
    void degenerateShapes() {
        // Every cell of a single row touches both the top (Pacific) and
        // bottom (Atlantic) edges simultaneously.
        assertEquals(3, PacificAtlanticWaterFlow.pacificAtlantic(new int[][] {{1, 2, 3}}).size());
        assertEquals(3, PacificAtlanticWaterFlow.pacificAtlantic(
                new int[][] {{1}, {2}, {3}}).size());
    }

    @Test
    @DisplayName("empty and null grids")
    void emptyGrid() {
        assertTrue(PacificAtlanticWaterFlow.pacificAtlantic(new int[0][]).isEmpty());
        assertTrue(PacificAtlanticWaterFlow.pacificAtlantic(new int[][] {{}}).isEmpty());
        assertTrue(PacificAtlanticWaterFlow.pacificAtlantic(null).isEmpty());
    }

    @Test
    @DisplayName("matches a per-cell forward-search reference on randomised grids")
    void matchesBruteForce() {
        Random random = new Random(417417L);
        for (int trial = 0; trial < 300; trial++) {
            int rows = 1 + random.nextInt(5);
            int cols = 1 + random.nextInt(5);
            int[][] heights = new int[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    heights[r][c] = random.nextInt(5);
                }
            }
            assertEquals(bruteForce(heights),
                    asSet(PacificAtlanticWaterFlow.pacificAtlantic(heights)),
                    () -> "mismatch for " + java.util.Arrays.deepToString(heights));
        }
    }

    /** From each cell, search forwards (downhill) and see which oceans it reaches. */
    private static Set<List<Integer>> bruteForce(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        Set<List<Integer>> out = new HashSet<>();
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int sr = 0; sr < rows; sr++) {
            for (int sc = 0; sc < cols; sc++) {
                boolean[][] seen = new boolean[rows][cols];
                boolean pacific = false;
                boolean atlantic = false;
                Deque<int[]> stack = new ArrayDeque<>();
                stack.push(new int[] {sr, sc});
                seen[sr][sc] = true;
                while (!stack.isEmpty()) {
                    int[] cell = stack.pop();
                    if (cell[0] == 0 || cell[1] == 0) {
                        pacific = true;
                    }
                    if (cell[0] == rows - 1 || cell[1] == cols - 1) {
                        atlantic = true;
                    }
                    for (int[] d : dirs) {
                        int r = cell[0] + d[0];
                        int c = cell[1] + d[1];
                        if (r < 0 || r >= rows || c < 0 || c >= cols || seen[r][c]) {
                            continue;
                        }
                        if (heights[r][c] <= heights[cell[0]][cell[1]]) {
                            seen[r][c] = true;
                            stack.push(new int[] {r, c});
                        }
                    }
                }
                if (pacific && atlantic) {
                    out.add(List.of(sr, sc));
                }
            }
        }
        return out;
    }
}
