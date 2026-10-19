package com.bhavesh.dsa.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 239 - Sliding Window Maximum (Hard).
 *
 * <p>Returns the maximum of every window of size {@code k}.
 *
 * <p>Recomputing each window is O(n * k). A max-heap improves that to
 * O(n log k) but needs lazy deletion, because a heap cannot remove an arbitrary
 * element efficiently. A monotonic deque achieves O(n) outright.
 *
 * <p>The deque stores indices whose values are strictly decreasing from front to
 * back. The key observation justifying the discards: if a newly arriving value
 * is at least as large as the value at the back, that older element can never be
 * the maximum of any future window - the newcomer is both larger and leaves the
 * window later. It is therefore safe to drop permanently.
 *
 * <p>Given that invariant the front is always the current window's maximum, and
 * it only needs removing when it falls out of range by index.
 *
 * <p>Using {@code >=} when discarding keeps the deque free of duplicate maxima,
 * which is harmless either way but keeps it smaller.
 *
 * <p>Time O(n) - each index is added and removed at most once. Auxiliary space
 * O(k).
 */
public final class SlidingWindowMaximum {

    private SlidingWindowMaximum() {
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        if (k > nums.length) {
            throw new IllegalArgumentException("window size " + k + " exceeds array length " + nums.length);
        }
        int[] answer = new int[nums.length - k + 1];
        Deque<Integer> candidates = new ArrayDeque<>();

        for (int right = 0; right < nums.length; right++) {
            // Drop indices that have slid out of the window.
            while (!candidates.isEmpty() && candidates.peekFirst() <= right - k) {
                candidates.pollFirst();
            }
            // Drop values that can never win again.
            while (!candidates.isEmpty() && nums[candidates.peekLast()] <= nums[right]) {
                candidates.pollLast();
            }
            candidates.addLast(right);
            if (right >= k - 1) {
                answer[right - k + 1] = nums[candidates.peekFirst()];
            }
        }
        return answer;
    }
}
