package com.bhavesh.dsa.fundamentals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlgorithmAnalysisTest {

    @Test
    @DisplayName("the loop and closed form agree for every small n")
    void summationFormsAgree() {
        for (int n = 0; n <= 2000; n++) {
            assertEquals(AlgorithmAnalysis.sumToNByLoop(n), AlgorithmAnalysis.sumToNByFormula(n),
                    "forms disagree at n=" + n);
        }
    }

    @Test
    @DisplayName("known summation values")
    void knownSums() {
        assertEquals(0, AlgorithmAnalysis.sumToNByFormula(0));
        assertEquals(1, AlgorithmAnalysis.sumToNByFormula(1));
        assertEquals(55, AlgorithmAnalysis.sumToNByFormula(10));
        assertEquals(5_000_050_000L, AlgorithmAnalysis.sumToNByFormula(100_000));
    }

    @Test
    @DisplayName("the closed form stays exact past int range")
    void closedFormDoesNotOverflow() {
        // n*(n+1) overflows an int above roughly 46340; the result must still be exact.
        long result = AlgorithmAnalysis.sumToNByFormula(1_000_000);
        assertEquals(500_000_500_000L, result);
        assertTrue(result > Integer.MAX_VALUE);
    }

    @Test
    @DisplayName("negative inputs are rejected")
    void negativeRejected() {
        assertThrows(IllegalArgumentException.class, () -> AlgorithmAnalysis.sumToNByLoop(-1));
        assertThrows(IllegalArgumentException.class, () -> AlgorithmAnalysis.sumToNByFormula(-1));
        assertThrows(IllegalArgumentException.class, () -> AlgorithmAnalysis.fibonacciIterative(-1));
    }

    @Test
    @DisplayName("all three Fibonacci forms agree where the naive one is still feasible")
    void fibonacciFormsAgree() {
        for (int n = 0; n <= 25; n++) {
            long naive = AlgorithmAnalysis.fibonacciNaive(n);
            assertEquals(naive, AlgorithmAnalysis.fibonacciMemoised(n), "memoised differs at n=" + n);
            assertEquals(naive, AlgorithmAnalysis.fibonacciIterative(n), "iterative differs at n=" + n);
        }
    }

    @Test
    @DisplayName("the linear forms agree far beyond where the naive one is usable")
    void linearFormsAgreeAtScale() {
        for (int n = 26; n <= 92; n++) {
            assertEquals(AlgorithmAnalysis.fibonacciMemoised(n), AlgorithmAnalysis.fibonacciIterative(n),
                    "forms disagree at n=" + n);
        }
    }

    @Test
    @DisplayName("known Fibonacci values including the largest that fits in a long")
    void knownFibonacciValues() {
        assertEquals(0, AlgorithmAnalysis.fibonacciIterative(0));
        assertEquals(1, AlgorithmAnalysis.fibonacciIterative(1));
        assertEquals(55, AlgorithmAnalysis.fibonacciIterative(10));
        assertEquals(832_040, AlgorithmAnalysis.fibonacciIterative(30));
        assertEquals(7_540_113_804_746_346_429L, AlgorithmAnalysis.fibonacciIterative(92));
    }

    @Test
    @DisplayName("inputs beyond the exact range are rejected rather than wrapping")
    void fibonacciOverflowRejected() {
        assertThrows(IllegalArgumentException.class, () -> AlgorithmAnalysis.fibonacciIterative(93));
        assertThrows(IllegalArgumentException.class, () -> AlgorithmAnalysis.fibonacciMemoised(93));
    }
}
