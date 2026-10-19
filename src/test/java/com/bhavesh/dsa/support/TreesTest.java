package com.bhavesh.dsa.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Direct tests for the shared tree helpers.
 *
 * <p>These exist because {@code toLevelOrder} previously contained a latent
 * NullPointerException: it enqueued {@code null} children into an
 * {@link java.util.ArrayDeque}, which rejects nulls. The only caller at the
 * time used it inside a lazily-evaluated assertion message, so the defect never
 * executed. Shared helpers get their own tests for exactly this reason.
 */
class TreesTest {

    @Test
    @DisplayName("round trip: of(...) then toLevelOrder returns the original notation")
    void roundTrip() {
        Integer[][] cases = {
            {1, 2, 3},
            {3, 9, 20, null, null, 15, 7},
            {1},
            {1, 2, null, 3},
            {1, null, 2, null, 3},
            {5, 3, 8, 1, 4, 7, 9},
            {4, 2, 7, 1, 3, 6, 9},
        };
        for (Integer[] level : cases) {
            assertEquals(Arrays.asList(level), Trees.toLevelOrder(Trees.of(level)),
                    () -> "round trip failed for " + Arrays.toString(level));
        }
    }

    @Test
    @DisplayName("toLevelOrder handles absent children without throwing")
    void nullChildrenAreTolerated() {
        // The regression: a tree with gaps forces nulls through the queue.
        assertEquals(List.of(1, 2, 3), Trees.toLevelOrder(Trees.of(1, 2, 3)));
        assertEquals(Arrays.asList(1, null, 2), Trees.toLevelOrder(Trees.of(1, null, 2)));
    }

    @Test
    @DisplayName("empty and single-node trees")
    void degenerate() {
        assertNull(Trees.of());
        assertTrue(Trees.toLevelOrder(null).isEmpty());
        assertEquals(List.of(7), Trees.toLevelOrder(Trees.of(7)));
    }

    @Test
    @DisplayName("trailing nulls are trimmed")
    void trailingNullsTrimmed() {
        List<Integer> result = Trees.toLevelOrder(Trees.of(1, 2));
        assertEquals(List.of(1, 2), result, "trailing nulls should not appear");
    }

    @Test
    @DisplayName("inorder returns the values left to right")
    void inorderOrder() {
        assertEquals(List.of(1, 2, 3, 4, 5), Trees.inorder(Trees.of(3, 2, 4, 1, null, null, 5)));
        assertTrue(Trees.inorder(null).isEmpty());
    }

    @Test
    @DisplayName("a null root in the notation yields a null tree")
    void nullRoot() {
        assertNull(Trees.of((Integer) null));
    }
}
