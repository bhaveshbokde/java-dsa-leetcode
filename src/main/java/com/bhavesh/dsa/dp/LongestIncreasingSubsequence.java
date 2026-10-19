package com.bhavesh.dsa.dp;

import java.util.Arrays;

/**
 * LeetCode 300 - Longest Increasing Subsequence (Medium).
 *
 * <p>Returns the length of the longest strictly increasing subsequence.
 *
 * <p>Two correct solutions with different complexities, and the faster one is
 * worth understanding rather than memorising.
 *
 * <p><b>O(n^2) DP.</b> {@code length[i]} is the best subsequence ending at
 * {@code i}; it extends the best earlier index holding a smaller value. Simple,
 * and it generalises to printing the subsequence itself.
 *
 * <p><b>O(n log n) patience method.</b> Maintains {@code tails}, where
 * {@code tails[k]} is the smallest possible final value of an increasing
 * subsequence of length {@code k + 1}. For each value, binary search for the
 * first tail that is at least as large and overwrite it - extending the array
 * only when the value exceeds every tail.
 *
 * <p>Why that is correct: {@code tails} is always sorted, because a longer
 * subsequence cannot end lower than a shorter one. Replacing a tail with a
 * smaller value never shortens what is achievable - it only makes future
 * extensions easier - and the array's length is exactly the best length found.
 *
 * <p>Note that {@code tails} is not itself a valid subsequence of the input;
 * only its length is meaningful. Reporting its contents as the answer is a
 * common and wrong extension of this method.
 */
public final class LongestIncreasingSubsequence {

    private LongestIncreasingSubsequence() {
    }

    /** Patience method. Time O(n log n), auxiliary space O(n). */
    public static int lengthOfLis(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] tails = new int[nums.length];
        int length = 0;
        for (int num : nums) {
            int position = Arrays.binarySearch(tails, 0, length, num);
            if (position < 0) {
                // Not present: convert to the insertion point.
                position = -(position + 1);
            }
            tails[position] = num;
            if (position == length) {
                length++;
            }
        }
        return length;
    }

    /**
     * Quadratic DP form, kept because it is the one that extends to recovering
     * the subsequence and to counting variants.
     *
     * <p>Time O(n^2), auxiliary space O(n).
     */
    public static int lengthOfLisQuadratic(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] best = new int[nums.length];
        Arrays.fill(best, 1);
        int answer = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    best[i] = Math.max(best[i], best[j] + 1);
                }
            }
            answer = Math.max(answer, best[i]);
        }
        return answer;
    }
}
