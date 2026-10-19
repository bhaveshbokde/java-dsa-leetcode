package com.bhavesh.dsa.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberOfOneBitsTest {

    @Test
    @DisplayName("LeetCode 191 examples")
    void hammingWeightExamples() {
        assertEquals(3, NumberOfOneBits.hammingWeight(0b0000_0000_0000_0000_0000_0000_0000_1011));
        assertEquals(1, NumberOfOneBits.hammingWeight(0b0000_0000_0000_0000_1000_0000_0000_0000));
        assertEquals(31, NumberOfOneBits.hammingWeight(0b1111_1111_1111_1111_1111_1111_1111_1101));
    }

    @Test
    @DisplayName("zero, all ones, and the sign bit alone")
    void boundaryPatterns() {
        assertEquals(0, NumberOfOneBits.hammingWeight(0));
        assertEquals(32, NumberOfOneBits.hammingWeight(-1));
        // Integer.MIN_VALUE is the sign bit alone; a signed shift would loop or
        // miscount here.
        assertEquals(1, NumberOfOneBits.hammingWeight(Integer.MIN_VALUE));
        assertEquals(31, NumberOfOneBits.hammingWeight(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("both counting variants agree with Integer.bitCount everywhere interesting")
    void countingMatchesJdk() {
        Random random = new Random(191191L);
        for (int trial = 0; trial < 20_000; trial++) {
            int value = random.nextInt();
            int expected = Integer.bitCount(value);
            assertEquals(expected, NumberOfOneBits.hammingWeight(value), "Kernighan differs for " + value);
            assertEquals(expected, NumberOfOneBits.hammingWeightByScanning(value), "scan differs for " + value);
        }
        for (int value : new int[] {0, -1, 1, -2, Integer.MIN_VALUE, Integer.MAX_VALUE}) {
            assertEquals(Integer.bitCount(value), NumberOfOneBits.hammingWeight(value));
        }
    }

    @Test
    @DisplayName("LeetCode 190 examples")
    void reverseBitsExamples() {
        // LeetCode 190: 00000010100101000001111010011100 reverses to
        //               00111001011110000010100101000000 (964176192).
        assertEquals(0b0011_1001_0111_1000_0010_1001_0100_0000,
                NumberOfOneBits.reverseBits(0b0000_0010_1001_0100_0001_1110_1001_1100));
        assertEquals(964_176_192, NumberOfOneBits.reverseBits(43261596));
        // -3 is 111...101; reversed it is 1011...1 = -1073741825.
        assertEquals(-1_073_741_825, NumberOfOneBits.reverseBits(-3));
        assertEquals(Integer.reverse(-3), NumberOfOneBits.reverseBits(-3));
    }

    @Test
    @DisplayName("reverseBits agrees with Integer.reverse")
    void reverseMatchesJdk() {
        Random random = new Random(190190L);
        for (int trial = 0; trial < 20_000; trial++) {
            int value = random.nextInt();
            assertEquals(Integer.reverse(value), NumberOfOneBits.reverseBits(value),
                    "differs for " + value);
        }
        for (int value : new int[] {0, -1, 1, Integer.MIN_VALUE, Integer.MAX_VALUE}) {
            assertEquals(Integer.reverse(value), NumberOfOneBits.reverseBits(value));
        }
    }

    @Test
    @DisplayName("reversing twice restores the original value")
    void doubleReversalIsIdentity() {
        Random random = new Random(5L);
        for (int trial = 0; trial < 5000; trial++) {
            int value = random.nextInt();
            assertEquals(value, NumberOfOneBits.reverseBits(NumberOfOneBits.reverseBits(value)));
        }
    }
}
