package com.bhavesh.dsa.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SubsetsTest {

    private static Set<List<Integer>> normalise(List<List<Integer>> subsets) {
        Set<List<Integer>> out = new HashSet<>();
        for (List<Integer> subset : subsets) {
            List<Integer> sorted = new ArrayList<>(subset);
            sorted.sort(null);
            out.add(sorted);
        }
        return out;
    }

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        Set<List<Integer>> expected = Set.of(
                List.of(), List.of(1), List.of(2), List.of(3),
                List.of(1, 2), List.of(1, 3), List.of(2, 3), List.of(1, 2, 3));
        assertEquals(expected, normalise(Subsets.subsets(new int[] {1, 2, 3})));
    }

    @Test
    @DisplayName("single element yields the empty set and itself")
    void singleElement() {
        assertEquals(Set.of(List.of(), List.of(0)), normalise(Subsets.subsets(new int[] {0})));
    }

    @Test
    @DisplayName("empty input yields exactly the empty subset")
    void emptyInput() {
        List<List<Integer>> result = Subsets.subsets(new int[] {});
        assertEquals(1, result.size());
        assertTrue(result.get(0).isEmpty());
    }

    @Test
    @DisplayName("null input")
    void nullInput() {
        assertTrue(Subsets.subsets(null).isEmpty());
    }

    @Test
    @DisplayName("produces exactly 2^n subsets with no duplicates")
    void countIsTwoToTheN() {
        for (int n = 0; n <= 10; n++) {
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i;
            }
            List<List<Integer>> result = Subsets.subsets(nums);
            assertEquals(1 << n, result.size(), () -> "wrong count for n=" + nums.length);
            assertEquals(1 << n, normalise(result).size(), () -> "duplicates for n=" + nums.length);
        }
    }

    @Test
    @DisplayName("the recursive and bitmask variants agree")
    void variantsAgree() {
        Random random = new Random(787878L);
        for (int trial = 0; trial < 200; trial++) {
            int n = random.nextInt(9);
            int[] nums = new int[n];
            Set<Integer> used = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int value;
                do {
                    value = random.nextInt(30);
                } while (!used.add(value));
                nums[i] = value;
            }
            assertEquals(normalise(Subsets.subsetsByBitmask(nums)), normalise(Subsets.subsets(nums)),
                    () -> "variants disagreed on " + Arrays.toString(nums));
        }
    }
}
