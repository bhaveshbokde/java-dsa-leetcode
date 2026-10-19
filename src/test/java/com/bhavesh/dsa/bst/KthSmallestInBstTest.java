package com.bhavesh.dsa.bst;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bhavesh.dsa.support.TreeNode;
import com.bhavesh.dsa.support.Trees;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KthSmallestInBstTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(1, KthSmallestInBst.kthSmallest(Trees.of(3, 1, 4, null, 2), 1));
        assertEquals(3, KthSmallestInBst.kthSmallest(Trees.of(5, 3, 6, 2, 4, null, null, 1), 3));
    }

    @Test
    @DisplayName("k = 1 is the minimum and k = n the maximum")
    void extremesOfK() {
        TreeNode root = Trees.of(5, 3, 8, 1, 4, 7, 9);
        assertEquals(1, KthSmallestInBst.kthSmallest(root, 1));
        assertEquals(9, KthSmallestInBst.kthSmallest(root, 7));
    }

    @Test
    @DisplayName("single node")
    void singleNode() {
        assertEquals(42, KthSmallestInBst.kthSmallest(Trees.of(42), 1));
    }

    @Test
    @DisplayName("invalid k and empty trees are rejected")
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> KthSmallestInBst.kthSmallest(null, 1));
        assertThrows(IllegalArgumentException.class,
                () -> KthSmallestInBst.kthSmallest(Trees.of(1), 0));
        assertThrows(IllegalArgumentException.class,
                () -> KthSmallestInBst.kthSmallest(Trees.of(1, null, 2), 5));
    }

    @Test
    @DisplayName("a right-skewed BST behaves like a sorted list")
    void skewedTree() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 2; i <= 50; i++) {
            current.right = new TreeNode(i);
            current = current.right;
        }
        assertEquals(1, KthSmallestInBst.kthSmallest(root, 1));
        assertEquals(25, KthSmallestInBst.kthSmallest(root, 25));
        assertEquals(50, KthSmallestInBst.kthSmallest(root, 50));
    }

    @Test
    @DisplayName("agrees with the sorted in-order traversal for every k")
    void matchesInorderForEveryK() {
        TreeNode root = Trees.of(8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7);
        List<Integer> sorted = Trees.inorder(root);
        for (int k = 1; k <= sorted.size(); k++) {
            assertEquals(sorted.get(k - 1), KthSmallestInBst.kthSmallest(root, k),
                    "wrong value for k=" + k);
        }
    }

    @Test
    @DisplayName("agrees with a TreeSet on randomly built BSTs")
    void matchesTreeSet() {
        Random random = new Random(230230L);
        for (int trial = 0; trial < 300; trial++) {
            TreeSet<Integer> values = new TreeSet<>();
            TreeNode root = null;
            int size = 1 + random.nextInt(25);
            while (values.size() < size) {
                int v = random.nextInt(200) - 100;
                if (values.add(v)) {
                    root = insert(root, v);
                }
            }
            List<Integer> sorted = List.copyOf(values);
            for (int k = 1; k <= sorted.size(); k++) {
                assertEquals(sorted.get(k - 1), KthSmallestInBst.kthSmallest(root, k),
                        "mismatch at k=" + k);
            }
        }
    }

    private static TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.val) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        return node;
    }
}
