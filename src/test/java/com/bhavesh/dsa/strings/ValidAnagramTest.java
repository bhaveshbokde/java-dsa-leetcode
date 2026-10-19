package com.bhavesh.dsa.strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidAnagramTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertTrue(ValidAnagram.isAnagram("anagram", "nagaram"));
        assertFalse(ValidAnagram.isAnagram("rat", "car"));
    }

    @Test
    @DisplayName("different lengths are rejected immediately")
    void differentLengths() {
        assertFalse(ValidAnagram.isAnagram("a", "ab"));
        assertFalse(ValidAnagram.isAnagram("abc", "ab"));
    }

    @Test
    @DisplayName("empty strings are anagrams of each other")
    void bothEmpty() {
        assertTrue(ValidAnagram.isAnagram("", ""));
    }

    @Test
    @DisplayName("same letters but different multiplicities are not anagrams")
    void differingCounts() {
        assertFalse(ValidAnagram.isAnagram("aacc", "ccac"));
    }

    @Test
    @DisplayName("null inputs are rejected rather than throwing")
    void nullInputs() {
        assertFalse(ValidAnagram.isAnagram(null, "a"));
        assertFalse(ValidAnagram.isAnagram("a", null));
    }

    @Test
    @DisplayName("the Unicode variant handles characters outside a-z")
    void unicodeVariant() {
        assertTrue(ValidAnagram.isAnagramUnicode("été", "téé"));
        assertFalse(ValidAnagram.isAnagramUnicode("été", "ttt"));
        assertTrue(ValidAnagram.isAnagramUnicode("Rat", "taR"));
    }

    @Test
    @DisplayName("matches a sort-and-compare reference on randomised lowercase inputs")
    void matchesSortReference() {
        Random random = new Random(1357L);
        for (int trial = 0; trial < 600; trial++) {
            int length = 1 + random.nextInt(8);
            char[] a = new char[length];
            char[] b = new char[length];
            for (int i = 0; i < length; i++) {
                a[i] = (char) ('a' + random.nextInt(4));
                b[i] = (char) ('a' + random.nextInt(4));
            }
            String s = new String(a);
            String t = new String(b);
            char[] sortedA = a.clone();
            char[] sortedB = b.clone();
            Arrays.sort(sortedA);
            Arrays.sort(sortedB);
            boolean expected = Arrays.equals(sortedA, sortedB);
            assertTrue(expected == ValidAnagram.isAnagram(s, t), () -> "mismatch for " + s + " / " + t);
            assertTrue(expected == ValidAnagram.isAnagramUnicode(s, t), () -> "unicode mismatch " + s + " / " + t);
        }
    }
}
