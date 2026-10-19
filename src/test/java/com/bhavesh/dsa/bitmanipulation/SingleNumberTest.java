package com.bhavesh.dsa.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SingleNumberTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(1, SingleNumber.singleNumber(new int[] {2, 2, 1}));
        assertEquals(4, SingleNumber.singleNumber(new int[] {4, 1, 2, 1, 2}));
        assertEquals(1, SingleNumber.singleNumber(new int[] {1}));
    }

    @Test
    @DisplayName("negative values and zero")
    void negativesAndZero() {
        assertEquals(-7, SingleNumber.singleNumber(new int[] {3, -7, 3}));
        assertEquals(0, SingleNumber.singleNumber(new int[] {5, 0, 5}));
    }

    @Test
    @DisplayName("extreme values")
    void extremes() {
        assertEquals(Integer.MIN_VALUE,
                SingleNumber.singleNumber(new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}));
    }

    @Test
    @DisplayName("empty and null inputs are rejected")
    void degenerate() {
        assertThrows(IllegalArgumentException.class, () -> SingleNumber.singleNumber(new int[] {}));
        assertThrows(IllegalArgumentException.class, () -> SingleNumber.singleNumber(null));
    }

    @Test
    @DisplayName("order does not matter, since XOR is commutative")
    void orderIndependent() {
        Random random = new Random(136136L);
        for (int trial = 0; trial < 500; trial++) {
            int unique = random.nextInt(1000) - 500;
            List<Integer> values = new ArrayList<>();
            values.add(unique);
            int pairs = random.nextInt(8);
            for (int i = 0; i < pairs; i++) {
                int paired = random.nextInt(1000) - 500;
                if (paired == unique) {
                    continue;
                }
                values.add(paired);
                values.add(paired);
            }
            Collections.shuffle(values, random);
            int[] nums = values.stream().mapToInt(Integer::intValue).toArray();
            assertEquals(unique, SingleNumber.singleNumber(nums),
                    () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    @Test
    @DisplayName("the two-unique variant finds both values")
    void twoUniqueValues() {
        int[] result = SingleNumber.singleNumberPair(new int[] {1, 2, 1, 3, 2, 5});
        Arrays.sort(result);
        assertEquals(3, result[0]);
        assertEquals(5, result[1]);
    }

    @Test
    @DisplayName("the two-unique variant works on randomised inputs")
    void twoUniqueRandomised() {
        Random random = new Random(260260L);
        for (int trial = 0; trial < 400; trial++) {
            int a = random.nextInt(2000) - 1000;
            int b = random.nextInt(2000) - 1000;
            if (a == b) {
                continue;
            }
            List<Integer> values = new ArrayList<>(List.of(a, b));
            for (int i = 0; i < random.nextInt(6); i++) {
                int paired = random.nextInt(2000) - 1000;
                if (paired == a || paired == b) {
                    continue;
                }
                values.add(paired);
                values.add(paired);
            }
            Collections.shuffle(values, random);
            int[] nums = values.stream().mapToInt(Integer::intValue).toArray();
            int[] found = SingleNumber.singleNumberPair(nums);
            Arrays.sort(found);
            int lower = Math.min(a, b);
            int higher = Math.max(a, b);
            assertEquals(lower, found[0], () -> "mismatch for " + Arrays.toString(nums));
            assertEquals(higher, found[1], () -> "mismatch for " + Arrays.toString(nums));
        }
    }
}
