package com.bhavesh.dsa.support;

import java.util.ArrayList;
import java.util.List;

/**
 * Undirected graph node with an adjacency list, matching the shape LeetCode
 * supplies for graph problems such as Clone Graph.
 *
 * <p>The fields are public and mutable for the same reason as {@link ListNode}:
 * encapsulating them would make every solution read differently from the form
 * an interviewer expects.
 */
public class GraphNode {

    public int val;
    public List<GraphNode> neighbors;

    public GraphNode() {
        this.neighbors = new ArrayList<>();
    }

    public GraphNode(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }

    public GraphNode(int val, List<GraphNode> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
