package com.bhavesh.dsa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.bhavesh.dsa.support.ListNode;
import com.bhavesh.dsa.support.Lists;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MergeKSortedListsTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        ListNode[] lists = {Lists.of(1, 4, 5), Lists.of(1, 3, 4), Lists.of(2, 6)};
        assertArrayEquals(new int[] {1, 1, 2, 3, 4, 4, 5, 6},
                Lists.toArray(MergeKSortedLists.mergeKLists(lists)));
    }

    @Test
    @DisplayName("empty array and array of empty lists")
    void degenerate() {
        assertNull(MergeKSortedLists.mergeKLists(new ListNode[0]));
        assertNull(MergeKSortedLists.mergeKLists(null));
        assertNull(MergeKSortedLists.mergeKLists(new ListNode[] {null}));
        assertNull(MergeKSortedLists.mergeKLists(new ListNode[] {null, null}));
    }

    @Test
    @DisplayName("some lists empty, some populated")
    void mixedEmptyAndPopulated() {
        ListNode[] lists = {null, Lists.of(1, 3), null, Lists.of(2)};
        assertArrayEquals(new int[] {1, 2, 3},
                Lists.toArray(MergeKSortedLists.mergeKLists(lists)));
    }

    @Test
    @DisplayName("single list is returned unchanged")
    void singleList() {
        assertArrayEquals(new int[] {1, 2, 3},
                Lists.toArray(MergeKSortedLists.mergeKLists(new ListNode[] {Lists.of(1, 2, 3)})));
    }

    @Test
    @DisplayName("disjoint ranges")
    void disjointRanges() {
        ListNode[] lists = {Lists.of(7, 8), Lists.of(1, 2), Lists.of(4, 5)};
        assertArrayEquals(new int[] {1, 2, 4, 5, 7, 8},
                Lists.toArray(MergeKSortedLists.mergeKLists(lists)));
    }

    @Test
    @DisplayName("all duplicates")
    void allDuplicates() {
        ListNode[] lists = {Lists.of(2, 2), Lists.of(2), Lists.of(2, 2)};
        assertArrayEquals(new int[] {2, 2, 2, 2, 2},
                Lists.toArray(MergeKSortedLists.mergeKLists(lists)));
    }

    @Test
    @DisplayName("the result terminates: the final node's next is nulled")
    void resultTerminates() {
        // Without the explicit tail.next = null the last node could still link
        // into the remainder of its original list.
        ListNode[] lists = {Lists.of(1, 5), Lists.of(2)};
        int[] result = Lists.toArray(MergeKSortedLists.mergeKLists(lists));
        assertArrayEquals(new int[] {1, 2, 5}, result);
    }

    @Test
    @DisplayName("both strategies agree with a flatten-and-sort reference")
    void bothStrategiesMatchReference() {
        Random random = new Random(2323L);
        for (int trial = 0; trial < 400; trial++) {
            int k = random.nextInt(6);
            int[][] values = new int[k][];
            for (int i = 0; i < k; i++) {
                int len = random.nextInt(5);
                int[] v = new int[len];
                for (int j = 0; j < len; j++) {
                    v[j] = random.nextInt(20) - 10;
                }
                Arrays.sort(v);
                values[i] = v;
            }
            int[] expected = Arrays.stream(values).flatMapToInt(Arrays::stream).sorted().toArray();

            ListNode[] heapInput = new ListNode[k];
            ListNode[] pairInput = new ListNode[k];
            for (int i = 0; i < k; i++) {
                heapInput[i] = Lists.of(values[i]);
                pairInput[i] = Lists.of(values[i]);
            }
            assertArrayEquals(expected, Lists.toArray(MergeKSortedLists.mergeKLists(heapInput)),
                    () -> "heap mismatch for " + Arrays.deepToString(values));
            assertArrayEquals(expected,
                    Lists.toArray(MergeKSortedLists.mergeKListsPairwise(pairInput)),
                    () -> "pairwise mismatch for " + Arrays.deepToString(values));
        }
    }

    @Test
    @DisplayName("many lists, which would be quadratic under sequential merging")
    void manyLists() {
        int k = 2000;
        int perList = 20;
        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            int[] v = new int[perList];
            for (int j = 0; j < perList; j++) {
                v[j] = i + j * k;
            }
            lists[i] = Lists.of(v);
        }
        int[] result = Lists.toArray(MergeKSortedLists.mergeKLists(lists));
        org.junit.jupiter.api.Assertions.assertEquals(k * perList, result.length);
        for (int i = 1; i < result.length; i++) {
            org.junit.jupiter.api.Assertions.assertTrue(result[i - 1] <= result[i],
                    "output must be sorted");
        }
    }
}
