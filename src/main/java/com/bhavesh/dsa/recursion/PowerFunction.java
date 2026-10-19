package com.bhavesh.dsa.recursion;

/**
 * LeetCode 50 - Pow(x, n) (Medium). Binary exponentiation.
 *
 * <p>Multiplying {@code x} by itself {@code n} times is O(n) and times out for
 * {@code n} near 2^31. Exponentiation by squaring is O(log n), from the
 * identity {@code x^n = (x^(n/2))^2} for even {@code n}, and
 * {@code x * (x^((n-1)/2))^2} for odd {@code n}. Each step halves the exponent,
 * so the recursion depth is log n.
 *
 * <p>The negative-exponent case hides a genuine trap. Negating {@code n}
 * directly overflows when {@code n == Integer.MIN_VALUE}, because its magnitude
 * exceeds {@code Integer.MAX_VALUE} - {@code -Integer.MIN_VALUE} is still
 * {@code Integer.MIN_VALUE}. Widening to {@code long} before negating is what
 * makes it correct.
 *
 * <p>Time O(log n), auxiliary space O(1) for the iterative form.
 */
public final class PowerFunction {

    private PowerFunction() {
    }

    public static double myPow(double x, int n) {
        // Widen BEFORE negating: -Integer.MIN_VALUE overflows back to itself.
        long exponent = n;
        if (exponent < 0) {
            exponent = -exponent;
            x = 1 / x;
        }
        double result = 1.0;
        double base = x;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result *= base;
            }
            base *= base;
            exponent >>= 1;
        }
        return result;
    }

    /**
     * Integer binary exponentiation modulo {@code mod}, the form used in
     * hashing and number theory.
     *
     * <p>Time O(log exponent), auxiliary space O(1).
     */
    public static long powMod(long base, long exponent, long mod) {
        if (exponent < 0) {
            throw new IllegalArgumentException("exponent must be non-negative");
        }
        if (mod == 1) {
            return 0;
        }
        long result = 1;
        long b = base % mod;
        long e = exponent;
        while (e > 0) {
            if ((e & 1) == 1) {
                result = result * b % mod;
            }
            b = b * b % mod;
            e >>= 1;
        }
        return result;
    }
}
