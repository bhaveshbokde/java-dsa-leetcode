package com.bhavesh.dsa.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MinStackTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        assertEquals(-3, stack.getMin());
        stack.pop();
        assertEquals(0, stack.top());
        assertEquals(-2, stack.getMin());
    }

    @Test
    @DisplayName("popping the minimum restores the previous minimum")
    void popRestoresPreviousMinimum() {
        MinStack stack = new MinStack();
        stack.push(5);
        stack.push(1);
        assertEquals(1, stack.getMin());
        stack.pop();
        assertEquals(5, stack.getMin());
    }

    @Test
    @DisplayName("duplicate minima survive a single pop")
    void duplicateMinima() {
        MinStack stack = new MinStack();
        stack.push(2);
        stack.push(2);
        stack.pop();
        // A '<' comparison when pushing would have lost this minimum.
        assertEquals(2, stack.getMin());
    }

    @Test
    @DisplayName("operations on an empty stack are rejected")
    void emptyStack() {
        MinStack stack = new MinStack();
        assertTrue(stack.isEmpty());
        assertThrows(IllegalStateException.class, stack::pop);
        assertThrows(IllegalStateException.class, stack::top);
        assertThrows(IllegalStateException.class, stack::getMin);
    }

    @Test
    @DisplayName("extreme values are handled")
    void extremeValues() {
        MinStack stack = new MinStack();
        stack.push(Integer.MAX_VALUE);
        stack.push(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, stack.getMin());
        stack.pop();
        assertEquals(Integer.MAX_VALUE, stack.getMin());
    }

    @Test
    @DisplayName("behaves like a plain deque plus a linear minimum scan, under random operations")
    void matchesReferenceUnderRandomOperations() {
        Random random = new Random(155155L);
        MinStack stack = new MinStack();
        Deque<Integer> reference = new ArrayDeque<>();
        for (int step = 0; step < 20_000; step++) {
            if (reference.isEmpty() || random.nextInt(3) != 0) {
                int value = random.nextInt(50) - 25;
                stack.push(value);
                reference.push(value);
            } else {
                stack.pop();
                reference.pop();
            }
            assertEquals(reference.size(), stack.size());
            if (!reference.isEmpty()) {
                assertEquals(reference.peek(), stack.top());
                assertEquals(reference.stream().min(Integer::compare).orElseThrow(), stack.getMin());
            }
        }
    }
}
