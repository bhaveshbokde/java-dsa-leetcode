package com.bhavesh.dsa.graphsadvanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Dijkstra's shortest-path algorithm, the engine behind LeetCode 743 - Network
 * Delay Time.
 *
 * <p>Finds the shortest distance from a source to every vertex in a directed
 * graph with non-negative edge weights.
 *
 * <p>The non-negativity requirement is not a formality. Dijkstra finalises a
 * vertex the moment it is removed from the queue, on the argument that no
 * later-discovered path can be shorter - which holds only if extending a path
 * can never reduce its length. A single negative edge breaks that, and the
 * algorithm returns wrong answers rather than failing loudly. Bellman-Ford is
 * the correct choice there.
 *
 * <p>This implementation uses lazy deletion: a vertex may be pushed several
 * times with decreasing distances, and stale entries are discarded on removal
 * by comparing against the recorded best. That avoids needing a priority queue
 * with decrease-key, which {@link PriorityQueue} does not provide.
 *
 * <p>Distances are {@code long} so that summing many large weights cannot
 * overflow, and unreachable vertices are reported as
 * {@code Long.MAX_VALUE} rather than -1, so that a genuine distance is never
 * confused with absence.
 *
 * <p>Time O((V + E) log V), auxiliary space O(V + E).
 */
public final class Dijkstra {

    /** A weighted directed edge. */
    public record Edge(int to, int weight) {
        public Edge {
            if (weight < 0) {
                throw new IllegalArgumentException("Dijkstra requires non-negative weights");
            }
        }
    }

    private Dijkstra() {
    }

    /** Shortest distances from {@code source}; unreachable is Long.MAX_VALUE. */
    public static long[] shortestPaths(int vertexCount, List<List<Edge>> adjacency, int source) {
        if (vertexCount <= 0) {
            return new long[0];
        }
        if (source < 0 || source >= vertexCount) {
            throw new IndexOutOfBoundsException("source " + source + " is outside the graph");
        }
        long[] distance = new long[vertexCount];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[source] = 0;

        Queue<long[]> frontier = new PriorityQueue<>(Comparator.comparingLong(entry -> entry[1]));
        frontier.add(new long[] {source, 0});

        while (!frontier.isEmpty()) {
            long[] entry = frontier.poll();
            int vertex = (int) entry[0];
            long dist = entry[1];
            // Lazy deletion: skip an entry already superseded by a shorter path.
            if (dist > distance[vertex]) {
                continue;
            }
            for (Edge edge : adjacency.get(vertex)) {
                long candidate = dist + edge.weight();
                if (candidate < distance[edge.to()]) {
                    distance[edge.to()] = candidate;
                    frontier.add(new long[] {edge.to(), candidate});
                }
            }
        }
        return distance;
    }

    /**
     * LeetCode 743 - the time for a signal to reach every node, or -1 if some
     * node is unreachable.
     */
    public static int networkDelayTime(int[][] times, int n, int k) {
        List<List<Edge>> adjacency = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] time : times) {
            adjacency.get(time[0]).add(new Edge(time[1], time[2]));
        }
        long[] distance = shortestPaths(n + 1, adjacency, k);

        long slowest = 0;
        // Vertex 0 is unused: the problem numbers nodes from 1.
        for (int node = 1; node <= n; node++) {
            if (distance[node] == Long.MAX_VALUE) {
                return -1;
            }
            slowest = Math.max(slowest, distance[node]);
        }
        return (int) slowest;
    }
}
