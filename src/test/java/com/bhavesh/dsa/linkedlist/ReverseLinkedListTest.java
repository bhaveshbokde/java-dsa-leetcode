package com.bhavesh.dsa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.bhavesh.dsa.support.ListNode;
import com.bhavesh.dsa.support.Lists;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReverseLinkedListTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        ListNode reversed = ReverseLinkedList.reverse(Lists.of(1, 2, 3, 4, 5));
        assertArrayEquals(new int[] {5, 4, 3, 2, 1}, Lists.toArray(reversed));
    }

    @Test
    @DisplayName("empty and single-node lists")
    void degenerate() {
        assertNull(ReverseLinkedList.reverse(null));
        assertArrayEquals(new int[] {1}, Lists.toArray(ReverseLinkedList.reverse(Lists.of(1))));
    }

    @Test
    @DisplayName("two nodes")
    void twoNodes() {
        assertArrayEquals(new int[] {2, 1}, Lists.toArray(ReverseLinkedList.reverse(Lists.of(1, 2))));
    }

    @Test
    @DisplayName("the old head becomes a proper tail with a null next")
    void oldHeadBecomesTail() {
        ListNode head = Lists.of(1, 2, 3);
        ListNode reversed = ReverseLinkedList.reverse(head);
        // head is now the last node; its next must be null or toArray would loop.
        assertNull(head.next);
        assertArrayEquals(new int[] {3, 2, 1}, Lists.toArray(reversed));
    }

    @Test
    @DisplayName("the recursive form agrees with the iterative one")
    void formsAgree() {
        Random random = new Random(206206L);
        for (int trial = 0; trial < 300; trial++) {
            int[] values = new int[random.nextInt(12)];
            for (int i = 0; i < values.length; i++) {
                values[i] = random.nextInt(100);
            }
            int[] iterative = Lists.toArray(ReverseLinkedList.reverse(Lists.of(values)));
            int[] recursive = Lists.toArray(ReverseLinkedList.reverseRecursive(Lists.of(values)));
            assertArrayEquals(iterative, recursive, () -> "forms disagreed on " + Arrays.toString(values));
        }
    }

    @Test
    @DisplayName("reversing twice restores the original order")
    void doubleReversalIsIdentity() {
        int[] values = {3, 1, 4, 1, 5, 9, 2, 6};
        ListNode once = ReverseLinkedList.reverse(Lists.of(values));
        ListNode twice = ReverseLinkedList.reverse(once);
        assertArrayEquals(values, Lists.toArray(twice));
    }

    @Test
    @DisplayName("the iterative form handles a list far deeper than the recursion limit")
    void largeListIterative() {
        int[] values = new int[100_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        int[] reversed = Lists.toArray(ReverseLinkedList.reverse(Lists.of(values)));
        assertEquals(values.length, reversed.length);
        assertEquals(values.length - 1, reversed[0], "the former tail must now be the head");
        assertEquals(0, reversed[reversed.length - 1], "the former head must now be the tail");
    }
}
