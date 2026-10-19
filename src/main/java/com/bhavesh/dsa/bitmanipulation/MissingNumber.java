package com.bhavesh.dsa.bitmanipulation;

/**
 * LeetCode 268 - Missing Number (Easy).
 *
 * <p>Given {@code n} distinct values drawn from {@code [0, n]}, returns the one
 * that is absent.
 *
 * <p>Three correct approaches, with a real trade-off between them:
 *
 * <ul>
 *   <li><b>Sorting</b> - O(n log n), no extra space beyond the sort. Slower than
 *       necessary.</li>
 *   <li><b>Gauss summation</b> - the expected total is {@code n(n+1)/2}, so the
 *       missing value is that minus the actual sum. O(n) time, O(1) space, but
 *       the intermediate sum can overflow {@code int} for large {@code n}, which
 *       is why the computation below is done in {@code long}.</li>
 *   <li><b>XOR</b> - XOR every index and every value together; each present
 *       value cancels its index, leaving the missing one. O(n) time, O(1) space,
 *       and cannot overflow at all.</li>
 * </ul>
 *
 * <p>The XOR version is the default here precisely because it sidesteps
 * overflow rather than managing it.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class MissingNumber {

    private MissingNumber() {
    }

    public static int missingNumber(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums must not be null");
        }
        int accumulator = nums.length;
        for (int i = 0; i < nums.length; i++) {
            accumulator ^= i ^ nums[i];
        }
        return accumulator;
    }

    /** Gauss-summation variant, computed in long to avoid overflow. */
    public static int missingNumberBySum(int[] nums) {
        long n = nums.length;
        long expected = n * (n + 1) / 2;
        long actual = 0;
        for (int num : nums) {
            actual += num;
        }
        return (int) (expected - actual);
    }
}
