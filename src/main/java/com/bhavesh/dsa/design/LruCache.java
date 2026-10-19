package com.bhavesh.dsa.design;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146 - LRU Cache (Medium).
 *
 * <p>Fixed-capacity cache evicting the least recently used entry, with
 * {@code get} and {@code put} both O(1).
 *
 * <p>Neither structure alone suffices, and seeing why is the point of the
 * problem. A hash map gives O(1) lookup but no recency order. A list gives
 * order but O(n) lookup. Combining them does both: the map holds key to node,
 * and a doubly linked list holds the recency order.
 *
 * <p>The list must be doubly linked. Eviction removes from the tail and a
 * refresh removes from the middle, and unlinking a node in O(1) requires
 * knowing its predecessor - which a singly linked list does not provide.
 *
 * <p>The two sentinel nodes remove every null check from the link surgery. With
 * a permanent head and tail, no insertion or removal ever has to special-case
 * an empty list or the end of one.
 *
 * <p>{@code LinkedHashMap} with {@code accessOrder = true} and an overridden
 * {@code removeEldestEntry} does the same job in a few lines, and is what one
 * would use in production. The explicit version is here because the interview
 * question is about the underlying structure.
 *
 * <p>Both operations O(1); space O(capacity).
 */
public final class LruCache {

    private static final class Node {
        private final int key;
        private int value;
        private Node previous;
        private Node next;

        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> index;
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);

    public LruCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        this.index = new HashMap<>(capacity * 2);
        head.next = tail;
        tail.previous = head;
    }

    /** Returns the value, or -1 when absent. A hit counts as a use. */
    public int get(int key) {
        Node node = index.get(key);
        if (node == null) {
            return -1;
        }
        moveToFront(node);
        return node.value;
    }

    /** Inserts or updates; evicts the least recently used entry when full. */
    public void put(int key, int value) {
        Node existing = index.get(key);
        if (existing != null) {
            existing.value = value;
            moveToFront(existing);
            return;
        }
        if (index.size() == capacity) {
            Node leastRecentlyUsed = tail.previous;
            unlink(leastRecentlyUsed);
            index.remove(leastRecentlyUsed.key);
        }
        Node inserted = new Node(key, value);
        index.put(key, inserted);
        linkAtFront(inserted);
    }

    public int size() {
        return index.size();
    }

    private void moveToFront(Node node) {
        unlink(node);
        linkAtFront(node);
    }

    private void linkAtFront(Node node) {
        node.next = head.next;
        node.previous = head;
        head.next.previous = node;
        head.next = node;
    }

    private void unlink(Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }
}
