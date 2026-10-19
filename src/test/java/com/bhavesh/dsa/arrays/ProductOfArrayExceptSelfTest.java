package com.bhavesh.dsa.arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductOfArrayExceptSelfTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        assertArrayEquals(new int[] {24, 12, 8, 6},
                ProductOfArrayExceptSelf.productExceptSelf(new int[] {1, 2, 3, 4}));
    }

    @Test
    @DisplayName("negatives and a zero")
    void negativesAndZero() {
        assertArrayEquals(new int[] {0, 0, 9, 0, 0},
                ProductOfArrayExceptSelf.productExceptSelf(new int[] {-1, 1, 0, -3, 3}));
    }

    @Test
    @DisplayName("exactly one zero: only that index is non-zero")
    void singleZero() {
        assertArrayEquals(new int[] {0, 0, 24, 0},
                ProductOfArrayExceptSelf.productExceptSelf(new int[] {2, 3, 0, 4}));
    }

    @Test
    @DisplayName("two zeros: every answer is zero")
    void twoZeros() {
        assertArrayEquals(new int[] {0, 0, 0, 0},
                ProductOfArrayExceptSelf.productExceptSelf(new int[] {2, 0, 0, 4}));
    }

    @Test
    @DisplayName("two elements")
    void twoElements() {
        assertArrayEquals(new int[] {7, 5}, ProductOfArrayExceptSelf.productExceptSelf(new int[] {5, 7}));
    }

    @Test
    @DisplayName("matches a division-free O(n^2) reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(99L);
        for (int trial = 0; trial < 400; trial++) {
            int[] nums = new int[2 + random.nextInt(7)];
            for (int i = 0; i < nums.length; i++) {
                // Deliberately includes 0 so zero-handling is exercised.
                nums[i] = random.nextInt(9) - 4;
            }
            assertArrayEquals(bruteForce(nums), ProductOfArrayExceptSelf.productExceptSelf(nums.clone()),
                    () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    private static int[] bruteForce(int[] nums) {
        int[] out = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            out[i] = product;
        }
        return out;
    }
}
