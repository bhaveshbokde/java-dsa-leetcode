package com.bhavesh.dsa.tries;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WordDictionaryTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        assertFalse(dict.search("pad"));
        assertTrue(dict.search("bad"));
        assertTrue(dict.search(".ad"));
        assertTrue(dict.search("b.."));
    }

    @Test
    @DisplayName("a wildcard must still respect the word length")
    void wildcardRespectsLength() {
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        assertFalse(dict.search("b."), "shorter pattern must not match");
        assertFalse(dict.search("b..."), "longer pattern must not match");
    }

    @Test
    @DisplayName("a prefix of a stored word is not itself stored")
    void prefixIsNotAWord() {
        WordDictionary dict = new WordDictionary();
        dict.addWord("hello");
        assertFalse(dict.search("hell"));
        assertTrue(dict.search("hello"));
    }

    @Test
    @DisplayName("all wildcards matches any word of that length")
    void allWildcards() {
        WordDictionary dict = new WordDictionary();
        dict.addWord("abc");
        assertTrue(dict.search("..."));
        assertFalse(dict.search(".."));
    }

    @Test
    @DisplayName("empty dictionary and empty word")
    void degenerate() {
        WordDictionary empty = new WordDictionary();
        assertFalse(empty.search("a"));
        assertFalse(empty.search(""));
        assertFalse(empty.search(null));

        WordDictionary dict = new WordDictionary();
        dict.addWord("");
        assertTrue(dict.search(""));
    }

    @Test
    @DisplayName("invalid input is rejected on add and reported absent on search")
    void invalidInput() {
        WordDictionary dict = new WordDictionary();
        assertThrows(IllegalArgumentException.class, () -> dict.addWord(null));
        assertThrows(IllegalArgumentException.class, () -> dict.addWord("Bad"));
        dict.addWord("ok");
        assertFalse(dict.search("OK"));
    }

    @Test
    @DisplayName("repeated adds are idempotent")
    void repeatedAdd() {
        WordDictionary dict = new WordDictionary();
        dict.addWord("word");
        dict.addWord("word");
        assertTrue(dict.search("word"));
    }

    @Test
    @DisplayName("matches a regex-based reference over randomised words and patterns")
    void matchesRegexReference() {
        Random random = new Random(211211L);
        WordDictionary dict = new WordDictionary();
        List<String> stored = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            String w = randomWord(random, 4, false);
            dict.addWord(w);
            stored.add(w);
        }
        for (int i = 0; i < 2000; i++) {
            String pattern = randomWord(random, 4, true);
            String regex = pattern.replace(".", "[a-z]");
            boolean expected = stored.stream().anyMatch(w -> w.matches(regex));
            org.junit.jupiter.api.Assertions.assertEquals(expected, dict.search(pattern),
                    () -> "disagreement for pattern '" + pattern + "'");
        }
    }

    private static String randomWord(Random random, int maxLength, boolean allowWildcard) {
        int length = 1 + random.nextInt(maxLength);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (allowWildcard && random.nextInt(4) == 0) {
                sb.append('.');
            } else {
                sb.append((char) ('a' + random.nextInt(3)));
            }
        }
        return sb.toString();
    }
}
