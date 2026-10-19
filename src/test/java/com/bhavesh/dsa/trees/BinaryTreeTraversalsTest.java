package com.bhavesh.dsa.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhavesh.dsa.support.TreeNode;
import com.bhavesh.dsa.support.Trees;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BinaryTreeTraversalsTest {

    @Test
    @DisplayName("LeetCode 104 example: depth 3")
    void maxDepthExample() {
        TreeNode root = Trees.of(3, 9, 20, null, null, 15, 7);
        assertEquals(3, BinaryTreeTraversals.maxDepth(root));
        assertEquals(3, BinaryTreeTraversals.maxDepthIterative(root));
    }

    @Test
    @DisplayName("empty tree has depth 0 and no levels")
    void emptyTree() {
        assertEquals(0, BinaryTreeTraversals.maxDepth(null));
        assertEquals(0, BinaryTreeTraversals.maxDepthIterative(null));
        assertTrue(BinaryTreeTraversals.levelOrder(null).isEmpty());
        assertTrue(BinaryTreeTraversals.inorder(null).isEmpty());
        assertTrue(BinaryTreeTraversals.preorder(null).isEmpty());
        assertTrue(BinaryTreeTraversals.postorder(null).isEmpty());
    }

    @Test
    @DisplayName("single node")
    void singleNode() {
        TreeNode root = Trees.of(1);
        assertEquals(1, BinaryTreeTraversals.maxDepth(root));
        assertEquals(List.of(List.of(1)), BinaryTreeTraversals.levelOrder(root));
        assertEquals(List.of(1), BinaryTreeTraversals.inorder(root));
    }

    @Test
    @DisplayName("LeetCode 102 example")
    void levelOrderExample() {
        TreeNode root = Trees.of(3, 9, 20, null, null, 15, 7);
        assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)),
                BinaryTreeTraversals.levelOrder(root));
    }

    @Test
    @DisplayName("the four depth-first orders on a known tree")
    void traversalOrders() {
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        TreeNode root = Trees.of(1, 2, 3, 4, 5);
        assertEquals(List.of(4, 2, 5, 1, 3), BinaryTreeTraversals.inorder(root));
        assertEquals(List.of(1, 2, 4, 5, 3), BinaryTreeTraversals.preorder(root));
        assertEquals(List.of(4, 5, 2, 3, 1), BinaryTreeTraversals.postorder(root));
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5)),
                BinaryTreeTraversals.levelOrder(root));
    }

    @Test
    @DisplayName("a left-skewed tree behaves like a linked list")
    void skewedTree() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 2; i <= 50; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        assertEquals(50, BinaryTreeTraversals.maxDepth(root));
        assertEquals(50, BinaryTreeTraversals.maxDepthIterative(root));
        assertEquals(50, BinaryTreeTraversals.levelOrder(root).size());
    }

    @Test
    @DisplayName("the iterative depth survives a tree far deeper than the recursion limit")
    void veryDeepTreeIterative() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i < 100_000; i++) {
            current.right = new TreeNode(i);
            current = current.right;
        }
        // The recursive form would overflow the stack here; the iterative one must not.
        assertEquals(100_000, BinaryTreeTraversals.maxDepthIterative(root));
    }

    @Test
    @DisplayName("in-order traversal agrees with the shared Trees helper")
    void agreesWithHelper() {
        TreeNode root = Trees.of(5, 3, 8, 1, 4, 7, 9);
        assertEquals(Trees.inorder(root), BinaryTreeTraversals.inorder(root));
    }
}
