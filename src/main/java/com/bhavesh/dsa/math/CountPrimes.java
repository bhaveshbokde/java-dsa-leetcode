package com.bhavesh.dsa.math;

/**
 * LeetCode 204 - Count Primes (Medium). Sieve of Eratosthenes.
 *
 * <p>Counts the primes strictly below {@code n}.
 *
 * <p>Testing each number by trial division costs O(n * sqrt(n)). The sieve
 * inverts the work: instead of asking whether each number has a factor, it
 * starts from each prime and crosses off its multiples. Every composite is
 * reached from its prime factors, so whatever survives is prime.
 *
 * <p>Two optimisations with real justifications, not folklore:
 *
 * <ul>
 *   <li>The outer loop stops at {@code sqrt(n)}. Any composite {@code c} has a
 *       factor no larger than {@code sqrt(c)}, so it is crossed off before the
 *       loop passes that point.</li>
 *   <li>The inner loop starts at {@code p * p}, not {@code 2p}. Every smaller
 *       multiple {@code k * p} with {@code k < p} has a prime factor below
 *       {@code p} and was already crossed off by it.</li>
 * </ul>
 *
 * <p>{@code p * p} is computed as a {@code long} because for {@code p} above
 * roughly 46341 the square overflows an {@code int} and wraps negative, which
 * would index out of bounds or skip work silently.
 *
 * <p>Time O(n log log n) - the sum of n/p over primes p below n. Auxiliary
 * space O(n) bits.
 */
public final class CountPrimes {

    private CountPrimes() {
    }

    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] composite = new boolean[n];
        int count = 0;
        for (int candidate = 2; candidate < n; candidate++) {
            if (composite[candidate]) {
                continue;
            }
            count++;
            // Start at the square; smaller multiples are already crossed off.
            long firstMultiple = (long) candidate * candidate;
            if (firstMultiple >= n) {
                continue;
            }
            for (int multiple = (int) firstMultiple; multiple < n; multiple += candidate) {
                composite[multiple] = true;
            }
        }
        return count;
    }
}
