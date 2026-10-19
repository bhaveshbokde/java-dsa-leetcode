package com.bhavesh.dsa.support;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Builds {@link TreeNode} trees from LeetCode's level-order array notation and
 * serialises them back.
 *
 * <p>LeetCode writes {@code [3,9,20,null,null,15,7]} as a breadth-first listing
 * in which {@code null} marks an absent child and children of absent nodes are
 * omitted entirely. Reproducing that convention here means a test can paste the
 * problem's own example instead of restating it as nested constructor calls.
 */
public final class Trees {

    /** Sentinel for an absent node in the level-order notation. */
    public static final Integer NULL = null;

    private Trees() {
    }

    /** Builds a tree from level-order values; an empty array yields {@code null}. */
    public static TreeNode of(Integer... levelOrder) {
        if (levelOrder.length == 0 || levelOrder[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(levelOrder[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < levelOrder.length) {
            TreeNode node = queue.poll();
            if (i < levelOrder.length) {
                Integer leftValue = levelOrder[i++];
                if (leftValue != null) {
                    node.left = new TreeNode(leftValue);
                    queue.add(node.left);
                }
            }
            if (i < levelOrder.length) {
                Integer rightValue = levelOrder[i++];
                if (rightValue != null) {
                    node.right = new TreeNode(rightValue);
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    /**
     * Serialises back to level-order with trailing nulls trimmed, so that a
     * round trip through {@link #of} is comparable to the original notation.
     */
    public static List<Integer> toLevelOrder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        if (root == null) {
            return out;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                out.add(null);
                continue;
            }
            out.add(node.val);
            queue.add(node.left);
            queue.add(node.right);
        }
        while (!out.isEmpty() && out.get(out.size() - 1) == null) {
            out.remove(out.size() - 1);
        }
        return out;
    }

    /** In-order traversal, used by tests to assert BST ordering properties. */
    public static List<Integer> inorder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            out.add(current.val);
            current = current.right;
        }
        return out;
    }
}
