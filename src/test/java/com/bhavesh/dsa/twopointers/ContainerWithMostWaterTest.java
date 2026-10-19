package com.bhavesh.dsa.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContainerWithMostWaterTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        assertEquals(49, ContainerWithMostWater.maxArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    @Test
    @DisplayName("two lines")
    void twoLines() {
        assertEquals(1, ContainerWithMostWater.maxArea(new int[] {1, 1}));
    }

    @Test
    @DisplayName("fewer than two lines holds no water")
    void degenerate() {
        assertEquals(0, ContainerWithMostWater.maxArea(new int[] {5}));
        assertEquals(0, ContainerWithMostWater.maxArea(new int[] {}));
        assertEquals(0, ContainerWithMostWater.maxArea(null));
    }

    @Test
    @DisplayName("zero heights")
    void zeroHeights() {
        assertEquals(0, ContainerWithMostWater.maxArea(new int[] {0, 0, 0}));
    }

    @Test
    @DisplayName("the tallest pair is not always the answer; width matters")
    void widthBeatsHeight() {
        // The two 100s are adjacent: width 1, area 100.
        // The widest pair (50 at index 0, 100 at index 4): width 4, height 50, area 200.
        // Greedily pairing the two tallest bars would return 100 and be wrong.
        assertEquals(200, ContainerWithMostWater.maxArea(new int[] {50, 1, 1, 100, 100}));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(1123L);
        for (int trial = 0; trial < 500; trial++) {
            int[] height = new int[random.nextInt(12)];
            for (int i = 0; i < height.length; i++) {
                height[i] = random.nextInt(20);
            }
            assertEquals(bruteForce(height), ContainerWithMostWater.maxArea(height.clone()),
                    () -> "mismatch for " + Arrays.toString(height));
        }
    }

    private static int bruteForce(int[] height) {
        int best = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                best = Math.max(best, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return best;
    }
}
