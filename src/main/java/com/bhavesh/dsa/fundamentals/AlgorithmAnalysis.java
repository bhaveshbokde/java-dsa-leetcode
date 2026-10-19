package com.bhavesh.dsa.fundamentals;

import java.util.HashMap;
import java.util.Map;

/**
 * Worked examples of algorithm analysis, where several correct implementations
 * of the same function differ by orders of magnitude.
 *
 * <p>The point is not the functions themselves but the gap between them. Each
 * group below computes exactly the same values, and the tests assert that they
 * agree - so any difference in running time comes purely from the algorithm.
 *
 * <p>Summation: the loop is O(n), the closed form O(1). For n = 10^9 that is
 * the difference between a billion additions and one multiplication. The
 * closed form divides by 2 after multiplying, because {@code n * (n + 1)} is
 * always even, so the division is exact.
 *
 * <p>Fibonacci: naive recursion is O(phi^n) because it recomputes the same
 * subproblems exponentially often - F(40) already takes on the order of a
 * second. Memoisation makes each subproblem unique, giving O(n) time and O(n)
 * space. The iterative form is O(n) time and O(1) space, since only two
 * previous values are needed.
 */
public final class AlgorithmAnalysis {

    /** Beyond F(92) the result exceeds Long.MAX_VALUE. */
    private static final int MAX_FIBONACCI_INDEX = 92;

    private AlgorithmAnalysis() {
    }

    /** Sum of 1..n by iteration. Time O(n), space O(1). */
    public static long sumToNByLoop(int n) {
        requireNonNegative(n);
        long total = 0;
        for (int i = 1; i <= n; i++) {
            total += i;
        }
        return total;
    }

    /**
     * Sum of 1..n in closed form. Time O(1), space O(1).
     *
     * <p>Computed in {@code long} because {@code n * (n + 1)} overflows an
     * {@code int} for n above roughly 46340.
     */
    public static long sumToNByFormula(int n) {
        requireNonNegative(n);
        return (long) n * (n + 1) / 2;
    }

    /**
     * Naive recursive Fibonacci. Time O(phi^n), space O(n) for the call stack.
     *
     * <p>Deliberately unmemoised: it is the baseline the other two are measured
     * against. Callers should keep n small.
     */
    public static long fibonacciNaive(int n) {
        requireNonNegative(n);
        if (n <= 1) {
            return n;
        }
        return fibonacciNaive(n - 1) + fibonacciNaive(n - 2);
    }

    /** Top-down memoisation. Time O(n), space O(n). */
    public static long fibonacciMemoised(int n) {
        requireFibonacciRange(n);
        return fibonacciMemoised(n, new HashMap<>());
    }

    private static long fibonacciMemoised(int n, Map<Integer, Long> memo) {
        if (n <= 1) {
            return n;
        }
        Long cached = memo.get(n);
        if (cached != null) {
            return cached;
        }
        long value = fibonacciMemoised(n - 1, memo) + fibonacciMemoised(n - 2, memo);
        memo.put(n, value);
        return value;
    }

    /** Bottom-up iteration. Time O(n), space O(1) - the preferred form. */
    public static long fibonacciIterative(int n) {
        requireFibonacciRange(n);
        if (n <= 1) {
            return n;
        }
        long twoBack = 0;
        long oneBack = 1;
        for (int i = 2; i <= n; i++) {
            long current = oneBack + twoBack;
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    private static void requireNonNegative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative but was " + n);
        }
    }

    private static void requireFibonacciRange(int n) {
        requireNonNegative(n);
        if (n > MAX_FIBONACCI_INDEX) {
            throw new IllegalArgumentException("F(" + n + ") exceeds Long.MAX_VALUE");
        }
    }
}
