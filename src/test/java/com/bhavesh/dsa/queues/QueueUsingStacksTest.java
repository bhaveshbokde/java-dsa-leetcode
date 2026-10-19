package com.bhavesh.dsa.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueueUsingStacksTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.push(1);
        queue.push(2);
        assertEquals(1, queue.peek());
        assertEquals(1, queue.pop());
        assertFalse(queue.empty());
        assertEquals(2, queue.pop());
        assertTrue(queue.empty());
    }

    @Test
    @DisplayName("pushes interleaved with pops keep FIFO order")
    void interleavedOperations() {
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.push(1);
        queue.push(2);
        assertEquals(1, queue.pop());
        queue.push(3);
        // 3 must come after 2, even though it was pushed after the first drain.
        assertEquals(2, queue.pop());
        assertEquals(3, queue.pop());
    }

    @Test
    @DisplayName("operations on an empty queue are rejected")
    void emptyQueue() {
        QueueUsingStacks queue = new QueueUsingStacks();
        assertTrue(queue.empty());
        assertThrows(IllegalStateException.class, queue::pop);
        assertThrows(IllegalStateException.class, queue::peek);
    }

    @Test
    @DisplayName("behaves identically to java.util.ArrayDeque under random operations")
    void matchesJdkQueue() {
        Random random = new Random(232232L);
        QueueUsingStacks queue = new QueueUsingStacks();
        Queue<Integer> reference = new ArrayDeque<>();
        for (int step = 0; step < 20_000; step++) {
            int action = random.nextInt(4);
            if (reference.isEmpty() || action < 2) {
                int value = random.nextInt(1000);
                queue.push(value);
                reference.add(value);
            } else if (action == 2) {
                assertEquals(reference.remove(), queue.pop());
            } else {
                assertEquals(reference.peek(), queue.peek());
            }
            assertEquals(reference.size(), queue.size());
            assertEquals(reference.isEmpty(), queue.empty());
        }
    }
}
