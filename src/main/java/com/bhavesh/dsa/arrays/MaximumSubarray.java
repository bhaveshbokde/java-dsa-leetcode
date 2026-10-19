package com.bhavesh.dsa.arrays;

/**
 * LeetCode 53 - Maximum Subarray (Medium). Kadane's algorithm.
 *
 * <p>Returns the largest sum obtainable from a contiguous non-empty subarray.
 *
 * <p>The observation that collapses this to one pass: for the best subarray
 * <em>ending at</em> index {@code i}, there are only two candidates - extend the
 * best subarray ending at {@code i - 1}, or start fresh at {@code i}. Extending
 * is worth it exactly when the previous best is positive. Every subarray ends
 * somewhere, so taking the maximum over all {@code i} of that quantity is the
 * global answer.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class MaximumSubarray {

    private MaximumSubarray() {
    }

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("a non-empty subarray must exist, so the input cannot be empty");
        }
        int bestEndingHere = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // Start fresh when carrying the previous sum forward would only hurt.
            bestEndingHere = Math.max(nums[i], bestEndingHere + nums[i]);
            best = Math.max(best, bestEndingHere);
        }
        return best;
    }
}
