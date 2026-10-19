package com.bhavesh.dsa.bst;

import com.bhavesh.dsa.support.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 98 - Validate Binary Search Tree (Medium).
 *
 * <p>Checks the global BST property: every value in the left subtree is
 * strictly smaller than the node, and every value in the right subtree strictly
 * larger.
 *
 * <p>The tempting wrong solution compares each node only with its immediate
 * children. That accepts {@code [5,1,6,null,null,3,7]}, where 3 sits in the
 * right subtree of 5 yet is smaller than it: the local check passes at every
 * node while the tree is not a BST. The constraint is inherited from every
 * ancestor, not just the parent.
 *
 * <p>Two correct approaches:
 *
 * <ul>
 *   <li>propagate an open interval {@code (low, high)} downwards, narrowing it
 *       at each step;</li>
 *   <li>traverse in order and verify the sequence is strictly increasing, since
 *       that is exactly equivalent to the BST property.</li>
 * </ul>
 *
 * <p>The interval version uses {@link Long} bounds rather than {@code int} so a
 * node holding {@code Integer.MIN_VALUE} or {@code MAX_VALUE} does not collide
 * with the sentinel - a real edge case that LeetCode tests.
 *
 * <p>Time O(n), auxiliary space O(h).
 */
public final class ValidateBinarySearchTree {

    private ValidateBinarySearchTree() {
    }

    public static boolean isValidBst(TreeNode root) {
        return withinBounds(root, null, null);
    }

    private static boolean withinBounds(TreeNode node, Long exclusiveLow, Long exclusiveHigh) {
        if (node == null) {
            return true;
        }
        if (exclusiveLow != null && node.val <= exclusiveLow) {
            return false;
        }
        if (exclusiveHigh != null && node.val >= exclusiveHigh) {
            return false;
        }
        return withinBounds(node.left, exclusiveLow, (long) node.val)
                && withinBounds(node.right, (long) node.val, exclusiveHigh);
    }

    /**
     * In-order variant: streams the traversal and checks strict ascent without
     * materialising the whole sequence.
     *
     * <p>Time O(n), auxiliary space O(h).
     */
    public static boolean isValidBstInorder(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        Integer previous = null;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (previous != null && current.val <= previous) {
                return false;
            }
            previous = current.val;
            current = current.right;
        }
        return true;
    }
}
