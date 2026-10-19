package com.bhavesh.dsa.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MaximumProductSubarrayTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(6, MaximumProductSubarray.maxProduct(new int[] {2, 3, -2, 4}));
        assertEquals(0, MaximumProductSubarray.maxProduct(new int[] {-2, 0, -1}));
    }

    @Test
    @DisplayName("two negatives multiply to a positive maximum")
    void twoNegativesMakeAPositive() {
        // The running minimum (-2 * 3 = -6) becomes the maximum when -4 arrives.
        assertEquals(24, MaximumProductSubarray.maxProduct(new int[] {-2, 3, -4}));
    }

    @Test
    @DisplayName("a single negative element is its own answer")
    void singleNegative() {
        assertEquals(-2, MaximumProductSubarray.maxProduct(new int[] {-2}));
    }

    @Test
    @DisplayName("zeros reset the running products")
    void zerosReset() {
        assertEquals(24, MaximumProductSubarray.maxProduct(new int[] {-2, 0, 4, 6}));
        assertEquals(0, MaximumProductSubarray.maxProduct(new int[] {0, 0, 0}));
    }

    @Test
    @DisplayName("all negatives with an odd count")
    void allNegativeOddCount() {
        assertEquals(6, MaximumProductSubarray.maxProduct(new int[] {-1, -2, -3}));
    }

    @Test
    @DisplayName("all positives multiply to the whole array")
    void allPositive() {
        assertEquals(24, MaximumProductSubarray.maxProduct(new int[] {1, 2, 3, 4}));
    }

    @Test
    @DisplayName("empty and null inputs are rejected")
    void degenerate() {
        assertThrows(IllegalArgumentException.class,
                () -> MaximumProductSubarray.maxProduct(new int[] {}));
        assertThrows(IllegalArgumentException.class,
                () -> MaximumProductSubarray.maxProduct(null));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised inputs with negatives and zeros")
    void matchesBruteForce() {
        Random random = new Random(152152L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[1 + random.nextInt(10)];
            for (int i = 0; i < nums.length; i++) {
                // Narrow range keeps products within int and makes zeros common.
                nums[i] = random.nextInt(7) - 3;
            }
            assertEquals(bruteForce(nums), MaximumProductSubarray.maxProduct(nums.clone()),
                    () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    private static int bruteForce(int[] nums) {
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                best = Math.max(best, product);
            }
        }
        return best;
    }
}
