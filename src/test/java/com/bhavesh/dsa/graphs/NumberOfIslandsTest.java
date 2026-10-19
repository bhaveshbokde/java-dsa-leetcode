package com.bhavesh.dsa.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberOfIslandsTest {

    private static char[][] grid(String... rows) {
        char[][] out = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            out[i] = rows[i].toCharArray();
        }
        return out;
    }

    @Test
    @DisplayName("LeetCode example: one island")
    void oneIsland() {
        assertEquals(1, NumberOfIslands.numIslands(grid(
                "11110",
                "11010",
                "11000",
                "00000")));
    }

    @Test
    @DisplayName("LeetCode example: three islands")
    void threeIslands() {
        assertEquals(3, NumberOfIslands.numIslands(grid(
                "11000",
                "11000",
                "00100",
                "00011")));
    }

    @Test
    @DisplayName("all water")
    void allWater() {
        assertEquals(0, NumberOfIslands.numIslands(grid("000", "000")));
    }

    @Test
    @DisplayName("all land forms a single island")
    void allLand() {
        assertEquals(1, NumberOfIslands.numIslands(grid("111", "111")));
    }

    @Test
    @DisplayName("diagonal land cells are separate islands")
    void diagonalsAreNotConnected() {
        // Four-directional adjacency only: diagonals do not join.
        assertEquals(2, NumberOfIslands.numIslands(grid("10", "01")));
    }

    @Test
    @DisplayName("single cell grids")
    void singleCell() {
        assertEquals(1, NumberOfIslands.numIslands(grid("1")));
        assertEquals(0, NumberOfIslands.numIslands(grid("0")));
    }

    @Test
    @DisplayName("empty and null grids")
    void degenerate() {
        assertEquals(0, NumberOfIslands.numIslands(new char[0][]));
        assertEquals(0, NumberOfIslands.numIslands(new char[][] {{}}));
        assertEquals(0, NumberOfIslands.numIslands(null));
    }

    @Test
    @DisplayName("a large all-land grid does not overflow the stack")
    void largeAllLandGrid() {
        // 300x300 of land gives a DFS depth of 90000; the iterative BFS is fine.
        int size = 300;
        char[][] big = new char[size][size];
        for (char[] row : big) {
            java.util.Arrays.fill(row, '1');
        }
        assertEquals(1, NumberOfIslands.numIslands(big));
    }

    @Test
    @DisplayName("a checkerboard yields one island per land cell")
    void checkerboard() {
        int size = 9;
        char[][] board = new char[size][size];
        int expected = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                boolean land = (r + c) % 2 == 0;
                board[r][c] = land ? '1' : '0';
                if (land) {
                    expected++;
                }
            }
        }
        assertEquals(expected, NumberOfIslands.numIslands(board));
    }
}
