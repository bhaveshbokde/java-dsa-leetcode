package com.bhavesh.dsa.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CountPrimesTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(4, CountPrimes.countPrimes(10));
        assertEquals(0, CountPrimes.countPrimes(0));
        assertEquals(0, CountPrimes.countPrimes(1));
    }

    @Test
    @DisplayName("boundaries around the first prime")
    void smallBoundaries() {
        assertEquals(0, CountPrimes.countPrimes(2));
        assertEquals(1, CountPrimes.countPrimes(3));
        assertEquals(2, CountPrimes.countPrimes(4));
        assertEquals(2, CountPrimes.countPrimes(5));
    }

    @Test
    @DisplayName("negative input")
    void negative() {
        assertEquals(0, CountPrimes.countPrimes(-10));
    }

    @Test
    @DisplayName("the bound is exclusive: countPrimes(7) excludes 7 itself")
    void boundIsExclusive() {
        assertEquals(3, CountPrimes.countPrimes(7));   // 2, 3, 5
        assertEquals(4, CountPrimes.countPrimes(8));   // 2, 3, 5, 7
    }

    @Test
    @DisplayName("known values of the prime-counting function")
    void knownValues() {
        assertEquals(25, CountPrimes.countPrimes(100));
        assertEquals(168, CountPrimes.countPrimes(1000));
        assertEquals(1229, CountPrimes.countPrimes(10_000));
        assertEquals(9592, CountPrimes.countPrimes(100_000));
        assertEquals(78_498, CountPrimes.countPrimes(1_000_000));
    }

    @Test
    @DisplayName("matches trial division for every bound up to 500")
    void matchesTrialDivision() {
        for (int n = 0; n <= 500; n++) {
            assertEquals(trialDivisionCount(n), CountPrimes.countPrimes(n), "mismatch at n=" + n);
        }
    }

    @Test
    @DisplayName("a bound large enough that p*p overflows an int is handled")
    void largeBound() {
        // Primes above 46341 have squares beyond Integer.MAX_VALUE; computing
        // p*p in int would wrap negative. 5000000 exercises that path.
        assertEquals(348_513, CountPrimes.countPrimes(5_000_000));
    }

    private static int trialDivisionCount(int n) {
        int count = 0;
        for (int candidate = 2; candidate < n; candidate++) {
            boolean prime = true;
            for (int divisor = 2; (long) divisor * divisor <= candidate; divisor++) {
                if (candidate % divisor == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                count++;
            }
        }
        return count;
    }
}
