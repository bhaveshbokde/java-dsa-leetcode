package com.bhavesh.dsa.linkedlist;

import com.bhavesh.dsa.support.ListNode;

/**
 * LeetCode 206 - Reverse Linked List (Easy).
 *
 * <p>The canonical pointer-manipulation exercise, and the one most often got
 * wrong by losing the rest of the list.
 *
 * <p>Three pointers are required, not two. Before {@code current.next} is
 * redirected backwards, the node it currently points to must be saved -
 * otherwise the remainder of the list becomes unreachable the instant the link
 * is overwritten. The order of the four statements inside the loop is therefore
 * fixed and not a matter of taste.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class ReverseLinkedList {

    private ReverseLinkedList() {
    }

    public static ListNode reverse(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;   // save before the link is destroyed
            current.next = previous;        // redirect
            previous = current;             // advance the trailing pointer
            current = next;                 // advance the leading pointer
        }
        return previous;
    }

    /**
     * Recursive form. Equivalent in time but O(n) stack, so it will overflow on
     * a list of a few hundred thousand nodes where the iterative form is fine.
     */
    public static ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
