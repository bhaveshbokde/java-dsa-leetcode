package com.bhavesh.dsa.binarysearch;

/**
 * LeetCode 34 - Find First and Last Position of Element in Sorted Array (Medium).
 *
 * <p>Returns the inclusive index range occupied by {@code target}, or
 * {@code [-1, -1]}.
 *
 * <p>A plain binary search lands on an arbitrary occurrence, and walking
 * outwards from it is O(n) when the array is one repeated value - which defeats
 * the required O(log n). Two boundary searches avoid that entirely: the first
 * index {@code >= target} and the first index {@code > target} delimit the run,
 * each in O(log n).
 *
 * <p>Presence is decided from the lower bound alone: the target exists exactly
 * when that index is in range and holds the target.
 *
 * <p>Time O(log n), auxiliary space O(1).
 */
public final class FindFirstAndLastPosition {

    private FindFirstAndLastPosition() {
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }
        int first = BinarySearch.lowerBound(nums, target);
        if (first == nums.length || nums[first] != target) {
            return new int[] {-1, -1};
        }
        int last = BinarySearch.upperBound(nums, target) - 1;
        return new int[] {first, last};
    }
}
