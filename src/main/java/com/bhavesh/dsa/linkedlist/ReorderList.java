package com.bhavesh.dsa.linkedlist;

import com.bhavesh.dsa.support.ListNode;

/**
 * LeetCode 143 - Reorder List (Medium).
 *
 * <p>Reorders {@code L0 → L1 → … → Ln} into
 * {@code L0 → Ln → L1 → Ln-1 → …}, in place.
 *
 * <p>The obvious solution copies node references into an {@link java.util.ArrayList}
 * and then relinks by index - O(n) time but O(n) space. The in-place version is
 * a <b>composition of three operations this repository already implements
 * separately</b>, which is what makes it worth studying:
 *
 * <ol>
 *   <li><b>Find the middle</b> with slow/fast pointers (the cycle-detection
 *       traversal, used here for its positional property).</li>
 *   <li><b>Reverse the second half</b> (LeetCode 206).</li>
 *   <li><b>Merge the two halves alternately</b> (the merge from LeetCode 21,
 *       with alternation instead of comparison).</li>
 * </ol>
 *
 * <p>The detail that matters: after splitting, the first half's tail must be
 * disconnected ({@code slow.next = null}). Leaving it attached means the
 * reversed second half still links back into the first, and the merge builds a
 * cycle - the list then appears to work until something traverses it.
 *
 * <p>For an odd length, the middle node stays with the first half, which is
 * what makes the alternating merge terminate cleanly.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class ReorderList {

    private ReorderList() {
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;   // 0, 1 or 2 nodes are already in the required order
        }

        // 1. Middle: slow lands on the last node of the first half.
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Detach, then reverse the second half.
        ListNode second = slow.next;
        slow.next = null;              // essential: prevents a cycle later
        ListNode previous = null;
        while (second != null) {
            ListNode next = second.next;
            second.next = previous;
            previous = second;
            second = next;
        }

        // 3. Merge alternately. The first half is never shorter than the second.
        ListNode first = head;
        ListNode reversed = previous;
        while (reversed != null) {
            ListNode firstNext = first.next;
            ListNode reversedNext = reversed.next;
            first.next = reversed;
            reversed.next = firstNext;
            first = firstNext;
            reversed = reversedNext;
        }
    }
}
