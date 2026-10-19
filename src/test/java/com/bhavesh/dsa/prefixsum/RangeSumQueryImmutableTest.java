package com.bhavesh.dsa.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RangeSumQueryImmutableTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        RangeSumQueryImmutable queries = new RangeSumQueryImmutable(new int[] {-2, 0, 3, -5, 2, -1});
        assertEquals(1, queries.sumRange(0, 2));
        assertEquals(-1, queries.sumRange(2, 5));
        assertEquals(-3, queries.sumRange(0, 5));
    }

    @Test
    @DisplayName("a single-element range returns that element")
    void singleElementRange() {
        RangeSumQueryImmutable queries = new RangeSumQueryImmutable(new int[] {7, 8, 9});
        assertEquals(7, queries.sumRange(0, 0));
        assertEquals(9, queries.sumRange(2, 2));
    }

    @Test
    @DisplayName("ranges starting at index 0 need no special case")
    void rangeFromZero() {
        RangeSumQueryImmutable queries = new RangeSumQueryImmutable(new int[] {1, 2, 3, 4});
        assertEquals(10, queries.sumRange(0, 3));
    }

    @Test
    @DisplayName("invalid ranges are rejected")
    void invalidRanges() {
        RangeSumQueryImmutable queries = new RangeSumQueryImmutable(new int[] {1, 2, 3});
        assertThrows(IndexOutOfBoundsException.class, () -> queries.sumRange(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> queries.sumRange(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> queries.sumRange(2, 1));
    }

    @Test
    @DisplayName("a null array is rejected at construction")
    void nullArray() {
        assertThrows(IllegalArgumentException.class, () -> new RangeSumQueryImmutable(null));
    }

    @Test
    @DisplayName("sums exceeding int range are available exactly as long")
    void largeSums() {
        RangeSumQueryImmutable queries =
                new RangeSumQueryImmutable(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE});
        assertEquals(2L * Integer.MAX_VALUE, queries.sumRangeExact(0, 1));
    }

    @Test
    @DisplayName("matches direct summation for every range on randomised inputs")
    void matchesDirectSummation() {
        Random random = new Random(303303L);
        for (int trial = 0; trial < 200; trial++) {
            int[] nums = new int[1 + random.nextInt(10)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(21) - 10;
            }
            RangeSumQueryImmutable queries = new RangeSumQueryImmutable(nums);
            for (int left = 0; left < nums.length; left++) {
                int expected = 0;
                for (int right = left; right < nums.length; right++) {
                    expected += nums[right];
                    assertEquals(expected, queries.sumRange(left, right));
                }
            }
        }
    }
}
