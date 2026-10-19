package com.bhavesh.dsa.linkedlist;

import com.bhavesh.dsa.support.ListNode;

/**
 * LeetCode 141 and 142 - Linked List Cycle I and II. Floyd's tortoise and hare.
 *
 * <p>Detects a cycle and returns the node where it begins, in O(1) space. The
 * hash-set solution is simpler and also O(n) time, but costs O(n) space; the
 * point of this problem is avoiding that.
 *
 * <p><b>Detection.</b> A slow pointer moves one step per iteration and a fast
 * pointer two. If a cycle exists, both are eventually inside it, and the gap
 * between them closes by exactly one each iteration, so the fast pointer cannot
 * step over the slow one without landing on it. Termination follows because the
 * gap is bounded by the cycle length.
 *
 * <p><b>Finding the entry.</b> Let {@code L} be the distance from the head to
 * the cycle entry, {@code C} the cycle length and {@code k} the distance from
 * the entry to the meeting point. When they meet, slow has travelled
 * {@code L + k} and fast {@code 2(L + k)}, and their difference is a whole
 * number of laps: {@code L + k = nC}, hence {@code L = nC - k}. Walking
 * {@code L} steps from the head and {@code L} steps from the meeting point
 * therefore arrives at the same node - the entry. That is why the second phase
 * advances both pointers one step at a time.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class LinkedListCycle {

    private LinkedListCycle() {
    }

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /** Returns the first node of the cycle, or {@code null} if there is none. */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode walker = head;
                while (walker != slow) {
                    walker = walker.next;
                    slow = slow.next;
                }
                return walker;
            }
        }
        return null;
    }
}
