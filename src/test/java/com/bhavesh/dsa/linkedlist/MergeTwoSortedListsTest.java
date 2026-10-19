package com.bhavesh.dsa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.bhavesh.dsa.support.Lists;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MergeTwoSortedListsTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        assertArrayEquals(new int[] {1, 1, 2, 3, 4, 4},
                Lists.toArray(MergeTwoSortedLists.mergeTwoLists(Lists.of(1, 2, 4), Lists.of(1, 3, 4))));
    }

    @Test
    @DisplayName("both lists empty")
    void bothEmpty() {
        assertNull(MergeTwoSortedLists.mergeTwoLists(null, null));
    }

    @Test
    @DisplayName("one list empty")
    void oneEmpty() {
        assertArrayEquals(new int[] {0}, Lists.toArray(MergeTwoSortedLists.mergeTwoLists(null, Lists.of(0))));
        assertArrayEquals(new int[] {5}, Lists.toArray(MergeTwoSortedLists.mergeTwoLists(Lists.of(5), null)));
    }

    @Test
    @DisplayName("disjoint ranges append wholesale")
    void disjointRanges() {
        assertArrayEquals(new int[] {1, 2, 3, 7, 8, 9},
                Lists.toArray(MergeTwoSortedLists.mergeTwoLists(Lists.of(1, 2, 3), Lists.of(7, 8, 9))));
    }

    @Test
    @DisplayName("lists of very different lengths")
    void unevenLengths() {
        assertArrayEquals(new int[] {1, 2, 3, 4, 5},
                Lists.toArray(MergeTwoSortedLists.mergeTwoLists(Lists.of(3), Lists.of(1, 2, 4, 5))));
    }

    @Test
    @DisplayName("all duplicates")
    void allDuplicates() {
        assertArrayEquals(new int[] {2, 2, 2, 2},
                Lists.toArray(MergeTwoSortedLists.mergeTwoLists(Lists.of(2, 2), Lists.of(2, 2))));
    }

    @Test
    @DisplayName("matches a concatenate-and-sort reference on randomised sorted inputs")
    void matchesReference() {
        Random random = new Random(2121L);
        for (int trial = 0; trial < 500; trial++) {
            int[] a = new int[random.nextInt(8)];
            int[] b = new int[random.nextInt(8)];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(20);
            }
            for (int i = 0; i < b.length; i++) {
                b[i] = random.nextInt(20);
            }
            Arrays.sort(a);
            Arrays.sort(b);
            int[] expected = new int[a.length + b.length];
            System.arraycopy(a, 0, expected, 0, a.length);
            System.arraycopy(b, 0, expected, a.length, b.length);
            Arrays.sort(expected);
            assertArrayEquals(expected,
                    Lists.toArray(MergeTwoSortedLists.mergeTwoLists(Lists.of(a), Lists.of(b))),
                    () -> "mismatch " + Arrays.toString(a) + " + " + Arrays.toString(b));
        }
    }
}
