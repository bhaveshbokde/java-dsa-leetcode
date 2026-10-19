package com.bhavesh.dsa.tries;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhavesh.dsa.backtracking.WordSearch;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WordSearchIITest {

    private static char[][] board(String... rows) {
        char[][] out = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            out[i] = rows[i].toCharArray();
        }
        return out;
    }

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        char[][] grid = board("oaan", "etae", "ihkr", "iflv");
        String[] words = {"oath", "pea", "eat", "rain"};
        assertEquals(Set.of("oath", "eat"),
                new HashSet<>(WordSearchII.findWords(grid, words)));
    }

    @Test
    @DisplayName("no words found")
    void noneFound() {
        assertTrue(WordSearchII.findWords(board("ab", "cd"), new String[] {"xyz"}).isEmpty());
    }

    @Test
    @DisplayName("a word reachable by several paths is reported once")
    void reportedOnce() {
        // "aa" can be spelled many ways on an all-'a' board.
        char[][] grid = board("aa", "aa");
        List<String> found = WordSearchII.findWords(grid, new String[] {"aa"});
        assertEquals(1, found.size());
        assertEquals("aa", found.get(0));
    }

    @Test
    @DisplayName("duplicate words in the input are not reported twice")
    void duplicateInput() {
        List<String> found = WordSearchII.findWords(board("ab"), new String[] {"ab", "ab"});
        assertEquals(1, found.size());
    }

    @Test
    @DisplayName("words sharing a prefix are all found")
    void sharedPrefixes() {
        char[][] grid = board("oath", "xxxx");
        Set<String> found = new HashSet<>(
                WordSearchII.findWords(grid, new String[] {"o", "oa", "oat", "oath"}));
        assertEquals(Set.of("o", "oa", "oat", "oath"), found);
    }

    @Test
    @DisplayName("the board is restored exactly")
    void boardIsRestored() {
        char[][] grid = board("oaan", "etae", "ihkr", "iflv");
        char[][] copy = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
        WordSearchII.findWords(grid, new String[] {"oath", "pea", "eat", "rain"});
        assertArrayEquals(copy, grid);
    }

    @Test
    @DisplayName("empty board, empty word list and nulls")
    void degenerate() {
        assertTrue(WordSearchII.findWords(new char[0][], new String[] {"a"}).isEmpty());
        assertTrue(WordSearchII.findWords(board("ab"), new String[0]).isEmpty());
        assertTrue(WordSearchII.findWords(null, new String[] {"a"}).isEmpty());
        assertTrue(WordSearchII.findWords(board("ab"), null).isEmpty());
    }

    @Test
    @DisplayName("agrees with running single-word search per word, on randomised boards")
    void matchesSingleWordSearch() {
        Random random = new Random(212212L);
        for (int trial = 0; trial < 200; trial++) {
            int rows = 1 + random.nextInt(4);
            int cols = 1 + random.nextInt(4);
            char[][] grid = new char[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    grid[r][c] = (char) ('a' + random.nextInt(3));
                }
            }
            Set<String> wordSet = new HashSet<>();
            while (wordSet.size() < 5) {
                int len = 1 + random.nextInt(4);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < len; i++) {
                    sb.append((char) ('a' + random.nextInt(3)));
                }
                wordSet.add(sb.toString());
            }
            String[] words = wordSet.toArray(new String[0]);

            Set<String> expected = new HashSet<>();
            for (String w : words) {
                char[][] copy = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
                if (WordSearch.exist(copy, w)) {
                    expected.add(w);
                }
            }
            Set<String> actual = new HashSet<>(WordSearchII.findWords(grid, words));
            assertEquals(expected, actual,
                    () -> "mismatch for " + Arrays.deepToString(grid) + " / " + Arrays.toString(words));
        }
    }
}
