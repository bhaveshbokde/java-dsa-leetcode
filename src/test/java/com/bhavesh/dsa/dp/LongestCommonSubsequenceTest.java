package com.bhavesh.dsa.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LongestCommonSubsequenceTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(3, LongestCommonSubsequence.longestCommonSubsequence("abcde", "ace"));
        assertEquals(3, LongestCommonSubsequence.longestCommonSubsequence("abc", "abc"));
        assertEquals(0, LongestCommonSubsequence.longestCommonSubsequence("abc", "def"));
    }

    @Test
    @DisplayName("empty and null inputs")
    void degenerate() {
        assertEquals(0, LongestCommonSubsequence.longestCommonSubsequence("", "abc"));
        assertEquals(0, LongestCommonSubsequence.longestCommonSubsequence("abc", ""));
        assertEquals(0, LongestCommonSubsequence.longestCommonSubsequence(null, "abc"));
        assertEquals(0, LongestCommonSubsequence.longestCommonSubsequence("abc", null));
    }

    @Test
    @DisplayName("subsequences need not be contiguous")
    void nonContiguous() {
        // "ace" appears in "abcde" with gaps; a substring algorithm would say 1.
        assertEquals(3, LongestCommonSubsequence.longestCommonSubsequence("abcde", "ace"));
    }

    @Test
    @DisplayName("order matters: a reversed string shares only one character runs")
    void orderMatters() {
        assertEquals(1, LongestCommonSubsequence.longestCommonSubsequence("abcd", "dcba"));
    }

    @Test
    @DisplayName("the result is symmetric in its arguments")
    void symmetric() {
        Random random = new Random(1143L);
        for (int trial = 0; trial < 300; trial++) {
            String a = randomWord(random, 8);
            String b = randomWord(random, 8);
            assertEquals(LongestCommonSubsequence.longestCommonSubsequence(a, b),
                    LongestCommonSubsequence.longestCommonSubsequence(b, a),
                    () -> "asymmetric for '" + a + "' and '" + b + "'");
        }
    }

    @Test
    @DisplayName("matches a full-table reference on randomised inputs")
    void matchesFullTableReference() {
        Random random = new Random(424242L);
        for (int trial = 0; trial < 600; trial++) {
            String a = randomWord(random, 10);
            String b = randomWord(random, 10);
            assertEquals(fullTable(a, b), LongestCommonSubsequence.longestCommonSubsequence(a, b),
                    () -> "mismatch for '" + a + "' and '" + b + "'");
        }
    }

    @Test
    @DisplayName("identical long strings give their own length")
    void identicalLongStrings() {
        String s = "x".repeat(500) + "y".repeat(500);
        assertEquals(1000, LongestCommonSubsequence.longestCommonSubsequence(s, s));
    }

    private static String randomWord(Random random, int maxLength) {
        int length = random.nextInt(maxLength + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(4)));
        }
        return sb.toString();
    }

    /** The textbook O(m*n) table, kept whole rather than rolled. */
    private static int fullTable(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                dp[i][j] = a.charAt(i - 1) == b.charAt(j - 1)
                        ? dp[i - 1][j - 1] + 1
                        : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[a.length()][b.length()];
    }
}
