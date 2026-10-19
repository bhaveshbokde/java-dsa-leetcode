package com.bhavesh.dsa.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClimbingStairsTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(2, ClimbingStairs.climbStairs(2));
        assertEquals(3, ClimbingStairs.climbStairs(3));
    }

    @Test
    @DisplayName("zero and one step")
    void baseCases() {
        assertEquals(1, ClimbingStairs.climbStairs(0));
        assertEquals(1, ClimbingStairs.climbStairs(1));
    }

    @Test
    @DisplayName("negative input is rejected")
    void negative() {
        assertThrows(IllegalArgumentException.class, () -> ClimbingStairs.climbStairs(-1));
    }

    @Test
    @DisplayName("inputs that would overflow a long are rejected rather than wrapping")
    void overflowRejected() {
        // climbStairs(n) is Fibonacci(n + 1); F(93) exceeds Long.MAX_VALUE,
        // so n = 91 is the largest input with an exact answer.
        assertEquals(2_880_067_194_370_816_120L, ClimbingStairs.climbStairs(89));
        assertEquals(4_660_046_610_375_530_309L, ClimbingStairs.climbStairs(90));
        assertEquals(7_540_113_804_746_346_429L, ClimbingStairs.climbStairs(91));
        assertThrows(IllegalArgumentException.class, () -> ClimbingStairs.climbStairs(92));
    }

    @Test
    @DisplayName("n = 45 exceeds int range, which is why the return type is long")
    void exceedsIntRange() {
        long result = ClimbingStairs.climbStairs(46);
        org.junit.jupiter.api.Assertions.assertTrue(result > Integer.MAX_VALUE,
                "the 46th value should already exceed Integer.MAX_VALUE");
    }

    @Test
    @DisplayName("matches a memoised recursive reference")
    void matchesMemoisedRecursion() {
        Map<Integer, Long> memo = new HashMap<>();
        for (int n = 0; n <= 60; n++) {
            assertEquals(recursive(n, memo), ClimbingStairs.climbStairs(n), "mismatch at n=" + n);
        }
    }

    private static long recursive(int n, Map<Integer, Long> memo) {
        if (n <= 1) {
            return 1;
        }
        Long cached = memo.get(n);
        if (cached != null) {
            return cached;
        }
        long value = recursive(n - 1, memo) + recursive(n - 2, memo);
        memo.put(n, value);
        return value;
    }
}
