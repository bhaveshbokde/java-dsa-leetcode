package com.bhavesh.dsa.strings;

/**
 * LeetCode 5 - Longest Palindromic Substring (Medium), with LeetCode 647 -
 * Palindromic Substrings.
 *
 * <p>Returns the longest contiguous palindrome, and separately counts how many
 * palindromic substrings exist.
 *
 * <p>Checking every substring for palindromicity is O(n^3). The DP table over
 * {@code (start, end)} pairs is O(n^2) time and O(n^2) space.
 *
 * <p>Expand-around-centre matches that time with **O(1) space**. The idea is
 * that every palindrome is determined by its centre, and there are only
 * {@code 2n - 1} centres: {@code n} single characters for odd lengths, and
 * {@code n - 1} gaps between adjacent characters for even lengths. Forgetting
 * the even centres is the usual bug and silently misses answers like
 * {@code "abba"}.
 *
 * <p>This is not the theoretical optimum. Manacher's algorithm solves it in
 * O(n) by reusing the mirror structure of already-discovered palindromes, but
 * it is long, delicate, and very rarely what an interviewer wants. O(n^2) with
 * O(1) space is the right practical answer here, and the trade is worth stating
 * rather than claiming optimality.
 *
 * <p>Time O(n^2), auxiliary space O(1).
 */
public final class LongestPalindromicSubstring {

    private LongestPalindromicSubstring() {
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s == null ? "" : s;
        }
        int bestStart = 0;
        int bestLength = 1;

        for (int centre = 0; centre < s.length(); centre++) {
            int odd = expand(s, centre, centre);
            int even = expand(s, centre, centre + 1);
            int longest = Math.max(odd, even);
            if (longest > bestLength) {
                bestLength = longest;
                // Recover the start from the centre and the length. The -1 is
                // what makes this work for both parities.
                bestStart = centre - (longest - 1) / 2;
            }
        }
        return s.substring(bestStart, bestStart + bestLength);
    }

    /** LeetCode 647 - the number of palindromic substrings. */
    public static int countPalindromicSubstrings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (int centre = 0; centre < s.length(); centre++) {
            count += countFrom(s, centre, centre);
            count += countFrom(s, centre, centre + 1);
        }
        return count;
    }

    /** Length of the longest palindrome centred between left and right. */
    private static int expand(String s, int left, int right) {
        int lo = left;
        int hi = right;
        while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) {
            lo--;
            hi++;
        }
        // The loop exits one step past the match on both sides.
        return hi - lo - 1;
    }

    /** Number of palindromes centred between left and right. */
    private static int countFrom(String s, int left, int right) {
        int lo = left;
        int hi = right;
        int found = 0;
        while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) {
            found++;
            lo--;
            hi++;
        }
        return found;
    }
}
