package com.bhavesh.dsa.linkedlist;

import com.bhavesh.dsa.support.ListNode;

/**
 * LeetCode 21 - Merge Two Sorted Lists (Easy).
 *
 * <p>Splices two sorted lists into one sorted list by relinking existing nodes,
 * allocating nothing except a single dummy head.
 *
 * <p>The dummy node is the point of the exercise. Without it, the first
 * iteration is a special case - there is no tail to attach to yet - and the
 * resulting null checks are where the bugs live. With it, every iteration is
 * identical and the real head is simply {@code dummy.next}.
 *
 * <p>Taking from the left list when the two fronts are equal keeps the merge
 * stable, which matters when this is reused inside merge sort.
 *
 * <p>Attaching the remaining list wholesale at the end is correct because it is
 * already sorted and every one of its values is at least the last value
 * appended.
 *
 * <p>Time O(m + n), auxiliary space O(1).
 */
public final class MergeTwoSortedLists {

    private MergeTwoSortedLists() {
    }

    public static ListNode mergeTwoLists(ListNode first, ListNode second) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        ListNode left = first;
        ListNode right = second;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        tail.next = (left != null) ? left : right;
        return dummy.next;
    }
}
