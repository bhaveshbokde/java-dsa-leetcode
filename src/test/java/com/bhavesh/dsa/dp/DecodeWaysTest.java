package com.bhavesh.dsa.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DecodeWaysTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(2, DecodeWays.numDecodings("12"));      // AB, L
        assertEquals(3, DecodeWays.numDecodings("226"));     // BZ, VF, BBF
        assertEquals(0, DecodeWays.numDecodings("06"));      // leading zero
    }

    @Test
    @DisplayName("a leading zero makes the whole string undecodable")
    void leadingZero() {
        assertEquals(0, DecodeWays.numDecodings("0"));
        assertEquals(0, DecodeWays.numDecodings("01"));
        assertEquals(0, DecodeWays.numDecodings("012"));
    }

    @Test
    @DisplayName("a zero is decodable only as part of 10 or 20")
    void zeroNeedsAPartner() {
        assertEquals(1, DecodeWays.numDecodings("10"));      // J
        assertEquals(1, DecodeWays.numDecodings("20"));      // T
        assertEquals(0, DecodeWays.numDecodings("30"));      // 30 > 26, 0 alone invalid
        assertEquals(0, DecodeWays.numDecodings("100"));     // the second 0 is stranded
        assertEquals(1, DecodeWays.numDecodings("101"));     // J, A
    }

    @Test
    @DisplayName("the 26 boundary")
    void twentySixBoundary() {
        assertEquals(2, DecodeWays.numDecodings("26"));      // Z, BF
        assertEquals(1, DecodeWays.numDecodings("27"));      // BG only
    }

    @Test
    @DisplayName("single digits")
    void singleDigits() {
        assertEquals(1, DecodeWays.numDecodings("1"));
        assertEquals(1, DecodeWays.numDecodings("9"));
        assertEquals(0, DecodeWays.numDecodings("0"));
    }

    @Test
    @DisplayName("all ones gives the Fibonacci sequence")
    void allOnesIsFibonacci() {
        // "1", "11", "111", ... decode in 1, 2, 3, 5, 8 ways.
        assertEquals(1, DecodeWays.numDecodings("1"));
        assertEquals(2, DecodeWays.numDecodings("11"));
        assertEquals(3, DecodeWays.numDecodings("111"));
        assertEquals(5, DecodeWays.numDecodings("1111"));
        assertEquals(8, DecodeWays.numDecodings("11111"));
    }

    @Test
    @DisplayName("empty, null and non-digit inputs")
    void degenerate() {
        assertEquals(0, DecodeWays.numDecodings(""));
        assertEquals(0, DecodeWays.numDecodings(null));
        assertThrows(IllegalArgumentException.class, () -> DecodeWays.numDecodings("1a2"));
    }

    @Test
    @DisplayName("matches a memoised recursive reference on randomised digit strings")
    void matchesRecursiveReference() {
        Random random = new Random(9191L);
        for (int trial = 0; trial < 2000; trial++) {
            int length = 1 + random.nextInt(12);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                // Skew towards 0,1,2 so zeros and valid pairs are common.
                sb.append((char) ('0' + random.nextInt(4)));
            }
            String s = sb.toString();
            assertEquals(recursive(s, 0, new HashMap<>()), DecodeWays.numDecodings(s),
                    () -> "mismatch for '" + s + "'");
        }
    }

    private static int recursive(String s, int at, Map<Integer, Integer> memo) {
        if (at == s.length()) {
            return 1;
        }
        if (s.charAt(at) == '0') {
            return 0;
        }
        Integer cached = memo.get(at);
        if (cached != null) {
            return cached;
        }
        int ways = recursive(s, at + 1, memo);
        if (at + 1 < s.length()) {
            int pair = (s.charAt(at) - '0') * 10 + (s.charAt(at + 1) - '0');
            if (pair <= 26) {
                ways += recursive(s, at + 2, memo);
            }
        }
        memo.put(at, ways);
        return ways;
    }
}
