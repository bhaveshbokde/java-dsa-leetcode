package com.bhavesh.dsa.linkedlist;

import com.bhavesh.dsa.support.ListNode;

/**
 * LeetCode 19 - Remove Nth Node From End of List (Medium).
 *
 * <p>Removes the n-th node counting from the end, in **one pass**.
 *
 * <p>The two-pass solution - count the length, then walk to
 * {@code length - n} - is correct and arguably clearer. The follow-up asks for
 * one pass, which the gap technique provides: advance a lead pointer {@code n}
 * steps first, then move both until the lead reaches the end. The trailing
 * pointer is then exactly {@code n} from the end, because the gap between them
 * never changes.
 *
 * <p>Two details make this work cleanly:
 *
 * <ul>
 *   <li>A <b>dummy head</b> removes the special case of deleting the first
 *       node. Without it, removing the head requires a separate branch and a
 *       different return value - the usual source of bugs here.</li>
 *   <li>The trailing pointer starts at the dummy, so it stops at the node
 *       <em>before</em> the target, which is what deletion needs.</li>
 * </ul>
 *
 * <p>Time O(length), auxiliary space O(1).
 */
public final class RemoveNthFromEnd {

    private RemoveNthFromEnd() {
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            throw new IllegalArgumentException("the list must be non-empty and n at least 1");
        }
        ListNode dummy = new ListNode(0, head);
        ListNode lead = dummy;
        ListNode trail = dummy;

        // Open a gap of exactly n nodes.
        for (int step = 0; step < n; step++) {
            if (lead.next == null) {
                throw new IllegalArgumentException("n exceeds the length of the list");
            }
            lead = lead.next;
        }
        // Advance together; when lead is at the last node, trail is at the
        // predecessor of the target.
        while (lead.next != null) {
            lead = lead.next;
            trail = trail.next;
        }
        trail.next = trail.next.next;
        return dummy.next;
    }
}
