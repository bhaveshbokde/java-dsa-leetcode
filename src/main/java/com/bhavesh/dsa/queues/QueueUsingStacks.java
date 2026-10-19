package com.bhavesh.dsa.queues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 232 - Implement Queue using Stacks (Easy).
 *
 * <p>FIFO behaviour built from two LIFO structures.
 *
 * <p>The naive approach moves every element between the stacks on each
 * {@code pop}, giving O(n) per operation. The two-stack arrangement here is
 * O(1) <em>amortised</em>: pushes land on an inbound stack, and the outbound
 * stack is refilled only when it is empty. Because reversing the inbound stack
 * places the oldest element on top, subsequent pops are O(1) until it drains.
 *
 * <p>The amortised argument: each element is pushed to inbound once, moved to
 * outbound once, and popped once - three O(1) steps over its lifetime. A single
 * {@code pop} can cost O(n), but any sequence of n operations costs O(n) total.
 *
 * <p>Refilling only when outbound is empty is required for correctness, not
 * just speed. Transferring eagerly while outbound still holds elements would
 * interleave newer elements in front of older ones.
 *
 * <p>Amortised O(1) per operation; space O(n).
 */
public final class QueueUsingStacks {

    private final Deque<Integer> inbound = new ArrayDeque<>();
    private final Deque<Integer> outbound = new ArrayDeque<>();

    public void push(int x) {
        inbound.push(x);
    }

    public int pop() {
        shiftIfNeeded();
        return outbound.pop();
    }

    public int peek() {
        shiftIfNeeded();
        return outbound.peek();
    }

    public boolean empty() {
        return inbound.isEmpty() && outbound.isEmpty();
    }

    public int size() {
        return inbound.size() + outbound.size();
    }

    private void shiftIfNeeded() {
        if (outbound.isEmpty()) {
            if (inbound.isEmpty()) {
                throw new IllegalStateException("queue is empty");
            }
            while (!inbound.isEmpty()) {
                outbound.push(inbound.pop());
            }
        }
    }
}
