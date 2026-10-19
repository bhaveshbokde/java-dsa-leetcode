package com.bhavesh.dsa.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.bhavesh.dsa.support.TreeNode;
import com.bhavesh.dsa.support.Trees;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SerializeDeserializeBinaryTreeTest {

    private static void assertRoundTrips(TreeNode original) {
        String data = SerializeDeserializeBinaryTree.serialize(original);
        TreeNode rebuilt = SerializeDeserializeBinaryTree.deserialize(data);
        assertEquals(Trees.toLevelOrder(original), Trees.toLevelOrder(rebuilt),
                () -> "round trip failed for " + data);
    }

    @Test
    @DisplayName("LeetCode example round trips")
    void leetCodeExample() {
        assertRoundTrips(Trees.of(1, 2, 3, null, null, 4, 5));
    }

    @Test
    @DisplayName("empty tree")
    void emptyTree() {
        String data = SerializeDeserializeBinaryTree.serialize(null);
        assertNull(SerializeDeserializeBinaryTree.deserialize(data));
        assertNull(SerializeDeserializeBinaryTree.deserialize(""));
        assertNull(SerializeDeserializeBinaryTree.deserialize(null));
    }

    @Test
    @DisplayName("single node")
    void singleNode() {
        assertRoundTrips(Trees.of(42));
    }

    @Test
    @DisplayName("trees that a traversal alone could not distinguish")
    void shapeIsPreserved() {
        // Both have preorder values 1,2 - only the null markers separate them.
        TreeNode leftChild = Trees.of(1, 2);
        TreeNode rightChild = Trees.of(1, null, 2);
        String a = SerializeDeserializeBinaryTree.serialize(leftChild);
        String b = SerializeDeserializeBinaryTree.serialize(rightChild);
        org.junit.jupiter.api.Assertions.assertNotEquals(a, b,
                "the two shapes must serialise differently");
        assertRoundTrips(leftChild);
        assertRoundTrips(rightChild);
    }

    @Test
    @DisplayName("negative and extreme values survive the round trip")
    void extremeValues() {
        assertRoundTrips(Trees.of(-1, -2, -3));
        assertRoundTrips(Trees.of(Integer.MIN_VALUE, Integer.MAX_VALUE, 0));
    }

    @Test
    @DisplayName("multi-digit values are not conflated by the delimiter")
    void multiDigitValues() {
        // Without a delimiter, 1 followed by 2 would be indistinguishable from 12.
        assertRoundTrips(Trees.of(12, 1, 2));
        assertRoundTrips(Trees.of(1, 2, 3, 45, 6));
    }

    @Test
    @DisplayName("skewed trees in both directions")
    void skewedTrees() {
        TreeNode left = new TreeNode(0);
        TreeNode cursor = left;
        for (int i = 1; i < 200; i++) {
            cursor.left = new TreeNode(i);
            cursor = cursor.left;
        }
        assertRoundTrips(left);

        TreeNode right = new TreeNode(0);
        cursor = right;
        for (int i = 1; i < 200; i++) {
            cursor.right = new TreeNode(i);
            cursor = cursor.right;
        }
        assertRoundTrips(right);
    }

    @Test
    @DisplayName("serialising twice gives the same string (deterministic)")
    void deterministic() {
        TreeNode tree = Trees.of(5, 3, 8, 1, 4, 7, 9);
        assertEquals(SerializeDeserializeBinaryTree.serialize(tree),
                SerializeDeserializeBinaryTree.serialize(tree));
    }

    @Test
    @DisplayName("round trips randomly shaped trees, and is idempotent")
    void roundTripsRandomTrees() {
        Random random = new Random(297297L);
        for (int trial = 0; trial < 500; trial++) {
            TreeNode tree = randomTree(random, 0, 1 + random.nextInt(25));
            assertRoundTrips(tree);
            // Serialising the reconstruction must reproduce the same string.
            String once = SerializeDeserializeBinaryTree.serialize(tree);
            String twice = SerializeDeserializeBinaryTree.serialize(
                    SerializeDeserializeBinaryTree.deserialize(once));
            assertEquals(once, twice, "serialisation must be idempotent through a round trip");
        }
    }

    private static TreeNode randomTree(Random random, int depth, int budget) {
        if (budget <= 0 || depth > 8 || random.nextInt(4) == 0) {
            return null;
        }
        TreeNode node = new TreeNode(random.nextInt(201) - 100);
        node.left = randomTree(random, depth + 1, budget / 2);
        node.right = randomTree(random, depth + 1, budget / 2);
        return node;
    }

    @Test
    @DisplayName("the serialised form contains explicit null markers")
    void nullMarkersPresent() {
        String data = SerializeDeserializeBinaryTree.serialize(Trees.of(1));
        List<String> tokens = new ArrayList<>(List.of(data.split(",", -1)));
        tokens.removeIf(String::isEmpty);
        // One value plus two null children.
        assertEquals(List.of("1", "#", "#"), tokens);
    }
}
