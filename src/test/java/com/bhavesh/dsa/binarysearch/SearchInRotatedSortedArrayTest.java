package com.bhavesh.dsa.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SearchInRotatedSortedArrayTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(4, SearchInRotatedSortedArray.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
        assertEquals(-1, SearchInRotatedSortedArray.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3));
        assertEquals(-1, SearchInRotatedSortedArray.search(new int[] {1}, 0));
    }

    @Test
    @DisplayName("an unrotated array still works")
    void notRotated() {
        assertEquals(2, SearchInRotatedSortedArray.search(new int[] {1, 2, 3, 4, 5}, 3));
    }

    @Test
    @DisplayName("two elements, both rotations")
    void twoElements() {
        assertEquals(0, SearchInRotatedSortedArray.search(new int[] {3, 1}, 3));
        assertEquals(1, SearchInRotatedSortedArray.search(new int[] {3, 1}, 1));
        assertEquals(1, SearchInRotatedSortedArray.search(new int[] {1, 3}, 3));
    }

    @Test
    @DisplayName("empty and null arrays")
    void degenerate() {
        assertEquals(-1, SearchInRotatedSortedArray.search(new int[] {}, 1));
        assertEquals(-1, SearchInRotatedSortedArray.search(null, 1));
    }

    @Test
    @DisplayName("the pivot element itself")
    void pivotElement() {
        assertEquals(3, SearchInRotatedSortedArray.search(new int[] {5, 6, 7, 0, 1, 2, 4}, 0));
    }

    @Test
    @DisplayName("exhaustive: every rotation of every distinct array up to length 8")
    void exhaustiveOverRotations() {
        Random random = new Random(3333L);
        for (int trial = 0; trial < 300; trial++) {
            int size = 1 + random.nextInt(8);
            Set<Integer> distinct = new LinkedHashSet<>();
            while (distinct.size() < size) {
                distinct.add(random.nextInt(40) - 20);
            }
            int[] sorted = distinct.stream().mapToInt(Integer::intValue).sorted().toArray();
            for (int rotation = 0; rotation < size; rotation++) {
                int[] rotated = new int[size];
                for (int i = 0; i < size; i++) {
                    rotated[i] = sorted[(i + rotation) % size];
                }
                // Every present value must be found at its true index.
                for (int i = 0; i < size; i++) {
                    assertEquals(i, SearchInRotatedSortedArray.search(rotated, rotated[i]),
                            () -> "lookup failed in " + Arrays.toString(rotated));
                }
                // An absent value must report -1.
                int absent = 1000;
                assertEquals(-1, SearchInRotatedSortedArray.search(rotated, absent));
            }
        }
    }
}
