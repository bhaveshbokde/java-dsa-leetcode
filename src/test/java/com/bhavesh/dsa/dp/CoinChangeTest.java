package com.bhavesh.dsa.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoinChangeTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(3, CoinChange.coinChange(new int[] {1, 2, 5}, 11));
        assertEquals(-1, CoinChange.coinChange(new int[] {2}, 3));
        assertEquals(0, CoinChange.coinChange(new int[] {1}, 0));
    }

    @Test
    @DisplayName("the case where a greedy largest-first choice is wrong")
    void greedyFails() {
        // Greedy: 4 + 1 + 1 = three coins. Optimum: 3 + 3 = two coins.
        assertEquals(2, CoinChange.coinChange(new int[] {1, 3, 4}, 6));
    }

    @Test
    @DisplayName("amount zero needs no coins")
    void amountZero() {
        assertEquals(0, CoinChange.coinChange(new int[] {5, 7}, 0));
        assertEquals(0, CoinChange.coinChange(new int[] {}, 0));
    }

    @Test
    @DisplayName("no coins and unreachable amounts")
    void unreachable() {
        assertEquals(-1, CoinChange.coinChange(new int[] {}, 5));
        assertEquals(-1, CoinChange.coinChange(null, 5));
        assertEquals(-1, CoinChange.coinChange(new int[] {5, 10}, 3));
    }

    @Test
    @DisplayName("negative amount is rejected")
    void negativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> CoinChange.coinChange(new int[] {1}, -1));
    }

    @Test
    @DisplayName("a single coin dividing the amount exactly")
    void exactDivision() {
        assertEquals(5, CoinChange.coinChange(new int[] {3}, 15));
    }

    @Test
    @DisplayName("non-positive coin values are ignored rather than looping forever")
    void nonPositiveCoinsIgnored() {
        assertEquals(3, CoinChange.coinChange(new int[] {0, 1, 5, -2}, 7));
    }

    @Test
    @DisplayName("matches a breadth-first reference on randomised inputs")
    void matchesBfsReference() {
        Random random = new Random(322322L);
        for (int trial = 0; trial < 400; trial++) {
            int[] coins = new int[1 + random.nextInt(4)];
            for (int i = 0; i < coins.length; i++) {
                coins[i] = 1 + random.nextInt(9);
            }
            int amount = random.nextInt(40);
            assertEquals(bfs(coins, amount), CoinChange.coinChange(coins.clone(), amount),
                    () -> "mismatch amount=" + amount + " coins=" + Arrays.toString(coins));
        }
    }

    /** Shortest path from 0 to amount where each coin is an edge weight of 1. */
    private static int bfs(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        boolean[] seen = new boolean[amount + 1];
        java.util.Deque<Integer> queue = new java.util.ArrayDeque<>();
        queue.add(0);
        seen[0] = true;
        int depth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;
            for (int i = 0; i < levelSize; i++) {
                int at = queue.poll();
                for (int coin : coins) {
                    int next = at + coin;
                    if (next == amount) {
                        return depth;
                    }
                    if (next < amount && !seen[next]) {
                        seen[next] = true;
                        queue.add(next);
                    }
                }
            }
        }
        return -1;
    }
}
