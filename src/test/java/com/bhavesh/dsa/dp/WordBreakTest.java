package com.bhavesh.dsa.dp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WordBreakTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertTrue(WordBreak.wordBreak("leetcode", List.of("leet", "code")));
        assertTrue(WordBreak.wordBreak("applepenapple", List.of("apple", "pen")));
        assertFalse(WordBreak.wordBreak("catsandog",
                List.of("cats", "dog", "sand", "and", "cat")));
    }

    @Test
    @DisplayName("a word may be reused any number of times")
    void wordsAreReusable() {
        assertTrue(WordBreak.wordBreak("aaaa", List.of("a")));
        assertTrue(WordBreak.wordBreak("abababab", List.of("ab")));
    }

    @Test
    @DisplayName("a greedy longest-prefix choice would fail here")
    void greedyWouldFail() {
        // Taking "aaaa" first leaves "b" unmatched; the DP keeps "aaa" available.
        assertTrue(WordBreak.wordBreak("aaaab", List.of("aaaa", "aaa", "ab", "b")));
    }

    @Test
    @DisplayName("empty string is trivially segmentable; empty dictionary is not")
    void degenerate() {
        assertTrue(WordBreak.wordBreak("", List.of("a")));
        assertTrue(WordBreak.wordBreak(null, List.of("a")));
        assertFalse(WordBreak.wordBreak("abc", List.of()));
        assertFalse(WordBreak.wordBreak("abc", null));
    }

    @Test
    @DisplayName("the whole string is a single dictionary word")
    void exactMatch() {
        assertTrue(WordBreak.wordBreak("word", List.of("word")));
    }

    @Test
    @DisplayName("a single unmatched character defeats an otherwise valid split")
    void oneCharacterShort() {
        assertFalse(WordBreak.wordBreak("leetcodex", List.of("leet", "code")));
    }

    @Test
    @DisplayName("duplicate dictionary entries are harmless")
    void duplicateDictionaryEntries() {
        assertTrue(WordBreak.wordBreak("abab", List.of("ab", "ab", "a", "b")));
    }

    @Test
    @DisplayName("the pathological input stays fast thanks to the longest-word bound")
    void pathologicalInput() {
        // Without the DP this is exponential; without the length bound it is
        // needlessly quadratic in the string length.
        String s = "a".repeat(300) + "b";
        List<String> dict = List.of("a", "aa", "aaa", "aaaa", "aaaaa");
        assertFalse(WordBreak.wordBreak(s, dict));
        assertTrue(WordBreak.wordBreak("a".repeat(300), dict));
    }
}
