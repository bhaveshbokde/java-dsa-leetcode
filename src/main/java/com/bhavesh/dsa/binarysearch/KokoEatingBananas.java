package com.bhavesh.dsa.binarysearch;

/**
 * LeetCode 875 - Koko Eating Bananas (Medium).
 *
 * <p>Returns the smallest eating speed at which all piles can be finished
 * within {@code h} hours.
 *
 * <p>This is binary search on the <em>answer</em> rather than on the input, and
 * the input array is never sorted. The requirement is a monotone predicate:
 * "speed k finishes in time". If a given speed is fast enough then every larger
 * speed is too, so the feasible speeds form a suffix of {@code [1, maxPile]},
 * and the task is to find its first element - exactly a lower-bound search.
 *
 * <p>Hours at speed k is {@code sum(ceil(pile / k))}. The ceiling is computed as
 * {@code (pile + k - 1) / k} to stay in integer arithmetic; floating-point
 * division then {@code Math.ceil} introduces rounding errors at large values.
 *
 * <p>The upper bound is the largest pile, because no speed above that finishes
 * sooner - each pile still costs a whole hour.
 *
 * <p>Time O(n log maxPile), auxiliary space O(1).
 */
public final class KokoEatingBananas {

    private KokoEatingBananas() {
    }

    public static int minEatingSpeed(int[] piles, int h) {
        if (piles == null || piles.length == 0 || h < piles.length) {
            throw new IllegalArgumentException("h must be at least the number of piles");
        }
        int low = 1;
        int high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        // Half-open lower-bound search over the monotone predicate.
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (hoursNeeded(piles, mid) <= h) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static long hoursNeeded(int[] piles, int speed) {
        long hours = 0;
        for (int pile : piles) {
            // The cast must come first. Written as `(pile + speed - 1L)`, Java
            // evaluates `pile + speed` in int arithmetic before the long is
            // involved, and that addition overflows for large piles and speeds.
            hours += ((long) pile + speed - 1) / speed;
        }
        return hours;
    }
}
