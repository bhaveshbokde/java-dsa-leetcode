package com.bhavesh.dsa.sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SortColorsTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[] {0, 0, 1, 1, 2, 2}, nums);
    }

    @Test
    @DisplayName("already sorted")
    void alreadySorted() {
        int[] nums = {0, 1, 2};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[] {0, 1, 2}, nums);
    }

    @Test
    @DisplayName("reverse sorted")
    void reverseSorted() {
        int[] nums = {2, 2, 1, 1, 0, 0};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[] {0, 0, 1, 1, 2, 2}, nums);
    }

    @Test
    @DisplayName("a single colour throughout")
    void singleColour() {
        for (int colour = 0; colour <= 2; colour++) {
            int[] nums = {colour, colour, colour};
            SortColors.sortColors(nums);
            assertArrayEquals(new int[] {colour, colour, colour}, nums);
        }
    }

    @Test
    @DisplayName("empty, null and single element")
    void degenerate() {
        int[] empty = {};
        SortColors.sortColors(empty);
        assertArrayEquals(new int[] {}, empty);

        SortColors.sortColors(null);

        int[] single = {1};
        SortColors.sortColors(single);
        assertArrayEquals(new int[] {1}, single);
    }

    @Test
    @DisplayName("values outside 0..2 are rejected rather than silently mis-sorted")
    void invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> SortColors.sortColors(new int[] {0, 3, 1}));
    }

    @Test
    @DisplayName("matches Arrays.sort on randomised colour arrays")
    void matchesJdk() {
        Random random = new Random(757575L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[random.nextInt(16)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(3);
            }
            int[] expected = nums.clone();
            Arrays.sort(expected);
            int[] actual = nums.clone();
            SortColors.sortColors(actual);
            assertArrayEquals(expected, actual, () -> "mismatch for " + Arrays.toString(nums));
        }
    }
}
