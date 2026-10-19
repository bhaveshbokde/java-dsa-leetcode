package com.bhavesh.dsa.hashing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContainsDuplicateTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertTrue(ContainsDuplicate.containsDuplicate(new int[] {1, 2, 3, 1}));
        assertFalse(ContainsDuplicate.containsDuplicate(new int[] {1, 2, 3, 4}));
        assertTrue(ContainsDuplicate.containsDuplicate(new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }

    @Test
    @DisplayName("single element and empty arrays cannot contain a duplicate")
    void degenerate() {
        assertFalse(ContainsDuplicate.containsDuplicate(new int[] {1}));
        assertFalse(ContainsDuplicate.containsDuplicate(new int[] {}));
        assertFalse(ContainsDuplicate.containsDuplicate(null));
    }

    @Test
    @DisplayName("the duplicate is at the very end")
    void duplicateAtEnd() {
        assertTrue(ContainsDuplicate.containsDuplicate(new int[] {1, 2, 3, 4, 5, 1}));
    }

    @Test
    @DisplayName("negatives and extreme values")
    void negativesAndExtremes() {
        assertTrue(ContainsDuplicate.containsDuplicate(new int[] {-1, 2, -1}));
        assertTrue(ContainsDuplicate.containsDuplicate(
                new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}));
        assertFalse(ContainsDuplicate.containsDuplicate(
                new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 0}));
    }

    @Test
    @DisplayName("the sorting variant does not modify the caller's array")
    void sortingVariantDoesNotMutate() {
        int[] nums = {3, 1, 2};
        ContainsDuplicate.containsDuplicateBySorting(nums);
        org.junit.jupiter.api.Assertions.assertArrayEquals(new int[] {3, 1, 2}, nums);
    }

    @Test
    @DisplayName("both variants agree on randomised inputs")
    void variantsAgree() {
        Random random = new Random(217217L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[random.nextInt(12)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(8) - 4;
            }
            assertTrue(ContainsDuplicate.containsDuplicateBySorting(nums)
                            == ContainsDuplicate.containsDuplicate(nums),
                    () -> "variants disagreed on " + Arrays.toString(nums));
        }
    }

    @Test
    @DisplayName("a large all-distinct array is handled without false positives")
    void largeDistinctInput() {
        int[] nums = new int[200_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        assertFalse(ContainsDuplicate.containsDuplicate(nums));
    }
}
