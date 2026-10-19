package com.bhavesh.dsa.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BestTimeToBuyAndSellStockTest {

    @Test
    @DisplayName("LeetCode example: buy at 1, sell at 6")
    void leetCodeExample() {
        assertEquals(5, BestTimeToBuyAndSellStock.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
    }

    @Test
    @DisplayName("monotonically decreasing prices yield no profit")
    void decreasingPrices() {
        assertEquals(0, BestTimeToBuyAndSellStock.maxProfit(new int[] {7, 6, 4, 3, 1}));
    }

    @Test
    @DisplayName("a single day cannot be both bought and sold")
    void singleDay() {
        assertEquals(0, BestTimeToBuyAndSellStock.maxProfit(new int[] {5}));
    }

    @Test
    @DisplayName("empty and null inputs return zero rather than throwing")
    void degenerateInputs() {
        assertEquals(0, BestTimeToBuyAndSellStock.maxProfit(new int[] {}));
        assertEquals(0, BestTimeToBuyAndSellStock.maxProfit(null));
    }

    @Test
    @DisplayName("all prices equal")
    void flatPrices() {
        assertEquals(0, BestTimeToBuyAndSellStock.maxProfit(new int[] {3, 3, 3, 3}));
    }

    @Test
    @DisplayName("the best buy is not the global minimum when the minimum comes last")
    void minimumAtEnd() {
        // The global minimum is 1 at the final index, which has no later sell day.
        assertEquals(4, BestTimeToBuyAndSellStock.maxProfit(new int[] {2, 6, 3, 1}));
    }

    @Test
    @DisplayName("matches an O(n^2) reference on randomised inputs")
    void matchesBruteForce() {
        Random random = new Random(20261019L);
        for (int trial = 0; trial < 500; trial++) {
            int[] prices = new int[1 + random.nextInt(12)];
            for (int i = 0; i < prices.length; i++) {
                prices[i] = random.nextInt(50);
            }
            assertEquals(bruteForce(prices), BestTimeToBuyAndSellStock.maxProfit(prices.clone()),
                    () -> "mismatch for " + java.util.Arrays.toString(prices));
        }
    }

    /** Checks every (buy, sell) pair; obviously correct and used only as an oracle. */
    private static int bruteForce(int[] prices) {
        int best = 0;
        for (int buy = 0; buy < prices.length; buy++) {
            for (int sell = buy + 1; sell < prices.length; sell++) {
                best = Math.max(best, prices[sell] - prices[buy]);
            }
        }
        return best;
    }
}
