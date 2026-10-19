package com.bhavesh.dsa.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 46 - Permutations (Medium).
 *
 * <p>Returns every ordering of an array of distinct integers.
 *
 * <p>Unlike subsets, order matters and every element appears in every output,
 * so the recursion branches over "which unused element comes next" rather than
 * "include or skip". There are n! permutations, which bounds any solution.
 *
 * <p>The swap-based formulation avoids the auxiliary {@code boolean[] used}
 * array: at depth {@code k}, swapping each candidate into position {@code k}
 * and recursing on the suffix enumerates exactly the distinct choices for that
 * position. The swap must be undone afterwards to restore the array for the
 * next sibling branch.
 *
 * <p>This relies on the input being distinct. With duplicates the swap version
 * emits repeats, and the fix is to sort and skip equal siblings using the
 * {@code used} array - a genuinely different algorithm (LeetCode 47).
 *
 * <p>Time O(n * n!), auxiliary space O(n) beyond the output.
 */
public final class Permutations {

    private Permutations() {
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        permuteFrom(nums.clone(), 0, result);
        return result;
    }

    private static void permuteFrom(int[] nums, int k, List<List<Integer>> result) {
        if (k == nums.length) {
            List<Integer> snapshot = new ArrayList<>(nums.length);
            for (int value : nums) {
                snapshot.add(value);
            }
            result.add(snapshot);
            return;
        }
        for (int i = k; i < nums.length; i++) {
            swap(nums, k, i);
            permuteFrom(nums, k + 1, result);
            swap(nums, k, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
