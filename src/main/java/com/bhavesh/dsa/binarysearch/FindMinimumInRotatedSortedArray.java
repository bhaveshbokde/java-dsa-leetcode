package com.bhavesh.dsa.binarysearch;

/**
 * LeetCode 153 - Find Minimum in Rotated Sorted Array (Medium).
 *
 * <p>An ascending array of <b>distinct</b> values has been rotated; return the
 * minimum in O(log n).
 *
 * <p>The minimum is the single point where the ascending order breaks - the
 * "pivot". Finding it is a binary search, but the comparison is **against
 * {@code nums[high]}, not against {@code nums[low]}**, and that choice is not
 * arbitrary.
 *
 * <p>Comparing with {@code nums[high]} gives an unambiguous answer every time:
 * <ul>
 *   <li>{@code nums[mid] > nums[high]} means the break lies strictly to the
 *       right, so the minimum is in {@code (mid, high]};</li>
 *   <li>{@code nums[mid] < nums[high]} means {@code mid..high} is already
 *       ascending, so the minimum is in {@code [low, mid]} - and {@code mid}
 *       itself remains a candidate.</li>
 * </ul>
 *
 * <p>Comparing with {@code nums[low]} does <em>not</em> have this property: on
 * a non-rotated array {@code nums[mid] > nums[low]} holds while the minimum is
 * to the <em>left</em>, so the same comparison means different things in
 * different cases and needs an extra guard.
 *
 * <p>The loop uses half-open convergence - {@code while (low < high)} with
 * {@code high = mid} - so it terminates on the answer with no equality branch
 * and no risk of an infinite loop. Note {@code high = mid}, never
 * {@code mid - 1}: {@code mid} may itself be the minimum.
 *
 * <p>Distinctness matters. With duplicates, {@code nums[mid] == nums[high]} is
 * uninformative and the only safe move is {@code high--}, degrading the worst
 * case to O(n). That is LeetCode 154.
 *
 * <p>Time O(log n), auxiliary space O(1).
 */
public final class FindMinimumInRotatedSortedArray {

    private FindMinimumInRotatedSortedArray() {
    }

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("the array must be non-empty");
        }
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;        // the break is strictly right of mid
            } else {
                high = mid;           // mid may be the minimum, so keep it
            }
        }
        return nums[low];
    }

    /** Index of the minimum - the rotation pivot. Same search, different return. */
    public static int findMinIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("the array must be non-empty");
        }
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
