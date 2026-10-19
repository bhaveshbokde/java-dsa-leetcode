package com.bhavesh.dsa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bhavesh.dsa.support.Lists;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RemoveNthFromEndTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertArrayEquals(new int[] {1, 2, 3, 5},
                Lists.toArray(RemoveNthFromEnd.removeNthFromEnd(Lists.of(1, 2, 3, 4, 5), 2)));
        assertNull(RemoveNthFromEnd.removeNthFromEnd(Lists.of(1), 1));
        assertArrayEquals(new int[] {1},
                Lists.toArray(RemoveNthFromEnd.removeNthFromEnd(Lists.of(1, 2), 1)));
    }

    @Test
    @DisplayName("removing the head, which is why the dummy node exists")
    void removeHead() {
        assertArrayEquals(new int[] {2, 3},
                Lists.toArray(RemoveNthFromEnd.removeNthFromEnd(Lists.of(1, 2, 3), 3)));
    }

    @Test
    @DisplayName("removing the tail")
    void removeTail() {
        assertArrayEquals(new int[] {1, 2},
                Lists.toArray(RemoveNthFromEnd.removeNthFromEnd(Lists.of(1, 2, 3), 1)));
    }

    @Test
    @DisplayName("invalid n and empty lists are rejected")
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class,
                () -> RemoveNthFromEnd.removeNthFromEnd(null, 1));
        assertThrows(IllegalArgumentException.class,
                () -> RemoveNthFromEnd.removeNthFromEnd(Lists.of(1, 2), 0));
        assertThrows(IllegalArgumentException.class,
                () -> RemoveNthFromEnd.removeNthFromEnd(Lists.of(1, 2), 5));
    }

    @Test
    @DisplayName("matches a count-then-remove reference for every valid n")
    void matchesReference() {
        Random random = new Random(1919L);
        for (int trial = 0; trial < 500; trial++) {
            int length = 1 + random.nextInt(10);
            int[] values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = random.nextInt(100);
            }
            for (int n = 1; n <= length; n++) {
                int[] expected = reference(values, n);
                int[] actual = Lists.toArray(
                        RemoveNthFromEnd.removeNthFromEnd(Lists.of(values), n));
                final int nn = n;
                assertArrayEquals(expected, actual,
                        () -> "mismatch removing " + nn + " from end of " + Arrays.toString(values));
            }
        }
    }

    /** Removes index (length - n) from a copy; obviously correct. */
    private static int[] reference(int[] values, int n) {
        int remove = values.length - n;
        int[] out = new int[values.length - 1];
        int at = 0;
        for (int i = 0; i < values.length; i++) {
            if (i != remove) {
                out[at++] = values[i];
            }
        }
        return out;
    }

    @Test
    @DisplayName("a long list is handled in one pass without recursion")
    void longList() {
        int[] values = new int[100_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        int[] result = Lists.toArray(RemoveNthFromEnd.removeNthFromEnd(Lists.of(values), 50_000));
        org.junit.jupiter.api.Assertions.assertEquals(values.length - 1, result.length);
        // The removed value was values[100000 - 50000] = 50000.
        org.junit.jupiter.api.Assertions.assertEquals(50_001, result[50_000]);
    }
}
