package com.bhavesh.dsa.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LongestConsecutiveSequenceTest {

    @Test
    @DisplayName("LeetCode example: [1,2,3,4] has length 4")
    void leetCodeExample() {
        assertEquals(4, LongestConsecutiveSequence.longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    }

    @Test
    @DisplayName("duplicates do not inflate the run length")
    void duplicates() {
        assertEquals(9, LongestConsecutiveSequence.longestConsecutive(
                new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    @Test
    @DisplayName("empty array")
    void empty() {
        assertEquals(0, LongestConsecutiveSequence.longestConsecutive(new int[] {}));
        assertEquals(0, LongestConsecutiveSequence.longestConsecutive(null));
    }

    @Test
    @DisplayName("single element")
    void single() {
        assertEquals(1, LongestConsecutiveSequence.longestConsecutive(new int[] {5}));
    }

    @Test
    @DisplayName("all identical values form a run of one")
    void allSame() {
        assertEquals(1, LongestConsecutiveSequence.longestConsecutive(new int[] {7, 7, 7, 7}));
    }

    @Test
    @DisplayName("negative numbers and a run crossing zero")
    void crossesZero() {
        assertEquals(5, LongestConsecutiveSequence.longestConsecutive(new int[] {-2, -1, 0, 1, 2, 9}));
    }

    @Test
    @DisplayName("extreme values do not overflow when walking upwards")
    void extremeValues() {
        assertEquals(1, LongestConsecutiveSequence.longestConsecutive(
                new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE}));
    }

    @Test
    @DisplayName("matches a sort-based reference on randomised inputs")
    void matchesSortReference() {
        Random random = new Random(13579L);
        for (int trial = 0; trial < 500; trial++) {
            int[] nums = new int[random.nextInt(14)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(12) - 6;
            }
            assertEquals(reference(nums), LongestConsecutiveSequence.longestConsecutive(nums.clone()),
                    () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    /** Deduplicates, sorts, then measures the longest consecutive run. */
    private static int reference(int[] nums) {
        TreeSet<Integer> sorted = new TreeSet<>();
        for (int num : nums) {
            sorted.add(num);
        }
        int best = 0;
        int run = 0;
        Integer previous = null;
        for (int value : sorted) {
            run = (previous != null && value == previous + 1) ? run + 1 : 1;
            best = Math.max(best, run);
            previous = value;
        }
        return best;
    }
}
