package com.bhavesh.dsa.hashing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TwoSumTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        assertArrayEquals(new int[] {0, 1}, TwoSum.twoSum(new int[] {2, 7, 11, 15}, 9));
    }

    @Test
    @DisplayName("the pair is not adjacent")
    void nonAdjacentPair() {
        assertArrayEquals(new int[] {1, 2}, TwoSum.twoSum(new int[] {3, 2, 4}, 6));
    }

    @Test
    @DisplayName("duplicate values: the same element must not pair with itself")
    void duplicateValues() {
        assertArrayEquals(new int[] {0, 1}, TwoSum.twoSum(new int[] {3, 3}, 6));
    }

    @Test
    @DisplayName("negative numbers and a negative target")
    void negatives() {
        assertArrayEquals(new int[] {0, 2}, TwoSum.twoSum(new int[] {-3, 4, -5}, -8));
    }

    @Test
    @DisplayName("target zero from two zeros")
    void zeros() {
        assertArrayEquals(new int[] {0, 1}, TwoSum.twoSum(new int[] {0, 0}, 0));
    }

    @Test
    @DisplayName("extreme values do not produce a false match through overflow")
    void overflowSafety() {
        // target - nums[i] overflows here; the wrapped value must not match.
        assertThrows(IllegalArgumentException.class,
                () -> TwoSum.twoSum(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE}, -2));
    }

    @Test
    @DisplayName("no valid pair is reported rather than returning a wrong answer")
    void noPair() {
        assertThrows(IllegalArgumentException.class, () -> TwoSum.twoSum(new int[] {1, 2, 3}, 100));
    }

    @Test
    @DisplayName("returned indices are distinct and actually sum to the target")
    void matchesBruteForce() {
        Random random = new Random(2468L);
        for (int trial = 0; trial < 500; trial++) {
            int[] nums = new int[2 + random.nextInt(10)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(21) - 10;
            }
            int a = random.nextInt(nums.length);
            int b = random.nextInt(nums.length);
            if (a == b) {
                continue;
            }
            int target = nums[a] + nums[b];
            int[] result = TwoSum.twoSum(nums, target);
            assertEquals(2, result.length);
            assertEquals(target, nums[result[0]] + nums[result[1]]);
            // The two indices must differ - the core correctness property.
            assertEquals(2, java.util.stream.IntStream.of(result).distinct().count());
        }
    }
}
