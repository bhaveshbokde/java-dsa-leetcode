package com.bhavesh.dsa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhavesh.dsa.support.ListNode;
import com.bhavesh.dsa.support.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LinkedListCycleTest {

    @Test
    @DisplayName("LeetCode example: cycle entering at index 1")
    void cycleAtIndexOne() {
        ListNode head = Lists.withCycle(new int[] {3, 2, 0, -4}, 1);
        assertTrue(LinkedListCycle.hasCycle(head));
        assertEquals(2, LinkedListCycle.detectCycle(head).val);
    }

    @Test
    @DisplayName("cycle entering at the head")
    void cycleAtHead() {
        ListNode head = Lists.withCycle(new int[] {1, 2}, 0);
        assertTrue(LinkedListCycle.hasCycle(head));
        assertSame(head, LinkedListCycle.detectCycle(head));
    }

    @Test
    @DisplayName("a single node pointing at itself")
    void selfLoop() {
        ListNode head = Lists.withCycle(new int[] {1}, 0);
        assertTrue(LinkedListCycle.hasCycle(head));
        assertSame(head, LinkedListCycle.detectCycle(head));
    }

    @Test
    @DisplayName("acyclic lists report no cycle")
    void acyclic() {
        assertFalse(LinkedListCycle.hasCycle(Lists.of(1, 2, 3, 4)));
        assertNull(LinkedListCycle.detectCycle(Lists.of(1, 2, 3, 4)));
        assertFalse(LinkedListCycle.hasCycle(Lists.of(1)));
        assertNull(LinkedListCycle.detectCycle(Lists.of(1)));
    }

    @Test
    @DisplayName("empty list")
    void empty() {
        assertFalse(LinkedListCycle.hasCycle(null));
        assertNull(LinkedListCycle.detectCycle(null));
    }

    @Test
    @DisplayName("the cycle entry is found for every tail position")
    void entryFoundForEveryTailPosition() {
        for (int size = 1; size <= 10; size++) {
            int[] values = new int[size];
            for (int i = 0; i < size; i++) {
                values[i] = i;
            }
            for (int entry = 0; entry < size; entry++) {
                ListNode head = Lists.withCycle(values, entry);
                ListNode found = LinkedListCycle.detectCycle(head);
                final int expected = entry;
                assertEquals(expected, found.val,
                        () -> "wrong entry for size " + values.length + " entry " + expected);
            }
        }
    }

    @Test
    @DisplayName("a long cycle is detected without quadratic behaviour")
    void longCycle() {
        int[] values = new int[100_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        ListNode head = Lists.withCycle(values, 50_000);
        assertTrue(LinkedListCycle.hasCycle(head));
        assertEquals(50_000, LinkedListCycle.detectCycle(head).val);
    }
}
