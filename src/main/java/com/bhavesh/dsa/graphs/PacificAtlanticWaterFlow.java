package com.bhavesh.dsa.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 417 - Pacific Atlantic Water Flow (Medium).
 *
 * <p>Water flows from a cell to a neighbour of <em>equal or lower</em> height.
 * The Pacific borders the top and left edges, the Atlantic the bottom and
 * right. Return every cell from which water can reach <b>both</b> oceans.
 *
 * <p>The naive approach runs a search from each cell asking "can I reach an
 * ocean", which is O((m*n)^2) - up to 10^8 steps at the stated limits.
 *
 * <p>The reframing that collapses it: <b>search backwards from the oceans
 * instead</b>. Starting at the border cells and moving to neighbours that are
 * <em>higher or equal</em>, a traversal marks exactly the cells from which
 * water could have flowed down to that ocean. Two such traversals - one per
 * ocean - and the answer is the intersection of the two reachable sets.
 *
 * <p>Inverting the direction turns many searches into two, which is the whole
 * trick and is worth recognising as a general technique: when every source must
 * reach a common target, search from the target.
 *
 * <p>The comparison flips with the direction. Forwards it is
 * {@code next <= current}; backwards it is {@code next >= current}. Using the
 * forward comparison in a backward search silently computes the wrong set.
 *
 * <p>Time O(m * n), auxiliary space O(m * n).
 */
public final class PacificAtlanticWaterFlow {

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private PacificAtlanticWaterFlow() {
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        Deque<int[]> pacificQueue = new ArrayDeque<>();
        Deque<int[]> atlanticQueue = new ArrayDeque<>();

        for (int r = 0; r < rows; r++) {
            seed(pacific, pacificQueue, r, 0);                 // left edge
            seed(atlantic, atlanticQueue, r, cols - 1);        // right edge
        }
        for (int c = 0; c < cols; c++) {
            seed(pacific, pacificQueue, 0, c);                 // top edge
            seed(atlantic, atlanticQueue, rows - 1, c);        // bottom edge
        }

        flowUphill(heights, pacific, pacificQueue);
        flowUphill(heights, atlantic, atlanticQueue);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(List.of(r, c));
                }
            }
        }
        return result;
    }

    private static void seed(boolean[][] reached, Deque<int[]> queue, int r, int c) {
        if (!reached[r][c]) {
            reached[r][c] = true;
            queue.add(new int[] {r, c});
        }
    }

    /** Expands to neighbours that are higher or equal - the reverse of flow. */
    private static void flowUphill(int[][] heights, boolean[][] reached, Deque<int[]> queue) {
        int rows = heights.length;
        int cols = heights[0].length;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : DIRECTIONS) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= rows || c < 0 || c >= cols || reached[r][c]) {
                    continue;
                }
                if (heights[r][c] >= heights[cell[0]][cell[1]]) {
                    reached[r][c] = true;
                    queue.add(new int[] {r, c});
                }
            }
        }
    }
}
