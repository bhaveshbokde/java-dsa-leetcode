package com.bhavesh.dsa.bitmanipulation;

/**
 * LeetCode 371 - Sum of Two Integers (Medium).
 *
 * <p>Adds two integers without using {@code +} or {@code -}.
 *
 * <p>Binary addition decomposes into two independent pieces:
 *
 * <ul>
 *   <li>{@code a ^ b} is the sum of each bit position <em>ignoring</em> carries
 *       - XOR is addition modulo 2;</li>
 *   <li>{@code (a & b) << 1} is exactly where a carry is generated: both bits
 *       set, and the carry lands one position to the left.</li>
 * </ul>
 *
 * <p>Adding those two quantities is the same problem again, so the process
 * repeats until no carry remains. It terminates because each iteration pushes
 * the carry strictly leftwards, and after at most 32 steps it falls off the end
 * of the word.
 *
 * <p>Negative values need no special handling: two's complement is designed so
 * that the same bit-level addition is correct for signed values, which is
 * precisely why hardware uses it.
 *
 * <p>Time O(1) - at most 32 iterations. Auxiliary space O(1).
 */
public final class SumOfTwoIntegers {

    private SumOfTwoIntegers() {
    }

    public static int getSum(int a, int b) {
        int sum = a;
        int carry = b;
        while (carry != 0) {
            int carried = (sum & carry) << 1;
            sum = sum ^ carry;
            carry = carried;
        }
        return sum;
    }

    /** Subtraction, for symmetry: {@code a - b} is {@code a + (-b)}. */
    public static int getDifference(int a, int b) {
        // Negation without '-': two's complement is ~b + 1.
        return getSum(a, getSum(~b, 1));
    }
}
