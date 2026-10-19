package com.bhavesh.dsa.trees;

import com.bhavesh.dsa.support.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 226 - Invert Binary Tree (Easy).
 *
 * <p>Mirrors the tree: every node's children are swapped.
 *
 * <p>The recursion is three lines, and the only thing worth thinking about is
 * why swapping at every node suffices. Mirroring is defined recursively - the
 * mirror of a tree is the mirror of its right subtree on the left and the
 * mirror of its left subtree on the right - so a post-order or pre-order sweep
 * swapping local children produces exactly that.
 *
 * <p>The iterative version exists for the same reason as elsewhere in this
 * repository: recursion depth equals tree height, and a degenerate chain of
 * 10^5 nodes would overflow the stack.
 *
 * <p>Time O(n), auxiliary space O(h) recursive / O(w) iterative.
 */
public final class InvertBinaryTree {

    private InvertBinaryTree() {
    }

    public static TreeNode invert(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = invert(root.right);
        root.right = invert(left);
        return root;
    }

    /** Breadth-first variant, safe at any tree height. */
    public static TreeNode invertIterative(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return root;
    }
}
