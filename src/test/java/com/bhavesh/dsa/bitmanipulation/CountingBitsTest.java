package com.bhavesh.dsa.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CountingBitsTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertArrayEquals(new int[] {0, 1, 1}, CountingBits.countBits(2));
        assertArrayEquals(new int[] {0, 1, 1, 2, 1, 2}, CountingBits.countBits(5));
    }

    @Test
    @DisplayName("n = 0 yields a single zero")
    void zero() {
        assertArrayEquals(new int[] {0}, CountingBits.countBits(0));
    }

    @Test
    @DisplayName("negative n is rejected")
    void negative() {
        assertThrows(IllegalArgumentException.class, () -> CountingBits.countBits(-1));
        assertThrows(IllegalArgumentException.class, () -> CountingBits.countBitsByClearingLowestBit(-1));
    }

    @Test
    @DisplayName("agrees with Integer.bitCount for every value up to 20000")
    void matchesIntegerBitCount() {
        int n = 20_000;
        int[] bits = CountingBits.countBits(n);
        assertEquals(n + 1, bits.length);
        for (int i = 0; i <= n; i++) {
            assertEquals(Integer.bitCount(i), bits[i], "wrong count for " + i);
        }
    }

    @Test
    @DisplayName("both recurrences agree")
    void variantsAgree() {
        for (int n = 0; n <= 500; n++) {
            assertArrayEquals(CountingBits.countBits(n), CountingBits.countBitsByClearingLowestBit(n),
                    "variants disagree at n=" + n);
        }
    }

    @Test
    @DisplayName("powers of two have exactly one set bit")
    void powersOfTwo() {
        int[] bits = CountingBits.countBits(1024);
        for (int power = 1; power <= 1024; power *= 2) {
            assertEquals(1, bits[power], "2^k must have one set bit, failed at " + power);
        }
    }
}
