package com.bhavesh.dsa.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SumOfTwoIntegersTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(3, SumOfTwoIntegers.getSum(1, 2));
        assertEquals(5, SumOfTwoIntegers.getSum(2, 3));
    }

    @Test
    @DisplayName("zero is the identity")
    void zeroIdentity() {
        assertEquals(7, SumOfTwoIntegers.getSum(7, 0));
        assertEquals(7, SumOfTwoIntegers.getSum(0, 7));
        assertEquals(0, SumOfTwoIntegers.getSum(0, 0));
    }

    @Test
    @DisplayName("negative operands, which two's complement handles without special cases")
    void negatives() {
        assertEquals(-3, SumOfTwoIntegers.getSum(-1, -2));
        assertEquals(1, SumOfTwoIntegers.getSum(-1, 2));
        assertEquals(-1, SumOfTwoIntegers.getSum(1, -2));
        assertEquals(0, SumOfTwoIntegers.getSum(-5, 5));
    }

    @Test
    @DisplayName("extreme values, including deliberate wraparound matching int semantics")
    void extremes() {
        assertEquals(Integer.MAX_VALUE, SumOfTwoIntegers.getSum(Integer.MAX_VALUE, 0));
        assertEquals(Integer.MIN_VALUE, SumOfTwoIntegers.getSum(Integer.MAX_VALUE, 1));
        assertEquals(-2, SumOfTwoIntegers.getSum(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(0, SumOfTwoIntegers.getSum(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("agrees with ordinary addition across randomised inputs, wraparound included")
    void matchesOrdinaryAddition() {
        Random random = new Random(371371L);
        for (int trial = 0; trial < 50_000; trial++) {
            int a = random.nextInt();
            int b = random.nextInt();
            assertEquals(a + b, SumOfTwoIntegers.getSum(a, b), () -> a + " + " + b);
        }
    }

    @Test
    @DisplayName("subtraction agrees with ordinary subtraction")
    void matchesOrdinarySubtraction() {
        Random random = new Random(7171L);
        for (int trial = 0; trial < 20_000; trial++) {
            int a = random.nextInt();
            int b = random.nextInt();
            assertEquals(a - b, SumOfTwoIntegers.getDifference(a, b), () -> a + " - " + b);
        }
    }
}
