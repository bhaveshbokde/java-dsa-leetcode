package com.bhavesh.dsa.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LongestSubstringWithoutRepeatingTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(3, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    @DisplayName("empty and null inputs")
    void degenerate() {
        assertEquals(0, LongestSubstringWithoutRepeating.lengthOfLongestSubstring(""));
        assertEquals(0, LongestSubstringWithoutRepeating.lengthOfLongestSubstring(null));
    }

    @Test
    @DisplayName("single character")
    void single() {
        assertEquals(1, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("a"));
    }

    @Test
    @DisplayName("all distinct characters: the whole string")
    void allDistinct() {
        assertEquals(6, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("abcdef"));
    }

    @Test
    @DisplayName("a stale last-seen index must not drag the window backwards")
    void staleIndexDoesNotRewindWindow() {
        // "abba": when the second 'a' is reached, lastSeen['a'] = 0 is stale
        // because left has already advanced past it. Without the Math.max the
        // window would widen incorrectly and report 3.
        assertEquals(2, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("abba"));
    }

    @Test
    @DisplayName("spaces and symbols count as characters")
    void symbols() {
        assertEquals(3, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("a b a"));
    }

    @Test
    @DisplayName("non-ASCII input is rejected explicitly rather than corrupting the array")
    void nonAsciiRejected() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> LongestSubstringWithoutRepeating.lengthOfLongestSubstring("é"));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(314L);
        for (int trial = 0; trial < 600; trial++) {
            int length = random.nextInt(12);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append((char) ('a' + random.nextInt(4)));
            }
            String s = sb.toString();
            assertEquals(bruteForce(s), LongestSubstringWithoutRepeating.lengthOfLongestSubstring(s),
                    () -> "mismatch for '" + s + "'");
        }
    }

    private static int bruteForce(String s) {
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (!seen.add(s.charAt(j))) {
                    break;
                }
                best = Math.max(best, j - i + 1);
            }
        }
        return best;
    }
}
