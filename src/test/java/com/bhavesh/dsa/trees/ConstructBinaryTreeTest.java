package com.bhavesh.dsa.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bhavesh.dsa.support.TreeNode;
import com.bhavesh.dsa.support.Trees;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConstructBinaryTreeTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        TreeNode root = ConstructBinaryTree.buildTree(
                new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7});
        assertEquals(Arrays.asList(3, 9, 20, null, null, 15, 7), Trees.toLevelOrder(root));

        assertEquals(List.of(-1), Trees.toLevelOrder(
                ConstructBinaryTree.buildTree(new int[] {-1}, new int[] {-1})));
    }

    @Test
    @DisplayName("empty traversals yield an empty tree")
    void emptyInput() {
        assertNull(ConstructBinaryTree.buildTree(new int[] {}, new int[] {}));
    }

    @Test
    @DisplayName("a left-skewed tree")
    void leftSkewed() {
        // preorder 1,2,3 with inorder 3,2,1 is a pure left chain.
        TreeNode root = ConstructBinaryTree.buildTree(new int[] {1, 2, 3}, new int[] {3, 2, 1});
        assertEquals(Arrays.asList(1, 2, null, 3), Trees.toLevelOrder(root));
    }

    @Test
    @DisplayName("a right-skewed tree")
    void rightSkewed() {
        TreeNode root = ConstructBinaryTree.buildTree(new int[] {1, 2, 3}, new int[] {1, 2, 3});
        assertEquals(Arrays.asList(1, null, 2, null, 3), Trees.toLevelOrder(root));
    }

    @Test
    @DisplayName("mismatched lengths, nulls and duplicates are rejected")
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class,
                () -> ConstructBinaryTree.buildTree(new int[] {1, 2}, new int[] {1}));
        assertThrows(IllegalArgumentException.class,
                () -> ConstructBinaryTree.buildTree(null, new int[] {1}));
        assertThrows(IllegalArgumentException.class,
                () -> ConstructBinaryTree.buildTree(new int[] {1, 1}, new int[] {1, 1}));
    }

    @Test
    @DisplayName("round trip: build from the traversals of a random tree and recover it")
    void roundTripOnRandomTrees() {
        Random random = new Random(105105L);
        for (int trial = 0; trial < 400; trial++) {
            int size = 1 + random.nextInt(20);
            Set<Integer> used = new LinkedHashSet<>();
            while (used.size() < size) {
                used.add(random.nextInt(500) - 250);
            }
            List<Integer> values = new ArrayList<>(used);
            TreeNode original = randomTree(values, 0, values.size() - 1, random);

            List<Integer> pre = new ArrayList<>();
            preorder(original, pre);
            List<Integer> in = Trees.inorder(original);

            TreeNode rebuilt = ConstructBinaryTree.buildTree(toArray(pre), toArray(in));
            assertEquals(Trees.toLevelOrder(original), Trees.toLevelOrder(rebuilt),
                    () -> "round trip failed for preorder " + pre);
        }
    }

    /** Builds an arbitrarily-shaped tree from a value pool. */
    private static TreeNode randomTree(List<Integer> values, int low, int high, Random random) {
        if (low > high) {
            return null;
        }
        int mid = low + random.nextInt(high - low + 1);
        TreeNode node = new TreeNode(values.get(mid));
        node.left = randomTree(values, low, mid - 1, random);
        node.right = randomTree(values, mid + 1, high, random);
        return node;
    }

    private static void preorder(TreeNode node, List<Integer> out) {
        if (node == null) {
            return;
        }
        out.add(node.val);
        preorder(node.left, out);
        preorder(node.right, out);
    }

    private static int[] toArray(List<Integer> list) {
        int[] out = new int[list.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = list.get(i);
        }
        return out;
    }
}
