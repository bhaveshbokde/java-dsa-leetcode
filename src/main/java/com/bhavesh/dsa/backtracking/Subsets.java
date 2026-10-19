package com.bhavesh.dsa.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 78 - Subsets (Medium).
 *
 * <p>Returns the power set of an array of distinct integers.
 *
 * <p>There are 2^n subsets, so no algorithm can beat O(2^n) output size; the
 * goal is to generate each exactly once with no duplicate work.
 *
 * <p>The recursion makes one binary decision per element - include it or not -
 * which maps the search tree directly onto the 2^n outcomes. Recording the
 * current path at <em>every</em> node rather than only at the leaves is what
 * makes this produce all subsets rather than only the full-length one.
 *
 * <p>The path is mutated and un-mutated rather than copied at each step: push
 * before recursing, pop after. Only the snapshots added to the result are
 * copies. Forgetting the pop is the classic bug and silently produces subsets
 * polluted by siblings.
 *
 * <p>Time O(n * 2^n) - 2^n subsets, each copied in O(n). Auxiliary space O(n)
 * for the recursion and path, excluding the output.
 */
public final class Subsets {

    private Subsets() {
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        explore(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void explore(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        // Every node of the tree is itself a valid subset.
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            explore(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    /**
     * Bitmask variant: the i-th bit of a counter from 0 to 2^n - 1 decides
     * whether element i is present. Iterative, and often easier to reason about.
     *
     * <p>Time O(n * 2^n), auxiliary space O(1) beyond the output.
     */
    public static List<List<Integer>> subsetsByBitmask(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int total = 1 << nums.length;
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>(Integer.bitCount(mask));
            for (int i = 0; i < nums.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
        }
        return result;
    }
}
