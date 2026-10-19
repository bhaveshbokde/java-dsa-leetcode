package com.bhavesh.dsa.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 20 - Valid Parentheses (Easy).
 *
 * <p>Decides whether a string of brackets is correctly nested and closed.
 *
 * <p>Counting brackets is not sufficient - "([)]" has balanced counts and is
 * invalid - because nesting order matters. A stack is the data structure that
 * encodes "most recently opened, first to be closed", which is exactly the
 * grammar being checked.
 *
 * <p>Pushing the <em>expected closer</em> rather than the opener removes the
 * mapping lookup at pop time and makes the comparison a single character
 * equality.
 *
 * <p>Three conditions must all be checked, and omitting any one is the usual
 * bug: a closer with an empty stack, a closer that does not match the top, and
 * a non-empty stack at the end.
 *
 * <p>{@link ArrayDeque} is used rather than {@link java.util.Stack}, which is
 * synchronised legacy API extending {@code Vector} and iterates in the wrong
 * order.
 *
 * <p>Time O(n), auxiliary space O(n).
 */
public final class ValidParentheses {

    private ValidParentheses() {
    }

    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        Deque<Character> expected = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    expected.push(')');
                    break;
                case '[':
                    expected.push(']');
                    break;
                case '{':
                    expected.push('}');
                    break;
                default:
                    if (expected.isEmpty() || expected.pop() != c) {
                        return false;
                    }
                    break;
            }
        }
        return expected.isEmpty();
    }
}
