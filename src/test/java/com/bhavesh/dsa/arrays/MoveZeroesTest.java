package com.bhavesh.dsa.arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveZeroesTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        int[] nums = {0, 1, 0, 3, 12};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[] {1, 3, 12, 0, 0}, nums);
    }

    @Test
    @DisplayName("relative order of non-zero elements is preserved")
    void preservesOrder() {
        int[] nums = {4, 0, 9, 0, 1, 0, 7};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[] {4, 9, 1, 7, 0, 0, 0}, nums);
    }

    @Test
    @DisplayName("no zeros leaves the array unchanged")
    void noZeros() {
        int[] nums = {3, 1, 2};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[] {3, 1, 2}, nums);
    }

    @Test
    @DisplayName("all zeros")
    void allZeros() {
        int[] nums = {0, 0, 0};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[] {0, 0, 0}, nums);
    }

    @Test
    @DisplayName("negative values are not treated as zero")
    void negativesAreNotZero() {
        int[] nums = {0, -1, 0, -2};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[] {-1, -2, 0, 0}, nums);
    }

    @Test
    @DisplayName("single element and empty arrays")
    void degenerate() {
        int[] single = {0};
        MoveZeroes.moveZeroes(single);
        assertArrayEquals(new int[] {0}, single);

        int[] empty = {};
        MoveZeroes.moveZeroes(empty);
        assertArrayEquals(new int[] {}, empty);
    }

    @Test
    @DisplayName("matches a stable-filter reference on randomised inputs")
    void matchesReference() {
        Random random = new Random(4242L);
        for (int trial = 0; trial < 500; trial++) {
            int[] nums = new int[random.nextInt(12)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(4) - 1;
            }
            int[] expected = reference(nums);
            int[] actual = nums.clone();
            MoveZeroes.moveZeroes(actual);
            assertArrayEquals(expected, actual, () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    private static int[] reference(int[] nums) {
        int[] out = new int[nums.length];
        int at = 0;
        for (int value : nums) {
            if (value != 0) {
                out[at++] = value;
            }
        }
        return out;
    }
}
