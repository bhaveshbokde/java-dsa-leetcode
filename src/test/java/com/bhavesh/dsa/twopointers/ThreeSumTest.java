package com.bhavesh.dsa.twopointers;

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

class ThreeSumTest {

    private static Set<List<Integer>> asSet(List<List<Integer>> triplets) {
        Set<List<Integer>> out = new HashSet<>();
        for (List<Integer> triplet : triplets) {
            List<Integer> sorted = new ArrayList<>(triplet);
            sorted.sort(null);
            out.add(sorted);
        }
        return out;
    }

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        Set<List<Integer>> expected = Set.of(List.of(-1, -1, 2), List.of(-1, 0, 1));
        assertEquals(expected, asSet(ThreeSum.threeSum(new int[] {-1, 0, 1, 2, -1, -4})));
    }

    @Test
    @DisplayName("no triplet sums to zero")
    void noTriplet() {
        assertTrue(ThreeSum.threeSum(new int[] {1, 2, 3}).isEmpty());
    }

    @Test
    @DisplayName("fewer than three elements")
    void tooShort() {
        assertTrue(ThreeSum.threeSum(new int[] {0, 1}).isEmpty());
        assertTrue(ThreeSum.threeSum(new int[] {}).isEmpty());
        assertTrue(ThreeSum.threeSum(null).isEmpty());
    }

    @Test
    @DisplayName("three zeros produce exactly one triplet")
    void threeZeros() {
        assertEquals(Set.of(List.of(0, 0, 0)), asSet(ThreeSum.threeSum(new int[] {0, 0, 0})));
    }

    @Test
    @DisplayName("many duplicates yield no duplicated triplets")
    void heavyDuplicates() {
        List<List<Integer>> result = ThreeSum.threeSum(new int[] {0, 0, 0, 0, 0, 0});
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("the input array is not modified")
    void doesNotMutateInput() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] copy = nums.clone();
        ThreeSum.threeSum(nums);
        org.junit.jupiter.api.Assertions.assertArrayEquals(copy, nums);
    }

    @Test
    @DisplayName("matches an O(n^3) reference on randomised inputs with many duplicates")
    void matchesBruteForce() {
        Random random = new Random(31415L);
        for (int trial = 0; trial < 400; trial++) {
            int[] nums = new int[random.nextInt(9)];
            for (int i = 0; i < nums.length; i++) {
                // Narrow range so zero-sum triplets and duplicates are common.
                nums[i] = random.nextInt(7) - 3;
            }
            assertEquals(bruteForce(nums), asSet(ThreeSum.threeSum(nums)),
                    () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    private static Set<List<Integer>> bruteForce(int[] nums) {
        Set<List<Integer>> out = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>(List.of(nums[i], nums[j], nums[k]));
                        triplet.sort(null);
                        out.add(triplet);
                    }
                }
            }
        }
        return out;
    }
}
