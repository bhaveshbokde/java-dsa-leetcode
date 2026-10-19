package com.bhavesh.dsa.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 155 - Min Stack (Medium).
 *
 * <p>A stack that also reports its minimum in O(1).
 *
 * <p>Scanning for the minimum on demand is O(n) per call. Caching a single
 * minimum field fails on {@code pop}: once the minimum is removed there is no
 * way to recover the previous one.
 *
 * <p>The fix is to store history rather than a single value. A parallel stack
 * holds, for each position, the minimum of the stack up to and including it.
 * Because that stack is pushed and popped in lockstep with the main one, its
 * top is always the current minimum, and popping restores the previous minimum
 * automatically.
 *
 * <p>Pushing on {@code <=} rather than {@code <} matters when the minimum value
 * appears more than once: with {@code <}, popping one copy would discard a
 * minimum that is still present in the stack.
 *
 * <p>Every operation O(1); space O(n).
 */
public final class MinStack {

    private final Deque<Integer> values = new ArrayDeque<>();
    private final Deque<Integer> minima = new ArrayDeque<>();

    public void push(int val) {
        values.push(val);
        if (minima.isEmpty() || val <= minima.peek()) {
            minima.push(val);
        } else {
            minima.push(minima.peek());
        }
    }

    public void pop() {
        requireNonEmpty();
        values.pop();
        minima.pop();
    }

    public int top() {
        requireNonEmpty();
        return values.peek();
    }

    public int getMin() {
        requireNonEmpty();
        return minima.peek();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public int size() {
        return values.size();
    }

    private void requireNonEmpty() {
        if (values.isEmpty()) {
            throw new IllegalStateException("stack is empty");
        }
    }
}
