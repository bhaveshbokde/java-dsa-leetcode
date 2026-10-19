package com.bhavesh.dsa.linkedlist;

import com.bhavesh.dsa.support.ListNode;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 23 - Merge k Sorted Lists (Hard).
 *
 * <p>Merges {@code k} sorted linked lists into one sorted list.
 *
 * <p>The tempting approach - merge list 1 with list 2, then the result with
 * list 3, and so on - is <b>O(kN)</b> for a total of N nodes, because the
 * accumulating result is re-traversed on every merge. With k = 10^4 that is
 * quadratic in k and far too slow. Recognising why it is slow is more than half
 * the problem.
 *
 * <p>Two approaches reach O(N log k), and they are worth knowing as a pair:
 *
 * <ul>
 *   <li><b>Min-heap over the k heads.</b> The smallest unconsumed node across
 *       all lists is always one of the k current heads, so a heap of size k
 *       yields it in O(log k). Each of the N nodes is pushed and popped once.</li>
 *   <li><b>Pairwise tournament merging.</b> Merge lists in pairs, halving k
 *       each round. Each of the log k rounds touches all N nodes once.</li>
 * </ul>
 *
 * <p>The heap version is implemented here because it generalises to streaming
 * input, where the full set of lists is not available up front. The tournament
 * version uses O(1) extra space against the heap's O(k), which is the trade.
 *
 * <p>Only the <b>heads</b> are ever in the heap - never whole lists - which is
 * what keeps the space at O(k) rather than O(N).
 *
 * <p>A dummy head removes the first-append special case, exactly as in
 * {@link MergeTwoSortedLists}.
 *
 * <p>Time O(N log k), auxiliary space O(k).
 */
public final class MergeKSortedLists {

    private MergeKSortedLists() {
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        Queue<ListNode> frontier = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for (ListNode head : lists) {
            if (head != null) {         // skip empty lists; the heap rejects null
                frontier.add(head);
            }
        }

        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (!frontier.isEmpty()) {
            ListNode smallest = frontier.poll();
            tail.next = smallest;
            tail = smallest;
            if (smallest.next != null) {
                frontier.add(smallest.next);
            }
        }
        // The last appended node may still link into its original list.
        tail.next = null;
        return dummy.next;
    }

    /**
     * Pairwise tournament variant: merge lists in pairs, halving k each round.
     *
     * <p>Time O(N log k), auxiliary space O(1) beyond the input array - no heap.
     */
    public static ListNode mergeKListsPairwise(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode[] working = lists.clone();
        int remaining = working.length;
        while (remaining > 1) {
            int write = 0;
            for (int i = 0; i < remaining; i += 2) {
                ListNode second = (i + 1 < remaining) ? working[i + 1] : null;
                working[write++] = MergeTwoSortedLists.mergeTwoLists(working[i], second);
            }
            remaining = write;
        }
        return working[0];
    }
}
