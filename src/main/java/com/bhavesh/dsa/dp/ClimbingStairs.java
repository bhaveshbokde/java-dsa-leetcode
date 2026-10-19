package com.bhavesh.dsa.dp;

/**
 * LeetCode 70 - Climbing Stairs (Easy). The entry point to dynamic programming.
 *
 * <p>Counts the distinct ways to climb {@code n} steps taking 1 or 2 at a time.
 *
 * <p>The recurrence is {@code ways(n) = ways(n-1) + ways(n-2)}: the last move
 * is either a single step from {@code n-1} or a double from {@code n-2}, and
 * those two sets of paths are disjoint and together exhaustive. That is the
 * Fibonacci recurrence with shifted indices.
 *
 * <p>Naive recursion recomputes the same subproblems and is O(phi^n) - about
 * 1.6^n - which is already unusable around n = 45. Memoising makes it O(n)
 * time and O(n) space; iterating bottom-up makes it O(n) time and O(1) space,
 * since only the previous two values are ever needed.
 *
 * <p>Results exceed {@code int} range beyond n = 45, so the return type is
 * {@code long} and larger inputs are rejected rather than silently wrapping.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class ClimbingStairs {

    /**
     * The result is Fibonacci(n + 1), and F(93) = 12200160415121876738 exceeds
     * Long.MAX_VALUE. n = 91 is therefore the largest input with an exact answer.
     */
    private static final int MAX_SUPPORTED = 91;

    private ClimbingStairs() {
    }

    public static long climbStairs(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        if (n > MAX_SUPPORTED) {
            throw new IllegalArgumentException("n above " + MAX_SUPPORTED + " overflows a long");
        }
        if (n <= 1) {
            return 1;
        }
        long twoBack = 1;
        long oneBack = 1;
        for (int step = 2; step <= n; step++) {
            long current = oneBack + twoBack;
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }
}
