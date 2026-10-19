package com.bhavesh.dsa.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 739 - Daily Temperatures (Medium).
 *
 * <p>For each day, how many days until a warmer one; 0 if none follows.
 *
 * <p>The quadratic solution scans forward from every day. The monotonic stack
 * removes that by inverting the question: instead of each day searching for its
 * successor, each new day <em>resolves</em> the earlier days that were waiting
 * for it.
 *
 * <p>The stack holds indices whose answers are still unknown, and their
 * temperatures are necessarily non-increasing from bottom to top. That
 * invariant is what makes the algorithm correct: when a warmer day arrives it
 * resolves every index on top of the stack that is colder, and the first index
 * it cannot resolve guarantees none deeper can be resolved either, so the loop
 * stops immediately.
 *
 * <p>Linearity: each index is pushed exactly once and popped at most once, so
 * the inner loop performs O(n) work in total across the whole run.
 *
 * <p>Time O(n), auxiliary space O(n).
 */
public final class DailyTemperatures {

    private DailyTemperatures() {
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }
        int[] answer = new int[temperatures.length];
        Deque<Integer> waiting = new ArrayDeque<>();
        for (int day = 0; day < temperatures.length; day++) {
            while (!waiting.isEmpty() && temperatures[day] > temperatures[waiting.peek()]) {
                int resolved = waiting.pop();
                answer[resolved] = day - resolved;
            }
            waiting.push(day);
        }
        // Anything still waiting never saw a warmer day; its answer stays 0.
        return answer;
    }
}
