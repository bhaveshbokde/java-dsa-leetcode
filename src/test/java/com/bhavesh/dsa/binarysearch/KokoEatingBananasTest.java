package com.bhavesh.dsa.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KokoEatingBananasTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(4, KokoEatingBananas.minEatingSpeed(new int[] {3, 6, 7, 11}, 8));
        assertEquals(30, KokoEatingBananas.minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 5));
        assertEquals(23, KokoEatingBananas.minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 6));
    }

    @Test
    @DisplayName("exactly one hour per pile forces the largest pile as the speed")
    void oneHourPerPile() {
        assertEquals(11, KokoEatingBananas.minEatingSpeed(new int[] {3, 6, 7, 11}, 4));
    }

    @Test
    @DisplayName("abundant time allows the slowest speed")
    void abundantTime() {
        assertEquals(1, KokoEatingBananas.minEatingSpeed(new int[] {1, 1, 1}, 1000));
    }

    @Test
    @DisplayName("single pile")
    void singlePile() {
        assertEquals(4, KokoEatingBananas.minEatingSpeed(new int[] {8}, 2));
    }

    @Test
    @DisplayName("impossible inputs are rejected")
    void impossible() {
        assertThrows(IllegalArgumentException.class, () -> KokoEatingBananas.minEatingSpeed(new int[] {1, 2, 3}, 2));
        assertThrows(IllegalArgumentException.class, () -> KokoEatingBananas.minEatingSpeed(new int[] {}, 5));
        assertThrows(IllegalArgumentException.class, () -> KokoEatingBananas.minEatingSpeed(null, 5));
    }

    @Test
    @DisplayName("very large piles do not overflow the hour computation")
    void largePiles() {
        int[] piles = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        assertEquals(Integer.MAX_VALUE, KokoEatingBananas.minEatingSpeed(piles, 2));
    }

    @Test
    @DisplayName("matches a linear scan over candidate speeds on randomised inputs")
    void matchesLinearScan() {
        Random random = new Random(875875L);
        for (int trial = 0; trial < 400; trial++) {
            int[] piles = new int[1 + random.nextInt(6)];
            for (int i = 0; i < piles.length; i++) {
                piles[i] = 1 + random.nextInt(20);
            }
            int h = piles.length + random.nextInt(15);
            int expected = linearScan(piles, h);
            assertEquals(expected, KokoEatingBananas.minEatingSpeed(piles, h),
                    () -> "mismatch h=" + h + " piles=" + Arrays.toString(piles));
        }
    }

    /** Tries every speed from 1 upwards; correct by construction. */
    private static int linearScan(int[] piles, int h) {
        for (int speed = 1; ; speed++) {
            long hours = 0;
            for (int pile : piles) {
                hours += ((long) pile + speed - 1) / speed;
            }
            if (hours <= h) {
                return speed;
            }
        }
    }
}
