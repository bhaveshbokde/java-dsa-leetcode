package com.bhavesh.dsa.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NQueensTest {

    @Test
    @DisplayName("LeetCode example: n = 4 has two solutions")
    void fourQueens() {
        List<List<String>> solutions = NQueens.solveNQueens(4);
        assertEquals(2, solutions.size());
        Set<List<String>> expected = Set.of(
                List.of(".Q..", "...Q", "Q...", "..Q."),
                List.of("..Q.", "Q...", "...Q", ".Q.."));
        assertEquals(expected, new HashSet<>(solutions));
    }

    @Test
    @DisplayName("n = 1 has exactly one solution")
    void oneQueen() {
        assertEquals(List.of(List.of("Q")), NQueens.solveNQueens(1));
    }

    @Test
    @DisplayName("n = 2 and n = 3 have no solutions")
    void impossibleBoards() {
        assertTrue(NQueens.solveNQueens(2).isEmpty());
        assertTrue(NQueens.solveNQueens(3).isEmpty());
    }

    @Test
    @DisplayName("non-positive board sizes yield nothing")
    void nonPositive() {
        assertTrue(NQueens.solveNQueens(0).isEmpty());
        assertTrue(NQueens.solveNQueens(-3).isEmpty());
    }

    @Test
    @DisplayName("solution counts match the known sequence for n = 1..9")
    void knownSolutionCounts() {
        // OEIS A000170, the number of distinct non-attacking arrangements.
        int[] known = {1, 0, 0, 2, 10, 4, 40, 92, 352};
        for (int n = 1; n <= known.length; n++) {
            assertEquals(known[n - 1], NQueens.countSolutions(n), "wrong count for n=" + n);
        }
    }

    @Test
    @DisplayName("countSolutions agrees with the size of the enumerated solution list")
    void countAgreesWithEnumeration() {
        for (int n = 0; n <= 8; n++) {
            assertEquals(NQueens.solveNQueens(n).size(), NQueens.countSolutions(n),
                    "counting and enumeration disagree for n=" + n);
        }
    }

    @Test
    @DisplayName("every reported board is genuinely conflict-free")
    void reportedBoardsAreValid() {
        for (int n = 1; n <= 7; n++) {
            for (List<String> board : NQueens.solveNQueens(n)) {
                assertEquals(n, board.size());
                int[] columns = new int[n];
                for (int row = 0; row < n; row++) {
                    String line = board.get(row);
                    assertEquals(n, line.length());
                    assertEquals(1, line.chars().filter(c -> c == 'Q').count(),
                            "each row must hold exactly one queen");
                    columns[row] = line.indexOf('Q');
                }
                for (int a = 0; a < n; a++) {
                    for (int b = a + 1; b < n; b++) {
                        assertTrue(columns[a] != columns[b], "two queens share a column");
                        assertTrue(Math.abs(columns[a] - columns[b]) != Math.abs(a - b),
                                "two queens share a diagonal");
                    }
                }
            }
        }
    }
}
