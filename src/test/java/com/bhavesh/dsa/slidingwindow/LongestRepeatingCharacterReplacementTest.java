package com.bhavesh.dsa.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LongestRepeatingCharacterReplacementTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(4, LongestRepeatingCharacterReplacement.characterReplacement("ABAB", 2));
        assertEquals(4, LongestRepeatingCharacterReplacement.characterReplacement("AABABBA", 1));
    }

    @Test
    @DisplayName("k = 0 reduces to the longest run of one character")
    void noReplacements() {
        assertEquals(3, LongestRepeatingCharacterReplacement.characterReplacement("AABBBCC", 0));
    }

    @Test
    @DisplayName("k at least the length makes the whole string uniform")
    void kCoversEverything() {
        assertEquals(5, LongestRepeatingCharacterReplacement.characterReplacement("ABCDE", 10));
    }

    @Test
    @DisplayName("already uniform")
    void alreadyUniform() {
        assertEquals(4, LongestRepeatingCharacterReplacement.characterReplacement("AAAA", 2));
    }

    @Test
    @DisplayName("empty and null inputs")
    void degenerate() {
        assertEquals(0, LongestRepeatingCharacterReplacement.characterReplacement("", 3));
        assertEquals(0, LongestRepeatingCharacterReplacement.characterReplacement(null, 3));
    }

    @Test
    @DisplayName("the fast and recomputed variants agree on randomised inputs")
    void variantsAgree() {
        Random random = new Random(424424L);
        for (int trial = 0; trial < 800; trial++) {
            int length = random.nextInt(14);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append((char) ('A' + random.nextInt(3)));
            }
            String s = sb.toString();
            int k = random.nextInt(5);
            assertEquals(LongestRepeatingCharacterReplacement.characterReplacementRecomputed(s, k),
                    LongestRepeatingCharacterReplacement.characterReplacement(s, k),
                    () -> "variants disagreed on '" + s + "' k=" + k);
        }
    }

    @Test
    @DisplayName("matches an O(n^2) reference that checks every substring")
    void matchesBruteForce() {
        Random random = new Random(777111L);
        for (int trial = 0; trial < 400; trial++) {
            int length = random.nextInt(11);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append((char) ('A' + random.nextInt(3)));
            }
            String s = sb.toString();
            int k = random.nextInt(4);
            assertEquals(bruteForce(s, k), LongestRepeatingCharacterReplacement.characterReplacement(s, k),
                    () -> "mismatch for '" + s + "' k=" + k);
        }
    }

    private static int bruteForce(String s, int k) {
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] counts = new int[26];
            int max = 0;
            for (int j = i; j < s.length(); j++) {
                counts[s.charAt(j) - 'A']++;
                max = Math.max(max, counts[s.charAt(j) - 'A']);
                if (j - i + 1 - max <= k) {
                    best = Math.max(best, j - i + 1);
                }
            }
        }
        return best;
    }
}
