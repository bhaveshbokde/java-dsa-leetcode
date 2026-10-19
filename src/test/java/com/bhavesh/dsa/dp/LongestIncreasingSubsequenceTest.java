package com.bhavesh.dsa.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LongestIncreasingSubsequenceTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(4, LongestIncreasingSubsequence.lengthOfLis(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
        assertEquals(4, LongestIncreasingSubsequence.lengthOfLis(new int[] {0, 1, 0, 3, 2, 3}));
        assertEquals(1, LongestIncreasingSubsequence.lengthOfLis(new int[] {7, 7, 7, 7, 7, 7, 7}));
    }

    @Test
    @DisplayName("strictly increasing input: the whole array")
    void strictlyIncreasing() {
        assertEquals(5, LongestIncreasingSubsequence.lengthOfLis(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    @DisplayName("strictly decreasing input: length one")
    void strictlyDecreasing() {
        assertEquals(1, LongestIncreasingSubsequence.lengthOfLis(new int[] {5, 4, 3, 2, 1}));
    }

    @Test
    @DisplayName("duplicates do not count as increasing")
    void duplicatesAreNotIncreasing() {
        assertEquals(2, LongestIncreasingSubsequence.lengthOfLis(new int[] {1, 1, 2, 2}));
    }

    @Test
    @DisplayName("single element, empty and null")
    void degenerate() {
        assertEquals(1, LongestIncreasingSubsequence.lengthOfLis(new int[] {4}));
        assertEquals(0, LongestIncreasingSubsequence.lengthOfLis(new int[] {}));
        assertEquals(0, LongestIncreasingSubsequence.lengthOfLis(null));
    }

    @Test
    @DisplayName("negatives and extreme values")
    void negativesAndExtremes() {
        assertEquals(3, LongestIncreasingSubsequence.lengthOfLis(
                new int[] {Integer.MIN_VALUE, -5, Integer.MAX_VALUE}));
    }

    @Test
    @DisplayName("the two implementations agree on randomised inputs")
    void variantsAgree() {
        Random random = new Random(300300L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[random.nextInt(16)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(15) - 7;
            }
            assertEquals(LongestIncreasingSubsequence.lengthOfLisQuadratic(nums.clone()),
                    LongestIncreasingSubsequence.lengthOfLis(nums.clone()),
                    () -> "variants disagreed on " + Arrays.toString(nums));
        }
    }

    @Test
    @DisplayName("matches an exhaustive subsequence search on small inputs")
    void matchesExhaustiveSearch() {
        Random random = new Random(900300L);
        for (int trial = 0; trial < 300; trial++) {
            int[] nums = new int[random.nextInt(13)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(8);
            }
            assertEquals(exhaustive(nums), LongestIncreasingSubsequence.lengthOfLis(nums.clone()),
                    () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    private static int exhaustive(int[] nums) {
        int n = nums.length;
        int best = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            int previous = Integer.MIN_VALUE;
            int count = 0;
            boolean increasing = true;
            for (int i = 0; i < n && increasing; i++) {
                if ((mask & (1 << i)) == 0) {
                    continue;
                }
                if (nums[i] <= previous) {
                    increasing = false;
                } else {
                    previous = nums[i];
                    count++;
                }
            }
            if (increasing) {
                best = Math.max(best, count);
            }
        }
        return best;
    }
}
