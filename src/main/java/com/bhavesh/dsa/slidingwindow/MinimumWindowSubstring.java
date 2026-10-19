package com.bhavesh.dsa.slidingwindow;

/**
 * LeetCode 76 - Minimum Window Substring (Hard).
 *
 * <p>Returns the shortest substring of {@code s} containing every character of
 * {@code t} <b>including multiplicities</b>, or {@code ""} if none exists.
 *
 * <p>The multiplicity requirement is the part most solutions get wrong: for
 * {@code t = "aab"} a window holding one {@code 'a'} and one {@code 'b'} is
 * not sufficient.
 *
 * <p>A sliding window applies because the "contains everything needed" property
 * is monotone: adding characters can only help, removing them can only hurt. So
 * the window expands until it is valid and then contracts while it stays valid,
 * recording the best length as it goes.
 *
 * <p><b>The device that keeps it O(n) is the {@code missing} counter.</b>
 * Re-checking "does the window still satisfy t" by comparing 128 counts would
 * make every step O(alphabet). Instead a single integer tracks how many
 * <em>required occurrences</em> are still unmet. A character decrements it only
 * when the window has not yet met that character's quota - which is exactly
 * what makes surplus copies not count. Symmetrically, removing a character
 * increments it only when that pushes the window below the quota.
 *
 * <p>That asymmetric update is the whole trick, and it is why the counts array
 * is allowed to go negative: a negative entry means "surplus", and surplus must
 * be consumed before a removal becomes significant.
 *
 * <p>Time O(|s| + |t|) - each index enters and leaves the window once.
 * Auxiliary space O(1) for a fixed alphabet.
 */
public final class MinimumWindowSubstring {

    private static final int ASCII = 128;

    private MinimumWindowSubstring() {
    }

    public static String minWindow(String s, String t) {
        if (s == null || t == null || t.isEmpty() || s.length() < t.length()) {
            return "";
        }
        int[] needed = new int[ASCII];
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c >= ASCII) {
                throw new IllegalArgumentException("this implementation assumes ASCII input");
            }
            needed[c]++;
        }

        int missing = t.length();      // required occurrences still unmet
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char entering = s.charAt(right);
            if (entering >= ASCII) {
                throw new IllegalArgumentException("this implementation assumes ASCII input");
            }
            // Only a still-needed character reduces the deficit.
            if (needed[entering] > 0) {
                missing--;
            }
            needed[entering]--;        // may go negative: that is surplus

            while (missing == 0) {
                if (right - left + 1 < bestLength) {
                    bestLength = right - left + 1;
                    bestStart = left;
                }
                char leaving = s.charAt(left++);
                needed[leaving]++;
                // Only crossing back above zero creates a real deficit.
                if (needed[leaving] > 0) {
                    missing++;
                }
            }
        }
        return bestLength == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLength);
    }
}
