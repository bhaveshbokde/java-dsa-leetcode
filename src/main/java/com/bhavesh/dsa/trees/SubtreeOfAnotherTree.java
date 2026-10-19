package com.bhavesh.dsa.trees;

import com.bhavesh.dsa.support.TreeNode;

/**
 * LeetCode 572 - Subtree of Another Tree (Easy by label, but the naive
 * solution is quadratic).
 *
 * <p>Decides whether {@code subRoot} appears as a <em>complete</em> subtree of
 * {@code root} - meaning some node of {@code root}, together with all of its
 * descendants, equals {@code subRoot} exactly.
 *
 * <p>"Complete" is the word that matters. Matching a fragment is not enough:
 * the candidate node's entire subtree must match, which is exactly the
 * {@link SameTree} comparison. So this problem is "try {@code isSameTree} at
 * every node", and it reuses that implementation rather than restating it.
 *
 * <p>The early check {@code root.val == subRoot.val} is a pure optimisation -
 * it avoids calling the full comparison at nodes that cannot match - but the
 * recursion into both children must happen regardless, because a match can sit
 * deeper even when the current node's value coincides.
 *
 * <p>An asymptotically better solution exists: serialise both trees with
 * explicit null markers and run a string search (KMP), giving O(n + m). It is
 * worth knowing, but it needs care with delimiters so that a value like 12 does
 * not match inside 123.
 *
 * <p>Time O(n * m) worst case, auxiliary space O(h).
 */
public final class SubtreeOfAnotherTree {

    private SubtreeOfAnotherTree() {
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            // The empty tree is a subtree of anything, including the empty tree.
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.val == subRoot.val && SameTree.isSameTree(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
}
