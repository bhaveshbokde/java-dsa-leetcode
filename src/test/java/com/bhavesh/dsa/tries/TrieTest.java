package com.bhavesh.dsa.tries;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrieTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        Trie trie = new Trie();
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        trie.insert("app");
        assertTrue(trie.search("app"));
    }

    @Test
    @DisplayName("a prefix of a stored word is not itself stored")
    void prefixIsNotAWord() {
        Trie trie = new Trie();
        trie.insert("hello");
        assertFalse(trie.search("hell"));
        assertTrue(trie.startsWith("hell"));
    }

    @Test
    @DisplayName("an empty string is a prefix of everything once anything is inserted")
    void emptyString() {
        Trie trie = new Trie();
        assertFalse(trie.search(""));
        assertTrue(trie.startsWith(""));
        trie.insert("");
        assertTrue(trie.search(""));
    }

    @Test
    @DisplayName("an empty trie contains nothing")
    void emptyTrie() {
        Trie trie = new Trie();
        assertFalse(trie.search("anything"));
        assertFalse(trie.startsWith("a"));
    }

    @Test
    @DisplayName("repeated insertion is idempotent")
    void repeatedInsert() {
        Trie trie = new Trie();
        trie.insert("dog");
        trie.insert("dog");
        assertTrue(trie.search("dog"));
    }

    @Test
    @DisplayName("words sharing a prefix are independent")
    void sharedPrefixes() {
        Trie trie = new Trie();
        trie.insert("car");
        trie.insert("card");
        trie.insert("care");
        assertTrue(trie.search("car"));
        assertTrue(trie.search("card"));
        assertTrue(trie.search("care"));
        assertFalse(trie.search("ca"));
        assertFalse(trie.search("cards"));
    }

    @Test
    @DisplayName("invalid characters are rejected on insert and reported absent on lookup")
    void invalidCharacters() {
        Trie trie = new Trie();
        assertThrows(IllegalArgumentException.class, () -> trie.insert("Apple"));
        assertThrows(IllegalArgumentException.class, () -> trie.insert("a1"));
        assertThrows(IllegalArgumentException.class, () -> trie.insert(null));
        trie.insert("ok");
        assertFalse(trie.search("OK"));
        assertFalse(trie.startsWith("O"));
        assertFalse(trie.search(null));
    }

    @Test
    @DisplayName("agrees with a HashSet for exact lookups on randomised words")
    void matchesHashSet() {
        Random random = new Random(208208L);
        Trie trie = new Trie();
        Set<String> reference = new HashSet<>();
        for (int i = 0; i < 2000; i++) {
            String word = randomWord(random);
            trie.insert(word);
            reference.add(word);
        }
        for (int i = 0; i < 4000; i++) {
            String probe = randomWord(random);
            org.junit.jupiter.api.Assertions.assertEquals(reference.contains(probe), trie.search(probe),
                    () -> "disagreement for '" + probe + "'");
        }
    }

    @Test
    @DisplayName("startsWith agrees with a linear scan over the stored words")
    void startsWithMatchesLinearScan() {
        Random random = new Random(909208L);
        Trie trie = new Trie();
        Set<String> reference = new HashSet<>();
        for (int i = 0; i < 300; i++) {
            String word = randomWord(random);
            trie.insert(word);
            reference.add(word);
        }
        for (int i = 0; i < 600; i++) {
            String prefix = randomWord(random);
            boolean expected = reference.stream().anyMatch(w -> w.startsWith(prefix));
            org.junit.jupiter.api.Assertions.assertEquals(expected, trie.startsWith(prefix),
                    () -> "disagreement for prefix '" + prefix + "'");
        }
    }

    private static String randomWord(Random random) {
        int length = random.nextInt(5);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(4)));
        }
        return sb.toString();
    }
}
