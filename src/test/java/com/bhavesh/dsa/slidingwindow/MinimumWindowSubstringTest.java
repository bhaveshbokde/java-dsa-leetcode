package com.bhavesh.dsa.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MinimumWindowSubstringTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals("BANC", MinimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
        assertEquals("a", MinimumWindowSubstring.minWindow("a", "a"));
        assertEquals("", MinimumWindowSubstring.minWindow("a", "aa"));
    }

    @Test
    @DisplayName("multiplicities must be satisfied, not just presence")
    void multiplicitiesMatter() {
        // One 'a' is not enough for "aa".
        assertEquals("", MinimumWindowSubstring.minWindow("ab", "aa"));
        assertEquals("aa", MinimumWindowSubstring.minWindow("aab", "aa"));
        assertEquals("aba", MinimumWindowSubstring.minWindow("abac", "aab"));
    }

    @Test
    @DisplayName("surplus characters do not satisfy an unrelated deficit")
    void surplusDoesNotCount() {
        assertEquals("", MinimumWindowSubstring.minWindow("aaaa", "aab"));
    }

    @Test
    @DisplayName("no valid window")
    void noWindow() {
        assertEquals("", MinimumWindowSubstring.minWindow("abc", "xyz"));
        assertEquals("", MinimumWindowSubstring.minWindow("abc", "abcd"));
    }

    @Test
    @DisplayName("empty and null inputs")
    void degenerate() {
        assertEquals("", MinimumWindowSubstring.minWindow("", "a"));
        assertEquals("", MinimumWindowSubstring.minWindow("abc", ""));
        assertEquals("", MinimumWindowSubstring.minWindow(null, "a"));
        assertEquals("", MinimumWindowSubstring.minWindow("abc", null));
    }

    @Test
    @DisplayName("the whole string is the only valid window")
    void wholeStringNeeded() {
        assertEquals("abc", MinimumWindowSubstring.minWindow("abc", "cba"));
    }

    @Test
    @DisplayName("case is significant")
    void caseSensitive() {
        assertEquals("", MinimumWindowSubstring.minWindow("abc", "A"));
    }

    @Test
    @DisplayName("non-ASCII input is rejected explicitly")
    void nonAsciiRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> MinimumWindowSubstring.minWindow("abc", "é"));
        assertThrows(IllegalArgumentException.class,
                () -> MinimumWindowSubstring.minWindow("é", "a"));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(7676L);
        for (int trial = 0; trial < 600; trial++) {
            String s = randomWord(random, 12, 3);
            String t = randomWord(random, 4, 3);
            if (t.isEmpty()) {
                continue;
            }
            String expected = bruteForce(s, t);
            String actual = MinimumWindowSubstring.minWindow(s, t);
            assertEquals(expected.length(), actual.length(),
                    () -> "wrong length for s='" + s + "' t='" + t + "'");
            if (!actual.isEmpty()) {
                assertTrue(covers(actual, t), () -> "'" + actual + "' does not cover '" + t + "'");
                assertTrue(s.contains(actual), () -> "'" + actual + "' is not a substring");
            }
        }
    }

    private static String randomWord(Random random, int maxLength, int alphabet) {
        int length = random.nextInt(maxLength + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(alphabet)));
        }
        return sb.toString();
    }

    private static boolean covers(String window, String t) {
        int[] counts = new int[128];
        for (char c : window.toCharArray()) {
            counts[c]++;
        }
        for (char c : t.toCharArray()) {
            if (--counts[c] < 0) {
                return false;
            }
        }
        return true;
    }

    private static String bruteForce(String s, String t) {
        String best = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String candidate = s.substring(i, j);
                if (covers(candidate, t) && (best.isEmpty() || candidate.length() < best.length())) {
                    best = candidate;
                }
            }
        }
        return best;
    }
}
