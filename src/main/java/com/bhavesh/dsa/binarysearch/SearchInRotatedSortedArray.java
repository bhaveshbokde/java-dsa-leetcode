package com.bhavesh.dsa.binarysearch;

/**
 * LeetCode 33 - Search in Rotated Sorted Array (Medium).
 *
 * <p>Finds {@code target} in an ascending array of distinct values that has
 * been rotated at an unknown pivot. Must run in O(log n), so scanning is out.
 *
 * <p>The array is not sorted, yet binary search still applies because of one
 * structural fact: cutting a rotated sorted array at any midpoint leaves at
 * least one of the two halves properly sorted. Comparing {@code nums[low]} with
 * {@code nums[mid]} identifies which.
 *
 * <p>Once the sorted half is known, a simple range check decides whether the
 * target lies inside it. If so, search there; otherwise the answer can only be
 * in the other half. Either way half the array is discarded per step.
 *
 * <p>The comparison uses {@code <=} rather than {@code <} so that a two-element
 * range, where {@code mid == low}, is still classified as a sorted left half.
 *
 * <p>Distinctness matters: with duplicates, {@code nums[low] == nums[mid]} no
 * longer identifies a sorted half and the worst case degrades to O(n).
 *
 * <p>Time O(log n), auxiliary space O(1).
 */
public final class SearchInRotatedSortedArray {

    private SearchInRotatedSortedArray() {
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[low] <= nums[mid]) {
                // Left half [low, mid] is sorted.
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // Right half [mid, high] is sorted.
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
