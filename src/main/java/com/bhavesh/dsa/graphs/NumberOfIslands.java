package com.bhavesh.dsa.graphs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 200 - Number of Islands (Medium).
 *
 * <p>Counts connected groups of '1' cells in a grid, with four-directional
 * adjacency.
 *
 * <p>The grid is a graph in disguise: cells are vertices and adjacent land
 * cells are edges. Counting islands is counting connected components, so a
 * traversal from every unvisited land cell does it - each traversal consumes
 * exactly one component.
 *
 * <p>The implementation is iterative breadth-first rather than recursive
 * depth-first on purpose. A 300 x 300 grid that is entirely land gives a
 * recursion depth of 90,000, which overflows the default stack; an explicit
 * queue has no such limit.
 *
 * <p>Marking a cell as visited when it is <em>enqueued</em>, not when it is
 * dequeued, is what keeps the queue linear in the grid size. Marking on dequeue
 * lets the same cell be enqueued once per neighbour.
 *
 * <p>Time O(m * n), auxiliary space O(min(m, n)) for the queue in the worst
 * case, plus O(m * n) for the visited array.
 */
public final class NumberOfIslands {

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private NumberOfIslands() {
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != '1' || visited[r][c]) {
                    continue;
                }
                islands++;
                floodFill(grid, visited, r, c);
            }
        }
        return islands;
    }

    private static void floodFill(char[][] grid, boolean[][] visited, int startRow, int startCol) {
        Deque<int[]> queue = new ArrayDeque<>();
        visited[startRow][startCol] = true;
        queue.add(new int[] {startRow, startCol});
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] direction : DIRECTIONS) {
                int r = cell[0] + direction[0];
                int c = cell[1] + direction[1];
                if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
                    continue;
                }
                if (grid[r][c] == '1' && !visited[r][c]) {
                    // Mark on enqueue, not dequeue, to avoid duplicates.
                    visited[r][c] = true;
                    queue.add(new int[] {r, c});
                }
            }
        }
    }
}
