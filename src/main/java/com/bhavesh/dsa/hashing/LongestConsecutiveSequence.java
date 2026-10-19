package com.bhavesh.dsa.hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 128 - Longest Consecutive Sequence (Medium).
 *
 * <p>Returns the length of the longest run of consecutive integers present in
 * the array, in O(n) time. Sorting would give O(n log n) and is explicitly not
 * what the problem is asking for.
 *
 * <p>The trick is to count each run exactly once, from its smallest member. A
 * value {@code v} starts a run precisely when {@code v - 1} is absent from the
 * set. Only then does the algorithm walk upwards counting {@code v + 1},
 * {@code v + 2}, and so on.
 *
 * <p>That guard is what makes the whole thing linear despite the inner while
 * loop: the upward walk from a run of length L runs L steps, and it happens for
 * exactly one of that run's members, so across all runs the inner loop performs
 * O(n) work in total - not O(n) per element.
 *
 * <p>Time O(n) expected, auxiliary space O(n).
 */
public final class LongestConsecutiveSequence {

    private LongestConsecutiveSequence() {
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> values = new HashSet<>();
        for (int num : nums) {
            values.add(num);
        }
        int best = 0;
        for (int value : values) {
            // The MIN_VALUE guard matters: `value - 1` would wrap to MAX_VALUE,
            // and if MAX_VALUE happens to be present this run would be skipped
            // as though it had a predecessor.
            if (value != Integer.MIN_VALUE && values.contains(value - 1)) {
                continue;
            }
            int length = 1;
            int current = value;
            // Likewise, `current + 1` must never be evaluated at MAX_VALUE: it
            // wraps to MIN_VALUE and would splice two unrelated runs together.
            while (current != Integer.MAX_VALUE && values.contains(current + 1)) {
                current++;
                length++;
            }
            best = Math.max(best, length);
        }
        return best;
    }
}
