package com.bhavesh.dsa.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PermutationsTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        Set<List<Integer>> expected = Set.of(
                List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3),
                List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1));
        assertEquals(expected, new HashSet<>(Permutations.permute(new int[] {1, 2, 3})));
    }

    @Test
    @DisplayName("two elements")
    void twoElements() {
        assertEquals(Set.of(List.of(0, 1), List.of(1, 0)),
                new HashSet<>(Permutations.permute(new int[] {0, 1})));
    }

    @Test
    @DisplayName("single element")
    void singleElement() {
        assertEquals(List.of(List.of(1)), Permutations.permute(new int[] {1}));
    }

    @Test
    @DisplayName("empty and null inputs")
    void degenerate() {
        assertTrue(Permutations.permute(new int[] {}).isEmpty());
        assertTrue(Permutations.permute(null).isEmpty());
    }

    @Test
    @DisplayName("produces exactly n! distinct permutations")
    void countIsFactorial() {
        int factorial = 1;
        for (int n = 1; n <= 7; n++) {
            factorial *= n;
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i;
            }
            List<List<Integer>> result = Permutations.permute(nums);
            final int expected = factorial;
            assertEquals(expected, result.size(), () -> "wrong count for n=" + nums.length);
            assertEquals(expected, new HashSet<>(result).size(),
                    () -> "duplicates present for n=" + nums.length);
        }
    }

    @Test
    @DisplayName("every permutation is a genuine rearrangement of the input")
    void everyOutputIsARearrangement() {
        int[] nums = {4, 7, 1, 9};
        List<Integer> sortedInput = new ArrayList<>(List.of(4, 7, 1, 9));
        sortedInput.sort(null);
        for (List<Integer> permutation : Permutations.permute(nums)) {
            List<Integer> sorted = new ArrayList<>(permutation);
            sorted.sort(null);
            assertEquals(sortedInput, sorted, () -> permutation + " is not a rearrangement");
        }
    }

    @Test
    @DisplayName("the input array is not left modified")
    void doesNotMutateInput() {
        int[] nums = {1, 2, 3};
        Permutations.permute(nums);
        org.junit.jupiter.api.Assertions.assertArrayEquals(new int[] {1, 2, 3}, nums);
    }
}
