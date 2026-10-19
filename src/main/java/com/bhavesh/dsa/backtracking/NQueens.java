package com.bhavesh.dsa.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 51 - N-Queens (Hard).
 *
 * <p>Places {@code n} queens on an n x n board so that none attack another.
 *
 * <p>Enumerating all placements is C(n^2, n) and hopeless. Two observations cut
 * it down: exactly one queen occupies each row, so the search can fix one row
 * at a time; and a partial placement that already contains a conflict can never
 * be completed, so the branch is abandoned immediately. That pruning is what
 * makes backtracking tractable here.
 *
 * <p>Conflict detection is O(1) using three boolean arrays rather than
 * re-scanning the board. The diagonal indices are the useful trick: cells on a
 * "down-right" diagonal share {@code row - col}, offset by {@code n - 1} to stay
 * non-negative, and cells on a "down-left" diagonal share {@code row + col}.
 *
 * <p>Time is exponential and has no tight closed form; the pruned search visits
 * far fewer than n^n nodes. Auxiliary space O(n) for the arrays and recursion,
 * excluding the output.
 */
public final class NQueens {

    private NQueens() {
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        if (n <= 0) {
            return solutions;
        }
        int[] queenColumnPerRow = new int[n];
        place(0, n, queenColumnPerRow, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], solutions);
        return solutions;
    }

    /**
     * Counts solutions without materialising any boards, so the O(n) string
     * rendering per solution is skipped entirely. For n = 9 that is 352 boards
     * of 9 strings each that never get built.
     */
    public static int countSolutions(int n) {
        if (n <= 0) {
            return 0;
        }
        return count(0, n, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1]);
    }

    private static int count(int row, int n, boolean[] columnUsed, boolean[] downRight, boolean[] downLeft) {
        if (row == n) {
            return 1;
        }
        int total = 0;
        for (int col = 0; col < n; col++) {
            int dr = row - col + n - 1;
            int dl = row + col;
            if (columnUsed[col] || downRight[dr] || downLeft[dl]) {
                continue;
            }
            columnUsed[col] = true;
            downRight[dr] = true;
            downLeft[dl] = true;

            total += count(row + 1, n, columnUsed, downRight, downLeft);

            columnUsed[col] = false;
            downRight[dr] = false;
            downLeft[dl] = false;
        }
        return total;
    }

    private static void place(int row, int n, int[] queenColumnPerRow, boolean[] columnUsed,
                              boolean[] downRight, boolean[] downLeft, List<List<String>> solutions) {
        if (row == n) {
            solutions.add(render(queenColumnPerRow, n));
            return;
        }
        for (int col = 0; col < n; col++) {
            int dr = row - col + n - 1;
            int dl = row + col;
            if (columnUsed[col] || downRight[dr] || downLeft[dl]) {
                continue;
            }
            queenColumnPerRow[row] = col;
            columnUsed[col] = true;
            downRight[dr] = true;
            downLeft[dl] = true;

            place(row + 1, n, queenColumnPerRow, columnUsed, downRight, downLeft, solutions);

            columnUsed[col] = false;
            downRight[dr] = false;
            downLeft[dl] = false;
        }
    }

    private static List<String> render(int[] queenColumnPerRow, int n) {
        List<String> board = new ArrayList<>(n);
        for (int row = 0; row < n; row++) {
            char[] line = new char[n];
            java.util.Arrays.fill(line, '.');
            line[queenColumnPerRow[row]] = 'Q';
            board.add(new String(line));
        }
        return board;
    }
}
