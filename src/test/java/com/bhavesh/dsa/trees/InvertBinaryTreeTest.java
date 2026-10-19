package com.bhavesh.dsa.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.bhavesh.dsa.support.TreeNode;
import com.bhavesh.dsa.support.Trees;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InvertBinaryTreeTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        TreeNode inverted = InvertBinaryTree.invert(Trees.of(4, 2, 7, 1, 3, 6, 9));
        assertEquals(List.of(4, 7, 2, 9, 6, 3, 1), Trees.toLevelOrder(inverted));
    }

    @Test
    @DisplayName("empty tree and single node")
    void degenerate() {
        assertNull(InvertBinaryTree.invert(null));
        assertEquals(List.of(1), Trees.toLevelOrder(InvertBinaryTree.invert(Trees.of(1))));
    }

    @Test
    @DisplayName("a left-skewed tree becomes right-skewed")
    void skewedTree() {
        TreeNode root = Trees.of(1, 2, null, 3);
        TreeNode inverted = InvertBinaryTree.invert(root);
        // List.of rejects null elements; the level-order notation needs them.
        assertEquals(java.util.Arrays.asList(1, null, 2, null, 3), Trees.toLevelOrder(inverted));
    }

    @Test
    @DisplayName("inverting twice restores the original tree")
    void doubleInversionIsIdentity() {
        Integer[] level = {4, 2, 7, 1, 3, 6, 9};
        List<Integer> original = Trees.toLevelOrder(Trees.of(level));
        TreeNode twice = InvertBinaryTree.invert(InvertBinaryTree.invert(Trees.of(level)));
        assertEquals(original, Trees.toLevelOrder(twice));
    }

    @Test
    @DisplayName("the in-order traversal of the inverted tree is the reverse of the original")
    void inorderReverses() {
        TreeNode original = Trees.of(4, 2, 7, 1, 3, 6, 9);
        List<Integer> before = Trees.inorder(original);
        List<Integer> after = Trees.inorder(InvertBinaryTree.invert(original));
        java.util.Collections.reverse(after);
        assertEquals(before, after);
    }

    @Test
    @DisplayName("the recursive and iterative forms agree")
    void formsAgree() {
        Integer[][] cases = {
            {4, 2, 7, 1, 3, 6, 9},
            {1},
            {1, 2, null, 3},
            {1, null, 2, null, 3},
            {5, 3, 8, 1, 4, 7, 9},
        };
        for (Integer[] level : cases) {
            assertEquals(Trees.toLevelOrder(InvertBinaryTree.invert(Trees.of(level))),
                    Trees.toLevelOrder(InvertBinaryTree.invertIterative(Trees.of(level))),
                    () -> "forms disagree for " + java.util.Arrays.toString(level));
        }
    }

    @Test
    @DisplayName("the iterative form survives a tree deeper than the recursion limit")
    void veryDeepTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i < 60_000; i++) {
            current.right = new TreeNode(i);
            current = current.right;
        }
        TreeNode inverted = InvertBinaryTree.invertIterative(root);
        // Every node moved to the left spine.
        assertEquals(1, inverted.left.val);
        assertNull(inverted.right);
    }
}
