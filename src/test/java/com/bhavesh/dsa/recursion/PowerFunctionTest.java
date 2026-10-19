package com.bhavesh.dsa.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PowerFunctionTest {

    private static final double TOLERANCE = 1e-9;

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(1024.0, PowerFunction.myPow(2.0, 10), TOLERANCE);
        assertEquals(9.261, PowerFunction.myPow(2.1, 3), TOLERANCE);
        assertEquals(0.25, PowerFunction.myPow(2.0, -2), TOLERANCE);
    }

    @Test
    @DisplayName("exponent zero is always one")
    void exponentZero() {
        assertEquals(1.0, PowerFunction.myPow(5.0, 0), TOLERANCE);
        assertEquals(1.0, PowerFunction.myPow(0.0, 0), TOLERANCE);
    }

    @Test
    @DisplayName("Integer.MIN_VALUE exponent does not overflow when negated")
    void minValueExponent() {
        // Negating MIN_VALUE in int arithmetic yields MIN_VALUE again; widening
        // to long first is what makes this finite and correct.
        double result = PowerFunction.myPow(2.0, Integer.MIN_VALUE);
        assertEquals(0.0, result, TOLERANCE);
        assertTrue(Double.isFinite(result));
        assertEquals(1.0, PowerFunction.myPow(1.0, Integer.MIN_VALUE), TOLERANCE);
    }

    @Test
    @DisplayName("base one and minus one")
    void unitBases() {
        assertEquals(1.0, PowerFunction.myPow(1.0, 1_000_000), TOLERANCE);
        assertEquals(1.0, PowerFunction.myPow(-1.0, 1_000_000), TOLERANCE);
        assertEquals(-1.0, PowerFunction.myPow(-1.0, 999_999), TOLERANCE);
    }

    @Test
    @DisplayName("agrees with Math.pow across randomised inputs")
    void matchesMathPow() {
        Random random = new Random(5050L);
        for (int trial = 0; trial < 2000; trial++) {
            double x = random.nextDouble() * 4 - 2;
            int n = random.nextInt(41) - 20;
            if (x == 0.0 && n < 0) {
                continue;
            }
            double expected = Math.pow(x, n);
            double actual = PowerFunction.myPow(x, n);
            assertEquals(expected, actual, Math.max(1e-9, Math.abs(expected) * 1e-9),
                    () -> "mismatch for " + x + "^" + n);
        }
    }

    @Test
    @DisplayName("powMod agrees with BigInteger.modPow")
    void powModMatchesBigInteger() {
        Random random = new Random(606060L);
        for (int trial = 0; trial < 2000; trial++) {
            long base = random.nextInt(1000);
            long exponent = random.nextInt(1000);
            long mod = 1 + random.nextInt(1_000_000);
            long expected = BigInteger.valueOf(base)
                    .modPow(BigInteger.valueOf(exponent), BigInteger.valueOf(mod))
                    .longValueExact();
            assertEquals(expected, PowerFunction.powMod(base, exponent, mod),
                    () -> base + "^" + exponent + " mod " + mod);
        }
    }

    @Test
    @DisplayName("powMod rejects a negative exponent and handles mod 1")
    void powModEdges() {
        assertThrows(IllegalArgumentException.class, () -> PowerFunction.powMod(2, -1, 7));
        assertEquals(0, PowerFunction.powMod(12345, 678, 1));
    }
}
