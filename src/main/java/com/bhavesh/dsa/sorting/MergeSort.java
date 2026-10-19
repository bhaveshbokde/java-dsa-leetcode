package com.bhavesh.dsa.sorting;

/**
 * Merge sort - the standard stable O(n log n) comparison sort.
 *
 * <p>Implemented explicitly rather than delegating to {@link java.util.Arrays}
 * because the merge step is the reusable part: it reappears in merging sorted
 * lists, counting inversions and external sorting.
 *
 * <p>Two implementation choices worth defending:
 *
 * <ul>
 *   <li>The scratch buffer is allocated once and passed down, not allocated per
 *       recursive call. The naive version allocates O(n log n) total and is
 *       noticeably slower despite identical asymptotics.</li>
 *   <li>The merge takes from the left run when the two fronts are equal
 *       ({@code <=}). That single character is what makes the sort stable;
 *       using {@code <} would reorder equal elements.</li>
 * </ul>
 *
 * <p>Time O(n log n) in all cases - unlike quicksort, there is no adversarial
 * input. Auxiliary space O(n) for the buffer plus O(log n) stack.
 */
public final class MergeSort {

    private MergeSort() {
    }

    public static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int[] buffer = new int[nums.length];
        sort(nums, buffer, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int[] buffer, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(nums, buffer, low, mid);
        sort(nums, buffer, mid + 1, high);
        // Already ordered: the largest of the left run precedes the smallest of
        // the right. Skipping the merge makes nearly-sorted input near-linear.
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        merge(nums, buffer, low, mid, high);
    }

    private static void merge(int[] nums, int[] buffer, int low, int mid, int high) {
        System.arraycopy(nums, low, buffer, low, high - low + 1);
        int left = low;
        int right = mid + 1;
        for (int at = low; at <= high; at++) {
            if (left > mid) {
                nums[at] = buffer[right++];
            } else if (right > high) {
                nums[at] = buffer[left++];
            } else if (buffer[left] <= buffer[right]) {
                nums[at] = buffer[left++];
            } else {
                nums[at] = buffer[right++];
            }
        }
    }
}
