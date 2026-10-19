package com.bhavesh.dsa.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FindMinimumInRotatedSortedArrayTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(1, FindMinimumInRotatedSortedArray.findMin(new int[] {3, 4, 5, 1, 2}));
        assertEquals(0, FindMinimumInRotatedSortedArray.findMin(new int[] {4, 5, 6, 7, 0, 1, 2}));
        assertEquals(11, FindMinimumInRotatedSortedArray.findMin(new int[] {11, 13, 15, 17}));
    }

    @Test
    @DisplayName("an unrotated array returns its first element")
    void notRotated() {
        assertEquals(1, FindMinimumInRotatedSortedArray.findMin(new int[] {1, 2, 3, 4, 5}));
        assertEquals(0, FindMinimumInRotatedSortedArray.findMinIndex(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    @DisplayName("single and two element arrays, both rotations")
    void smallArrays() {
        assertEquals(5, FindMinimumInRotatedSortedArray.findMin(new int[] {5}));
        assertEquals(1, FindMinimumInRotatedSortedArray.findMin(new int[] {1, 2}));
        assertEquals(1, FindMinimumInRotatedSortedArray.findMin(new int[] {2, 1}));
    }

    @Test
    @DisplayName("rotation by exactly one position, at both ends")
    void singleStepRotations() {
        assertEquals(1, FindMinimumInRotatedSortedArray.findMin(new int[] {2, 3, 4, 5, 1}));
        assertEquals(1, FindMinimumInRotatedSortedArray.findMin(new int[] {5, 1, 2, 3, 4}));
    }

    @Test
    @DisplayName("negative values and extremes")
    void negativesAndExtremes() {
        assertEquals(-5, FindMinimumInRotatedSortedArray.findMin(new int[] {-1, 0, -5, -4, -3}));
        assertEquals(Integer.MIN_VALUE, FindMinimumInRotatedSortedArray.findMin(
                new int[] {0, Integer.MAX_VALUE, Integer.MIN_VALUE, -1}));
    }

    @Test
    @DisplayName("empty and null arrays are rejected")
    void degenerate() {
        assertThrows(IllegalArgumentException.class,
                () -> FindMinimumInRotatedSortedArray.findMin(new int[] {}));
        assertThrows(IllegalArgumentException.class,
                () -> FindMinimumInRotatedSortedArray.findMin(null));
    }

    @Test
    @DisplayName("exhaustive: every rotation of every distinct array up to length 9")
    void exhaustiveOverRotations() {
        Random random = new Random(153153L);
        for (int trial = 0; trial < 400; trial++) {
            int size = 1 + random.nextInt(9);
            Set<Integer> distinct = new LinkedHashSet<>();
            while (distinct.size() < size) {
                distinct.add(random.nextInt(60) - 30);
            }
            int[] sorted = distinct.stream().mapToInt(Integer::intValue).sorted().toArray();
            int expectedMin = sorted[0];
            for (int rotation = 0; rotation < size; rotation++) {
                int[] rotated = new int[size];
                for (int i = 0; i < size; i++) {
                    rotated[i] = sorted[(i + rotation) % size];
                }
                assertEquals(expectedMin, FindMinimumInRotatedSortedArray.findMin(rotated),
                        () -> "wrong minimum for " + Arrays.toString(rotated));
                int index = FindMinimumInRotatedSortedArray.findMinIndex(rotated);
                assertEquals(expectedMin, rotated[index],
                        () -> "index points at the wrong element for " + Arrays.toString(rotated));
            }
        }
    }
}
