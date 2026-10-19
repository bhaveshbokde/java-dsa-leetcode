package com.bhavesh.dsa.strings;

/**
 * LeetCode 125 - Valid Palindrome (Easy).
 *
 * <p>Decides whether a string reads the same forwards and backwards once
 * non-alphanumeric characters are ignored and case is folded.
 *
 * <p>The obvious implementation builds a cleaned, lower-cased copy and compares
 * it to its reverse. That is also O(n) time but allocates two additional
 * strings. Two pointers walking inwards and skipping junk in place decide the
 * same question with no allocation, and short-circuit on the first mismatch.
 *
 * <p>{@link Character#isLetterOrDigit} and {@link Character#toLowerCase} are
 * used rather than hand-rolled ASCII range checks so the behaviour stays
 * correct for non-ASCII input.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class ValidPalindrome {

    private ValidPalindrome() {
    }

    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
