package com.bhavesh.dsa.monotonic;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DailyTemperaturesTest {

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        assertArrayEquals(new int[] {1, 1, 4, 2, 1, 1, 0, 0},
                DailyTemperatures.dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73}));
    }

    @Test
    @DisplayName("monotonically increasing: always the next day")
    void increasing() {
        assertArrayEquals(new int[] {1, 1, 1, 0},
                DailyTemperatures.dailyTemperatures(new int[] {30, 40, 50, 60}));
    }

    @Test
    @DisplayName("monotonically decreasing: never warmer")
    void decreasing() {
        assertArrayEquals(new int[] {0, 0, 0}, DailyTemperatures.dailyTemperatures(new int[] {50, 40, 30}));
    }

    @Test
    @DisplayName("all equal temperatures never count as warmer")
    void allEqual() {
        assertArrayEquals(new int[] {0, 0, 0}, DailyTemperatures.dailyTemperatures(new int[] {40, 40, 40}));
    }

    @Test
    @DisplayName("single day, empty and null")
    void degenerate() {
        assertArrayEquals(new int[] {0}, DailyTemperatures.dailyTemperatures(new int[] {70}));
        assertArrayEquals(new int[0], DailyTemperatures.dailyTemperatures(new int[] {}));
        assertArrayEquals(new int[0], DailyTemperatures.dailyTemperatures(null));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(739739L);
        for (int trial = 0; trial < 600; trial++) {
            int[] temperatures = new int[random.nextInt(14)];
            for (int i = 0; i < temperatures.length; i++) {
                temperatures[i] = 30 + random.nextInt(8);
            }
            assertArrayEquals(bruteForce(temperatures),
                    DailyTemperatures.dailyTemperatures(temperatures.clone()),
                    () -> "mismatch for " + Arrays.toString(temperatures));
        }
    }

    @Test
    @DisplayName("a strictly decreasing input of 100k days stays linear")
    void largeDecreasingInput() {
        int[] temperatures = new int[100_000];
        for (int i = 0; i < temperatures.length; i++) {
            temperatures[i] = temperatures.length - i;
        }
        int[] answer = DailyTemperatures.dailyTemperatures(temperatures);
        assertArrayEquals(new int[temperatures.length], answer);
    }

    private static int[] bruteForce(int[] temperatures) {
        int[] out = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    out[i] = j - i;
                    break;
                }
            }
        }
        return out;
    }
}
