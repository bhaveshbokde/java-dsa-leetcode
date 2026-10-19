package com.bhavesh.dsa.arrays;

/**
 * LeetCode 121 - Best Time to Buy and Sell Stock (Easy).
 *
 * <p>Buy on one day and sell on a strictly later day; return the maximum profit,
 * or 0 if no profitable pair exists.
 *
 * <p>The pairing constraint is what makes this linear. A profit is
 * {@code prices[sell] - prices[buy]} with {@code buy < sell}, so when the scan
 * reaches index {@code i} every valid buy day is already behind it. Only the
 * cheapest of those matters - a more expensive earlier day can never produce a
 * larger profit against the same sell day - so one running minimum replaces the
 * inner loop over candidate buy days.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class BestTimeToBuyAndSellStock {

    private BestTimeToBuyAndSellStock() {
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int cheapestSoFar = prices[0];
        int best = 0;
        for (int i = 1; i < prices.length; i++) {
            // Sell at i against the cheapest day strictly before i.
            best = Math.max(best, prices[i] - cheapestSoFar);
            cheapestSoFar = Math.min(cheapestSoFar, prices[i]);
        }
        return best;
    }
}
