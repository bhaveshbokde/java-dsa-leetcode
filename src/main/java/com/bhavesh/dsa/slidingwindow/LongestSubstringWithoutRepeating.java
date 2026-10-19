package com.bhavesh.dsa.slidingwindow;

/**
 * LeetCode 3 - Longest Substring Without Repeating Characters (Medium).
 *
 * <p>Returns the length of the longest substring containing no repeated
 * character.
 *
 * <p>Checking every substring is O(n^2) or worse. The property that enables a
 * sliding window is monotonicity: if a window contains a duplicate, every
 * larger window containing it does too. So the window never needs to shrink
 * more than enough to remove the offending repeat, and each endpoint only ever
 * moves forward.
 *
 * <p>Rather than stepping {@code left} one position at a time, the last index
 * of each character is remembered, so the window jumps straight past the
 * previous occurrence. The {@code Math.max} on that jump is essential: a stale
 * index from before the current window must never drag {@code left} backwards.
 *
 * <p>A 128-slot array replaces a hash map for ASCII input - no boxing or
 * hashing - and is initialised to -1 to mean "not yet seen".
 *
 * <p>Time O(n), auxiliary space O(1) for a fixed alphabet.
 */
public final class LongestSubstringWithoutRepeating {

    private static final int ASCII = 128;

    private LongestSubstringWithoutRepeating() {
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] lastSeen = new int[ASCII];
        java.util.Arrays.fill(lastSeen, -1);

        int best = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (c >= ASCII) {
                throw new IllegalArgumentException("this implementation assumes ASCII input");
            }
            // Never move left backwards: lastSeen[c] may predate the window.
            left = Math.max(left, lastSeen[c] + 1);
            lastSeen[c] = right;
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
