package com.bhavesh.dsa.backtracking;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WordSearchTest {

    private static char[][] board(String... rows) {
        char[][] out = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            out[i] = rows[i].toCharArray();
        }
        return out;
    }

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertTrue(WordSearch.exist(board("ABCE", "SFCS", "ADEE"), "ABCCED"));
        assertTrue(WordSearch.exist(board("ABCE", "SFCS", "ADEE"), "SEE"));
        assertFalse(WordSearch.exist(board("ABCE", "SFCS", "ADEE"), "ABCB"));
    }

    @Test
    @DisplayName("a cell may not be reused within one path")
    void noCellReuse() {
        // "AAA" needs three distinct cells; only two exist.
        assertFalse(WordSearch.exist(board("AA"), "AAA"));
        assertTrue(WordSearch.exist(board("AA"), "AA"));
    }

    @Test
    @DisplayName("the board is restored exactly after searching")
    void boardIsRestored() {
        char[][] grid = board("ABCE", "SFCS", "ADEE");
        char[][] copy = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
        WordSearch.exist(grid, "ABCCED");
        assertArrayEquals(copy, grid, "a failed or successful search must not mutate the board");
        WordSearch.exist(grid, "NOTPRESENT");
        assertArrayEquals(copy, grid);
    }

    @Test
    @DisplayName("diagonal adjacency is not allowed")
    void noDiagonals() {
        // 'A' at (0,0) and 'B' at (1,1) are diagonal, so "AB" must not be found.
        assertFalse(WordSearch.exist(board("AX", "XB"), "AB"));
    }

    @Test
    @DisplayName("single cell boards")
    void singleCell() {
        assertTrue(WordSearch.exist(board("A"), "A"));
        assertFalse(WordSearch.exist(board("A"), "B"));
        assertFalse(WordSearch.exist(board("A"), "AA"));
    }

    @Test
    @DisplayName("empty word, empty board and nulls")
    void degenerate() {
        assertTrue(WordSearch.exist(board("AB"), ""));
        assertTrue(WordSearch.exist(null, ""));
        assertFalse(WordSearch.exist(new char[0][], "A"));
        assertTrue(WordSearch.exist(board("AB"), null), "a null word is treated as empty");
    }

    @Test
    @DisplayName("the path may turn in any direction")
    void pathTurns() {
        assertTrue(WordSearch.exist(board("ABC", "XXD", "GFE"), "ABCDEFG"));
    }

    @Test
    @DisplayName("word longer than the whole board cannot be found")
    void wordTooLong() {
        assertFalse(WordSearch.exist(board("AB", "CD"), "ABCDE"));
    }

    @Test
    @DisplayName("a repeated-letter board exercises heavy backtracking without wrong answers")
    void heavyBacktracking() {
        char[][] grid = new char[6][6];
        for (char[] row : grid) {
            Arrays.fill(row, 'A');
        }
        assertTrue(WordSearch.exist(grid, "A".repeat(20)));
        // 37 cells are needed but only 36 exist.
        assertFalse(WordSearch.exist(grid, "A".repeat(37)));
    }
}
