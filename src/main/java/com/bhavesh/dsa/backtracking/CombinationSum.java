package com.bhavesh.dsa.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 39 - Combination Sum (Medium).
 *
 * <p>From an array of <b>distinct</b> positive integers, return every unique
 * combination summing to {@code target}. Each candidate may be reused
 * <b>unlimited</b> times; two combinations differ only if their multisets
 * differ, so {@code [2,2,3]} and {@code [2,3,2]} are the same answer.
 *
 * <p>Two design decisions follow from that last sentence.
 *
 * <p><b>Reuse is expressed by recursing on {@code i}, not {@code i + 1}.</b>
 * Passing the same index back down keeps the current candidate available; this
 * one character is the entire difference from the 0/1 variant (LeetCode 40).
 *
 * <p><b>Order is suppressed by never looking backwards.</b> The loop starts at
 * {@code start} rather than 0, so candidates are only ever appended in
 * non-decreasing index order. That makes each multiset reachable by exactly one
 * path through the search tree - which is why no deduplicating {@link
 * java.util.Set} is needed at the end.
 *
 * <p><b>Pruning.</b> Sorting the candidates first lets the loop {@code break}
 * as soon as one exceeds the remaining target: everything after it is larger
 * and cannot fit either. Without the sort only {@code continue} is valid, and
 * the search explores many doomed branches.
 *
 * <p>Positivity is what guarantees termination: every choice strictly decreases
 * the remaining target, so no branch recurses forever. With a zero or negative
 * candidate the search would not terminate, which is why those are rejected.
 *
 * <p>Time is exponential in the worst case - the output itself can be - and is
 * bounded by O(n^(target/min)) nodes. Auxiliary space O(target/min) for the
 * recursion and path, excluding output.
 */
public final class CombinationSum {

    private CombinationSum() {
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target <= 0) {
            return result;
        }
        for (int candidate : candidates) {
            if (candidate <= 0) {
                throw new IllegalArgumentException(
                        "candidates must be positive; a non-positive value would not terminate");
            }
        }
        int[] sorted = candidates.clone();
        Arrays.sort(sorted);
        explore(sorted, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void explore(int[] candidates, int remaining, int start,
                                List<Integer> path, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) {
                // Sorted, so every later candidate is larger too.
                break;
            }
            path.add(candidates[i]);
            // i, not i + 1: the same candidate may be reused.
            explore(candidates, remaining - candidates[i], i, path, result);
            path.remove(path.size() - 1);
        }
    }
}
