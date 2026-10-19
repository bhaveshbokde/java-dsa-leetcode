package com.bhavesh.dsa.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MedianFinderTest {

    private static final double EPS = 1e-9;

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        assertEquals(1.5, finder.findMedian(), EPS);
        finder.addNum(3);
        assertEquals(2.0, finder.findMedian(), EPS);
    }

    @Test
    @DisplayName("single element")
    void singleElement() {
        MedianFinder finder = new MedianFinder();
        finder.addNum(7);
        assertEquals(7.0, finder.findMedian(), EPS);
    }

    @Test
    @DisplayName("querying before any value is added is rejected")
    void emptyQuery() {
        assertThrows(IllegalStateException.class, () -> new MedianFinder().findMedian());
    }

    @Test
    @DisplayName("ascending and descending insertion orders give the same medians")
    void insertionOrderIrrelevant() {
        MedianFinder ascending = new MedianFinder();
        MedianFinder descending = new MedianFinder();
        for (int i = 1; i <= 9; i++) {
            ascending.addNum(i);
        }
        for (int i = 9; i >= 1; i--) {
            descending.addNum(i);
        }
        assertEquals(5.0, ascending.findMedian(), EPS);
        assertEquals(5.0, descending.findMedian(), EPS);
    }

    @Test
    @DisplayName("duplicates and negatives")
    void duplicatesAndNegatives() {
        MedianFinder finder = new MedianFinder();
        for (int v : new int[] {-5, -5, -5, -5}) {
            finder.addNum(v);
        }
        assertEquals(-5.0, finder.findMedian(), EPS);
        finder.addNum(5);
        assertEquals(-5.0, finder.findMedian(), EPS);
    }

    @Test
    @DisplayName("extreme values do not overflow when averaged")
    void averagingDoesNotOverflow() {
        MedianFinder finder = new MedianFinder();
        finder.addNum(Integer.MAX_VALUE);
        finder.addNum(Integer.MAX_VALUE);
        // Summing as int then halving would wrap to -1.
        assertEquals((double) Integer.MAX_VALUE, finder.findMedian(), EPS);

        MedianFinder spread = new MedianFinder();
        spread.addNum(Integer.MIN_VALUE);
        spread.addNum(Integer.MAX_VALUE);
        assertEquals(-0.5, spread.findMedian(), EPS);
    }

    @Test
    @DisplayName("matches a sort-every-time reference after every insertion")
    void matchesSortingReference() {
        Random random = new Random(295295L);
        for (int trial = 0; trial < 60; trial++) {
            MedianFinder finder = new MedianFinder();
            List<Integer> seen = new ArrayList<>();
            for (int step = 0; step < 200; step++) {
                int value = random.nextInt(201) - 100;
                finder.addNum(value);
                seen.add(value);
                assertEquals(seen.size(), finder.size());
                assertEquals(medianOf(seen), finder.findMedian(), EPS,
                        () -> "mismatch after " + seen.size() + " values");
            }
        }
    }

    private static double medianOf(List<Integer> values) {
        List<Integer> sorted = new ArrayList<>(values);
        Collections.sort(sorted);
        int n = sorted.size();
        if (n % 2 == 1) {
            return sorted.get(n / 2);
        }
        return (sorted.get(n / 2 - 1) + (double) sorted.get(n / 2)) / 2.0;
    }

    @Test
    @DisplayName("a large stream stays responsive")
    void largeStream() {
        MedianFinder finder = new MedianFinder();
        for (int i = 1; i <= 100_000; i++) {
            finder.addNum(i);
        }
        assertEquals(50_000.5, finder.findMedian(), EPS);
        assertEquals(100_000, finder.size());
    }
}
