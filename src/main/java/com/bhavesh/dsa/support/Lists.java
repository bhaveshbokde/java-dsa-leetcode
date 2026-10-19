package com.bhavesh.dsa.support;

import java.util.ArrayList;
import java.util.List;

/**
 * Conversions between {@link ListNode} chains and Java arrays/lists.
 *
 * <p>Exists so that linked-list tests can state inputs and expectations as
 * arrays. Hand-building a chain inside each test obscures what is being
 * asserted and is a reliable source of test bugs.
 */
public final class Lists {

    private Lists() {
    }

    /** Builds a chain from {@code values}; an empty array yields {@code null}. */
    public static ListNode of(int... values) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    /** Flattens a chain back to an array. Does not terminate on a cyclic list. */
    public static int[] toArray(ListNode head) {
        List<Integer> out = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next) {
            out.add(node.val);
        }
        int[] result = new int[out.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = out.get(i);
        }
        return result;
    }

    /**
     * Builds a chain whose tail links back to index {@code cycleIndex}.
     * A negative index produces an acyclic chain.
     */
    public static ListNode withCycle(int[] values, int cycleIndex) {
        ListNode head = of(values);
        if (cycleIndex < 0 || head == null) {
            return head;
        }
        ListNode entry = head;
        for (int i = 0; i < cycleIndex; i++) {
            entry = entry.next;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = entry;
        return head;
    }
}
