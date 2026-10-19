package com.bhavesh.dsa.dp;

import java.util.Arrays;

/**
 * LeetCode 322 - Coin Change (Medium). Unbounded knapsack.
 *
 * <p>Returns the fewest coins summing to {@code amount}, or -1 if impossible.
 *
 * <p>The greedy "largest coin first" strategy is wrong for arbitrary
 * denominations, and the counterexample is worth remembering: for coins
 * {@code [1, 3, 4]} and amount 6, greedy takes 4 then 1 then 1 for three coins,
 * while the optimum is 3 + 3 for two. Greedy happens to work for real currency
 * systems, which is why the error is easy to make.
 *
 * <p>The recurrence is
 * {@code best(a) = 1 + min over coins c of best(a - c)}. Every amount is solved
 * once and each solution inspects every coin, so the table is filled in
 * O(amount * coins).
 *
 * <p>Iterating amounts in the outer loop makes the coins reusable - this is the
 * unbounded case. Swapping the loop order so each coin is considered once
 * instead solves the 0/1 knapsack, a different problem.
 *
 * <p>The sentinel is {@code amount + 1} rather than {@code Integer.MAX_VALUE},
 * so that {@code 1 + sentinel} cannot overflow while still exceeding any real
 * answer.
 *
 * <p>Time O(amount * coins), auxiliary space O(amount).
 */
public final class CoinChange {

    private CoinChange() {
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be non-negative");
        }
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int unreachable = amount + 1;
        int[] fewest = new int[amount + 1];
        Arrays.fill(fewest, unreachable);
        fewest[0] = 0;

        for (int value = 1; value <= amount; value++) {
            for (int coin : coins) {
                if (coin > 0 && coin <= value && fewest[value - coin] != unreachable) {
                    fewest[value] = Math.min(fewest[value], 1 + fewest[value - coin]);
                }
            }
        }
        return fewest[amount] == unreachable ? -1 : fewest[amount];
    }
}
