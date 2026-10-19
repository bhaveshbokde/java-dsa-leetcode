package com.bhavesh.dsa.trees;

import com.bhavesh.dsa.support.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Core binary-tree traversals, including LeetCode 102 (level order) and
 * LeetCode 104 (maximum depth).
 *
 * <p>The depth-first traversals are given in iterative form as well as
 * recursive. That is not academic: recursion depth equals tree height, so a
 * degenerate tree of 10^5 nodes - which is a valid LeetCode input - overflows
 * the default stack, while the explicit-stack version does not.
 *
 * <p>Level order needs a queue rather than a stack, and the size snapshot taken
 * at the top of each iteration is what separates one level from the next.
 * Reading {@code queue.size()} inside the inner loop instead would see children
 * added during the same level and merge them together.
 *
 * <p>Each traversal is O(n) time. Space is O(h) for the depth-first forms and
 * O(w) for level order, where h is the height and w the maximum width.
 */
public final class BinaryTreeTraversals {

    private BinaryTreeTraversals() {
    }

    /** LeetCode 104 - height measured in nodes; an empty tree is depth 0. */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /** Iterative depth, safe on a degenerate tree of any height. */
    public static int maxDepthIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /** LeetCode 102 - values grouped by depth, left to right. */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();   // snapshot before adding children
            List<Integer> level = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            levels.add(level);
        }
        return levels;
    }

    /** Iterative in-order traversal. */
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

    /** Iterative pre-order traversal. */
    public static List<Integer> preorder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        if (root == null) {
            return out;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            out.add(node.val);
            // Right first, so left is processed first on the next pop.
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return out;
    }

    /**
     * Iterative post-order traversal.
     *
     * <p>Built by producing root-right-left with a single stack and reversing,
     * which is markedly less error-prone than the two-stack or visited-flag
     * variants.
     */
    public static List<Integer> postorder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        if (root == null) {
            return out;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            out.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        java.util.Collections.reverse(out);
        return out;
    }
}
