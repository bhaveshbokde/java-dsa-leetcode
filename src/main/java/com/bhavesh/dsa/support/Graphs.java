package com.bhavesh.dsa.support;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Builds {@link GraphNode} graphs from LeetCode's adjacency-list notation and
 * reads them back.
 *
 * <p>LeetCode writes {@code [[2,4],[1,3],[2,4],[1,3]]} meaning node 1 is
 * adjacent to 2 and 4, node 2 to 1 and 3, and so on - values are 1-indexed and
 * equal to the node's position. Reproducing that convention lets a test paste
 * the problem's own example rather than restating it as constructor calls.
 */
public final class Graphs {

    private Graphs() {
    }

    /** Builds a graph from 1-indexed adjacency lists; empty input yields null. */
    public static GraphNode of(int[][] adjacency) {
        if (adjacency == null || adjacency.length == 0) {
            return null;
        }
        Map<Integer, GraphNode> nodes = new HashMap<>();
        for (int i = 1; i <= adjacency.length; i++) {
            nodes.put(i, new GraphNode(i));
        }
        for (int i = 1; i <= adjacency.length; i++) {
            for (int neighbour : adjacency[i - 1]) {
                nodes.get(i).neighbors.add(nodes.get(neighbour));
            }
        }
        return nodes.get(1);
    }

    /**
     * Serialises back to adjacency lists keyed by value, with each neighbour
     * list sorted so comparisons are order-independent.
     */
    public static Map<Integer, List<Integer>> toAdjacency(GraphNode start) {
        Map<Integer, List<Integer>> out = new TreeMap<>();
        if (start == null) {
            return out;
        }
        Deque<GraphNode> queue = new ArrayDeque<>();
        queue.add(start);
        out.put(start.val, new ArrayList<>());
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            List<Integer> neighbours = new ArrayList<>();
            for (GraphNode n : node.neighbors) {
                neighbours.add(n.val);
                if (!out.containsKey(n.val)) {
                    out.put(n.val, new ArrayList<>());
                    queue.add(n);
                }
            }
            neighbours.sort(null);
            out.put(node.val, neighbours);
        }
        return out;
    }

    /** Collects every node reachable from {@code start}, keyed by value. */
    public static Map<Integer, GraphNode> reachable(GraphNode start) {
        Map<Integer, GraphNode> seen = new HashMap<>();
        if (start == null) {
            return seen;
        }
        Deque<GraphNode> queue = new ArrayDeque<>();
        queue.add(start);
        seen.put(start.val, start);
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            for (GraphNode n : node.neighbors) {
                if (!seen.containsKey(n.val)) {
                    seen.put(n.val, n);
                    queue.add(n);
                }
            }
        }
        return seen;
    }
}
