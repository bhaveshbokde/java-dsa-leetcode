package com.bhavesh.dsa.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15 - 3Sum (Medium).
 *
 * <p>Returns every unique triplet summing to zero.
 *
 * <p>Three nested loops are O(n^3) and then still need a deduplication pass.
 * Sorting first costs O(n log n) and buys two things at once: the inner search
 * becomes the O(n) two-pointer scan from Two Sum II, and duplicates become
 * adjacent, so they can be skipped positionally instead of by storing every
 * triplet in a set.
 *
 * <p>Deduplication happens in two places, and both are needed. Skipping
 * repeated values of the outer element prevents emitting the same triplet from
 * a different first index; skipping repeated values after recording a hit
 * prevents it from a different second index.
 *
 * <p>The {@code nums[i] > 0} early exit is sound because the array is sorted:
 * once the smallest of the three is positive, no triplet can sum to zero.
 *
 * <p>Time O(n^2), auxiliary space O(1) beyond the sort and the output.
 */
public final class ThreeSum {

    private ThreeSum() {
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return triplets;
        }
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        for (int i = 0; i < sorted.length - 2; i++) {
            if (sorted[i] > 0) {
                break;
            }
            if (i > 0 && sorted[i] == sorted[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = sorted.length - 1;
            while (left < right) {
                long sum = (long) sorted[i] + sorted[left] + sorted[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    triplets.add(List.of(sorted[i], sorted[left], sorted[right]));
                    // Advance past every copy of the values just consumed.
                    int leftValue = sorted[left];
                    int rightValue = sorted[right];
                    while (left < right && sorted[left] == leftValue) {
                        left++;
                    }
                    while (left < right && sorted[right] == rightValue) {
                        right--;
                    }
                }
            }
        }
        return triplets;
    }
}
