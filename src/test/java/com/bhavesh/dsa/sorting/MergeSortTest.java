package com.bhavesh.dsa.sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MergeSortTest {

    @Test
    @DisplayName("sorts a typical array")
    void sortsTypicalArray() {
        int[] nums = {5, 2, 9, 1, 5, 6};
        MergeSort.sort(nums);
        assertArrayEquals(new int[] {1, 2, 5, 5, 6, 9}, nums);
    }

    @Test
    @DisplayName("already sorted and reverse sorted")
    void sortedAndReversed() {
        int[] ascending = {1, 2, 3, 4};
        MergeSort.sort(ascending);
        assertArrayEquals(new int[] {1, 2, 3, 4}, ascending);

        int[] descending = {4, 3, 2, 1};
        MergeSort.sort(descending);
        assertArrayEquals(new int[] {1, 2, 3, 4}, descending);
    }

    @Test
    @DisplayName("empty, null and single element")
    void degenerate() {
        int[] empty = {};
        MergeSort.sort(empty);
        assertArrayEquals(new int[] {}, empty);

        MergeSort.sort(null);

        int[] single = {3};
        MergeSort.sort(single);
        assertArrayEquals(new int[] {3}, single);
    }

    @Test
    @DisplayName("all duplicates")
    void allDuplicates() {
        int[] nums = {7, 7, 7, 7};
        MergeSort.sort(nums);
        assertArrayEquals(new int[] {7, 7, 7, 7}, nums);
    }

    @Test
    @DisplayName("extreme values do not overflow in the midpoint or comparisons")
    void extremeValues() {
        int[] nums = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        MergeSort.sort(nums);
        assertArrayEquals(new int[] {Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, nums);
    }

    @Test
    @DisplayName("agrees with Arrays.sort on randomised inputs")
    void matchesJdk() {
        Random random = new Random(606606L);
        for (int trial = 0; trial < 500; trial++) {
            int[] nums = new int[random.nextInt(40)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(21) - 10;
            }
            int[] expected = nums.clone();
            Arrays.sort(expected);
            int[] actual = nums.clone();
            MergeSort.sort(actual);
            assertArrayEquals(expected, actual, () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    @Test
    @DisplayName("handles an input large enough to make an O(n^2) sort visibly slow")
    void largeInput() {
        Random random = new Random(1L);
        int[] nums = new int[200_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt();
        }
        int[] expected = nums.clone();
        Arrays.sort(expected);
        MergeSort.sort(nums);
        assertArrayEquals(expected, nums);
    }
}
