package com.bhavesh.dsa.trees;

import com.bhavesh.dsa.support.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 105 - Construct Binary Tree from Preorder and Inorder Traversal
 * (Medium).
 *
 * <p>Rebuilds a tree from two traversals. Values are guaranteed distinct, which
 * the algorithm depends on.
 *
 * <p>Neither traversal alone determines a tree - preorder {@code [1,2]} could
 * mean 2 is a left child or a right child. Together they do, because each
 * supplies what the other lacks:
 *
 * <ul>
 *   <li><b>Preorder</b> gives the <em>root first</em>, so the next unconsumed
 *       preorder value is always the root of the subtree being built.</li>
 *   <li><b>Inorder</b> gives the <em>split</em>: everything before the root's
 *       position belongs to the left subtree, everything after to the right.</li>
 * </ul>
 *
 * <p>Scanning the inorder array for the root at each step makes this O(n^2) on
 * a skewed tree. A value-to-index map built once reduces each lookup to O(1)
 * and the whole construction to O(n) - and that map is the reason distinct
 * values matter, since duplicates would make the split ambiguous.
 *
 * <p>The preorder cursor is a single shared field-like counter rather than an
 * index recomputed per call: preorder consumption is strictly sequential, and
 * recomputing it from subtree sizes is where off-by-one errors appear.
 *
 * <p>Time O(n), auxiliary space O(n) for the map plus O(h) recursion.
 */
public final class ConstructBinaryTree {

    private ConstructBinaryTree() {
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            throw new IllegalArgumentException("both traversals are required and must be equal length");
        }
        if (preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> inorderIndex = new HashMap<>(inorder.length * 2);
        for (int i = 0; i < inorder.length; i++) {
            if (inorderIndex.put(inorder[i], i) != null) {
                throw new IllegalArgumentException("values must be distinct; duplicate " + inorder[i]);
            }
        }
        // A one-element array as a mutable cursor shared across the recursion.
        int[] preorderCursor = {0};
        return build(preorder, preorderCursor, 0, inorder.length - 1, inorderIndex);
    }

    private static TreeNode build(int[] preorder, int[] cursor,
                                  int inorderLow, int inorderHigh,
                                  Map<Integer, Integer> inorderIndex) {
        if (inorderLow > inorderHigh) {
            return null;
        }
        int rootValue = preorder[cursor[0]++];
        TreeNode root = new TreeNode(rootValue);

        Integer split = inorderIndex.get(rootValue);
        if (split == null) {
            throw new IllegalArgumentException("traversals are inconsistent: " + rootValue
                    + " is absent from the inorder array");
        }
        // Left must be built first: preorder lists the entire left subtree
        // before the right, and the cursor advances in that order.
        root.left = build(preorder, cursor, inorderLow, split - 1, inorderIndex);
        root.right = build(preorder, cursor, split + 1, inorderHigh, inorderIndex);
        return root;
    }
}
