package com.bhavesh.dsa.monotonic;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SlidingWindowMaximumTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        assertArrayEquals(new int[] {3, 3, 5, 5, 6, 7},
                SlidingWindowMaximum.maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    @Test
    @DisplayName("window of one returns the array itself")
    void windowOfOne() {
        assertArrayEquals(new int[] {1, 2, 3}, SlidingWindowMaximum.maxSlidingWindow(new int[] {1, 2, 3}, 1));
    }

    @Test
    @DisplayName("window covering the whole array")
    void fullWindow() {
        assertArrayEquals(new int[] {9}, SlidingWindowMaximum.maxSlidingWindow(new int[] {4, 9, 2}, 3));
    }

    @Test
    @DisplayName("all equal values")
    void allEqual() {
        assertArrayEquals(new int[] {5, 5, 5}, SlidingWindowMaximum.maxSlidingWindow(new int[] {5, 5, 5, 5}, 2));
    }

    @Test
    @DisplayName("negatives only")
    void negatives() {
        assertArrayEquals(new int[] {-1, -3, -3},
                SlidingWindowMaximum.maxSlidingWindow(new int[] {-1, -3, -5, -3}, 2));
    }

    @Test
    @DisplayName("empty, null and invalid window sizes")
    void degenerate() {
        assertArrayEquals(new int[0], SlidingWindowMaximum.maxSlidingWindow(new int[] {}, 3));
        assertArrayEquals(new int[0], SlidingWindowMaximum.maxSlidingWindow(null, 3));
        assertArrayEquals(new int[0], SlidingWindowMaximum.maxSlidingWindow(new int[] {1, 2}, 0));
        assertThrows(IllegalArgumentException.class,
                () -> SlidingWindowMaximum.maxSlidingWindow(new int[] {1, 2}, 5));
    }

    @Test
    @DisplayName("matches an O(n*k) reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(239239L);
        for (int trial = 0; trial < 600; trial++) {
            int[] nums = new int[1 + random.nextInt(14)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(21) - 10;
            }
            int k = 1 + random.nextInt(nums.length);
            assertArrayEquals(bruteForce(nums, k),
                    SlidingWindowMaximum.maxSlidingWindow(nums.clone(), k),
                    () -> "mismatch k=" + k + " nums=" + Arrays.toString(nums));
        }
    }

    @Test
    @DisplayName("a large input with a large window stays linear")
    void largeInput() {
        Random random = new Random(5L);
        int[] nums = new int[200_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt();
        }
        int[] result = SlidingWindowMaximum.maxSlidingWindow(nums, 1000);
        org.junit.jupiter.api.Assertions.assertEquals(nums.length - 1000 + 1, result.length);
    }

    private static int[] bruteForce(int[] nums, int k) {
        int[] out = new int[nums.length - k + 1];
        for (int i = 0; i + k <= nums.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            out[i] = max;
        }
        return out;
    }
}
