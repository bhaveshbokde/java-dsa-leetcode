package com.bhavesh.dsa.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KthLargestElementTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(5, KthLargestElement.findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2));
        assertEquals(4, KthLargestElement.findKthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test
    @DisplayName("k = 1 is the maximum and k = n the minimum")
    void extremesOfK() {
        int[] nums = {7, 2, 9, 4};
        assertEquals(9, KthLargestElement.findKthLargest(nums, 1));
        assertEquals(2, KthLargestElement.findKthLargest(nums, 4));
    }

    @Test
    @DisplayName("duplicates count individually, not as one distinct value")
    void duplicatesCountIndividually() {
        // The 2nd largest of [5,5,4] is 5, not 4.
        assertEquals(5, KthLargestElement.findKthLargest(new int[] {5, 5, 4}, 2));
    }

    @Test
    @DisplayName("single element")
    void singleElement() {
        assertEquals(3, KthLargestElement.findKthLargest(new int[] {3}, 1));
    }

    @Test
    @DisplayName("negatives and extreme values")
    void negativesAndExtremes() {
        assertEquals(-2, KthLargestElement.findKthLargest(new int[] {-5, -2, -9}, 1));
        assertEquals(Integer.MIN_VALUE,
                KthLargestElement.findKthLargest(new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE}, 2));
    }

    @Test
    @DisplayName("invalid k and empty inputs are rejected")
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> KthLargestElement.findKthLargest(new int[] {1}, 0));
        assertThrows(IllegalArgumentException.class, () -> KthLargestElement.findKthLargest(new int[] {1}, 2));
        assertThrows(IllegalArgumentException.class, () -> KthLargestElement.findKthLargest(new int[] {}, 1));
        assertThrows(IllegalArgumentException.class, () -> KthLargestElement.findKthLargest(null, 1));
    }

    @Test
    @DisplayName("quickselect does not modify the caller's array")
    void quickselectDoesNotMutate() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        KthLargestElement.findKthLargestByQuickselect(nums, 2);
        org.junit.jupiter.api.Assertions.assertArrayEquals(new int[] {3, 2, 1, 5, 6, 4}, nums);
    }

    @Test
    @DisplayName("both approaches match a sort-based reference on randomised inputs")
    void bothMatchSortReference() {
        Random random = new Random(215215L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[1 + random.nextInt(15)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(21) - 10;
            }
            int k = 1 + random.nextInt(nums.length);
            int[] sorted = nums.clone();
            Arrays.sort(sorted);
            int expected = sorted[sorted.length - k];
            assertEquals(expected, KthLargestElement.findKthLargest(nums.clone(), k),
                    () -> "heap mismatch k=" + k + " " + Arrays.toString(nums));
            assertEquals(expected, KthLargestElement.findKthLargestByQuickselect(nums.clone(), k),
                    () -> "quickselect mismatch k=" + k + " " + Arrays.toString(nums));
        }
    }

    @Test
    @DisplayName("quickselect stays fast on already-sorted input thanks to the random pivot")
    void sortedInputDoesNotDegrade() {
        int[] nums = new int[100_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        // With a fixed last-element pivot this input is the O(n^2) worst case.
        assertEquals(99_000, KthLargestElement.findKthLargestByQuickselect(nums, 1000));
    }
}
