package com.bhavesh.dsa.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseScheduleTest {

    @Test
    @DisplayName("LeetCode 207 examples")
    void canFinishExamples() {
        assertTrue(CourseSchedule.canFinish(2, new int[][] {{1, 0}}));
        assertFalse(CourseSchedule.canFinish(2, new int[][] {{1, 0}, {0, 1}}));
    }

    @Test
    @DisplayName("LeetCode 210 example produces a valid ordering")
    void findOrderExample() {
        int[] order = CourseSchedule.findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertEquals(4, order.length);
        assertValidOrder(order, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}});
    }

    @Test
    @DisplayName("no prerequisites: any order of all courses")
    void noPrerequisites() {
        int[] order = CourseSchedule.findOrder(3, new int[][] {});
        assertEquals(3, order.length);
        int[] sorted = order.clone();
        Arrays.sort(sorted);
        org.junit.jupiter.api.Assertions.assertArrayEquals(new int[] {0, 1, 2}, sorted);
        assertTrue(CourseSchedule.canFinish(3, null));
    }

    @Test
    @DisplayName("a self-loop is a cycle")
    void selfLoop() {
        assertFalse(CourseSchedule.canFinish(1, new int[][] {{0, 0}}));
        assertEquals(0, CourseSchedule.findOrder(1, new int[][] {{0, 0}}).length);
    }

    @Test
    @DisplayName("a three-course cycle is rejected")
    void threeCycle() {
        assertFalse(CourseSchedule.canFinish(3, new int[][] {{0, 1}, {1, 2}, {2, 0}}));
    }

    @Test
    @DisplayName("a cycle among only some courses still blocks the whole ordering")
    void partialCycle() {
        // 0 is free, but 1 <-> 2 is a cycle, so no full ordering exists.
        assertFalse(CourseSchedule.canFinish(3, new int[][] {{1, 2}, {2, 1}}));
    }

    @Test
    @DisplayName("zero and negative course counts")
    void degenerate() {
        assertEquals(0, CourseSchedule.findOrder(0, new int[][] {}).length);
        assertEquals(0, CourseSchedule.findOrder(-1, new int[][] {}).length);
    }

    @Test
    @DisplayName("a long chain is ordered correctly")
    void longChain() {
        int n = 10_000;
        int[][] prerequisites = new int[n - 1][2];
        for (int i = 1; i < n; i++) {
            prerequisites[i - 1] = new int[] {i, i - 1};
        }
        int[] order = CourseSchedule.findOrder(n, prerequisites);
        assertEquals(n, order.length);
        for (int i = 0; i < n; i++) {
            assertEquals(i, order[i], "a single chain has exactly one valid order");
        }
    }

    @Test
    @DisplayName("randomised acyclic graphs always produce a valid ordering")
    void randomisedAcyclicGraphs() {
        Random random = new Random(207207L);
        for (int trial = 0; trial < 300; trial++) {
            int n = 1 + random.nextInt(10);
            // Every edge runs strictly from a lower index to a higher one,
            // which makes the graph acyclic by construction. Pairs that collide
            // on the same vertex are skipped rather than nudged, because a
            // self-loop is itself a cycle and would break the invariant.
            List<int[]> edges = new ArrayList<>();
            int attempts = random.nextInt(12);
            for (int e = 0; e < attempts; e++) {
                int a = random.nextInt(n);
                int b = random.nextInt(n);
                if (a == b) {
                    continue;
                }
                edges.add(new int[] {Math.max(a, b), Math.min(a, b)});
            }
            int[][] prerequisites = edges.toArray(new int[0][]);
            int[] order = CourseSchedule.findOrder(n, prerequisites);
            assertEquals(n, order.length, () -> "expected an ordering for an acyclic graph");
            assertValidOrder(order, prerequisites);
        }
    }

    /** Every prerequisite must appear before the course that needs it. */
    private static void assertValidOrder(int[] order, int[][] prerequisites) {
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < order.length; i++) {
            position.put(order[i], i);
        }
        for (int[] pair : prerequisites) {
            if (pair[0] == pair[1]) {
                continue;
            }
            assertTrue(position.get(pair[1]) < position.get(pair[0]),
                    () -> "prerequisite " + pair[1] + " must precede " + pair[0]);
        }
    }
}
