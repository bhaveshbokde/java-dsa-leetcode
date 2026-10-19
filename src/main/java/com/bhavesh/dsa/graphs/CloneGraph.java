package com.bhavesh.dsa.graphs;

import com.bhavesh.dsa.support.GraphNode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 133 - Clone Graph (Medium).
 *
 * <p>Returns a deep copy of a connected undirected graph: every node and every
 * edge duplicated, sharing nothing with the original.
 *
 * <p>The difficulty is not traversal but <b>cycles</b>. An undirected edge is a
 * two-way link, so even a two-node graph contains a cycle. Naively recursing
 * into every neighbour therefore never terminates, and copying a node twice
 * produces a structurally wrong graph with duplicated vertices.
 *
 * <p>A single map from original node to its clone solves both problems at once:
 * it is the visited set <em>and</em> the lookup that lets an edge point at an
 * already-created clone rather than making a second copy.
 *
 * <p>The ordering inside the loop is what makes it correct. The clone must be
 * <b>created and registered in the map before</b> its neighbours are explored.
 * Registering afterwards leaves a window in which a neighbour recursing back
 * finds nothing and starts a second copy - which is exactly the infinite
 * regress the map was meant to prevent.
 *
 * <p>Iterative BFS rather than recursion: a path graph of 100 nodes is a legal
 * input, and deep recursion over a long chain is an avoidable risk.
 *
 * <p>Time O(V + E), auxiliary space O(V).
 */
public final class CloneGraph {

    private CloneGraph() {
    }

    public static GraphNode cloneGraph(GraphNode node) {
        if (node == null) {
            return null;
        }
        Map<GraphNode, GraphNode> clones = new HashMap<>();
        clones.put(node, new GraphNode(node.val));

        Deque<GraphNode> queue = new ArrayDeque<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            GraphNode original = queue.poll();
            GraphNode copy = clones.get(original);
            for (GraphNode neighbour : original.neighbors) {
                GraphNode neighbourClone = clones.get(neighbour);
                if (neighbourClone == null) {
                    // Register before enqueuing, so a cycle back to this node
                    // finds the clone instead of creating a second one.
                    neighbourClone = new GraphNode(neighbour.val);
                    clones.put(neighbour, neighbourClone);
                    queue.add(neighbour);
                }
                copy.neighbors.add(neighbourClone);
            }
        }
        return clones.get(node);
    }
}
