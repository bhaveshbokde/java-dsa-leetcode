package com.bhavesh.dsa.dp;

/**
 * LeetCode 1143 - Longest Common Subsequence (Medium).
 *
 * <p>Returns the length of the longest sequence appearing in both strings in
 * order, though not necessarily contiguously.
 *
 * <p>The recurrence branches on the last characters. If they match, that
 * character can always be taken - there is never a reason to discard a
 * matching pair - so the answer is one plus the LCS of both prefixes. If they
 * differ, at least one of the two characters is unusable, and the answer is the
 * better of dropping either.
 *
 * <p>The number of distinct subproblems is (m+1)(n+1), which bounds the work at
 * O(m * n). Exhaustive subsequence enumeration is O(2^m) and useless beyond
 * tiny inputs.
 *
 * <p>Only the previous row is ever read, so the table collapses to two rows of
 * length {@code n + 1}. The diagonal term must be saved before being
 * overwritten, which is the one subtle part of the rolling version.
 *
 * <p>Note this is <em>subsequence</em>, not substring: contiguity is not
 * required, and the substring variant has a different recurrence that resets to
 * zero on a mismatch.
 *
 * <p>Time O(m * n), auxiliary space O(min(m, n)).
 */
public final class LongestCommonSubsequence {

    private LongestCommonSubsequence() {
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        // Iterate over the longer string so the rolling array is the shorter one.
        String outer = text1.length() >= text2.length() ? text1 : text2;
        String inner = text1.length() >= text2.length() ? text2 : text1;

        int[] previous = new int[inner.length() + 1];
        int[] current = new int[inner.length() + 1];

        for (int i = 1; i <= outer.length(); i++) {
            for (int j = 1; j <= inner.length(); j++) {
                if (outer.charAt(i - 1) == inner.charAt(j - 1)) {
                    current[j] = previous[j - 1] + 1;
                } else {
                    current[j] = Math.max(previous[j], current[j - 1]);
                }
            }
            int[] swap = previous;
            previous = current;
            current = swap;
            java.util.Arrays.fill(current, 0);
        }
        return previous[inner.length()];
    }
}
