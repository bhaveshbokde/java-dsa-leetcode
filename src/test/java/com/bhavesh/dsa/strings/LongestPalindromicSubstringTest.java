package com.bhavesh.dsa.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LongestPalindromicSubstringTest {

    @Test
    @DisplayName("LeetCode 5 examples")
    void leetCodeExamples() {
        // "aba" is equally valid; the problem accepts any longest palindrome.
        String result = LongestPalindromicSubstring.longestPalindrome("babad");
        assertTrue(result.equals("bab") || result.equals("aba"), "got " + result);
        assertEquals("bb", LongestPalindromicSubstring.longestPalindrome("cbbd"));
    }

    @Test
    @DisplayName("even-length palindromes are found, which needs the gap centres")
    void evenLengthCentres() {
        assertEquals("abba", LongestPalindromicSubstring.longestPalindrome("abba"));
        assertEquals("aa", LongestPalindromicSubstring.longestPalindrome("xaay"));
    }

    @Test
    @DisplayName("single character, empty and null")
    void degenerate() {
        assertEquals("a", LongestPalindromicSubstring.longestPalindrome("a"));
        assertEquals("", LongestPalindromicSubstring.longestPalindrome(""));
        assertEquals("", LongestPalindromicSubstring.longestPalindrome(null));
    }

    @Test
    @DisplayName("all identical characters: the whole string")
    void allSame() {
        assertEquals("aaaa", LongestPalindromicSubstring.longestPalindrome("aaaa"));
    }

    @Test
    @DisplayName("no palindrome longer than one character")
    void noLongPalindrome() {
        assertEquals(1, LongestPalindromicSubstring.longestPalindrome("abcde").length());
    }

    @Test
    @DisplayName("the whole string is a palindrome")
    void wholeStringPalindrome() {
        assertEquals("racecar", LongestPalindromicSubstring.longestPalindrome("racecar"));
    }

    @Test
    @DisplayName("LeetCode 647 examples")
    void countExamples() {
        assertEquals(3, LongestPalindromicSubstring.countPalindromicSubstrings("abc"));
        assertEquals(6, LongestPalindromicSubstring.countPalindromicSubstrings("aaa"));
        assertEquals(0, LongestPalindromicSubstring.countPalindromicSubstrings(""));
        assertEquals(0, LongestPalindromicSubstring.countPalindromicSubstrings(null));
    }

    @Test
    @DisplayName("both agree with an O(n^3) reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(5647L);
        for (int trial = 0; trial < 500; trial++) {
            int length = random.nextInt(12);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append((char) ('a' + random.nextInt(3)));
            }
            String s = sb.toString();

            String actual = LongestPalindromicSubstring.longestPalindrome(s);
            assertTrue(isPalindrome(actual), () -> "'" + actual + "' is not a palindrome");
            assertTrue(s.contains(actual), () -> "'" + actual + "' is not a substring of '" + s + "'");
            assertEquals(bruteForceLongest(s).length(), actual.length(),
                    () -> "wrong length for '" + s + "'");

            assertEquals(bruteForceCount(s), LongestPalindromicSubstring.countPalindromicSubstrings(s),
                    () -> "wrong count for '" + s + "'");
        }
    }

    private static boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private static String bruteForceLongest(String s) {
        String best = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String candidate = s.substring(i, j);
                if (isPalindrome(candidate) && candidate.length() > best.length()) {
                    best = candidate;
                }
            }
        }
        return best;
    }

    private static int bruteForceCount(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (isPalindrome(s.substring(i, j))) {
                    count++;
                }
            }
        }
        return count;
    }
}
