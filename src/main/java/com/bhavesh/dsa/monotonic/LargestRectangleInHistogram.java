package com.bhavesh.dsa.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 84 - Largest Rectangle in Histogram (Hard).
 *
 * <p>Returns the area of the largest axis-aligned rectangle fitting under the
 * histogram.
 *
 * <p>Every maximal rectangle is limited in height by its shortest bar. So it
 * suffices to ask, for each bar, how far it can extend left and right before
 * meeting a strictly shorter bar - that span times its height is the largest
 * rectangle in which this bar is the shortest. Taking the maximum over all bars
 * covers every candidate.
 *
 * <p>Computing those spans by scanning is O(n^2). A monotonic increasing stack
 * of indices finds both boundaries in one pass: when a bar shorter than the
 * stack top arrives, it is exactly the right boundary for that top, and the new
 * stack top is exactly its left boundary.
 *
 * <p>The width calculation {@code right - left - 1} uses the index below the
 * popped one as an exclusive left edge, which is why an empty stack means the
 * bar extends all the way to index 0.
 *
 * <p>A sentinel height of 0 is processed after the real bars so that every
 * remaining index is forced off the stack, rather than duplicating the drain
 * logic after the loop.
 *
 * <p>Time O(n), auxiliary space O(n).
 */
public final class LargestRectangleInHistogram {

    private LargestRectangleInHistogram() {
    }

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int best = 0;
        for (int i = 0; i <= heights.length; i++) {
            int current = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > current) {
                int height = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;
                best = Math.max(best, height * width);
            }
            stack.push(i);
        }
        return best;
    }
}
