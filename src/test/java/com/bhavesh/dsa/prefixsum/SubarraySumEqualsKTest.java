package com.bhavesh.dsa.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SubarraySumEqualsKTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(2, SubarraySumEqualsK.subarraySum(new int[] {1, 1, 1}, 2));
        assertEquals(2, SubarraySumEqualsK.subarraySum(new int[] {1, 2, 3}, 3));
    }

    @Test
    @DisplayName("negative numbers, where a sliding window would fail")
    void negatives() {
        // Three subarrays sum to 0: [1,-1], [-1,0] and [0].
        assertEquals(3, SubarraySumEqualsK.subarraySum(new int[] {1, -1, 0}, 0));
    }

    @Test
    @DisplayName("subarrays starting at index 0 are counted via the empty prefix")
    void prefixStartingAtZero() {
        assertEquals(1, SubarraySumEqualsK.subarraySum(new int[] {3, 4}, 3));
    }

    @Test
    @DisplayName("no subarray matches")
    void noMatch() {
        assertEquals(0, SubarraySumEqualsK.subarraySum(new int[] {1, 2, 3}, 100));
    }

    @Test
    @DisplayName("empty and null inputs")
    void degenerate() {
        assertEquals(0, SubarraySumEqualsK.subarraySum(new int[] {}, 0));
        assertEquals(0, SubarraySumEqualsK.subarraySum(null, 0));
    }

    @Test
    @DisplayName("all zeros with k = 0 counts every subarray")
    void allZeros() {
        // n(n+1)/2 = 10 subarrays for n = 4.
        assertEquals(10, SubarraySumEqualsK.subarraySum(new int[] {0, 0, 0, 0}, 0));
    }

    @Test
    @DisplayName("prefix sums beyond int range do not collide")
    void largePrefixSums() {
        int[] nums = {Integer.MAX_VALUE, Integer.MAX_VALUE, -1};
        assertEquals(1, SubarraySumEqualsK.subarraySum(nums, -1));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised inputs including negatives")
    void matchesBruteForce() {
        Random random = new Random(560560L);
        for (int trial = 0; trial < 600; trial++) {
            int[] nums = new int[random.nextInt(11)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(9) - 4;
            }
            int k = random.nextInt(7) - 3;
            assertEquals(bruteForce(nums, k), SubarraySumEqualsK.subarraySum(nums.clone(), k),
                    () -> "mismatch k=" + k + " nums=" + Arrays.toString(nums));
        }
    }

    private static int bruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            long sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
