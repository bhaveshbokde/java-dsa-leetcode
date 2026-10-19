package com.bhavesh.dsa.arrays;

/**
 * LeetCode 238 - Product of Array Except Self (Medium).
 *
 * <p>Returns an array where {@code answer[i]} is the product of every element
 * except {@code nums[i]}, without using division and in O(n) time.
 *
 * <p>Division is banned precisely because it hides the interesting case: a
 * single zero makes the total product zero, and two zeros make every answer
 * zero, so "total / nums[i]" needs special-casing it cannot express cleanly.
 *
 * <p>The decomposition that avoids it: the answer at {@code i} is the product of
 * everything strictly left of {@code i} times the product of everything strictly
 * right of it. Two sweeps compute those, and the second sweep multiplies its
 * running suffix into the array the first sweep produced, so no second buffer
 * is needed.
 *
 * <p>Time O(n), auxiliary space O(1) - the returned array is output, not
 * auxiliary storage.
 */
public final class ProductOfArrayExceptSelf {

    private ProductOfArrayExceptSelf() {
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Pass 1: answer[i] becomes the product of everything strictly left of i.
        int prefix = 1;
        for (int i = 0; i < n; i++) {
            answer[i] = prefix;
            prefix *= nums[i];
        }

        // Pass 2: fold in the product of everything strictly right of i.
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }
        return answer;
    }
}
