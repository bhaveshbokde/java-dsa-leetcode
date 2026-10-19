package com.bhavesh.dsa.bitmanipulation;

/**
 * LeetCode 338 - Counting Bits (Easy).
 *
 * <p>Returns, for every {@code i} from 0 to {@code n}, the number of set bits
 * in {@code i}.
 *
 * <p>Calling a popcount routine per value is O(n log n) overall. The problem
 * asks for O(n), which requires reusing previously computed answers - in other
 * words dynamic programming over the bit representation.
 *
 * <p>The recurrence used here is {@code bits[i] = bits[i >> 1] + (i & 1)}.
 * Shifting right by one discards the lowest bit, so {@code i >> 1} has exactly
 * the same bits except that one, and {@code i & 1} adds it back. Since
 * {@code i >> 1 < i} for all positive {@code i}, the subproblem is always
 * already computed.
 *
 * <p>An equivalent recurrence, {@code bits[i] = bits[i & (i - 1)] + 1}, uses the
 * fact that {@code i & (i - 1)} clears the lowest set bit. Both are O(1) per
 * entry; the shift version is marginally easier to justify.
 *
 * <p>Time O(n), auxiliary space O(1) beyond the required output.
 */
public final class CountingBits {

    private CountingBits() {
    }

    public static int[] countBits(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    /** Variant built on clearing the lowest set bit. */
    public static int[] countBitsByClearingLowestBit(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }
}
