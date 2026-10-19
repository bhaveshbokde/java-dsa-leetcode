package com.bhavesh.dsa.bst;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhavesh.dsa.support.TreeNode;
import com.bhavesh.dsa.support.Trees;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidateBinarySearchTreeTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertTrue(ValidateBinarySearchTree.isValidBst(Trees.of(2, 1, 3)));
        assertFalse(ValidateBinarySearchTree.isValidBst(Trees.of(5, 1, 4, null, null, 3, 6)));
    }

    @Test
    @DisplayName("a violation only visible to a grandparent is caught")
    void grandparentViolation() {
        // 3 is in 5's right subtree but smaller than 5. Every parent-child
        // comparison passes, so a local-only check would wrongly accept this.
        TreeNode root = Trees.of(5, 1, 6, null, null, 3, 7);
        assertFalse(ValidateBinarySearchTree.isValidBst(root));
        assertFalse(ValidateBinarySearchTree.isValidBstInorder(root));
    }

    @Test
    @DisplayName("empty tree and single node are valid")
    void degenerate() {
        assertTrue(ValidateBinarySearchTree.isValidBst(null));
        assertTrue(ValidateBinarySearchTree.isValidBst(Trees.of(1)));
    }

    @Test
    @DisplayName("duplicate values are rejected, since the property is strict")
    void duplicatesRejected() {
        assertFalse(ValidateBinarySearchTree.isValidBst(Trees.of(2, 2, 3)));
        assertFalse(ValidateBinarySearchTree.isValidBst(Trees.of(2, 1, 2)));
    }

    @Test
    @DisplayName("extreme int values do not collide with the bound sentinels")
    void extremeValues() {
        assertTrue(ValidateBinarySearchTree.isValidBst(Trees.of(Integer.MIN_VALUE)));
        assertTrue(ValidateBinarySearchTree.isValidBst(Trees.of(Integer.MAX_VALUE)));
        // A MIN_VALUE left child of a MIN_VALUE root is invalid, not a sentinel clash.
        assertFalse(ValidateBinarySearchTree.isValidBst(Trees.of(Integer.MIN_VALUE, Integer.MIN_VALUE)));
        assertTrue(ValidateBinarySearchTree.isValidBst(
                Trees.of(0, Integer.MIN_VALUE, Integer.MAX_VALUE)));
    }

    @Test
    @DisplayName("a right-skewed increasing chain is a valid BST")
    void skewedValid() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 2; i <= 100; i++) {
            current.right = new TreeNode(i);
            current = current.right;
        }
        assertTrue(ValidateBinarySearchTree.isValidBst(root));
        assertTrue(ValidateBinarySearchTree.isValidBstInorder(root));
    }

    @Test
    @DisplayName("the bounds and in-order variants agree on assorted trees")
    void variantsAgree() {
        TreeNode[] trees = {
            Trees.of(2, 1, 3),
            Trees.of(5, 1, 4, null, null, 3, 6),
            Trees.of(10, 5, 15, null, null, 6, 20),
            Trees.of(1, null, 2, null, 3),
            Trees.of(3, 1, 5, 0, 2, 4, 6),
            null,
        };
        for (TreeNode tree : trees) {
            org.junit.jupiter.api.Assertions.assertEquals(
                    ValidateBinarySearchTree.isValidBst(tree),
                    ValidateBinarySearchTree.isValidBstInorder(tree),
                    () -> "variants disagree on " + Trees.toLevelOrder(tree));
        }
    }
}
