package com.bhavesh.dsa.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode 56 - Merge Intervals (Medium).
 *
 * <p>Merges all overlapping intervals.
 *
 * <p>Comparing every pair is O(n^2) and awkward, because merging two intervals
 * can create a new overlap with a third. Sorting by start time removes that
 * entirely: afterwards, any interval that overlaps the one being built must be
 * the very next one in order, so a single pass suffices.
 *
 * <p>The merge condition is {@code current.start <= lastEnd}. Using strict
 * {@code <} would treat touching intervals such as {@code [1,4]} and
 * {@code [4,5]} as disjoint, which LeetCode 56 considers overlapping.
 *
 * <p>Extending takes the maximum of the two ends rather than the new one,
 * because the next interval can be fully contained within the current merge -
 * {@code [1,10]} followed by {@code [2,3]} must stay {@code [1,10]}.
 *
 * <p>Comparing with {@link Integer#compare} rather than subtraction avoids
 * overflow when the two starts straddle zero with large magnitudes.
 *
 * <p>Time O(n log n), dominated by the sort. Auxiliary space O(n) for the
 * output, O(log n) for the sort.
 */
public final class MergeIntervals {

    private MergeIntervals() {
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }
        int[][] sorted = Arrays.stream(intervals).map(int[]::clone).toArray(int[][]::new);
        Arrays.sort(sorted, Comparator.comparingInt(interval -> interval[0]));

        List<int[]> merged = new ArrayList<>();
        int[] current = sorted[0];
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i][0] <= current[1]) {
                current[1] = Math.max(current[1], sorted[i][1]);
            } else {
                merged.add(current);
                current = sorted[i];
            }
        }
        merged.add(current);
        return merged.toArray(new int[0][]);
    }
}
