package com.bhavesh.dsa.intervals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NonOverlappingIntervalsTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(1, NonOverlappingIntervals.eraseOverlapIntervals(
                new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
        assertEquals(2, NonOverlappingIntervals.eraseOverlapIntervals(
                new int[][] {{1, 2}, {1, 2}, {1, 2}}));
        assertEquals(0, NonOverlappingIntervals.eraseOverlapIntervals(
                new int[][] {{1, 2}, {2, 3}}));
    }

    @Test
    @DisplayName("sorting by start would give the wrong answer here")
    void endSortIsRequired() {
        // Start-sorted greedy keeps [1,100] and removes 2; the optimum removes 1.
        assertEquals(1, NonOverlappingIntervals.eraseOverlapIntervals(
                new int[][] {{1, 100}, {2, 3}, {3, 4}}));
    }

    @Test
    @DisplayName("touching intervals do not overlap")
    void touchingIsCompatible() {
        assertEquals(0, NonOverlappingIntervals.eraseOverlapIntervals(
                new int[][] {{1, 2}, {2, 3}, {3, 4}}));
    }

    @Test
    @DisplayName("already disjoint, and completely nested")
    void disjointAndNested() {
        assertEquals(0, NonOverlappingIntervals.eraseOverlapIntervals(
                new int[][] {{1, 2}, {5, 6}, {9, 10}}));
        // All share a point, so only one can be kept.
        assertEquals(2, NonOverlappingIntervals.eraseOverlapIntervals(
                new int[][] {{1, 10}, {2, 9}, {3, 8}}));
    }

    @Test
    @DisplayName("single interval, empty and null")
    void degenerate() {
        assertEquals(0, NonOverlappingIntervals.eraseOverlapIntervals(new int[][] {{1, 5}}));
        assertEquals(0, NonOverlappingIntervals.eraseOverlapIntervals(new int[0][]));
        assertEquals(0, NonOverlappingIntervals.eraseOverlapIntervals(null));
    }

    @Test
    @DisplayName("the input array is not modified")
    void doesNotMutateInput() {
        int[][] intervals = {{3, 4}, {1, 2}};
        NonOverlappingIntervals.eraseOverlapIntervals(intervals);
        assertArrayEquals(new int[][] {{3, 4}, {1, 2}}, intervals);
    }

    @Test
    @DisplayName("extreme bounds do not overflow the comparator")
    void extremeBounds() {
        assertEquals(0, NonOverlappingIntervals.eraseOverlapIntervals(
                new int[][] {{Integer.MIN_VALUE, Integer.MIN_VALUE + 1},
                             {Integer.MAX_VALUE - 1, Integer.MAX_VALUE}}));
    }

    @Test
    @DisplayName("matches an exhaustive subset search on small inputs")
    void matchesExhaustiveSearch() {
        Random random = new Random(435435L);
        for (int trial = 0; trial < 400; trial++) {
            int count = 1 + random.nextInt(8);
            int[][] intervals = new int[count][2];
            for (int i = 0; i < count; i++) {
                int start = random.nextInt(10);
                intervals[i] = new int[] {start, start + 1 + random.nextInt(4)};
            }
            assertEquals(exhaustive(intervals),
                    NonOverlappingIntervals.eraseOverlapIntervals(intervals),
                    () -> "mismatch for " + Arrays.deepToString(intervals));
        }
    }

    /** Tries every subset and finds the largest pairwise-compatible one. */
    private static int exhaustive(int[][] intervals) {
        int n = intervals.length;
        int bestKept = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            boolean ok = true;
            for (int i = 0; i < n && ok; i++) {
                if ((mask & (1 << i)) == 0) {
                    continue;
                }
                for (int j = i + 1; j < n && ok; j++) {
                    if ((mask & (1 << j)) == 0) {
                        continue;
                    }
                    // Overlap unless one ends at or before the other starts.
                    boolean disjoint = intervals[i][1] <= intervals[j][0]
                            || intervals[j][1] <= intervals[i][0];
                    if (!disjoint) {
                        ok = false;
                    }
                }
            }
            if (ok) {
                bestKept = Math.max(bestKept, Integer.bitCount(mask));
            }
        }
        return n - bestKept;
    }
}
