package com.bhavesh.dsa.prefixsum;

/**
 * LeetCode 1109 - Corporate Flight Bookings (Medium).
 *
 * <p>Each booking {@code [first, last, seats]} adds {@code seats} to every
 * flight in the inclusive range. Returns the final per-flight totals.
 *
 * <p>Applying each booking directly is O(bookings * n) and times out on the
 * stated constraints. The difference-array technique turns each range update
 * into two point updates: add {@code seats} at {@code first}, subtract it just
 * past {@code last}. A running prefix sum over that difference array then
 * reconstructs every total in one pass.
 *
 * <p>This is the inverse of the prefix-sum idea. Prefix sums answer range
 * queries on static data in O(1); difference arrays apply range updates to data
 * read only at the end, also in O(1) each. Neither handles interleaved updates
 * and queries - that needs a Fenwick or segment tree.
 *
 * <p>Time O(bookings + n), auxiliary space O(n).
 */
public final class CorporateFlightBookings {

    private CorporateFlightBookings() {
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        // One extra slot so a booking ending at the last flight can write its
        // cancelling entry without a bounds check.
        long[] delta = new long[n + 1];
        for (int[] booking : bookings) {
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];
            delta[first - 1] += seats;
            delta[last] -= seats;
        }
        int[] totals = new int[n];
        long running = 0;
        for (int i = 0; i < n; i++) {
            running += delta[i];
            totals[i] = (int) running;
        }
        return totals;
    }
}
