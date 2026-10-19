package com.bhavesh.dsa.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CombinationSumTest {

    private static Set<List<Integer>> normalise(List<List<Integer>> combos) {
        Set<List<Integer>> out = new HashSet<>();
        for (List<Integer> c : combos) {
            List<Integer> sorted = new ArrayList<>(c);
            sorted.sort(null);
            out.add(sorted);
        }
        return out;
    }

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(Set.of(List.of(2, 2, 3), List.of(7)),
                normalise(CombinationSum.combinationSum(new int[] {2, 3, 6, 7}, 7)));
        assertEquals(Set.of(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5)),
                normalise(CombinationSum.combinationSum(new int[] {2, 3, 5}, 8)));
        assertTrue(CombinationSum.combinationSum(new int[] {2}, 1).isEmpty());
    }

    @Test
    @DisplayName("a candidate may be reused many times")
    void unlimitedReuse() {
        assertEquals(Set.of(List.of(1, 1, 1, 1)),
                normalise(CombinationSum.combinationSum(new int[] {1}, 4)));
    }

    @Test
    @DisplayName("combinations are unique as multisets, not as orderings")
    void noPermutationDuplicates() {
        // [2,3] and [3,2] are the same combination and must appear once.
        List<List<Integer>> result = CombinationSum.combinationSum(new int[] {2, 3}, 5);
        assertEquals(1, result.size());
        assertEquals(Set.of(List.of(2, 3)), normalise(result));
    }

    @Test
    @DisplayName("no combination reaches the target")
    void unreachableTarget() {
        assertTrue(CombinationSum.combinationSum(new int[] {5, 7}, 3).isEmpty());
    }

    @Test
    @DisplayName("degenerate inputs")
    void degenerate() {
        assertTrue(CombinationSum.combinationSum(new int[] {}, 5).isEmpty());
        assertTrue(CombinationSum.combinationSum(null, 5).isEmpty());
        assertTrue(CombinationSum.combinationSum(new int[] {1}, 0).isEmpty());
        assertTrue(CombinationSum.combinationSum(new int[] {1}, -3).isEmpty());
    }

    @Test
    @DisplayName("non-positive candidates are rejected rather than looping forever")
    void nonPositiveRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> CombinationSum.combinationSum(new int[] {0, 2}, 4));
        assertThrows(IllegalArgumentException.class,
                () -> CombinationSum.combinationSum(new int[] {-1, 2}, 4));
    }

    @Test
    @DisplayName("the input array is not modified by the internal sort")
    void doesNotMutateInput() {
        int[] candidates = {7, 3, 2};
        CombinationSum.combinationSum(candidates, 7);
        org.junit.jupiter.api.Assertions.assertArrayEquals(new int[] {7, 3, 2}, candidates);
    }

    @Test
    @DisplayName("every reported combination really sums to the target, and all are distinct")
    void outputsAreValidAndDistinct() {
        Random random = new Random(3939L);
        for (int trial = 0; trial < 300; trial++) {
            Set<Integer> distinct = new HashSet<>();
            while (distinct.size() < 1 + random.nextInt(4)) {
                distinct.add(1 + random.nextInt(8));
            }
            int[] candidates = distinct.stream().mapToInt(Integer::intValue).toArray();
            int target = 1 + random.nextInt(14);
            List<List<Integer>> result = CombinationSum.combinationSum(candidates, target);

            for (List<Integer> combo : result) {
                int sum = combo.stream().mapToInt(Integer::intValue).sum();
                assertEquals(target, sum, () -> combo + " does not sum to " + target);
                for (int v : combo) {
                    assertTrue(distinct.contains(v), () -> v + " is not a candidate");
                }
            }
            assertEquals(result.size(), normalise(result).size(), "duplicate combinations emitted");
        }
    }

    @Test
    @DisplayName("matches a counting DP on the number of distinct combinations")
    void countMatchesDp() {
        Random random = new Random(909039L);
        for (int trial = 0; trial < 200; trial++) {
            Set<Integer> distinct = new HashSet<>();
            while (distinct.size() < 1 + random.nextInt(4)) {
                distinct.add(1 + random.nextInt(9));
            }
            int[] candidates = distinct.stream().mapToInt(Integer::intValue).sorted().toArray();
            int target = 1 + random.nextInt(20);
            assertEquals(countCombinations(candidates, target),
                    CombinationSum.combinationSum(candidates, target).size(),
                    () -> "count mismatch for target " + target);
        }
    }

    /** Unbounded-knapsack combination count: coins outer avoids counting permutations. */
    private static long countCombinations(int[] candidates, int target) {
        long[] ways = new long[target + 1];
        ways[0] = 1;
        for (int coin : candidates) {
            for (int amount = coin; amount <= target; amount++) {
                ways[amount] += ways[amount - coin];
            }
        }
        return ways[target];
    }
}
