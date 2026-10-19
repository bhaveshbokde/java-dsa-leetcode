package com.bhavesh.dsa.dp;

/**
 * LeetCode 198 and 213 - House Robber I and II (Medium).
 *
 * <p>Maximises the sum of a subset of values subject to no two being adjacent.
 *
 * <p>The decision at house {@code i} is binary: rob it and add
 * {@code best(i-2)}, or skip it and keep {@code best(i-1)}. Taking the maximum
 * of those is the whole recurrence, and it is correct because robbing {@code i}
 * forbids only {@code i-1} - everything earlier remains available, already
 * optimally solved.
 *
 * <p>Greedy choices fail here. Taking the largest value first breaks on
 * {@code [2, 7, 9, 3, 1]}, where the optimum skips the 7; alternating even or
 * odd indices breaks on {@code [2, 1, 1, 2]}, where the answer takes both ends.
 *
 * <p>Only two previous values are needed, so the O(n) table collapses to two
 * variables.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class HouseRobber {

    private HouseRobber() {
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return robRange(nums, 0, nums.length - 1);
    }

    /**
     * LeetCode 213 - the houses form a circle, so the first and last are
     * adjacent.
     *
     * <p>The circular constraint is resolved by observing that the first and
     * last house cannot both be robbed. So the answer is the better of two
     * linear subproblems: one excluding the last house, one excluding the
     * first. No third case is needed - a solution robbing neither is already
     * contained in both ranges.
     *
     * <p>Time O(n), auxiliary space O(1).
     */
    public static int robCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(
                robRange(nums, 0, nums.length - 2),
                robRange(nums, 1, nums.length - 1));
    }

    private static int robRange(int[] nums, int from, int to) {
        int twoBack = 0;
        int oneBack = 0;
        for (int i = from; i <= to; i++) {
            int current = Math.max(oneBack, twoBack + nums[i]);
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }
}
