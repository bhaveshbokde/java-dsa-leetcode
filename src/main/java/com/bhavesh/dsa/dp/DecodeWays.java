package com.bhavesh.dsa.dp;

/**
 * LeetCode 91 - Decode Ways (Medium).
 *
 * <p>Counts the ways a digit string decodes to letters, where
 * {@code 'A'..'Z'} map to {@code "1".."26"}.
 *
 * <p>The shape is the same as Climbing Stairs - each position is reached by a
 * step of one or two - but with <b>validity conditions on each step</b>, and
 * those conditions are where every bug lives:
 *
 * <ul>
 *   <li>A <b>one-digit</b> step is valid only when the digit is not
 *       {@code '0'}. There is no letter for 0.</li>
 *   <li>A <b>two-digit</b> step is valid only when the pair is between 10 and
 *       26. Note that excludes {@code "06"} - a leading zero is not a valid
 *       two-digit encoding, so {@code "06"} decodes zero ways while
 *       {@code "6"} decodes one.</li>
 * </ul>
 *
 * <p>Zeros are the whole difficulty. A {@code '0'} cannot stand alone, so it is
 * decodable <b>only</b> as part of a preceding 10 or 20. A string containing
 * {@code "30"} or starting with {@code '0'} therefore has zero decodings, and
 * the recurrence produces that naturally once both validity tests are in place
 * - no special-casing required.
 *
 * <p>Only the previous two counts are ever read, so the O(n) table collapses to
 * two variables.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class DecodeWays {

    private DecodeWays() {
    }

    public static int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("the input must contain digits only");
            }
        }
        // twoBack = ways to decode the prefix of length i-2
        // oneBack = ways to decode the prefix of length i-1
        int twoBack = 1;                                   // the empty prefix
        int oneBack = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 1; i < s.length(); i++) {
            int current = 0;
            if (s.charAt(i) != '0') {
                current += oneBack;                        // single-digit step
            }
            int pair = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            if (pair >= 10 && pair <= 26) {                // excludes "06" etc.
                current += twoBack;                        // two-digit step
            }
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }
}
