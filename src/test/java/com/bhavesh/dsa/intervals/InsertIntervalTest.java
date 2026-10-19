package com.bhavesh.dsa.intervals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InsertIntervalTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertArrayEquals(new int[][] {{1, 5}, {6, 9}},
                InsertInterval.insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5}));
        assertArrayEquals(new int[][] {{1, 2}, {3, 10}, {12, 16}},
                InsertInterval.insert(
                        new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}},
                        new int[] {4, 8}));
    }

    @Test
    @DisplayName("inserting into an empty list")
    void emptyList() {
        assertArrayEquals(new int[][] {{5, 7}},
                InsertInterval.insert(new int[0][], new int[] {5, 7}));
        assertArrayEquals(new int[][] {{5, 7}},
                InsertInterval.insert(null, new int[] {5, 7}));
    }

    @Test
    @DisplayName("the new interval goes entirely before or after")
    void noOverlap() {
        assertArrayEquals(new int[][] {{1, 2}, {5, 7}},
                InsertInterval.insert(new int[][] {{5, 7}}, new int[] {1, 2}));
        assertArrayEquals(new int[][] {{1, 2}, {5, 7}},
                InsertInterval.insert(new int[][] {{1, 2}}, new int[] {5, 7}));
    }

    @Test
    @DisplayName("touching intervals are merged, not left adjacent")
    void touchingMerges() {
        assertArrayEquals(new int[][] {{1, 5}},
                InsertInterval.insert(new int[][] {{1, 3}}, new int[] {3, 5}));
        assertArrayEquals(new int[][] {{1, 5}},
                InsertInterval.insert(new int[][] {{3, 5}}, new int[] {1, 3}));
    }

    @Test
    @DisplayName("the new interval swallows every existing one")
    void swallowsAll() {
        assertArrayEquals(new int[][] {{0, 20}},
                InsertInterval.insert(new int[][] {{1, 2}, {5, 7}, {9, 11}}, new int[] {0, 20}));
    }

    @Test
    @DisplayName("the new interval is contained within an existing one")
    void containedWithin() {
        assertArrayEquals(new int[][] {{1, 10}},
                InsertInterval.insert(new int[][] {{1, 10}}, new int[] {3, 4}));
    }

    @Test
    @DisplayName("a null new interval leaves the list unchanged")
    void nullNewInterval() {
        assertArrayEquals(new int[][] {{1, 2}},
                InsertInterval.insert(new int[][] {{1, 2}}, null));
    }

    @Test
    @DisplayName("matches append-then-merge on randomised sorted inputs")
    void matchesAppendThenMerge() {
        Random random = new Random(5757L);
        for (int trial = 0; trial < 600; trial++) {
            int count = random.nextInt(7);
            int[][] intervals = new int[count][2];
            int cursor = random.nextInt(4);
            for (int i = 0; i < count; i++) {
                int start = cursor + 1 + random.nextInt(3);
                int end = start + random.nextInt(3);
                intervals[i] = new int[] {start, end};
                cursor = end + 1 + random.nextInt(2);   // keep them disjoint and sorted
            }
            int newStart = random.nextInt(25);
            int[] newInterval = {newStart, newStart + random.nextInt(6)};

            int[][] expected = reference(intervals, newInterval);
            int[][] actual = InsertInterval.insert(intervals, newInterval);
            assertArrayEquals(expected, actual,
                    () -> "mismatch inserting " + Arrays.toString(newInterval)
                            + " into " + Arrays.deepToString(intervals));
            for (int i = 1; i < actual.length; i++) {
                assertTrue(actual[i][0] > actual[i - 1][1], "output must stay disjoint and sorted");
            }
        }
    }

    /** Append then merge from scratch - correct, and deliberately not O(n). */
    private static int[][] reference(int[][] intervals, int[] newInterval) {
        int[][] all = Arrays.copyOf(intervals, intervals.length + 1);
        all[intervals.length] = newInterval;
        int[][] copy = Arrays.stream(all).map(int[]::clone).toArray(int[][]::new);
        Arrays.sort(copy, Comparator.comparingInt(x -> x[0]));
        return MergeIntervals.merge(copy);
    }
}
