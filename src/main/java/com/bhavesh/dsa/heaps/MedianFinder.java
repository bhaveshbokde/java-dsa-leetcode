package com.bhavesh.dsa.heaps;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 295 - Find Median from Data Stream (Hard).
 *
 * <p>Supports {@code addNum} and {@code findMedian} over an unbounded stream.
 *
 * <p>Keeping a sorted list gives O(1) median but O(n) insertion. Keeping an
 * unsorted list gives O(1) insertion but O(n log n) per median query. Either
 * degrades badly because both operations are frequent.
 *
 * <p>The insight is that <b>the median does not need the data sorted - it needs
 * the middle</b>. Splitting the stream into two halves and keeping only the
 * boundary of each accessible gives exactly that:
 *
 * <ul>
 *   <li>a <b>max-heap</b> holding the smaller half, whose root is the largest
 *       of the small values;</li>
 *   <li>a <b>min-heap</b> holding the larger half, whose root is the smallest
 *       of the large values.</li>
 * </ul>
 *
 * <p>The median is then one root, or the average of both. Each heap sorts
 * nothing internally beyond what the heap property requires, which is why
 * insertion is O(log n) rather than O(n).
 *
 * <p><b>Two invariants must hold after every insert:</b>
 * <ol>
 *   <li><i>Ordering</i> - every value in the low heap is at most every value in
 *       the high heap. This is enforced by always pushing onto the low heap
 *       first and then moving its maximum across, rather than guessing which
 *       side a value belongs to.</li>
 *   <li><i>Balance</i> - the sizes differ by at most one. Keeping the extra
 *       element on a fixed side (the low heap here) removes an entire class of
 *       parity bugs from {@code findMedian}.</li>
 * </ol>
 *
 * <p>The push-then-transfer-then-rebalance order is deliberate: it makes both
 * invariants hold unconditionally, with no case analysis on where the new value
 * falls.
 *
 * <p>{@code addNum} O(log n); {@code findMedian} O(1); space O(n).
 */
public final class MedianFinder {

    /** Smaller half; root is its maximum. Holds the extra element when odd. */
    private final Queue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
    /** Larger half; root is its minimum. */
    private final Queue<Integer> high = new PriorityQueue<>();

    public void addNum(int num) {
        low.add(num);
        // Restore ordering: the low heap's maximum may belong on the high side.
        high.add(low.poll());
        // Restore balance, keeping any surplus on the low side.
        if (high.size() > low.size()) {
            low.add(high.poll());
        }
    }

    public double findMedian() {
        if (low.isEmpty()) {
            throw new IllegalStateException("no values have been added");
        }
        if (low.size() > high.size()) {
            return low.peek();
        }
        // Average as double, and halve rather than divide by 2 after summing as
        // int - two large values would overflow.
        return (low.peek() + (double) high.peek()) / 2.0;
    }

    public int size() {
        return low.size() + high.size();
    }
}
