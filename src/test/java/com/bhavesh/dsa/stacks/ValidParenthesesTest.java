package com.bhavesh.dsa.stacks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidParenthesesTest {

    @ParameterizedTest
    @ValueSource(strings = {"()", "()[]{}", "{[]}", "", "((()))", "{[()]}"})
    @DisplayName("well-formed inputs")
    void valid(String input) {
        assertTrue(ValidParentheses.isValid(input), () -> "expected valid: '" + input + "'");
    }

    @ParameterizedTest
    @ValueSource(strings = {"(]", "([)]", "(", ")", "([", "]", "{[}]", "(()"})
    @DisplayName("malformed inputs")
    void invalid(String input) {
        assertFalse(ValidParentheses.isValid(input), () -> "expected invalid: '" + input + "'");
    }

    @Test
    @DisplayName("correct counts but wrong nesting order is rejected")
    void countsAreNotEnough() {
        // Both brackets appear exactly twice, yet the nesting interleaves.
        assertFalse(ValidParentheses.isValid("([)]"));
    }

    @Test
    @DisplayName("a closer arriving on an empty stack is rejected")
    void closerOnEmptyStack() {
        assertFalse(ValidParentheses.isValid(")("));
    }

    @Test
    @DisplayName("unclosed openers at the end are rejected")
    void unclosedAtEnd() {
        assertFalse(ValidParentheses.isValid("{[("));
    }

    @Test
    @DisplayName("null is rejected rather than throwing")
    void nullInput() {
        assertFalse(ValidParentheses.isValid(null));
    }

    @Test
    @DisplayName("a deeply nested but valid string is accepted")
    void deeplyNested() {
        int depth = 50_000;
        String input = "(".repeat(depth) + ")".repeat(depth);
        assertTrue(ValidParentheses.isValid(input));
    }
}
