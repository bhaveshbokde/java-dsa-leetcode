package com.bhavesh.dsa.tries;

/**
 * LeetCode 208 - Implement Trie (Prefix Tree) (Medium).
 *
 * <p>A prefix tree supporting insert, exact lookup and prefix lookup.
 *
 * <p>The structure exists for one reason a hash set cannot match: prefix
 * queries. A {@code HashSet<String>} answers {@code contains} in O(1) but
 * cannot answer "does any stored word start with this" without scanning every
 * entry. A trie answers both in O(L) for a key of length L, independent of how
 * many words are stored.
 *
 * <p>The distinction between {@code search} and {@code startsWith} is carried
 * entirely by the {@code terminal} flag. Without it there is no way to tell a
 * stored word from a mere prefix of one - inserting "apple" would make "app"
 * appear present.
 *
 * <p>Children are a fixed 26-slot array rather than a map, matching the
 * problem's lowercase-English constraint: no hashing, no boxing, direct
 * indexing. The cost is 26 references per node even for sparse branches, which
 * a {@code HashMap} would avoid at the price of constant-factor overhead. For a
 * large alphabet the map is the better choice.
 *
 * <p>Every operation O(L); space O(total characters inserted * 26) in the worst
 * case.
 */
public final class Trie {

    private static final int ALPHABET = 26;

    private final Node root = new Node();

    private static final class Node {
        private final Node[] children = new Node[ALPHABET];
        private boolean terminal;
    }

    public void insert(String word) {
        requireLowercase(word);
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Node();
            }
            current = current.children[index];
        }
        current.terminal = true;
    }

    /** True only for a word that was inserted, not for a bare prefix. */
    public boolean search(String word) {
        Node node = walk(word);
        return node != null && node.terminal;
    }

    /** True when any inserted word begins with {@code prefix}. */
    public boolean startsWith(String prefix) {
        return walk(prefix) != null;
    }

    private Node walk(String key) {
        if (key == null) {
            return null;
        }
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (c < 'a' || c > 'z') {
                return null;
            }
            current = current.children[c - 'a'];
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    private static void requireLowercase(String word) {
        if (word == null) {
            throw new IllegalArgumentException("word must not be null");
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c < 'a' || c > 'z') {
                throw new IllegalArgumentException("only lowercase a-z is supported, found '" + c + "'");
            }
        }
    }
}
