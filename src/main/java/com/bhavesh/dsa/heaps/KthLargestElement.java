package com.bhavesh.dsa.heaps;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * LeetCode 215 - Kth Largest Element in an Array (Medium).
 *
 * <p>Two genuinely different optimal answers, with a trade-off worth stating
 * rather than picking blindly.
 *
 * <p><b>Min-heap of size k</b> - O(n log k) time, O(k) space. The heap holds
 * the k largest values seen so far; its root is the smallest of those, so a new
 * value only matters if it exceeds the root. Preferred when k is far smaller
 * than n, when the data arrives as a stream, or when the input must not be
 * modified. Sorting the whole array would be O(n log n), which is worse for
 * small k.
 *
 * <p><b>Quickselect</b> - O(n) expected time, O(1) extra space, but O(n^2)
 * worst case and it reorders the input. The partition step discards the side of
 * the pivot that cannot contain the answer, so the expected work forms a
 * geometric series summing to O(n) rather than the O(n log n) of a full sort.
 *
 * <p>The pivot here is chosen at random. With a fixed pivot, an already-sorted
 * array degrades to O(n^2) - a realistic input, not a contrived one.
 */
public final class KthLargestElement {

    private static final Random PIVOT_CHOOSER = new Random();

    private KthLargestElement() {
    }

    /** Min-heap approach. Time O(n log k), auxiliary space O(k). */
    public static int findKthLargest(int[] nums, int k) {
        validate(nums, k);
        Queue<Integer> smallestOfTheLargest = new PriorityQueue<>(k);
        for (int num : nums) {
            if (smallestOfTheLargest.size() < k) {
                smallestOfTheLargest.add(num);
            } else if (num > smallestOfTheLargest.peek()) {
                smallestOfTheLargest.poll();
                smallestOfTheLargest.add(num);
            }
        }
        return smallestOfTheLargest.peek();
    }

    /**
     * Quickselect. Expected O(n), worst case O(n^2); reorders a copy of the
     * input rather than the caller's array.
     */
    public static int findKthLargestByQuickselect(int[] nums, int k) {
        validate(nums, k);
        int[] working = nums.clone();
        // The k-th largest sits at this index once the array is ordered ascending.
        int targetIndex = working.length - k;
        int low = 0;
        int high = working.length - 1;
        while (true) {
            int pivotIndex = partition(working, low, high);
            if (pivotIndex == targetIndex) {
                return working[pivotIndex];
            }
            if (pivotIndex < targetIndex) {
                low = pivotIndex + 1;
            } else {
                high = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] nums, int low, int high) {
        // Random pivot: a fixed choice makes sorted input the worst case.
        int randomIndex = low + PIVOT_CHOOSER.nextInt(high - low + 1);
        swap(nums, randomIndex, high);
        int pivot = nums[high];
        int boundary = low;
        for (int i = low; i < high; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, boundary++);
            }
        }
        swap(nums, boundary, high);
        return boundary;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void validate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums must be non-empty");
        }
        if (k < 1 || k > nums.length) {
            throw new IllegalArgumentException("k must be within 1.." + nums.length + " but was " + k);
        }
    }
}
