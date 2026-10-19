package com.bhavesh.dsa.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissingNumberTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(2, MissingNumber.missingNumber(new int[] {3, 0, 1}));
        assertEquals(2, MissingNumber.missingNumber(new int[] {0, 1}));
        assertEquals(8, MissingNumber.missingNumber(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    @Test
    @DisplayName("the missing value is 0")
    void missingZero() {
        assertEquals(0, MissingNumber.missingNumber(new int[] {1, 2, 3}));
    }

    @Test
    @DisplayName("the missing value is n")
    void missingN() {
        assertEquals(3, MissingNumber.missingNumber(new int[] {0, 1, 2}));
    }

    @Test
    @DisplayName("empty input means 0 is missing")
    void empty() {
        assertEquals(0, MissingNumber.missingNumber(new int[] {}));
    }

    @Test
    @DisplayName("null is rejected")
    void nullInput() {
        assertThrows(IllegalArgumentException.class, () -> MissingNumber.missingNumber(null));
    }

    @Test
    @DisplayName("both variants agree on randomised inputs")
    void variantsAgree() {
        Random random = new Random(268268L);
        for (int trial = 0; trial < 800; trial++) {
            int n = 1 + random.nextInt(20);
            int missing = random.nextInt(n + 1);
            List<Integer> values = new ArrayList<>();
            for (int v = 0; v <= n; v++) {
                if (v != missing) {
                    values.add(v);
                }
            }
            Collections.shuffle(values, random);
            int[] nums = values.stream().mapToInt(Integer::intValue).toArray();
            assertEquals(missing, MissingNumber.missingNumber(nums), "XOR variant failed");
            assertEquals(missing, MissingNumber.missingNumberBySum(nums), "sum variant failed");
        }
    }

    @Test
    @DisplayName("a large input where an int sum would overflow is still handled")
    void largeInputDoesNotOverflow() {
        // n = 100000 gives a total of about 5e9, well past Integer.MAX_VALUE.
        int n = 100_000;
        int missing = 54_321;
        int[] nums = new int[n];
        int at = 0;
        for (int v = 0; v <= n; v++) {
            if (v != missing) {
                nums[at++] = v;
            }
        }
        assertEquals(missing, MissingNumber.missingNumber(nums));
        assertEquals(missing, MissingNumber.missingNumberBySum(nums));
    }
}
