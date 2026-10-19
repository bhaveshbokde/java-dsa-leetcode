package com.bhavesh.dsa.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TopKFrequentElementsTest {

    private static Set<Integer> asSet(int[] values) {
        Set<Integer> out = new HashSet<>();
        for (int value : values) {
            out.add(value);
        }
        return out;
    }

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(Set.of(1, 2), asSet(TopKFrequentElements.topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2)));
        assertEquals(Set.of(1), asSet(TopKFrequentElements.topKFrequent(new int[] {1}, 1)));
    }

    @Test
    @DisplayName("k equal to the number of distinct values returns all of them")
    void kCoversEverything() {
        assertEquals(Set.of(1, 2, 3), asSet(TopKFrequentElements.topKFrequent(new int[] {1, 2, 3}, 3)));
    }

    @Test
    @DisplayName("all identical values")
    void allIdentical() {
        assertEquals(Set.of(9), asSet(TopKFrequentElements.topKFrequent(new int[] {9, 9, 9}, 1)));
    }

    @Test
    @DisplayName("negative values are counted like any other")
    void negatives() {
        assertEquals(Set.of(-1), asSet(TopKFrequentElements.topKFrequent(new int[] {-1, -1, 5}, 1)));
    }

    @Test
    @DisplayName("invalid k and empty inputs are rejected")
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> TopKFrequentElements.topKFrequent(new int[] {1}, 0));
        assertThrows(IllegalArgumentException.class, () -> TopKFrequentElements.topKFrequent(new int[] {1}, 2));
        assertThrows(IllegalArgumentException.class, () -> TopKFrequentElements.topKFrequent(new int[] {}, 1));
        assertThrows(IllegalArgumentException.class, () -> TopKFrequentElements.topKFrequent(null, 1));
    }

    @Test
    @DisplayName("the returned values really are the k most frequent, on randomised inputs")
    void matchesCountingReference() {
        Random random = new Random(347347L);
        for (int trial = 0; trial < 600; trial++) {
            int[] nums = new int[1 + random.nextInt(20)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(6);
            }
            Map<Integer, Integer> counts = new HashMap<>();
            for (int num : nums) {
                counts.merge(num, 1, Integer::sum);
            }
            int k = 1 + random.nextInt(counts.size());
            int[] result = TopKFrequentElements.topKFrequent(nums.clone(), k);
            assertEquals(k, result.length);
            assertEquals(k, asSet(result).size(), "results must be distinct values");

            // The smallest frequency among the chosen must be at least the
            // largest frequency among those not chosen.
            int minChosen = Integer.MAX_VALUE;
            for (int value : result) {
                minChosen = Math.min(minChosen, counts.get(value));
            }
            int maxOmitted = 0;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (!asSet(result).contains(entry.getKey())) {
                    maxOmitted = Math.max(maxOmitted, entry.getValue());
                }
            }
            final int chosen = minChosen;
            final int omitted = maxOmitted;
            org.junit.jupiter.api.Assertions.assertTrue(chosen >= omitted,
                    () -> "a more frequent value was omitted for " + Arrays.toString(nums));
        }
    }
}
