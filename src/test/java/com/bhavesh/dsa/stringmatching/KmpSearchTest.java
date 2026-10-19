package com.bhavesh.dsa.stringmatching;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KmpSearchTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(0, KmpSearch.indexOf("sadbutsad", "sad"));
        assertEquals(-1, KmpSearch.indexOf("leetcode", "leeto"));
    }

    @Test
    @DisplayName("an empty needle matches at index 0")
    void emptyNeedle() {
        assertEquals(0, KmpSearch.indexOf("abc", ""));
        assertEquals(0, KmpSearch.indexOf("", ""));
    }

    @Test
    @DisplayName("a needle longer than the haystack cannot match")
    void needleTooLong() {
        assertEquals(-1, KmpSearch.indexOf("ab", "abc"));
        assertEquals(-1, KmpSearch.indexOf("", "a"));
    }

    @Test
    @DisplayName("null inputs")
    void nullInputs() {
        assertEquals(-1, KmpSearch.indexOf(null, "a"));
        assertEquals(-1, KmpSearch.indexOf("a", null));
    }

    @Test
    @DisplayName("the match is at the very end")
    void matchAtEnd() {
        assertEquals(3, KmpSearch.indexOf("abcde", "de"));
    }

    @Test
    @DisplayName("overlapping prefixes, the case naive search handles badly")
    void overlappingPrefixes() {
        // "aaaaab" is a,a,a,a,a,b - the needle occupies indices 2..5.
        assertEquals(2, KmpSearch.indexOf("aaaaab", "aaab"));
        assertEquals("aaaaab".indexOf("aaab"), KmpSearch.indexOf("aaaaab", "aaab"));
        assertEquals(-1, KmpSearch.indexOf("aaaaa", "aaab"));
    }

    @Test
    @DisplayName("the failure table for a known pattern")
    void failureTable() {
        // "ababaca": prefixes that are also suffixes grow then reset at 'c'.
        assertArrayEquals(new int[] {0, 0, 1, 2, 3, 0, 1}, KmpSearch.buildFailureTable("ababaca"));
        assertArrayEquals(new int[] {0, 1, 2, 3}, KmpSearch.buildFailureTable("aaaa"));
        assertArrayEquals(new int[] {0, 0, 0}, KmpSearch.buildFailureTable("abc"));
    }

    @Test
    @DisplayName("agrees with String.indexOf on randomised inputs")
    void matchesStringIndexOf() {
        Random random = new Random(282828L);
        for (int trial = 0; trial < 3000; trial++) {
            String haystack = randomWord(random, 14);
            String needle = randomWord(random, 4);
            assertEquals(haystack.indexOf(needle), KmpSearch.indexOf(haystack, needle),
                    () -> "mismatch searching '" + needle + "' in '" + haystack + "'");
        }
    }

    @Test
    @DisplayName("the adversarial input for naive search stays linear")
    void adversarialInput() {
        // Naive search backtracks nearly the whole pattern at every position here.
        String haystack = "a".repeat(100_000) + "b";
        String needle = "a".repeat(1_000) + "b";
        assertEquals(99_000, KmpSearch.indexOf(haystack, needle));
    }

    private static String randomWord(Random random, int maxLength) {
        int length = random.nextInt(maxLength + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(2)));
        }
        return sb.toString();
    }
}
