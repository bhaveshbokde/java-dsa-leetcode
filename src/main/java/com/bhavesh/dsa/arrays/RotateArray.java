package com.bhavesh.dsa.arrays;

/**
 * LeetCode 189 - Rotate Array (Medium).
 *
 * <p>Rotates {@code nums} right by {@code k} steps, in place.
 *
 * <p>The triple-reversal trick: reversing the whole array puts every element on
 * the correct side of the split but in the wrong internal order; reversing each
 * of the two resulting segments repairs that. Concretely, for k = 3 on
 * [1,2,3,4,5,6,7]: reverse all gives [7,6,5,4,3,2,1], reverse the first k gives
 * [5,6,7,4,3,2,1], reverse the rest gives [5,6,7,1,2,3,4].
 *
 * <p>The alternative - copying into a new array at index {@code (i + k) % n} -
 * is also O(n) time but costs O(n) extra space. The cyclic-replacement method
 * is O(1) space too, but needs a gcd-based cycle count to avoid revisiting
 * elements, which is easy to get wrong under interview pressure.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class RotateArray {

    private RotateArray() {
    }

    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = nums.length;
        // k may exceed n, and Java's % keeps the sign of the dividend, so a
        // negative k would otherwise index out of bounds.
        int shift = ((k % n) + n) % n;
        if (shift == 0) {
            return;
        }
        reverse(nums, 0, n - 1);
        reverse(nums, 0, shift - 1);
        reverse(nums, shift, n - 1);
    }

    private static void reverse(int[] nums, int from, int to) {
        int left = from;
        int right = to;
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }
}
