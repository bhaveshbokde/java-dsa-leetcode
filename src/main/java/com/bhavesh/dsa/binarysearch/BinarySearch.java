package com.bhavesh.dsa.binarysearch;

/**
 * LeetCode 704 - Binary Search (Easy), plus the two boundary variants that most
 * other binary-search problems reduce to.
 *
 * <p>Two details account for most buggy implementations:
 *
 * <ul>
 *   <li>The midpoint is {@code low + (high - low) / 2}, not
 *       {@code (low + high) / 2}. The latter overflows once the indices sum
 *       past {@code Integer.MAX_VALUE} - the defect that sat in the JDK's own
 *       {@code Arrays.binarySearch} for nine years.</li>
 *   <li>The loop condition and the update must agree. With an inclusive
 *       {@code high} the condition is {@code low <= high} and the updates must
 *       exclude {@code mid}, otherwise the range never shrinks and the loop
 *       hangs.</li>
 * </ul>
 *
 * <p>Time O(log n), auxiliary space O(1).
 */
public final class BinarySearch {

    private BinarySearch() {
    }

    /** Returns any index holding {@code target}, or -1. */
    public static int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * First index whose value is {@code >= target}; {@code nums.length} if none.
     *
     * <p>This is the half-open form: {@code high} starts past the end and the
     * loop runs while {@code low < high}, so the answer is wherever the two
     * meet. Expressing boundary searches this way avoids the off-by-one errors
     * that the inclusive form invites.
     */
    public static int lowerBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /** First index whose value is {@code > target}; {@code nums.length} if none. */
    public static int upperBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
