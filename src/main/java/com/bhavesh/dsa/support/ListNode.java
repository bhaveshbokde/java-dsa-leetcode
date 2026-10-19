package com.bhavesh.dsa.support;

/**
 * Singly linked list node, matching the shape LeetCode supplies for list problems.
 *
 * <p>The field is public and mutable on purpose. Encapsulating it behind getters
 * would make every pointer-manipulation solution read differently from the form
 * an interviewer expects, and pointer manipulation is the entire point of these
 * problems.
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
