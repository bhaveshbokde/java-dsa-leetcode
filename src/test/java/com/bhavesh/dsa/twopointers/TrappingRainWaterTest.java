package com.bhavesh.dsa.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrappingRainWaterTest {

    @Test
    @DisplayName("LeetCode example traps 6 units")
    void leetCodeExample() {
        assertEquals(6, TrappingRainWater.trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    @Test
    @DisplayName("second LeetCode example")
    void secondExample() {
        assertEquals(9, TrappingRainWater.trap(new int[] {4, 2, 0, 3, 2, 5}));
    }

    @Test
    @DisplayName("monotonic profiles trap nothing")
    void monotonic() {
        assertEquals(0, TrappingRainWater.trap(new int[] {1, 2, 3, 4, 5}));
        assertEquals(0, TrappingRainWater.trap(new int[] {5, 4, 3, 2, 1}));
    }

    @Test
    @DisplayName("fewer than three bars cannot trap water")
    void tooShort() {
        assertEquals(0, TrappingRainWater.trap(new int[] {5, 2}));
        assertEquals(0, TrappingRainWater.trap(new int[] {}));
        assertEquals(0, TrappingRainWater.trap(null));
    }

    @Test
    @DisplayName("flat ground traps nothing")
    void flat() {
        assertEquals(0, TrappingRainWater.trap(new int[] {3, 3, 3, 3}));
    }

    @Test
    @DisplayName("a single deep basin")
    void singleBasin() {
        // Columns 1..3 are capped at min(5,5)=5, so they hold 5, 4 and 5 units.
        assertEquals(14, TrappingRainWater.trap(new int[] {5, 0, 1, 0, 5}));
    }

    @Test
    @DisplayName("the two-pointer and prefix-array forms agree on randomised inputs")
    void bothFormsAgree() {
        Random random = new Random(2025L);
        for (int trial = 0; trial < 600; trial++) {
            int[] height = new int[random.nextInt(15)];
            for (int i = 0; i < height.length; i++) {
                height[i] = random.nextInt(8);
            }
            assertEquals(TrappingRainWater.trapWithPrefixArrays(height.clone()),
                    TrappingRainWater.trap(height.clone()),
                    () -> "forms disagreed on " + Arrays.toString(height));
        }
    }

    @Test
    @DisplayName("matches an O(n^2) per-column reference")
    void matchesBruteForce() {
        Random random = new Random(777L);
        for (int trial = 0; trial < 300; trial++) {
            int[] height = new int[random.nextInt(12)];
            for (int i = 0; i < height.length; i++) {
                height[i] = random.nextInt(6);
            }
            assertEquals(bruteForce(height), TrappingRainWater.trap(height.clone()),
                    () -> "mismatch for " + Arrays.toString(height));
        }
    }

    private static int bruteForce(int[] height) {
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0;
            int rightMax = 0;
            for (int l = 0; l <= i; l++) {
                leftMax = Math.max(leftMax, height[l]);
            }
            for (int r = i; r < height.length; r++) {
                rightMax = Math.max(rightMax, height[r]);
            }
            water += Math.min(leftMax, rightMax) - height[i];
        }
        return water;
    }
}
