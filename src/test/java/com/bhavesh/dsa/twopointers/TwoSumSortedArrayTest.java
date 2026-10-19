package com.bhavesh.dsa.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TwoSumSortedArrayTest {

    @Test
    @DisplayName("LeetCode example returns 1-based indices")
    void leetCodeExample() {
        assertArrayEquals(new int[] {1, 2}, TwoSumSortedArray.twoSum(new int[] {2, 7, 11, 15}, 9));
    }

    @Test
    @DisplayName("pair at the extremes")
    void extremes() {
        assertArrayEquals(new int[] {1, 3}, TwoSumSortedArray.twoSum(new int[] {2, 3, 4}, 6));
    }

    @Test
    @DisplayName("negative values")
    void negatives() {
        assertArrayEquals(new int[] {1, 2}, TwoSumSortedArray.twoSum(new int[] {-1, 0}, -1));
    }

    @Test
    @DisplayName("duplicates adjacent to one another")
    void duplicates() {
        assertArrayEquals(new int[] {1, 2}, TwoSumSortedArray.twoSum(new int[] {1, 1, 2, 3}, 2));
    }

    @Test
    @DisplayName("no pair reports rather than returning a wrong answer")
    void noPair() {
        assertThrows(IllegalArgumentException.class, () -> TwoSumSortedArray.twoSum(new int[] {1, 2, 3}, 99));
    }

    @Test
    @DisplayName("extreme values do not wrap when summed")
    void overflowSafety() {
        assertThrows(IllegalArgumentException.class,
                () -> TwoSumSortedArray.twoSum(new int[] {Integer.MAX_VALUE - 1, Integer.MAX_VALUE}, -2));
    }

    @Test
    @DisplayName("returns a valid pair for randomised sorted inputs")
    void randomisedSortedInputs() {
        Random random = new Random(606L);
        for (int trial = 0; trial < 500; trial++) {
            int[] numbers = new int[2 + random.nextInt(10)];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = random.nextInt(31) - 15;
            }
            Arrays.sort(numbers);
            int a = random.nextInt(numbers.length);
            int b = random.nextInt(numbers.length);
            if (a == b) {
                continue;
            }
            int target = numbers[a] + numbers[b];
            int[] result = TwoSumSortedArray.twoSum(numbers, target);
            org.junit.jupiter.api.Assertions.assertEquals(target,
                    numbers[result[0] - 1] + numbers[result[1] - 1],
                    () -> "indices do not sum to target for " + Arrays.toString(numbers));
            org.junit.jupiter.api.Assertions.assertTrue(result[0] < result[1], "indices must be increasing");
        }
    }
}
