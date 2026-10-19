package com.bhavesh.dsa.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.bhavesh.dsa.support.TreeNode;
import com.bhavesh.dsa.support.Trees;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LowestCommonAncestorTest {

    private static TreeNode find(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.val == value) {
            return root;
        }
        TreeNode left = find(root.left, value);
        return left != null ? left : find(root.right, value);
    }

    @Test
    @DisplayName("LeetCode example: LCA of 5 and 1 is 3")
    void leetCodeExample() {
        TreeNode root = Trees.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode lca = LowestCommonAncestor.lowestCommonAncestor(root, find(root, 5), find(root, 1));
        assertEquals(3, lca.val);
    }

    @Test
    @DisplayName("a node is its own descendant: LCA of 5 and 4 is 5")
    void nodeIsItsOwnDescendant() {
        TreeNode root = Trees.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode lca = LowestCommonAncestor.lowestCommonAncestor(root, find(root, 5), find(root, 4));
        assertEquals(5, lca.val);
    }

    @Test
    @DisplayName("both targets are the same node")
    void sameNode() {
        TreeNode root = Trees.of(1, 2, 3);
        TreeNode two = find(root, 2);
        assertSame(two, LowestCommonAncestor.lowestCommonAncestor(root, two, two));
    }

    @Test
    @DisplayName("the root itself is the ancestor")
    void rootIsAncestor() {
        TreeNode root = Trees.of(1, 2, 3);
        TreeNode lca = LowestCommonAncestor.lowestCommonAncestor(root, find(root, 2), find(root, 3));
        assertSame(root, lca);
    }

    @Test
    @DisplayName("a null tree yields null")
    void nullTree() {
        assertNull(LowestCommonAncestor.lowestCommonAncestor(null, new TreeNode(1), new TreeNode(2)));
    }

    @Test
    @DisplayName("a skewed tree: the shallower node is the ancestor")
    void skewedTree() {
        TreeNode root = Trees.of(1, 2, null, 3, null, 4);
        TreeNode lca = LowestCommonAncestor.lowestCommonAncestor(root, find(root, 2), find(root, 4));
        assertEquals(2, lca.val);
    }

    @Test
    @DisplayName("the BST variant agrees with the general one on a BST")
    void bstVariantAgrees() {
        TreeNode root = Trees.of(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
        int[][] pairs = {{2, 8}, {2, 4}, {3, 5}, {0, 5}, {7, 9}, {2, 2}};
        for (int[] pair : pairs) {
            TreeNode p = find(root, pair[0]);
            TreeNode q = find(root, pair[1]);
            assertEquals(LowestCommonAncestor.lowestCommonAncestor(root, p, q).val,
                    LowestCommonAncestor.lowestCommonAncestorBst(root, p, q).val,
                    "variants disagree for " + pair[0] + " and " + pair[1]);
        }
    }
}
