package com.bhavesh.dsa.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhavesh.dsa.support.GraphNode;
import com.bhavesh.dsa.support.Graphs;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CloneGraphTest {

    @Test
    @DisplayName("LeetCode example: a 4-node cycle")
    void leetCodeExample() {
        GraphNode original = Graphs.of(new int[][] {{2, 4}, {1, 3}, {2, 4}, {1, 3}});
        GraphNode copy = CloneGraph.cloneGraph(original);
        assertEquals(Graphs.toAdjacency(original), Graphs.toAdjacency(copy));
    }

    @Test
    @DisplayName("the clone shares no node object with the original")
    void deepCopyNotShallow() {
        GraphNode original = Graphs.of(new int[][] {{2, 4}, {1, 3}, {2, 4}, {1, 3}});
        GraphNode copy = CloneGraph.cloneGraph(original);

        Map<Integer, GraphNode> originals = Graphs.reachable(original);
        Map<Integer, GraphNode> copies = Graphs.reachable(copy);
        assertEquals(originals.keySet(), copies.keySet());
        for (Integer val : originals.keySet()) {
            assertNotSame(originals.get(val), copies.get(val),
                    "node " + val + " must be a distinct object");
        }
    }

    @Test
    @DisplayName("mutating the clone does not affect the original")
    void cloneIsIndependent() {
        GraphNode original = Graphs.of(new int[][] {{2}, {1}});
        GraphNode copy = CloneGraph.cloneGraph(original);
        copy.neighbors.clear();
        assertEquals(1, original.neighbors.size(), "the original must be untouched");
    }

    @Test
    @DisplayName("single node with no edges")
    void singleNode() {
        GraphNode original = Graphs.of(new int[][] {{}});
        GraphNode copy = CloneGraph.cloneGraph(original);
        assertEquals(1, copy.val);
        assertTrue(copy.neighbors.isEmpty());
        assertNotSame(original, copy);
    }

    @Test
    @DisplayName("empty graph")
    void emptyGraph() {
        assertNull(CloneGraph.cloneGraph(null));
    }

    @Test
    @DisplayName("two mutually connected nodes, the minimal cycle")
    void twoNodeCycle() {
        GraphNode original = Graphs.of(new int[][] {{2}, {1}});
        GraphNode copy = CloneGraph.cloneGraph(original);
        assertEquals(Map.of(1, List.of(2), 2, List.of(1)), Graphs.toAdjacency(copy));
        assertNotSame(original.neighbors.get(0), copy.neighbors.get(0));
    }

    @Test
    @DisplayName("a self-loop is cloned without recursing forever")
    void selfLoop() {
        GraphNode node = new GraphNode(1);
        node.neighbors.add(node);
        GraphNode copy = CloneGraph.cloneGraph(node);
        assertNotSame(node, copy);
        assertEquals(1, copy.neighbors.size());
        // The clone's self-edge must point at the clone, not the original.
        assertEquals(copy, copy.neighbors.get(0));
    }

    @Test
    @DisplayName("a long path does not overflow the stack")
    void longPath() {
        int n = 50_000;
        GraphNode[] nodes = new GraphNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new GraphNode(i + 1);
        }
        for (int i = 0; i + 1 < n; i++) {
            nodes[i].neighbors.add(nodes[i + 1]);
            nodes[i + 1].neighbors.add(nodes[i]);
        }
        GraphNode copy = CloneGraph.cloneGraph(nodes[0]);
        assertEquals(n, Graphs.reachable(copy).size());
    }
}
