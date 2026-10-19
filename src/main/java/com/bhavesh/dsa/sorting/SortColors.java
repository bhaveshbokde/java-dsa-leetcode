package com.bhavesh.dsa.sorting;

/**
 * LeetCode 75 - Sort Colors (Medium). Dijkstra's Dutch national flag partition.
 *
 * <p>Sorts an array of 0s, 1s and 2s in place in a single pass.
 *
 * <p>Counting occurrences and overwriting is simpler and also O(n), but makes
 * two passes; the problem explicitly asks for one. The three-way partition
 * maintains three regions and one unexamined gap:
 *
 * <pre>
 *   [0, low)        all 0s
 *   [low, mid)      all 1s
 *   [mid, high]     not yet examined
 *   (high, n)       all 2s
 * </pre>
 *
 * <p>The asymmetry is the part worth remembering. After swapping a 2 into
 * place, {@code mid} must <em>not</em> advance: the value swapped back from the
 * tail has never been examined. After swapping a 0, it is safe to advance
 * because that value came from the 1s region and is known to be a 1.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class SortColors {

    private SortColors() {
    }

    public static void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    swap(nums, low++, mid++);
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    // Do not advance mid: the incoming value is unexamined.
                    swap(nums, mid, high--);
                    break;
                default:
                    throw new IllegalArgumentException("expected only 0, 1 or 2 but found " + nums[mid]);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
