package com.bhavesh.dsa.trees;

import com.bhavesh.dsa.support.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 100 - Same Tree (Easy).
 *
 * <p>Two trees are the same when they have identical structure <em>and</em>
 * identical values at every position.
 *
 * <p>The structural check is what distinguishes this from comparing
 * traversals. A single traversal is not enough to identify a tree: the
 * pre-order of {@code [1,2]} (left child) and {@code [1,null,2]} (right child)
 * are both {@code 1,2}. Comparing in-order plus pre-order does determine a tree,
 * but comparing position by position is simpler and cheaper.
 *
 * <p>Both null means equal; exactly one null means different structure. Those
 * two base cases carry all of the structural comparison.
 *
 * <p>Time O(min(n, m)) - it stops at the first difference. Auxiliary space
 * O(min(h1, h2)).
 */
public final class SameTree {

    private SameTree() {
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }

    /** Iterative variant, comparing node pairs breadth-first. */
    public static boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        Deque<TreeNode[]> pairs = new ArrayDeque<>();
        pairs.add(new TreeNode[] {p, q});
        while (!pairs.isEmpty()) {
            TreeNode[] pair = pairs.poll();
            TreeNode a = pair[0];
            TreeNode b = pair[1];
            if (a == null && b == null) {
                continue;
            }
            if (a == null || b == null || a.val != b.val) {
                return false;
            }
            pairs.add(new TreeNode[] {a.left, b.left});
            pairs.add(new TreeNode[] {a.right, b.right});
        }
        return true;
    }
}
