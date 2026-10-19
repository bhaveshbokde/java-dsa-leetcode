package com.bhavesh.dsa.stringmatching;

/**
 * LeetCode 28 - Find the Index of the First Occurrence in a String (Easy by
 * LeetCode's label, but the optimal algorithm is not). Knuth-Morris-Pratt.
 *
 * <p>Returns the first index at which {@code needle} occurs in {@code haystack},
 * or -1.
 *
 * <p>The naive scan restarts the pattern at every alignment, giving O(n * m) -
 * and the worst case is reachable, not theoretical: searching "aaaab" inside a
 * long run of 'a's backtracks almost the full pattern at every position.
 *
 * <p>KMP removes the backtracking entirely by precomputing, for each pattern
 * prefix, the length of its longest proper prefix that is also a suffix (the
 * "failure function"). On a mismatch, that table says how far the pattern can
 * slide without missing an occurrence, because the matched suffix already
 * equals a pattern prefix of that length. The haystack index never moves
 * backwards.
 *
 * <p>Linearity: each iteration either advances the haystack index or decreases
 * the match length, and the match length only ever grows one step per advance.
 * So the total number of decreases is bounded by the number of advances, giving
 * O(n + m) overall.
 *
 * <p>Time O(n + m), auxiliary space O(m).
 */
public final class KmpSearch {

    private KmpSearch() {
    }

    public static int indexOf(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        int[] failure = buildFailureTable(needle);

        int matched = 0;
        for (int i = 0; i < haystack.length(); i++) {
            // Slide the pattern instead of rewinding the haystack.
            while (matched > 0 && haystack.charAt(i) != needle.charAt(matched)) {
                matched = failure[matched - 1];
            }
            if (haystack.charAt(i) == needle.charAt(matched)) {
                matched++;
            }
            if (matched == needle.length()) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    /**
     * {@code failure[i]} is the length of the longest proper prefix of
     * {@code pattern[0..i]} that is also a suffix of it.
     *
     * <p>Built by matching the pattern against itself with the same sliding
     * rule, which is why the construction is O(m) rather than O(m^2).
     */
    static int[] buildFailureTable(String pattern) {
        int[] failure = new int[pattern.length()];
        int length = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (length > 0 && pattern.charAt(i) != pattern.charAt(length)) {
                length = failure[length - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
            }
            failure[i] = length;
        }
        return failure;
    }
}
