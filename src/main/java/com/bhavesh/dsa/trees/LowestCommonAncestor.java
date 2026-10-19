package com.bhavesh.dsa.trees;

import com.bhavesh.dsa.support.TreeNode;

/**
 * LeetCode 236 - Lowest Common Ancestor of a Binary Tree (Medium).
 *
 * <p>Finds the deepest node having both {@code p} and {@code q} as descendants,
 * where a node counts as a descendant of itself.
 *
 * <p>Without parent pointers, collecting root-to-node paths and comparing them
 * works but costs O(n) extra space. The recursive formulation needs only the
 * call stack.
 *
 * <p>The function returns, for any subtree, either the LCA if both targets lie
 * within it, or whichever single target it contains, or null. Given that
 * contract, the combination rule at each node follows: if both children report
 * a non-null find, the targets lie in different subtrees and this node is the
 * answer. If only one does, the answer - or the only target seen so far - is
 * propagated upward unchanged.
 *
 * <p>The early return when a node matches a target is what makes the
 * "descendant of itself" case work: it stops before looking deeper, so an
 * ancestor target is reported rather than its descendant.
 *
 * <p>This assumes both nodes are present, as the problem guarantees. If one may
 * be absent the single-target result would be wrongly reported as an ancestor,
 * and a presence check is required.
 *
 * <p>Time O(n), auxiliary space O(h) for recursion.
 */
public final class LowestCommonAncestor {

    private LowestCommonAncestor() {
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * LeetCode 235 - the binary-search-tree variant.
     *
     * <p>Ordering turns the search into a single descent with no recursion over
     * both children: if both targets are smaller than the current node go left,
     * if both are larger go right, otherwise the paths diverge here and this
     * node is the answer.
     *
     * <p>Time O(h) - O(log n) on a balanced tree. Auxiliary space O(1).
     */
    public static TreeNode lowestCommonAncestorBst(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        while (current != null) {
            if (p.val < current.val && q.val < current.val) {
                current = current.left;
            } else if (p.val > current.val && q.val > current.val) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }
}
