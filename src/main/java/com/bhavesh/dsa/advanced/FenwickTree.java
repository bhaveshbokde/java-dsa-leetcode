package com.bhavesh.dsa.advanced;

/**
 * Fenwick tree (binary indexed tree), the structure behind LeetCode 307 -
 * Range Sum Query, Mutable.
 *
 * <p>Supports point updates and prefix-sum queries, both in O(log n).
 *
 * <p>It exists for the case a prefix-sum array cannot handle. A prefix array
 * answers range queries in O(1) but a single update invalidates every later
 * entry, costing O(n). A plain array is the reverse: O(1) updates, O(n)
 * queries. When updates and queries are interleaved, both degrade to O(n) per
 * operation, and the Fenwick tree gives O(log n) for each.
 *
 * <p>The structure is the clever part. Node {@code i} stores the sum of the
 * {@code lowbit(i)} elements ending at {@code i}, where {@code lowbit} is the
 * lowest set bit, isolated as {@code i & -i}. A prefix sum is then assembled by
 * repeatedly stripping the lowest set bit, and an update propagates by
 * repeatedly adding it. Both walks touch one node per set bit, hence O(log n).
 *
 * <p>Indices are 1-based internally because {@code lowbit(0)} is 0, which would
 * make both loops spin forever. The public API stays 0-based and converts.
 *
 * <p>A segment tree solves the same problem and generalises to min, max and
 * range assignment, at roughly twice the memory and noticeably more code. The
 * Fenwick tree is preferred when sums are all that is required.
 */
public final class FenwickTree {

    private final long[] tree;
    private final int size;

    public FenwickTree(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size must be non-negative");
        }
        this.size = size;
        this.tree = new long[size + 1];
    }

    /** Builds from existing values in O(n) rather than n separate updates. */
    public FenwickTree(int[] values) {
        this(values.length);
        for (int i = 0; i < values.length; i++) {
            tree[i + 1] += values[i];
            int parent = (i + 1) + ((i + 1) & -(i + 1));
            if (parent <= size) {
                tree[parent] += tree[i + 1];
            }
        }
    }

    /** Adds {@code delta} to the element at {@code index}. */
    public void add(int index, long delta) {
        checkBounds(index);
        for (int at = index + 1; at <= size; at += at & -at) {
            tree[at] += delta;
        }
    }

    /** Sum of {@code [0, index]} inclusive; 0 for a negative index. */
    public long prefixSum(int index) {
        if (index < 0) {
            return 0;
        }
        int bounded = Math.min(index, size - 1);
        long total = 0;
        for (int at = bounded + 1; at > 0; at -= at & -at) {
            total += tree[at];
        }
        return total;
    }

    /** Sum of {@code [from, to]} inclusive. */
    public long rangeSum(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("from must not exceed to");
        }
        checkBounds(from);
        checkBounds(to);
        return prefixSum(to) - prefixSum(from - 1);
    }

    public int size() {
        return size;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " is outside 0.." + (size - 1));
        }
    }
}
