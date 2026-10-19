package com.bhavesh.dsa.bst;

import com.bhavesh.dsa.support.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 230 - Kth Smallest Element in a BST (Medium).
 *
 * <p>Returns the k-th smallest value, counting from 1.
 *
 * <p>The whole problem is one observation: <b>the in-order traversal of a BST
 * is sorted</b>. So the k-th smallest is simply the k-th node visited in order,
 * and no sorting or extra structure is needed.
 *
 * <p>Collecting the full traversal into a list and indexing it works but visits
 * every node and allocates O(n). The iterative traversal below stops as soon as
 * the count reaches {@code k}, so it touches only O(h + k) nodes - a real
 * saving when k is small and the tree is large.
 *
 * <p>Early exit is also why this uses an explicit stack rather than recursion:
 * stopping a recursion partway needs either an exception or a mutable flag
 * threaded through every frame, both of which are uglier than the loop.
 *
 * <p>The problem's follow-up asks what to do if the tree is modified often and
 * k-th queries are frequent: augment each node with its subtree size, which
 * makes a single query O(h) and keeps updates O(h) too.
 *
 * <p>Time O(h + k), auxiliary space O(h).
 */
public final class KthSmallestInBst {

    private KthSmallestInBst() {
    }

    public static int kthSmallest(TreeNode root, int k) {
        if (root == null || k < 1) {
            throw new IllegalArgumentException("k must be at least 1 and the tree non-empty");
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        int remaining = k;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (--remaining == 0) {
                return current.val;      // stop as soon as the k-th is reached
            }
            current = current.right;
        }
        throw new IllegalArgumentException("the tree holds fewer than " + k + " nodes");
    }
}
