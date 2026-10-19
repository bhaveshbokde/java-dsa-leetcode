package com.bhavesh.dsa.advanced;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FenwickTreeTest {

    @Test
    @DisplayName("LeetCode 307 example: query, update, query again")
    void leetCodeExample() {
        FenwickTree tree = new FenwickTree(new int[] {1, 3, 5});
        assertEquals(9, tree.rangeSum(0, 2));
        tree.add(1, 2);                 // nums[1] becomes 5
        assertEquals(11, tree.rangeSum(0, 2));
    }

    @Test
    @DisplayName("single-element ranges")
    void singleElementRanges() {
        FenwickTree tree = new FenwickTree(new int[] {4, 7, 2});
        assertEquals(4, tree.rangeSum(0, 0));
        assertEquals(7, tree.rangeSum(1, 1));
        assertEquals(2, tree.rangeSum(2, 2));
    }

    @Test
    @DisplayName("an empty tree and invalid indices")
    void degenerate() {
        FenwickTree empty = new FenwickTree(0);
        assertEquals(0, empty.size());
        assertEquals(0, empty.prefixSum(-1));

        FenwickTree tree = new FenwickTree(3);
        assertThrows(IndexOutOfBoundsException.class, () -> tree.add(3, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> tree.add(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> tree.rangeSum(2, 1));
        assertThrows(IllegalArgumentException.class, () -> new FenwickTree(-1));
    }

    @Test
    @DisplayName("negative deltas work, so the structure handles decrements")
    void negativeDeltas() {
        FenwickTree tree = new FenwickTree(new int[] {5, 5, 5});
        tree.add(1, -3);
        assertEquals(7, tree.rangeSum(0, 1));
        assertEquals(12, tree.rangeSum(0, 2));
    }

    @Test
    @DisplayName("sums beyond int range are exact")
    void largeSums() {
        FenwickTree tree = new FenwickTree(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE});
        assertEquals(2L * Integer.MAX_VALUE, tree.rangeSum(0, 1));
    }

    @Test
    @DisplayName("the O(n) constructor agrees with repeated point updates")
    void constructorsAgree() {
        Random random = new Random(307307L);
        for (int trial = 0; trial < 200; trial++) {
            int[] values = new int[1 + random.nextInt(20)];
            for (int i = 0; i < values.length; i++) {
                values[i] = random.nextInt(41) - 20;
            }
            FenwickTree built = new FenwickTree(values);
            FenwickTree incremental = new FenwickTree(values.length);
            for (int i = 0; i < values.length; i++) {
                incremental.add(i, values[i]);
            }
            for (int i = 0; i < values.length; i++) {
                assertEquals(built.prefixSum(i), incremental.prefixSum(i), "prefix mismatch at " + i);
            }
        }
    }

    @Test
    @DisplayName("matches a plain array under interleaved updates and queries")
    void matchesPlainArray() {
        Random random = new Random(555307L);
        for (int trial = 0; trial < 100; trial++) {
            int n = 1 + random.nextInt(25);
            long[] plain = new long[n];
            FenwickTree tree = new FenwickTree(n);
            for (int step = 0; step < 300; step++) {
                if (random.nextBoolean()) {
                    int index = random.nextInt(n);
                    int delta = random.nextInt(21) - 10;
                    plain[index] += delta;
                    tree.add(index, delta);
                } else {
                    int from = random.nextInt(n);
                    int to = from + random.nextInt(n - from);
                    long expected = 0;
                    for (int i = from; i <= to; i++) {
                        expected += plain[i];
                    }
                    assertEquals(expected, tree.rangeSum(from, to),
                            "range sum mismatch for [" + from + ", " + to + "]");
                }
            }
        }
    }

    @Test
    @DisplayName("a large tree stays responsive under many operations")
    void largeTree() {
        int n = 200_000;
        FenwickTree tree = new FenwickTree(n);
        for (int i = 0; i < n; i++) {
            tree.add(i, 1);
        }
        assertEquals(n, tree.rangeSum(0, n - 1));
        assertEquals(1000, tree.rangeSum(500, 1499));
    }
}
