package com.bhavesh.dsa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.bhavesh.dsa.support.ListNode;
import com.bhavesh.dsa.support.Lists;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReorderListTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        ListNode even = Lists.of(1, 2, 3, 4);
        ReorderList.reorderList(even);
        assertArrayEquals(new int[] {1, 4, 2, 3}, Lists.toArray(even));

        ListNode odd = Lists.of(1, 2, 3, 4, 5);
        ReorderList.reorderList(odd);
        assertArrayEquals(new int[] {1, 5, 2, 4, 3}, Lists.toArray(odd));
    }

    @Test
    @DisplayName("lists of zero, one and two nodes are unchanged")
    void degenerate() {
        ReorderList.reorderList(null);

        ListNode one = Lists.of(1);
        ReorderList.reorderList(one);
        assertArrayEquals(new int[] {1}, Lists.toArray(one));

        ListNode two = Lists.of(1, 2);
        ReorderList.reorderList(two);
        assertArrayEquals(new int[] {1, 2}, Lists.toArray(two));
    }

    @Test
    @DisplayName("three nodes")
    void threeNodes() {
        ListNode list = Lists.of(1, 2, 3);
        ReorderList.reorderList(list);
        assertArrayEquals(new int[] {1, 3, 2}, Lists.toArray(list));
    }

    @Test
    @DisplayName("the result is acyclic, which the split guards against")
    void resultIsAcyclic() {
        // Lists.toArray would loop forever on a cycle, so reaching the
        // assertion at all proves termination; the explicit check documents why.
        ListNode list = Lists.of(1, 2, 3, 4, 5, 6);
        ReorderList.reorderList(list);
        org.junit.jupiter.api.Assertions.assertFalse(LinkedListCycle.hasCycle(list),
                "reordering must not create a cycle");
        assertArrayEquals(new int[] {1, 6, 2, 5, 3, 4}, Lists.toArray(list));
    }

    @Test
    @DisplayName("matches an index-based reference on randomised lengths")
    void matchesReference() {
        Random random = new Random(143143L);
        for (int trial = 0; trial < 500; trial++) {
            int length = random.nextInt(14);
            int[] values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = random.nextInt(100);
            }
            ListNode list = Lists.of(values);
            ReorderList.reorderList(list);
            assertArrayEquals(reference(values), Lists.toArray(list),
                    () -> "mismatch for " + Arrays.toString(values));
        }
    }

    /** Takes alternately from the front and the back. */
    private static int[] reference(int[] values) {
        int[] out = new int[values.length];
        int left = 0;
        int right = values.length - 1;
        int at = 0;
        boolean fromFront = true;
        while (left <= right) {
            out[at++] = fromFront ? values[left++] : values[right--];
            fromFront = !fromFront;
        }
        return out;
    }

    @Test
    @DisplayName("a long list is reordered iteratively without stack growth")
    void longList() {
        int[] values = new int[100_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        ListNode list = Lists.of(values);
        ReorderList.reorderList(list);
        int[] result = Lists.toArray(list);
        org.junit.jupiter.api.Assertions.assertEquals(values.length, result.length);
        org.junit.jupiter.api.Assertions.assertEquals(0, result[0]);
        org.junit.jupiter.api.Assertions.assertEquals(99_999, result[1]);
    }
}
