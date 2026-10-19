package com.bhavesh.dsa.backtracking;

/**
 * LeetCode 79 - Word Search (Medium).
 *
 * <p>Decides whether a word can be spelled by a path of horizontally or
 * vertically adjacent cells, where <b>no cell may be reused within one path</b>.
 *
 * <p>This is backtracking on a grid rather than on a list, and the
 * no-reuse rule is what forces the backtracking: a cell consumed by one branch
 * must become available again when that branch fails, or a later branch that
 * legitimately needs it is wrongly blocked.
 *
 * <p>Marking is done <b>in the grid itself</b> rather than in a separate
 * {@code boolean[][]}: the visited cell is temporarily overwritten with a
 * sentinel and restored on the way out. That saves the O(m*n) array and, more
 * importantly, keeps mark and restore adjacent in the code so they cannot drift
 * apart. The grid is restored exactly, so the method leaves no trace - asserted
 * by a test, because a search that corrupts its input is a nasty bug.
 *
 * <p>The sentinel {@code '#'} is safe because the problem guarantees letters
 * only. If arbitrary characters were possible, a separate visited array would
 * be the correct choice rather than hunting for an unused value.
 *
 * <p>Pruning is simply the character mismatch: the moment the current cell does
 * not match the expected letter the branch dies, which is what keeps the search
 * from exploring all 4^L paths.
 *
 * <p>Time O(m * n * 4^L) worst case for a word of length L; auxiliary space
 * O(L) for the recursion.
 */
public final class WordSearch {

    private static final char VISITED = '#';

    private WordSearch() {
    }

    public static boolean exist(char[][] board, String word) {
        if (word == null || word.isEmpty()) {
            return true;      // the empty word is trivially present
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (search(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean search(char[][] board, String word, int r, int c, int index) {
        if (index == word.length()) {
            return true;
        }
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length
                || board[r][c] != word.charAt(index)) {
            return false;
        }
        char original = board[r][c];
        board[r][c] = VISITED;                       // mark

        boolean found = search(board, word, r + 1, c, index + 1)
                || search(board, word, r - 1, c, index + 1)
                || search(board, word, r, c + 1, index + 1)
                || search(board, word, r, c - 1, index + 1);

        board[r][c] = original;                      // restore, always
        return found;
    }
}
