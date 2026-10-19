package com.bhavesh.dsa.trees;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhavesh.dsa.support.Trees;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SameTreeTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertTrue(SameTree.isSameTree(Trees.of(1, 2, 3), Trees.of(1, 2, 3)));
        assertFalse(SameTree.isSameTree(Trees.of(1, 2), Trees.of(1, null, 2)));
        assertFalse(SameTree.isSameTree(Trees.of(1, 2, 1), Trees.of(1, 1, 2)));
    }

    @Test
    @DisplayName("structure matters, not just the traversal sequence")
    void structureMatters() {
        // Both have pre-order 1,2 but differ structurally.
        assertFalse(SameTree.isSameTree(Trees.of(1, 2), Trees.of(1, null, 2)));
    }

    @Test
    @DisplayName("both empty trees are the same")
    void bothEmpty() {
        assertTrue(SameTree.isSameTree(null, null));
    }

    @Test
    @DisplayName("one empty tree is never the same as a non-empty one")
    void oneEmpty() {
        assertFalse(SameTree.isSameTree(null, Trees.of(1)));
        assertFalse(SameTree.isSameTree(Trees.of(1), null));
    }

    @Test
    @DisplayName("identical structure with one differing value")
    void oneValueDiffers() {
        assertFalse(SameTree.isSameTree(Trees.of(1, 2, 3), Trees.of(1, 2, 4)));
    }

    @Test
    @DisplayName("negative and extreme values compare correctly")
    void extremeValues() {
        assertTrue(SameTree.isSameTree(Trees.of(Integer.MIN_VALUE), Trees.of(Integer.MIN_VALUE)));
        assertFalse(SameTree.isSameTree(Trees.of(Integer.MIN_VALUE), Trees.of(Integer.MAX_VALUE)));
    }

    @Test
    @DisplayName("the recursive and iterative forms agree")
    void formsAgree() {
        Integer[][][] cases = {
            {{1, 2, 3}, {1, 2, 3}},
            {{1, 2}, {1, null, 2}},
            {{1}, {1}},
            {{1, 2, 3}, {1, 2, 4}},
            {{}, {}},
            {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}},
        };
        for (Integer[][] pair : cases) {
            boolean recursive = SameTree.isSameTree(Trees.of(pair[0]), Trees.of(pair[1]));
            boolean iterative = SameTree.isSameTreeIterative(Trees.of(pair[0]), Trees.of(pair[1]));
            org.junit.jupiter.api.Assertions.assertEquals(recursive, iterative,
                    () -> "forms disagree for " + java.util.Arrays.deepToString(pair));
        }
    }
}
