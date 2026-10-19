package com.bhavesh.dsa.graphsadvanced;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DijkstraTest {

    private static List<List<Dijkstra.Edge>> graph(int vertices, int[][] edges) {
        List<List<Dijkstra.Edge>> adjacency = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacency.get(edge[0]).add(new Dijkstra.Edge(edge[1], edge[2]));
        }
        return adjacency;
    }

    @Test
    @DisplayName("LeetCode 743 examples")
    void networkDelayExamples() {
        assertEquals(2, Dijkstra.networkDelayTime(new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        assertEquals(1, Dijkstra.networkDelayTime(new int[][] {{1, 2, 1}}, 2, 1));
        assertEquals(-1, Dijkstra.networkDelayTime(new int[][] {{1, 2, 1}}, 2, 2));
    }

    @Test
    @DisplayName("a shorter indirect route beats a direct edge")
    void indirectRouteWins() {
        // 0 -> 1 directly costs 10; 0 -> 2 -> 1 costs 3.
        long[] distance = Dijkstra.shortestPaths(3, graph(3, new int[][] {
            {0, 1, 10}, {0, 2, 1}, {2, 1, 2},
        }), 0);
        assertEquals(3, distance[1]);
    }

    @Test
    @DisplayName("unreachable vertices stay at Long.MAX_VALUE")
    void unreachable() {
        long[] distance = Dijkstra.shortestPaths(3, graph(3, new int[][] {{0, 1, 5}}), 0);
        assertEquals(0, distance[0]);
        assertEquals(5, distance[1]);
        assertEquals(Long.MAX_VALUE, distance[2]);
    }

    @Test
    @DisplayName("zero-weight edges are allowed")
    void zeroWeights() {
        long[] distance = Dijkstra.shortestPaths(2, graph(2, new int[][] {{0, 1, 0}}), 0);
        assertEquals(0, distance[1]);
    }

    @Test
    @DisplayName("negative weights are rejected at construction")
    void negativeWeightRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Dijkstra.Edge(1, -1));
    }

    @Test
    @DisplayName("invalid sources and empty graphs")
    void degenerate() {
        assertEquals(0, Dijkstra.shortestPaths(0, new ArrayList<>(), 0).length);
        assertThrows(IndexOutOfBoundsException.class,
                () -> Dijkstra.shortestPaths(2, graph(2, new int[0][]), 5));
    }

    @Test
    @DisplayName("parallel edges: the cheapest one wins")
    void parallelEdges() {
        long[] distance = Dijkstra.shortestPaths(2, graph(2, new int[][] {{0, 1, 9}, {0, 1, 4}}), 0);
        assertEquals(4, distance[1]);
    }

    @Test
    @DisplayName("large weights do not overflow when summed along a path")
    void largeWeightsDoNotOverflow() {
        int[][] edges = new int[20][3];
        for (int i = 0; i < 20; i++) {
            edges[i] = new int[] {i, i + 1, Integer.MAX_VALUE};
        }
        long[] distance = Dijkstra.shortestPaths(21, graph(21, edges), 0);
        assertEquals(20L * Integer.MAX_VALUE, distance[20]);
    }

    @Test
    @DisplayName("matches Bellman-Ford on randomised non-negative graphs")
    void matchesBellmanFord() {
        Random random = new Random(743743L);
        for (int trial = 0; trial < 300; trial++) {
            int vertices = 1 + random.nextInt(8);
            int edgeCount = random.nextInt(16);
            int[][] edges = new int[edgeCount][3];
            for (int e = 0; e < edgeCount; e++) {
                edges[e] = new int[] {
                    random.nextInt(vertices),
                    random.nextInt(vertices),
                    random.nextInt(20),
                };
            }
            int source = random.nextInt(vertices);
            long[] actual = Dijkstra.shortestPaths(vertices, graph(vertices, edges), source);
            long[] expected = bellmanFord(vertices, edges, source);
            org.junit.jupiter.api.Assertions.assertArrayEquals(expected, actual,
                    () -> "mismatch for " + Arrays.deepToString(edges) + " from " + source);
        }
    }

    /** Relaxes every edge V-1 times; correct for any graph without negative cycles. */
    private static long[] bellmanFord(int vertices, int[][] edges, int source) {
        long[] distance = new long[vertices];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[source] = 0;
        for (int round = 0; round < vertices - 1; round++) {
            for (int[] edge : edges) {
                if (distance[edge[0]] != Long.MAX_VALUE
                        && distance[edge[0]] + edge[2] < distance[edge[1]]) {
                    distance[edge[1]] = distance[edge[0]] + edge[2];
                }
            }
        }
        return distance;
    }
}
