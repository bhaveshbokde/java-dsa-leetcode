package com.bhavesh.dsa.bitmanipulation;

/**
 * LeetCode 191 - Number of 1 Bits (Easy), and LeetCode 190 - Reverse Bits.
 *
 * <p>Both treat the input as an unsigned 32-bit pattern, which is the trap in
 * Java: there is no unsigned {@code int}, so a value with the top bit set is
 * negative and {@code >>} sign-extends. Every shift here is the unsigned
 * {@code >>>}.
 *
 * <p><b>Counting set bits.</b> The naive loop tests all 32 positions.
 * Kernighan's trick instead clears the lowest set bit each iteration with
 * {@code n &= n - 1}, so it runs once per set bit. For sparse values that is a
 * large constant-factor win; the worst case is still 32.
 *
 * <p>Why {@code n & (n - 1)} clears the lowest set bit: subtracting 1 flips
 * that bit to 0 and sets every bit below it, so the AND keeps the higher bits
 * unchanged and zeroes everything from that bit down.
 *
 * <p><b>Reversing bits.</b> Shift the result left, append the input's lowest
 * bit, shift the input right - 32 times.
 *
 * <p>Time O(set bits) for counting, O(32) for reversing. Auxiliary space O(1).
 */
public final class NumberOfOneBits {

    private static final int WORD_BITS = 32;

    private NumberOfOneBits() {
    }

    /** LeetCode 191. Kernighan's algorithm: one iteration per set bit. */
    public static int hammingWeight(int n) {
        int remaining = n;
        int count = 0;
        while (remaining != 0) {
            remaining &= remaining - 1;
            count++;
        }
        return count;
    }

    /** Fixed 32-iteration variant, for contrast with Kernighan's. */
    public static int hammingWeightByScanning(int n) {
        int count = 0;
        for (int bit = 0; bit < WORD_BITS; bit++) {
            // >>> not >>: a negative n must not sign-extend.
            count += (n >>> bit) & 1;
        }
        return count;
    }

    /** LeetCode 190 - Reverse Bits. */
    public static int reverseBits(int n) {
        int result = 0;
        int remaining = n;
        for (int bit = 0; bit < WORD_BITS; bit++) {
            result = (result << 1) | (remaining & 1);
            remaining >>>= 1;
        }
        return result;
    }
}
