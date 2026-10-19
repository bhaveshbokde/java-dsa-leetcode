package com.bhavesh.dsa.arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RotateArrayTest {

    @Test
    @DisplayName("LeetCode example: k = 3")
    void leetCodeExample() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        RotateArray.rotate(nums, 3);
        assertArrayEquals(new int[] {5, 6, 7, 1, 2, 3, 4}, nums);
    }

    @Test
    @DisplayName("k larger than the array length wraps around")
    void kExceedsLength() {
        int[] nums = {1, 2, 3};
        RotateArray.rotate(nums, 7);
        assertArrayEquals(new int[] {3, 1, 2}, nums);
    }

    @Test
    @DisplayName("k equal to the length is a no-op")
    void kEqualsLength() {
        int[] nums = {1, 2, 3, 4};
        RotateArray.rotate(nums, 4);
        assertArrayEquals(new int[] {1, 2, 3, 4}, nums);
    }

    @Test
    @DisplayName("k = 0 is a no-op")
    void zeroShift() {
        int[] nums = {9, 8};
        RotateArray.rotate(nums, 0);
        assertArrayEquals(new int[] {9, 8}, nums);
    }

    @Test
    @DisplayName("negative k rotates left without indexing out of bounds")
    void negativeK() {
        int[] nums = {1, 2, 3, 4, 5};
        RotateArray.rotate(nums, -2);
        assertArrayEquals(new int[] {3, 4, 5, 1, 2}, nums);
    }

    @Test
    @DisplayName("single element and empty arrays are left untouched")
    void degenerate() {
        int[] single = {42};
        RotateArray.rotate(single, 3);
        assertArrayEquals(new int[] {42}, single);

        int[] empty = {};
        RotateArray.rotate(empty, 3);
        assertArrayEquals(new int[] {}, empty);
    }

    @Test
    @DisplayName("matches a copy-into-new-array reference on randomised inputs")
    void matchesReference() {
        Random random = new Random(7788L);
        for (int trial = 0; trial < 500; trial++) {
            int[] nums = new int[1 + random.nextInt(12)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(100);
            }
            int k = random.nextInt(30);
            int[] expected = reference(nums, k);
            int[] actual = nums.clone();
            RotateArray.rotate(actual, k);
            assertArrayEquals(expected, actual,
                    () -> "mismatch rotating " + Arrays.toString(nums) + " by " + k);
        }
    }

    private static int[] reference(int[] nums, int k) {
        int n = nums.length;
        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            out[(i + k) % n] = nums[i];
        }
        return out;
    }
}
