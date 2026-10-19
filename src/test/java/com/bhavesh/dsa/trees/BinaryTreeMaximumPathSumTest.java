package com.bhavesh.dsa.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bhavesh.dsa.support.TreeNode;
import com.bhavesh.dsa.support.Trees;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BinaryTreeMaximumPathSumTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(6, BinaryTreeMaximumPathSum.maxPathSum(Trees.of(1, 2, 3)));
        assertEquals(42, BinaryTreeMaximumPathSum.maxPathSum(Trees.of(-10, 9, 20, null, null, 15, 7)));
    }

    @Test
    @DisplayName("single node")
    void singleNode() {
        assertEquals(5, BinaryTreeMaximumPathSum.maxPathSum(Trees.of(5)));
        assertEquals(-5, BinaryTreeMaximumPathSum.maxPathSum(Trees.of(-5)));
    }

    @Test
    @DisplayName("all negative values: the answer is the largest single node, not zero")
    void allNegative() {
        // Clamping child contributions must not clamp the node's own value.
        assertEquals(-1, BinaryTreeMaximumPathSum.maxPathSum(Trees.of(-3, -1, -2)));
    }

    @Test
    @DisplayName("a negative subtree is excluded")
    void negativeSubtreeExcluded() {
        assertEquals(11, BinaryTreeMaximumPathSum.maxPathSum(Trees.of(5, 6, -100)));
    }

    @Test
    @DisplayName("the best path need not pass through the root")
    void pathAvoidsRoot() {
        //      -10
        //      /  \
        //     20   1
        //    /  \
        //   15   7
        assertEquals(42, BinaryTreeMaximumPathSum.maxPathSum(Trees.of(-10, 20, 1, 15, 7)));
    }

    @Test
    @DisplayName("an empty tree is rejected, since a path needs at least one node")
    void emptyTree() {
        assertThrows(IllegalArgumentException.class, () -> BinaryTreeMaximumPathSum.maxPathSum(null));
    }

    @Test
    @DisplayName("a left-skewed chain of positives sums the whole chain")
    void skewedPositiveChain() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        int expected = 1;
        for (int i = 2; i <= 100; i++) {
            current.left = new TreeNode(i);
            current = current.left;
            expected += i;
        }
        assertEquals(expected, BinaryTreeMaximumPathSum.maxPathSum(root));
    }
}
