package com.bhavesh.dsa.strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidPalindromeTest {

    @Test
    @DisplayName("LeetCode example with punctuation and mixed case")
    void leetCodeExample() {
        assertTrue(ValidPalindrome.isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    @DisplayName("a non-palindrome is rejected")
    void notAPalindrome() {
        assertFalse(ValidPalindrome.isPalindrome("race a car"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", ".,", "a", "aa", "aba", "0P0"})
    @DisplayName("degenerate and minimal inputs that are palindromes")
    void palindromes(String input) {
        assertTrue(ValidPalindrome.isPalindrome(input), () -> "expected palindrome: '" + input + "'");
    }

    @Test
    @DisplayName("digits are significant: '0P' is not a palindrome")
    void digitsMatter() {
        // A common bug is comparing letters case-insensitively but mishandling
        // digits; '0' and 'P' must not be treated as equal.
        assertFalse(ValidPalindrome.isPalindrome("0P"));
    }

    @Test
    @DisplayName("a string of only punctuation is vacuously a palindrome")
    void onlyPunctuation() {
        assertTrue(ValidPalindrome.isPalindrome("!@#$%^&*()"));
    }

    @Test
    @DisplayName("null is rejected rather than throwing")
    void nullInput() {
        assertFalse(ValidPalindrome.isPalindrome(null));
    }

    @Test
    @DisplayName("matches a clean-then-reverse reference")
    void matchesReference() {
        String[] cases = {"ab_a", "Was it a car or a cat I saw?", "No 'x' in Nixon", "hello", "12321", "12345"};
        for (String input : cases) {
            String cleaned = input.replaceAll("[^A-Za-z0-9]", "").toLowerCase(java.util.Locale.ROOT);
            boolean expected = cleaned.equals(new StringBuilder(cleaned).reverse().toString());
            assertTrue(expected == ValidPalindrome.isPalindrome(input), () -> "mismatch for '" + input + "'");
        }
    }
}
