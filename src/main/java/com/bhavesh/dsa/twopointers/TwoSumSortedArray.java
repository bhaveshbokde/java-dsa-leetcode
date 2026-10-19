package com.bhavesh.dsa.twopointers;

/**
 * LeetCode 167 - Two Sum II, Input Array Is Sorted (Medium).
 *
 * <p>Returns the 1-based indices of the two values summing to {@code target} in
 * an array sorted in non-decreasing order.
 *
 * <p>Sortedness is the whole asset, and it buys O(1) space that the hash-map
 * solution to the unsorted version cannot. Starting with the widest pair, the
 * sum is monotone in each pointer: moving {@code left} right can only increase
 * it, moving {@code right} left can only decrease it. So if the current sum is
 * too small the only productive move is {@code left++}, and if it is too large
 * the only productive move is {@code right--}. Each step discards exactly one
 * candidate that provably cannot participate in any solution.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class TwoSumSortedArray {

    private TwoSumSortedArray() {
    }

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            // Widened to long so that adding two extreme ints cannot wrap and
            // make a too-large sum look negative.
            long sum = (long) numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] {left + 1, right + 1};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        throw new IllegalArgumentException("no pair sums to " + target);
    }
}
