package com.bhavesh.dsa.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MaximumSubarrayTest {

    @Test
    @DisplayName("LeetCode example: [4,-1,2,1] sums to 6")
    void leetCodeExample() {
        assertEquals(6, MaximumSubarray.maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    @DisplayName("all negative: the answer is the single largest element, not zero")
    void allNegative() {
        // The classic Kadane bug is initialising best to 0, which returns 0 here.
        assertEquals(-1, MaximumSubarray.maxSubArray(new int[] {-3, -1, -7, -4}));
    }

    @Test
    @DisplayName("single element")
    void singleElement() {
        assertEquals(1, MaximumSubarray.maxSubArray(new int[] {1}));
        assertEquals(-5, MaximumSubarray.maxSubArray(new int[] {-5}));
    }

    @Test
    @DisplayName("all positive: the whole array is the answer")
    void allPositive() {
        assertEquals(15, MaximumSubarray.maxSubArray(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    @DisplayName("zeros mixed with negatives")
    void zerosAndNegatives() {
        assertEquals(0, MaximumSubarray.maxSubArray(new int[] {-1, 0, -2}));
    }

    @Test
    @DisplayName("an empty input is rejected, since a non-empty subarray is required")
    void emptyRejected() {
        assertThrows(IllegalArgumentException.class, () -> MaximumSubarray.maxSubArray(new int[] {}));
        assertThrows(IllegalArgumentException.class, () -> MaximumSubarray.maxSubArray(null));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised inputs including all-negative cases")
    void matchesBruteForce() {
        Random random = new Random(531L);
        for (int trial = 0; trial < 600; trial++) {
            int[] nums = new int[1 + random.nextInt(10)];
            boolean forceNegative = trial % 3 == 0;
            for (int i = 0; i < nums.length; i++) {
                nums[i] = forceNegative ? -1 - random.nextInt(20) : random.nextInt(41) - 20;
            }
            assertEquals(bruteForce(nums), MaximumSubarray.maxSubArray(nums.clone()),
                    () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    private static int bruteForce(int[] nums) {
        int best = Integer.MIN_VALUE;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                best = Math.max(best, sum);
            }
        }
        return best;
    }
}
