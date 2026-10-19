package com.bhavesh.dsa.dp;

/**
 * LeetCode 62 - Unique Paths (Medium). Grid dynamic programming.
 *
 * <p>Counts the monotone lattice paths from the top-left to the bottom-right of
 * an m x n grid, moving only right or down.
 *
 * <p>The recurrence is {@code paths(r, c) = paths(r-1, c) + paths(r, c-1)}: the
 * final move arrives either from above or from the left, and those path sets
 * are disjoint. The first row and column are 1, since there is a single way to
 * travel along an edge.
 *
 * <p>The full table is O(m * n) space, but each row depends only on the row
 * above, so one array of length {@code n} suffices. Updating it left to right
 * is what makes this work: when {@code row[c]} is read it still holds the value
 * from the previous row, and {@code row[c-1]} has already been updated for the
 * current one - exactly the two terms the recurrence needs.
 *
 * <p>There is also a closed form - the answer is C(m+n-2, m-1) - which is O(min(m, n))
 * time. It is provided separately, with the multiplication ordered to keep every
 * intermediate value an exact integer and to avoid overflowing.
 *
 * <p>Time O(m * n), auxiliary space O(n).
 */
public final class UniquePaths {

    private UniquePaths() {
    }

    public static long uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            throw new IllegalArgumentException("grid dimensions must be positive");
        }
        long[] row = new long[n];
        java.util.Arrays.fill(row, 1L);
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                // row[c] is still the row above; row[c-1] is already this row.
                row[c] = row[c] + row[c - 1];
            }
        }
        return row[n - 1];
    }

    /**
     * Closed form C(m+n-2, m-1).
     *
     * <p>Dividing at each step rather than computing the full factorial keeps
     * the running value small and exact: after multiplying by {@code i} terms
     * the product is always divisible by {@code i}, because a product of
     * {@code i} consecutive integers always is.
     *
     * <p>Time O(min(m, n)), auxiliary space O(1).
     */
    public static long uniquePathsByCombinatorics(int m, int n) {
        if (m <= 0 || n <= 0) {
            throw new IllegalArgumentException("grid dimensions must be positive");
        }
        int totalMoves = m + n - 2;
        int choose = Math.min(m - 1, n - 1);
        long result = 1;
        for (int i = 1; i <= choose; i++) {
            result = result * (totalMoves - choose + i) / i;
        }
        return result;
    }
}
