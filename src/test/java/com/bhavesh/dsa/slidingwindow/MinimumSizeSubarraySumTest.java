package com.bhavesh.dsa.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MinimumSizeSubarraySumTest {

    @Test
    @DisplayName("LeetCode example: [4,3] has length 2")
    void leetCodeExample() {
        assertEquals(2, MinimumSizeSubarraySum.minSubArrayLen(7, new int[] {2, 3, 1, 2, 4, 3}));
    }

    @Test
    @DisplayName("no subarray reaches the target")
    void unreachable() {
        assertEquals(0, MinimumSizeSubarraySum.minSubArrayLen(11, new int[] {1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    @DisplayName("a single element already meets the target")
    void singleElementSuffices() {
        assertEquals(1, MinimumSizeSubarraySum.minSubArrayLen(4, new int[] {1, 4, 4}));
    }

    @Test
    @DisplayName("the whole array is needed")
    void wholeArray() {
        assertEquals(5, MinimumSizeSubarraySum.minSubArrayLen(15, new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    @DisplayName("empty and null inputs")
    void degenerate() {
        assertEquals(0, MinimumSizeSubarraySum.minSubArrayLen(5, new int[] {}));
        assertEquals(0, MinimumSizeSubarraySum.minSubArrayLen(5, null));
    }

    @Test
    @DisplayName("sums near the int ceiling do not overflow")
    void largeSums() {
        int[] nums = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        assertEquals(1, MinimumSizeSubarraySum.minSubArrayLen(Integer.MAX_VALUE, nums));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised positive inputs")
    void matchesBruteForce() {
        Random random = new Random(8080L);
        for (int trial = 0; trial < 600; trial++) {
            int[] nums = new int[random.nextInt(12)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = 1 + random.nextInt(8);
            }
            int target = 1 + random.nextInt(25);
            assertEquals(bruteForce(target, nums), MinimumSizeSubarraySum.minSubArrayLen(target, nums.clone()),
                    () -> "mismatch target=" + target + " nums=" + Arrays.toString(nums));
        }
    }

    private static int bruteForce(int target, int[] nums) {
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            long sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    best = Math.min(best, j - i + 1);
                    break;
                }
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;
    }
}
