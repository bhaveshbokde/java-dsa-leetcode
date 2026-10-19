package com.bhavesh.dsa.tries;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 212 - Word Search II (Hard).
 *
 * <p>Given a board and a list of words, return every word that can be spelled
 * by a path of adjacent cells without reusing a cell within one path.
 *
 * <p>Running the single-word search (LeetCode 79) once per word is the obvious
 * approach and is O(W * m * n * 4^L) - with 3·10^4 words it is far too slow.
 * The waste is specific and visible: searching for "oath", "oats" and "oaf"
 * each re-walks the same {@code o → a} prefix from the same cell.
 *
 * <p>A <b>trie of the word list</b> removes exactly that duplication. One
 * traversal of the board carries a trie pointer alongside the grid position,
 * so all words sharing a prefix are explored together, and a branch dies the
 * moment no word continues that way - which is the real pruning.
 *
 * <p>Two refinements matter at scale:
 *
 * <ul>
 *   <li><b>Store the word at its terminal node</b> rather than rebuilding it
 *       from the path. Collecting on a hit is then O(1) with no StringBuilder.</li>
 *   <li><b>Null the stored word after collecting it</b>, so the same word is
 *       never reported twice when it is reachable by several paths. This also
 *       serves as deduplication without a {@link java.util.Set}.</li>
 * </ul>
 *
 * <p>The cell marking is the same temporary-overwrite-and-restore used in
 * LeetCode 79, so the board is left exactly as it was found.
 *
 * <p>Time O(m * n * 4^L) worst case, but with the trie pruning the practical
 * cost is far lower and independent of how many words share prefixes.
 * Auxiliary space O(total characters in the word list).
 */
public final class WordSearchII {

    private static final int ALPHABET = 26;
    private static final char VISITED = '#';
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static final class Node {
        private final Node[] children = new Node[ALPHABET];
        /** The complete word ending here, or null. Cleared once collected. */
        private String word;
    }

    private WordSearchII() {
    }

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> found = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0
                || words == null || words.length == 0) {
            return found;
        }
        Node root = buildTrie(words);
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                search(board, r, c, root, found);
            }
        }
        return found;
    }

    private static Node buildTrie(String[] words) {
        Node root = new Node();
        for (String word : words) {
            if (word == null || word.isEmpty()) {
                continue;
            }
            Node current = root;
            boolean usable = true;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c < 'a' || c > 'z') {
                    usable = false;
                    break;
                }
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new Node();
                }
                current = current.children[index];
            }
            if (usable) {
                current.word = word;
            }
        }
        return root;
    }

    private static void search(char[][] board, int r, int c, Node node, List<String> found) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
            return;
        }
        char ch = board[r][c];
        if (ch == VISITED || ch < 'a' || ch > 'z') {
            return;
        }
        Node next = node.children[ch - 'a'];
        if (next == null) {
            return;   // no word continues this way - prune the whole branch
        }
        if (next.word != null) {
            found.add(next.word);
            next.word = null;   // collect once, even if reachable many ways
        }
        board[r][c] = VISITED;
        for (int[] d : DIRECTIONS) {
            search(board, r + d[0], c + d[1], next, found);
        }
        board[r][c] = ch;       // restore
    }
}
