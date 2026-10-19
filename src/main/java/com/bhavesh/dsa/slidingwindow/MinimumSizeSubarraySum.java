package com.bhavesh.dsa.slidingwindow;

/**
 * LeetCode 209 - Minimum Size Subarray Sum (Medium).
 *
 * <p>Returns the length of the shortest contiguous subarray whose sum is at
 * least {@code target}, or 0 if none exists.
 *
 * <p>A variable-size sliding window applies only because the values are
 * positive: extending the window strictly increases the sum and shrinking it
 * strictly decreases it. That monotonicity is what lets a single pass work -
 * with negative numbers present the window could need to re-expand after
 * shrinking, and this approach would be wrong. (The negative-friendly version
 * needs prefix sums with a monotonic deque.)
 *
 * <p>The loop expands on every step and then shrinks greedily while the
 * constraint still holds, recording the length each time. Each index enters and
 * leaves the window at most once, so the nested while loop does O(n) total work.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class MinimumSizeSubarraySum {

    private MinimumSizeSubarraySum() {
    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        long windowSum = 0;
        int best = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];
            // Shrink while the window still satisfies the constraint, so the
            // recorded length is minimal for this right endpoint.
            while (windowSum >= target) {
                best = Math.min(best, right - left + 1);
                windowSum -= nums[left++];
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;
    }
}
