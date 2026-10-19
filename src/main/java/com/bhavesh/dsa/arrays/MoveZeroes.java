package com.bhavesh.dsa.arrays;

/**
 * LeetCode 283 - Move Zeroes (Easy).
 *
 * <p>Moves every zero to the end of the array in place while preserving the
 * relative order of the non-zero elements.
 *
 * <p>A stable partition in one pass. The invariant is that everything strictly
 * before {@code write} is a non-zero element in its original relative order, so
 * copying each non-zero element to {@code write} and advancing preserves order
 * by construction. A second short pass fills the tail with zeros.
 *
 * <p>Swapping instead of a two-pass fill also works and touches each element
 * once, but performs a write even when the array has no zeros at all. The
 * version here writes nothing in that case beyond the self-assignments.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class MoveZeroes {

    private MoveZeroes() {
    }

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                nums[write++] = nums[read];
            }
        }
        while (write < nums.length) {
            nums[write++] = 0;
        }
    }
}
