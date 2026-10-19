package com.bhavesh.dsa.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 57 - Insert Interval (Medium).
 *
 * <p>Inserts a new interval into a list that is already sorted by start and
 * non-overlapping, merging where necessary, keeping the result sorted and
 * non-overlapping.
 *
 * <p>The naive route is to append and re-run {@link MergeIntervals}, which is
 * O(n log n) - and the sort is entirely wasted, because the input was already
 * sorted. Exploiting that precondition gives O(n).
 *
 * <p>The list divides into exactly three regions, and walking them in order is
 * the whole algorithm:
 *
 * <ol>
 *   <li><b>Entirely before</b> the new interval ({@code end < newStart}) -
 *       emit unchanged.</li>
 *   <li><b>Overlapping</b> it - do not emit; instead widen the new interval to
 *       cover them, taking the minimum start and maximum end.</li>
 *   <li><b>Entirely after</b> ({@code start > newEnd}) - emit the merged
 *       interval first, then the rest unchanged.</li>
 * </ol>
 *
 * <p>The overlap test is {@code !(end < newStart || start > newEnd)}, i.e. two
 * intervals overlap unless one ends strictly before the other begins. Using
 * {@code <=} instead would treat touching intervals as disjoint; LeetCode 57
 * merges them, so the comparison is strict.
 *
 * <p>The merged interval is emitted <b>once</b>, at the boundary between
 * regions 2 and 3 - or at the very end if region 3 is empty. Emitting it inside
 * the overlap loop would produce duplicates.
 *
 * <p>Time O(n) - one pass. Auxiliary space O(n) for the output only.
 */
public final class InsertInterval {

    private InsertInterval() {
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null) {
            return intervals == null ? new int[0][] : intervals;
        }
        List<int[]> result = new ArrayList<>();
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int i = 0;
        int n = intervals == null ? 0 : intervals.length;

        // 1. Everything ending strictly before the new interval begins.
        while (i < n && intervals[i][1] < newStart) {
            result.add(intervals[i]);
            i++;
        }
        // 2. Everything overlapping: absorb into the new interval.
        while (i < n && intervals[i][0] <= newEnd) {
            newStart = Math.min(newStart, intervals[i][0]);
            newEnd = Math.max(newEnd, intervals[i][1]);
            i++;
        }
        result.add(new int[] {newStart, newEnd});

        // 3. Everything starting strictly after.
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[0][]);
    }
}
