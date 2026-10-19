package com.bhavesh.dsa.monotonic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LargestRectangleInHistogramTest {

    @Test
    @DisplayName("LeetCode example: area 10")
    void leetCodeExample() {
        assertEquals(10, LargestRectangleInHistogram.largestRectangleArea(new int[] {2, 1, 5, 6, 2, 3}));
    }

    @Test
    @DisplayName("second LeetCode example")
    void secondExample() {
        assertEquals(4, LargestRectangleInHistogram.largestRectangleArea(new int[] {2, 4}));
    }

    @Test
    @DisplayName("increasing heights")
    void increasing() {
        // Candidates: 3 wide x 3 high = 9, 2 wide x 4 high = 8, 1 wide x 5 high = 5.
        assertEquals(9, LargestRectangleInHistogram.largestRectangleArea(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    @DisplayName("decreasing heights")
    void decreasing() {
        assertEquals(9, LargestRectangleInHistogram.largestRectangleArea(new int[] {5, 4, 3, 2, 1}));
    }

    @Test
    @DisplayName("uniform heights span the whole histogram")
    void uniform() {
        assertEquals(12, LargestRectangleInHistogram.largestRectangleArea(new int[] {3, 3, 3, 3}));
    }

    @Test
    @DisplayName("zero heights contribute nothing")
    void zeroes() {
        assertEquals(0, LargestRectangleInHistogram.largestRectangleArea(new int[] {0, 0, 0}));
    }

    @Test
    @DisplayName("single bar, empty and null")
    void degenerate() {
        assertEquals(7, LargestRectangleInHistogram.largestRectangleArea(new int[] {7}));
        assertEquals(0, LargestRectangleInHistogram.largestRectangleArea(new int[] {}));
        assertEquals(0, LargestRectangleInHistogram.largestRectangleArea(null));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(848484L);
        for (int trial = 0; trial < 600; trial++) {
            int[] heights = new int[random.nextInt(13)];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = random.nextInt(9);
            }
            assertEquals(bruteForce(heights),
                    LargestRectangleInHistogram.largestRectangleArea(heights.clone()),
                    () -> "mismatch for " + Arrays.toString(heights));
        }
    }

    private static int bruteForce(int[] heights) {
        int best = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                best = Math.max(best, minHeight * (j - i + 1));
            }
        }
        return best;
    }
}
