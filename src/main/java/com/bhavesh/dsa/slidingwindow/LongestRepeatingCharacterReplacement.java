package com.bhavesh.dsa.slidingwindow;

/**
 * LeetCode 424 - Longest Repeating Character Replacement (Medium).
 *
 * <p>Returns the length of the longest substring that can be made uniform by
 * replacing at most {@code k} characters.
 *
 * <p>A window is feasible when {@code windowLength - countOfMostFrequent <= k},
 * because the cheapest way to make it uniform is to keep the most frequent
 * character and replace the rest.
 *
 * <p>The subtle part of the fast version is that {@code maxCount} is never
 * decreased when the window shrinks, which looks like a bug. It is not, given
 * what the function returns. {@code maxCount} is the best frequency seen in
 * <em>any</em> window so far; if it is stale and too large, the feasibility test
 * wrongly passes and the window slides forward without shrinking - but it also
 * never grows, so {@code best} cannot increase from a stale value. The recorded
 * answer therefore only ever comes from a window that was genuinely feasible at
 * the time its length was recorded.
 *
 * <p>Because that reasoning is easy to get wrong, {@link #characterReplacementRecomputed}
 * implements the obviously-correct variant that recomputes the maximum over the
 * 26 counters, and the tests assert the two agree.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class LongestRepeatingCharacterReplacement {

    private static final int ALPHABET = 26;

    private LongestRepeatingCharacterReplacement() {
    }

    public static int characterReplacement(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] counts = new int[ALPHABET];
        int left = 0;
        int maxCount = 0;
        int best = 0;
        for (int right = 0; right < s.length(); right++) {
            maxCount = Math.max(maxCount, ++counts[s.charAt(right) - 'A']);
            // Window length minus the most frequent character is the number of
            // replacements required; shrink by one when that exceeds k.
            if (right - left + 1 - maxCount > k) {
                counts[s.charAt(left) - 'A']--;
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    /**
     * Obviously-correct variant: recomputes the window maximum on every shrink.
     *
     * <p>Time O(26n), auxiliary space O(1).
     */
    public static int characterReplacementRecomputed(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] counts = new int[ALPHABET];
        int left = 0;
        int best = 0;
        for (int right = 0; right < s.length(); right++) {
            counts[s.charAt(right) - 'A']++;
            while (right - left + 1 - maxOf(counts) > k) {
                counts[s.charAt(left) - 'A']--;
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    private static int maxOf(int[] counts) {
        int max = 0;
        for (int count : counts) {
            max = Math.max(max, count);
        }
        return max;
    }
}
