package com.bhavesh.dsa.trees;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhavesh.dsa.support.Trees;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SubtreeOfAnotherTreeTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertTrue(SubtreeOfAnotherTree.isSubtree(Trees.of(3, 4, 5, 1, 2), Trees.of(4, 1, 2)));
        assertFalse(SubtreeOfAnotherTree.isSubtree(
                Trees.of(3, 4, 5, 1, 2, null, null, null, null, 0), Trees.of(4, 1, 2)));
    }

    @Test
    @DisplayName("the match must be a COMPLETE subtree, not a fragment")
    void mustBeCompleteSubtree() {
        // 4 -> (1, 2) exists in the big tree, but the real node 4 has an extra
        // descendant, so it is not a complete match.
        assertFalse(SubtreeOfAnotherTree.isSubtree(
                Trees.of(3, 4, 5, 1, 2, null, null, 0), Trees.of(4, 1, 2)));
    }

    @Test
    @DisplayName("a tree is a subtree of itself")
    void treeIsItsOwnSubtree() {
        assertTrue(SubtreeOfAnotherTree.isSubtree(Trees.of(1, 2, 3), Trees.of(1, 2, 3)));
    }

    @Test
    @DisplayName("single-node subtree found at a leaf")
    void singleNodeSubtree() {
        assertTrue(SubtreeOfAnotherTree.isSubtree(Trees.of(1, 2, 3), Trees.of(3)));
        assertFalse(SubtreeOfAnotherTree.isSubtree(Trees.of(1, 2, 3), Trees.of(4)));
    }

    @Test
    @DisplayName("empty trees")
    void emptyTrees() {
        assertTrue(SubtreeOfAnotherTree.isSubtree(Trees.of(1), null));
        assertTrue(SubtreeOfAnotherTree.isSubtree(null, null));
        assertFalse(SubtreeOfAnotherTree.isSubtree(null, Trees.of(1)));
    }

    @Test
    @DisplayName("a subtree larger than the host cannot match")
    void subtreeLargerThanHost() {
        assertFalse(SubtreeOfAnotherTree.isSubtree(Trees.of(1), Trees.of(1, 2, 3)));
    }

    @Test
    @DisplayName("repeated values do not cause a false positive")
    void repeatedValues() {
        // Every node is 1, but the shapes differ.
        assertFalse(SubtreeOfAnotherTree.isSubtree(Trees.of(1, 1), Trees.of(1, null, 1)));
        assertTrue(SubtreeOfAnotherTree.isSubtree(Trees.of(1, 1, 1), Trees.of(1)));
    }
}
