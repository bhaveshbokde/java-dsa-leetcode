package com.bhavesh.dsa.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 435 - Non-overlapping Intervals (Medium).
 *
 * <p>Returns the minimum number of intervals to remove so that the rest do not
 * overlap.
 *
 * <p>Minimising removals is the same as <b>maximising how many are kept</b>,
 * which is the classic activity-selection problem.
 *
 * <p>The decisive choice is the <b>sort key</b>, and it differs from
 * {@link MergeIntervals}. Merging sorts by <em>start</em>; here the correct key
 * is the <b>end</b> time. The greedy rule is then: always keep the interval
 * that finishes earliest among those that still fit.
 *
 * <p><b>Why that is safe</b> (exchange argument): suppose an optimal solution
 * keeps some interval {@code X} where the earliest-finishing compatible
 * interval is {@code Y}, with {@code Y.end <= X.end}. Replacing {@code X} with
 * {@code Y} cannot conflict with anything the optimum kept after {@code X},
 * because {@code Y} finishes no later. The swapped solution is therefore still
 * valid and the same size - so a solution containing the earliest-finishing
 * interval is always at least as good.
 *
 * <p><b>Sorting by start would be wrong.</b> For {@code [[1,100],[2,3],[3,4]]}
 * the start-sorted greedy keeps {@code [1,100]} and discards both others,
 * removing 2; end-sorted keeps {@code [2,3]} and {@code [3,4]}, removing only 1.
 *
 * <p>Touching intervals such as {@code [1,2]} and {@code [2,3]} do <b>not</b>
 * overlap under this problem's definition, so the compatibility test is
 * {@code start >= lastEnd} rather than {@code >}.
 *
 * <p>Time O(n log n), dominated by the sort. Auxiliary space O(n) for the
 * defensive copy plus O(log n) sort stack.
 */
public final class NonOverlappingIntervals {

    private NonOverlappingIntervals() {
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }
        int[][] sorted = Arrays.stream(intervals).map(int[]::clone).toArray(int[][]::new);
        // comparingInt, not subtraction: a - b overflows for extreme bounds.
        Arrays.sort(sorted, Comparator.comparingInt(interval -> interval[1]));

        int kept = 1;
        int lastEnd = sorted[0][1];
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i][0] >= lastEnd) {     // touching counts as compatible
                kept++;
                lastEnd = sorted[i][1];
            }
        }
        return sorted.length - kept;
    }
}
