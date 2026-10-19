package com.bhavesh.dsa.trees;

import com.bhavesh.dsa.support.TreeNode;

/**
 * LeetCode 124 - Binary Tree Maximum Path Sum (Hard).
 *
 * <p>A path is any sequence of adjacent nodes, not necessarily through the
 * root; returns the maximum of its node sums.
 *
 * <p>The difficulty is that two different quantities are involved at every
 * node, and conflating them is the classic error:
 *
 * <ul>
 *   <li>the best path that <em>passes through</em> this node, which may descend
 *       into both children - this is a candidate for the global answer, but
 *       cannot be extended further upward;</li>
 *   <li>the best path that <em>starts at</em> this node and goes down one side
 *       only - this is what the parent can use.</li>
 * </ul>
 *
 * <p>So the recursion returns the second while updating a field holding the
 * maximum of the first. Returning the two-sided value would allow a parent to
 * build an invalid branching path.
 *
 * <p>Clamping each child's contribution at zero encodes the choice to exclude a
 * negative subtree entirely, which is always at least as good as including it.
 * A tree of all negative values therefore still answers with its largest single
 * node rather than 0, because the node's own value is never clamped.
 *
 * <p>Time O(n), auxiliary space O(h).
 */
public final class BinaryTreeMaximumPathSum {

    private BinaryTreeMaximumPathSum() {
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("the tree must contain at least one node");
        }
        int[] best = {Integer.MIN_VALUE};
        bestDownward(root, best);
        return best[0];
    }

    /** Returns the best single-sided path starting at {@code node}. */
    private static int bestDownward(TreeNode node, int[] best) {
        if (node == null) {
            return 0;
        }
        // A negative subtree is worth excluding entirely, hence the clamp.
        int left = Math.max(0, bestDownward(node.left, best));
        int right = Math.max(0, bestDownward(node.right, best));

        // Candidate for the answer: may use both sides.
        best[0] = Math.max(best[0], node.val + left + right);

        // Returned to the parent: at most one side may be used.
        return node.val + Math.max(left, right);
    }
}
