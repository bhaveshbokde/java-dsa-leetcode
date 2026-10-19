package com.bhavesh.dsa.dpadvanced;

/**
 * LeetCode 72 - Edit Distance (Hard). Levenshtein distance.
 *
 * <p>Returns the fewest single-character insertions, deletions or replacements
 * converting one word into another.
 *
 * <p>The state is the pair of prefix lengths, and the recurrence considers the
 * last operation applied. If the final characters already agree, no operation
 * is needed and the cost is that of the two shorter prefixes. Otherwise the
 * last operation was exactly one of three things, and the answer is one plus
 * the cheapest:
 *
 * <ul>
 *   <li>replace - {@code dp[i-1][j-1]}, both prefixes shrink;</li>
 *   <li>delete from the first word - {@code dp[i-1][j]};</li>
 *   <li>insert into the first word - {@code dp[i][j-1]}.</li>
 * </ul>
 *
 * <p>Those three cases are exhaustive because any edit script can be reordered
 * so that the operation touching the last position comes last.
 *
 * <p>The base cases carry real meaning: converting a prefix to the empty string
 * costs one deletion per character, which is why the first row and column count
 * upwards rather than being zero.
 *
 * <p>A rolling pair of rows reduces space from O(m * n) to O(n). The diagonal
 * value must be captured before the row is overwritten.
 *
 * <p>Time O(m * n), auxiliary space O(min(m, n)).
 */
public final class EditDistance {

    private EditDistance() {
    }

    public static int minDistance(String word1, String word2) {
        String first = word1 == null ? "" : word1;
        String second = word2 == null ? "" : word2;
        if (first.isEmpty()) {
            return second.length();
        }
        if (second.isEmpty()) {
            return first.length();
        }
        // Roll over the shorter dimension.
        String outer = first.length() >= second.length() ? first : second;
        String inner = first.length() >= second.length() ? second : first;

        int[] previous = new int[inner.length() + 1];
        for (int j = 0; j <= inner.length(); j++) {
            previous[j] = j;
        }
        int[] current = new int[inner.length() + 1];

        for (int i = 1; i <= outer.length(); i++) {
            current[0] = i;
            for (int j = 1; j <= inner.length(); j++) {
                if (outer.charAt(i - 1) == inner.charAt(j - 1)) {
                    current[j] = previous[j - 1];
                } else {
                    int replace = previous[j - 1];
                    int delete = previous[j];
                    int insert = current[j - 1];
                    current[j] = 1 + Math.min(replace, Math.min(delete, insert));
                }
            }
            int[] swap = previous;
            previous = current;
            current = swap;
        }
        return previous[inner.length()];
    }
}
