package com.bhavesh.dsa.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BinarySearchTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(4, BinarySearch.search(new int[] {-1, 0, 3, 5, 9, 12}, 9));
        assertEquals(-1, BinarySearch.search(new int[] {-1, 0, 3, 5, 9, 12}, 2));
    }

    @Test
    @DisplayName("empty, null and single-element arrays")
    void degenerate() {
        assertEquals(-1, BinarySearch.search(new int[] {}, 1));
        assertEquals(-1, BinarySearch.search(null, 1));
        assertEquals(0, BinarySearch.search(new int[] {5}, 5));
        assertEquals(-1, BinarySearch.search(new int[] {5}, 4));
    }

    @Test
    @DisplayName("targets at the first and last positions")
    void boundaries() {
        int[] nums = {1, 3, 5, 7, 9};
        assertEquals(0, BinarySearch.search(nums, 1));
        assertEquals(4, BinarySearch.search(nums, 9));
    }

    @Test
    @DisplayName("lowerBound and upperBound bracket a run of duplicates")
    void boundsOverDuplicates() {
        int[] nums = {1, 2, 2, 2, 5};
        assertEquals(1, BinarySearch.lowerBound(nums, 2));
        assertEquals(4, BinarySearch.upperBound(nums, 2));
        // The number of occurrences is the gap between the two bounds.
        assertEquals(3, BinarySearch.upperBound(nums, 2) - BinarySearch.lowerBound(nums, 2));
    }

    @Test
    @DisplayName("bounds for absent targets")
    void boundsForAbsentTargets() {
        int[] nums = {10, 20, 30};
        assertEquals(0, BinarySearch.lowerBound(nums, 5));
        assertEquals(3, BinarySearch.lowerBound(nums, 99));
        assertEquals(0, BinarySearch.upperBound(nums, 5));
        assertEquals(3, BinarySearch.upperBound(nums, 99));
    }

    @Test
    @DisplayName("agrees with Arrays.binarySearch on randomised sorted inputs")
    void matchesJdk() {
        Random random = new Random(704704L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[random.nextInt(14)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(20) - 10;
            }
            Arrays.sort(nums);
            int target = random.nextInt(22) - 11;
            int found = BinarySearch.search(nums, target);
            boolean present = Arrays.binarySearch(nums, target) >= 0;
            if (present) {
                assertTrue(found >= 0 && nums[found] == target,
                        () -> "expected to find " + target + " in " + Arrays.toString(nums));
            } else {
                assertEquals(-1, found, () -> target + " is absent from " + Arrays.toString(nums));
            }
        }
    }

    @Test
    @DisplayName("bounds match a linear-scan reference on randomised sorted inputs")
    void boundsMatchLinearScan() {
        Random random = new Random(99004L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[random.nextInt(12)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(8);
            }
            Arrays.sort(nums);
            int target = random.nextInt(10);

            int expectedLower = 0;
            while (expectedLower < nums.length && nums[expectedLower] < target) {
                expectedLower++;
            }
            int expectedUpper = 0;
            while (expectedUpper < nums.length && nums[expectedUpper] <= target) {
                expectedUpper++;
            }
            assertEquals(expectedLower, BinarySearch.lowerBound(nums, target));
            assertEquals(expectedUpper, BinarySearch.upperBound(nums, target));
        }
    }
}
