package com.bhavesh.dsa.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DisjointSetUnionTest {

    @Test
    @DisplayName("every element starts in its own set")
    void startsFullyDisjoint() {
        DisjointSetUnion dsu = new DisjointSetUnion(5);
        assertEquals(5, dsu.componentCount());
        for (int i = 0; i < 5; i++) {
            assertEquals(1, dsu.componentSize(i));
            for (int j = i + 1; j < 5; j++) {
                assertFalse(dsu.connected(i, j));
            }
        }
    }

    @Test
    @DisplayName("union merges and reduces the component count")
    void unionMerges() {
        DisjointSetUnion dsu = new DisjointSetUnion(4);
        assertTrue(dsu.union(0, 1));
        assertTrue(dsu.connected(0, 1));
        assertEquals(3, dsu.componentCount());
        assertEquals(2, dsu.componentSize(0));
    }

    @Test
    @DisplayName("a repeated union is a no-op and reports false")
    void repeatedUnion() {
        DisjointSetUnion dsu = new DisjointSetUnion(3);
        assertTrue(dsu.union(0, 1));
        assertFalse(dsu.union(0, 1));
        assertFalse(dsu.union(1, 0));
        assertEquals(2, dsu.componentCount());
    }

    @Test
    @DisplayName("connectivity is transitive")
    void transitivity() {
        DisjointSetUnion dsu = new DisjointSetUnion(4);
        dsu.union(0, 1);
        dsu.union(2, 3);
        assertFalse(dsu.connected(1, 2));
        dsu.union(1, 2);
        assertTrue(dsu.connected(0, 3));
        assertEquals(1, dsu.componentCount());
        assertEquals(4, dsu.componentSize(0));
    }

    @Test
    @DisplayName("zero-sized and invalid inputs")
    void degenerate() {
        DisjointSetUnion empty = new DisjointSetUnion(0);
        assertEquals(0, empty.componentCount());
        assertThrows(IllegalArgumentException.class, () -> new DisjointSetUnion(-1));
        DisjointSetUnion dsu = new DisjointSetUnion(2);
        assertThrows(IndexOutOfBoundsException.class, () -> dsu.find(2));
        assertThrows(IndexOutOfBoundsException.class, () -> dsu.find(-1));
    }

    @Test
    @DisplayName("a long chain of unions stays fast and does not overflow the stack")
    void longChain() {
        int n = 200_000;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 1; i < n; i++) {
            dsu.union(i - 1, i);
        }
        assertEquals(1, dsu.componentCount());
        assertEquals(n, dsu.componentSize(0));
        assertTrue(dsu.connected(0, n - 1));
    }

    @Test
    @DisplayName("matches a naive O(n) label-propagation reference under random unions")
    void matchesNaiveReference() {
        Random random = new Random(547547L);
        for (int trial = 0; trial < 200; trial++) {
            int n = 1 + random.nextInt(12);
            DisjointSetUnion dsu = new DisjointSetUnion(n);
            int[] label = new int[n];
            for (int i = 0; i < n; i++) {
                label[i] = i;
            }
            for (int step = 0; step < 20; step++) {
                int a = random.nextInt(n);
                int b = random.nextInt(n);
                dsu.union(a, b);
                // Naive merge: relabel every member of b's group.
                int from = label[b];
                int to = label[a];
                for (int i = 0; i < n; i++) {
                    if (label[i] == from) {
                        label[i] = to;
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        assertEquals(label[i] == label[j], dsu.connected(i, j),
                                "connectivity disagreement at " + i + "," + j);
                    }
                }
            }
        }
    }
}
