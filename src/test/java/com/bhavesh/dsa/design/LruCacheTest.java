package com.bhavesh.dsa.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LruCacheTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        LruCache cache = new LruCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);               // evicts key 2
        assertEquals(-1, cache.get(2));
        cache.put(4, 4);               // evicts key 1
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    @DisplayName("a get counts as a use and protects the entry from eviction")
    void getRefreshesRecency() {
        LruCache cache = new LruCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);                  // 1 becomes most recent, so 2 is evicted next
        cache.put(3, 3);
        assertEquals(1, cache.get(1));
        assertEquals(-1, cache.get(2));
    }

    @Test
    @DisplayName("updating an existing key refreshes it without growing the cache")
    void updateExisting() {
        LruCache cache = new LruCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10);
        assertEquals(2, cache.size());
        assertEquals(10, cache.get(1));
        cache.put(3, 3);               // 2 is least recent now
        assertEquals(-1, cache.get(2));
    }

    @Test
    @DisplayName("capacity one evicts on every new key")
    void capacityOne() {
        LruCache cache = new LruCache(1);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
    }

    @Test
    @DisplayName("a missing key returns -1")
    void missingKey() {
        LruCache cache = new LruCache(2);
        assertEquals(-1, cache.get(42));
    }

    @Test
    @DisplayName("non-positive capacity is rejected")
    void invalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new LruCache(0));
        assertThrows(IllegalArgumentException.class, () -> new LruCache(-1));
    }

    @Test
    @DisplayName("the cache never exceeds its capacity")
    void neverExceedsCapacity() {
        LruCache cache = new LruCache(3);
        for (int i = 0; i < 100; i++) {
            cache.put(i, i);
            org.junit.jupiter.api.Assertions.assertTrue(cache.size() <= 3);
        }
    }

    @Test
    @DisplayName("behaves identically to an access-ordered LinkedHashMap under random operations")
    void matchesLinkedHashMapReference() {
        Random random = new Random(146146L);
        int capacity = 4;
        LruCache cache = new LruCache(capacity);
        Map<Integer, Integer> reference = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };

        for (int step = 0; step < 20_000; step++) {
            int key = random.nextInt(8);
            if (random.nextBoolean()) {
                int value = random.nextInt(1000);
                cache.put(key, value);
                reference.put(key, value);
            } else {
                int expected = reference.getOrDefault(key, -1);
                assertEquals(expected, cache.get(key), "get disagreement for key " + key);
            }
            assertEquals(reference.size(), cache.size(), "size disagreement at step " + step);
        }
    }
}
