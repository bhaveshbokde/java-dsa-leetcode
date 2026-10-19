package com.bhavesh.dsa.bitmanipulation;

/**
 * LeetCode 136 - Single Number (Easy).
 *
 * <p>Every value appears twice except one; returns that one, in O(n) time and
 * O(1) space.
 *
 * <p>A hash map of counts solves it in O(n) time but O(n) space, which the
 * problem rules out. XOR is the operation that makes constant space possible,
 * because of three algebraic properties: {@code x ^ x == 0},
 * {@code x ^ 0 == x}, and XOR is commutative and associative.
 *
 * <p>Together those mean the order of the array is irrelevant - every pair
 * cancels to zero regardless of position, leaving only the unpaired value.
 *
 * <p>It is worth being clear about the limits: this works only because every
 * other element appears exactly <em>twice</em>. For elements appearing three
 * times (LeetCode 137) the pairwise cancellation no longer holds and a
 * bit-counting approach modulo 3 is needed instead.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class SingleNumber {

    private SingleNumber() {
    }

    public static int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("there must be at least one element");
        }
        int accumulator = 0;
        for (int num : nums) {
            accumulator ^= num;
        }
        return accumulator;
    }

    /**
     * LeetCode 260 variant: exactly two values appear once.
     *
     * <p>XOR of everything gives {@code a ^ b}. Any set bit in that result is a
     * bit where {@code a} and {@code b} differ, so partitioning the array on
     * that bit separates them into two groups, each reducible by XOR. The
     * lowest set bit is isolated with {@code x & -x}.
     *
     * <p>Time O(n), auxiliary space O(1).
     */
    public static int[] singleNumberPair(int[] nums) {
        int xorBoth = 0;
        for (int num : nums) {
            xorBoth ^= num;
        }
        // Lowest set bit: in two's complement, -x is ~x + 1, so x & -x keeps
        // only the lowest bit that is set in x.
        int distinguishingBit = xorBoth & -xorBoth;
        int first = 0;
        int second = 0;
        for (int num : nums) {
            if ((num & distinguishingBit) != 0) {
                first ^= num;
            } else {
                second ^= num;
            }
        }
        return new int[] {first, second};
    }
}
