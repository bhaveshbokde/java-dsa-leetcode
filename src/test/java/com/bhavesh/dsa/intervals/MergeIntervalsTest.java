package com.bhavesh.dsa.intervals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MergeIntervalsTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        int[][] input = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        assertArrayEquals(new int[][] {{1, 6}, {8, 10}, {15, 18}}, MergeIntervals.merge(input));
    }

    @Test
    @DisplayName("touching intervals are merged")
    void touchingIntervals() {
        assertArrayEquals(new int[][] {{1, 5}}, MergeIntervals.merge(new int[][] {{1, 4}, {4, 5}}));
    }

    @Test
    @DisplayName("a fully contained interval does not shrink the merge")
    void containedInterval() {
        assertArrayEquals(new int[][] {{1, 10}}, MergeIntervals.merge(new int[][] {{1, 10}, {2, 3}}));
    }

    @Test
    @DisplayName("unsorted input is handled")
    void unsortedInput() {
        assertArrayEquals(new int[][] {{1, 6}, {8, 10}},
                MergeIntervals.merge(new int[][] {{8, 10}, {2, 6}, {1, 3}}));
    }

    @Test
    @DisplayName("disjoint intervals are left alone")
    void disjoint() {
        assertArrayEquals(new int[][] {{1, 2}, {5, 6}}, MergeIntervals.merge(new int[][] {{1, 2}, {5, 6}}));
    }

    @Test
    @DisplayName("single, empty and null inputs")
    void degenerate() {
        assertArrayEquals(new int[][] {{3, 7}}, MergeIntervals.merge(new int[][] {{3, 7}}));
        assertEquals(0, MergeIntervals.merge(new int[0][]).length);
        assertEquals(0, MergeIntervals.merge(null).length);
    }

    @Test
    @DisplayName("the input array is not modified")
    void doesNotMutateInput() {
        int[][] input = {{1, 3}, {2, 6}};
        MergeIntervals.merge(input);
        assertArrayEquals(new int[][] {{1, 3}, {2, 6}}, input);
    }

    @Test
    @DisplayName("extreme bounds do not overflow the comparator")
    void extremeBounds() {
        int[][] input = {{Integer.MAX_VALUE - 1, Integer.MAX_VALUE}, {Integer.MIN_VALUE, Integer.MIN_VALUE + 1}};
        int[][] result = MergeIntervals.merge(input);
        assertEquals(2, result.length);
        assertArrayEquals(new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE + 1}, result[0]);
    }

    @Test
    @DisplayName("matches a point-set reference on randomised small intervals")
    void matchesPointSetReference() {
        Random random = new Random(565656L);
        for (int trial = 0; trial < 500; trial++) {
            int count = random.nextInt(7);
            int[][] intervals = new int[count][2];
            for (int i = 0; i < count; i++) {
                int start = random.nextInt(12);
                intervals[i][0] = start;
                intervals[i][1] = start + random.nextInt(5);
            }
            int[][] merged = MergeIntervals.merge(intervals);
            // Every covered integer point must be identical before and after.
            boolean[] before = new boolean[40];
            for (int[] interval : intervals) {
                for (int p = interval[0]; p <= interval[1]; p++) {
                    before[p] = true;
                }
            }
            boolean[] after = new boolean[40];
            for (int[] interval : merged) {
                for (int p = interval[0]; p <= interval[1]; p++) {
                    after[p] = true;
                }
            }
            org.junit.jupiter.api.Assertions.assertArrayEquals(before, after,
                    () -> "coverage changed for " + Arrays.deepToString(intervals));
            // And the result must be disjoint and ordered.
            for (int i = 1; i < merged.length; i++) {
                org.junit.jupiter.api.Assertions.assertTrue(merged[i][0] > merged[i - 1][1],
                        () -> "overlapping output " + Arrays.deepToString(merged));
            }
        }
    }
}
