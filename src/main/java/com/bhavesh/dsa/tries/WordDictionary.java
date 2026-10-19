package com.bhavesh.dsa.tries;

/**
 * LeetCode 211 - Design Add and Search Words Data Structure (Medium).
 *
 * <p>Supports {@code addWord} and {@code search}, where the search pattern may
 * contain {@code '.'} matching any single character.
 *
 * <p>The wildcard is exactly why this needs a trie rather than a hash set. A
 * {@link java.util.HashSet} answers exact lookups in O(1) but has no way to
 * resolve {@code "b.."} short of testing every stored word. A trie stores words
 * by shared prefix, so a wildcard simply means "descend into <em>all</em>
 * children at this position" - the branching is natural rather than bolted on.
 *
 * <p>Search is therefore recursive, not a loop: a {@code '.'} forks into up to
 * 26 subsearches and each must be explored until one succeeds. An ordinary
 * character still follows a single edge, so a pattern with no wildcards costs
 * exactly the same as a plain trie lookup.
 *
 * <p>The {@code terminal} flag is what separates a stored word from a prefix of
 * one, exactly as in the plain {@link Trie}.
 *
 * <p>Time: {@code addWord} O(L). {@code search} is O(L) with no wildcards, and
 * O(26^d * L) in the worst case for d wildcards - the stated constraints cap
 * wildcards at 2, which is what keeps this tractable. Space O(total characters
 * added).
 */
public final class WordDictionary {

    private static final int ALPHABET = 26;
    private static final char WILDCARD = '.';

    private static final class Node {
        private final Node[] children = new Node[ALPHABET];
        private boolean terminal;
    }

    private final Node root = new Node();

    public void addWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("word must not be null");
        }
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c < 'a' || c > 'z') {
                throw new IllegalArgumentException("only lowercase a-z is supported, found '" + c + "'");
            }
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Node();
            }
            current = current.children[index];
        }
        current.terminal = true;
    }

    /** True when some added word matches the pattern; '.' matches any letter. */
    public boolean search(String pattern) {
        return pattern != null && matches(pattern, 0, root);
    }

    private boolean matches(String pattern, int index, Node node) {
        if (node == null) {
            return false;
        }
        if (index == pattern.length()) {
            return node.terminal;
        }
        char c = pattern.charAt(index);
        if (c == WILDCARD) {
            // Fork: any existing child may lead to a match.
            for (Node child : node.children) {
                if (child != null && matches(pattern, index + 1, child)) {
                    return true;
                }
            }
            return false;
        }
        if (c < 'a' || c > 'z') {
            return false;   // an unsupported character simply cannot match
        }
        return matches(pattern, index + 1, node.children[c - 'a']);
    }
}
