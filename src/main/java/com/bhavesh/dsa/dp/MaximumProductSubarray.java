package com.bhavesh.dsa.dp;

/**
 * LeetCode 152 - Maximum Product Subarray (Medium).
 *
 * <p>Returns the largest product of a contiguous non-empty subarray.
 *
 * <p>The instinct is to copy Kadane's algorithm from Maximum Subarray, and that
 * fails. Addition is monotone - a larger running sum is always at least as
 * useful - but multiplication is not: a large <em>negative</em> running product
 * becomes the best possible value the moment another negative appears.
 *
 * <p>So the running state must track both extremes. Carrying the minimum
 * alongside the maximum captures exactly the quantity that a future negative
 * can convert into the maximum.
 *
 * <p>Each step considers three candidates: start fresh at {@code nums[i]},
 * extend the running maximum, or extend the running minimum. A negative
 * {@code nums[i]} swaps which of the latter two is larger, which is why both
 * products are computed before either variable is reassigned - overwriting
 * {@code maxEndingHere} first would feed the new value into the minimum's
 * calculation.
 *
 * <p>Zeros need no special case: they force both running values to 0, and the
 * "start fresh" candidate restarts the scan naturally at the next element.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class MaximumProductSubarray {

    private MaximumProductSubarray() {
    }

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("a non-empty subarray must exist");
        }
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];
        int best = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            // Both products are computed from the PREVIOUS values before either
            // is reassigned.
            int extendedMax = maxEndingHere * value;
            int extendedMin = minEndingHere * value;

            maxEndingHere = Math.max(value, Math.max(extendedMax, extendedMin));
            minEndingHere = Math.min(value, Math.min(extendedMax, extendedMin));

            best = Math.max(best, maxEndingHere);
        }
        return best;
    }
}
