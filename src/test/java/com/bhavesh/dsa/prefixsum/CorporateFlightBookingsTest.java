package com.bhavesh.dsa.prefixsum;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CorporateFlightBookingsTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        assertArrayEquals(new int[] {10, 55, 45, 25, 25},
                CorporateFlightBookings.corpFlightBookings(bookings, 5));
    }

    @Test
    @DisplayName("second LeetCode example")
    void secondExample() {
        int[][] bookings = {{1, 2, 10}, {2, 2, 15}};
        assertArrayEquals(new int[] {10, 25}, CorporateFlightBookings.corpFlightBookings(bookings, 2));
    }

    @Test
    @DisplayName("no bookings leaves every flight empty")
    void noBookings() {
        assertArrayEquals(new int[] {0, 0, 0}, CorporateFlightBookings.corpFlightBookings(new int[0][], 3));
    }

    @Test
    @DisplayName("a booking spanning every flight")
    void fullSpan() {
        int[][] bookings = {{1, 4, 7}};
        assertArrayEquals(new int[] {7, 7, 7, 7}, CorporateFlightBookings.corpFlightBookings(bookings, 4));
    }

    @Test
    @DisplayName("a booking ending on the last flight writes past the end safely")
    void endsOnLastFlight() {
        int[][] bookings = {{3, 3, 5}};
        assertArrayEquals(new int[] {0, 0, 5}, CorporateFlightBookings.corpFlightBookings(bookings, 3));
    }

    @Test
    @DisplayName("single flight")
    void singleFlight() {
        assertArrayEquals(new int[] {9}, CorporateFlightBookings.corpFlightBookings(new int[][] {{1, 1, 9}}, 1));
    }

    @Test
    @DisplayName("matches a direct range-application reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(1109L);
        for (int trial = 0; trial < 400; trial++) {
            int n = 1 + random.nextInt(10);
            int[][] bookings = new int[random.nextInt(6)][3];
            for (int[] booking : bookings) {
                int first = 1 + random.nextInt(n);
                int last = first + random.nextInt(n - first + 1);
                booking[0] = first;
                booking[1] = last;
                booking[2] = random.nextInt(30);
            }
            assertArrayEquals(bruteForce(bookings, n),
                    CorporateFlightBookings.corpFlightBookings(bookings, n),
                    () -> "mismatch n=" + n + " bookings=" + Arrays.deepToString(bookings));
        }
    }

    private static int[] bruteForce(int[][] bookings, int n) {
        int[] totals = new int[n];
        for (int[] booking : bookings) {
            for (int flight = booking[0]; flight <= booking[1]; flight++) {
                totals[flight - 1] += booking[2];
            }
        }
        return totals;
    }
}
