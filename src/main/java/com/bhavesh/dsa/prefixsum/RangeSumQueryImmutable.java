package com.bhavesh.dsa.prefixsum;

/**
 * LeetCode 303 - Range Sum Query, Immutable (Easy).
 *
 * <p>Answers repeated inclusive range-sum queries over an array that never
 * changes.
 *
 * <p>The point of the problem is the trade: summing on demand is O(1) to build
 * and O(n) per query, which degrades badly when queries are frequent. Paying
 * O(n) once at construction to store prefix sums makes every query O(1).
 *
 * <p>The prefix array has {@code n + 1} entries with {@code prefix[0] = 0}.
 * That extra slot removes the special case for {@code left == 0}: the answer is
 * uniformly {@code prefix[right + 1] - prefix[left]}.
 *
 * <p>If the array were mutable, this structure would be wrong - a single update
 * invalidates every later prefix, costing O(n). That case wants a Fenwick tree
 * (see {@code com.bhavesh.dsa.advanced.FenwickTree}).
 *
 * <p>Construction O(n); each query O(1); space O(n).
 */
public final class RangeSumQueryImmutable {

    private final long[] prefix;

    public RangeSumQueryImmutable(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums must not be null");
        }
        prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    /** Inclusive sum of {@code nums[left..right]}. */
    public int sumRange(int left, int right) {
        if (left < 0 || right >= prefix.length - 1 || left > right) {
            throw new IndexOutOfBoundsException("invalid range [" + left + ", " + right + "]");
        }
        return (int) (prefix[right + 1] - prefix[left]);
    }

    /** Same range as a long, for inputs whose sums exceed int range. */
    public long sumRangeExact(int left, int right) {
        if (left < 0 || right >= prefix.length - 1 || left > right) {
            throw new IndexOutOfBoundsException("invalid range [" + left + ", " + right + "]");
        }
        return prefix[right + 1] - prefix[left];
    }
}
