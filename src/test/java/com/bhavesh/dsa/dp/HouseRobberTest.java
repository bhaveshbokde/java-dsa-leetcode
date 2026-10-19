package com.bhavesh.dsa.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HouseRobberTest {

    @Test
    @DisplayName("LeetCode 198 examples")
    void leetCodeExamples() {
        assertEquals(4, HouseRobber.rob(new int[] {1, 2, 3, 1}));
        assertEquals(12, HouseRobber.rob(new int[] {2, 7, 9, 3, 1}));
    }

    @Test
    @DisplayName("a greedy largest-first choice would fail here")
    void greedyWouldFail() {
        // Greedy takes 9 then is blocked around it; the optimum is 2 + 9 + 1.
        assertEquals(12, HouseRobber.rob(new int[] {2, 7, 9, 3, 1}));
        // Alternating indices would take 2 + 1 = 3; the optimum takes both 2s.
        assertEquals(4, HouseRobber.rob(new int[] {2, 1, 1, 2}));
    }

    @Test
    @DisplayName("single house and empty input")
    void degenerate() {
        assertEquals(5, HouseRobber.rob(new int[] {5}));
        assertEquals(0, HouseRobber.rob(new int[] {}));
        assertEquals(0, HouseRobber.rob(null));
    }

    @Test
    @DisplayName("two houses: take the larger")
    void twoHouses() {
        assertEquals(7, HouseRobber.rob(new int[] {3, 7}));
    }

    @Test
    @DisplayName("all zeros")
    void allZeros() {
        assertEquals(0, HouseRobber.rob(new int[] {0, 0, 0}));
    }

    @Test
    @DisplayName("LeetCode 213 circular examples")
    void circularExamples() {
        assertEquals(3, HouseRobber.robCircular(new int[] {2, 3, 2}));
        assertEquals(4, HouseRobber.robCircular(new int[] {1, 2, 3, 1}));
        assertEquals(3, HouseRobber.robCircular(new int[] {1, 2, 3}));
    }

    @Test
    @DisplayName("circular: a single house has no neighbour to conflict with")
    void circularSingleHouse() {
        assertEquals(9, HouseRobber.robCircular(new int[] {9}));
        assertEquals(0, HouseRobber.robCircular(new int[] {}));
    }

    @Test
    @DisplayName("matches an exhaustive subset search on randomised inputs")
    void matchesExhaustiveSearch() {
        Random random = new Random(198198L);
        for (int trial = 0; trial < 500; trial++) {
            int[] nums = new int[random.nextInt(14)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(30);
            }
            assertEquals(exhaustive(nums, false), HouseRobber.rob(nums.clone()),
                    () -> "linear mismatch for " + Arrays.toString(nums));
            assertEquals(exhaustive(nums, true), HouseRobber.robCircular(nums.clone()),
                    () -> "circular mismatch for " + Arrays.toString(nums));
        }
    }

    /** Tries every subset and keeps the best valid one. */
    private static int exhaustive(int[] nums, boolean circular) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int best = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            boolean valid = true;
            for (int i = 0; i + 1 < n && valid; i++) {
                if ((mask & (1 << i)) != 0 && (mask & (1 << (i + 1))) != 0) {
                    valid = false;
                }
            }
            if (valid && circular && n > 1
                    && (mask & 1) != 0 && (mask & (1 << (n - 1))) != 0) {
                valid = false;
            }
            if (!valid) {
                continue;
            }
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += nums[i];
                }
            }
            best = Math.max(best, sum);
        }
        return best;
    }
}
