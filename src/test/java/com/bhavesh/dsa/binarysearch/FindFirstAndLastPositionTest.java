package com.bhavesh.dsa.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FindFirstAndLastPositionTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertArrayEquals(new int[] {3, 4}, FindFirstAndLastPosition.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8));
        assertArrayEquals(new int[] {-1, -1}, FindFirstAndLastPosition.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6));
        assertArrayEquals(new int[] {-1, -1}, FindFirstAndLastPosition.searchRange(new int[] {}, 0));
    }

    @Test
    @DisplayName("a single occurrence yields an identical pair")
    void singleOccurrence() {
        assertArrayEquals(new int[] {1, 1}, FindFirstAndLastPosition.searchRange(new int[] {1, 2, 3}, 2));
    }

    @Test
    @DisplayName("the entire array is the target")
    void allSame() {
        assertArrayEquals(new int[] {0, 4}, FindFirstAndLastPosition.searchRange(new int[] {7, 7, 7, 7, 7}, 7));
    }

    @Test
    @DisplayName("target at the extremes")
    void extremes() {
        assertArrayEquals(new int[] {0, 1}, FindFirstAndLastPosition.searchRange(new int[] {1, 1, 2, 3}, 1));
        assertArrayEquals(new int[] {2, 3}, FindFirstAndLastPosition.searchRange(new int[] {1, 2, 3, 3}, 3));
    }

    @Test
    @DisplayName("null input")
    void nullInput() {
        assertArrayEquals(new int[] {-1, -1}, FindFirstAndLastPosition.searchRange(null, 1));
    }

    @Test
    @DisplayName("matches a linear-scan reference on randomised sorted inputs")
    void matchesLinearScan() {
        Random random = new Random(343434L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[random.nextInt(14)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(6);
            }
            Arrays.sort(nums);
            int target = random.nextInt(8);
            int first = -1;
            int last = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    if (first == -1) {
                        first = i;
                    }
                    last = i;
                }
            }
            assertArrayEquals(new int[] {first, last},
                    FindFirstAndLastPosition.searchRange(nums, target),
                    () -> "mismatch target=" + target + " nums=" + Arrays.toString(nums));
        }
    }
}
