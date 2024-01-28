package com.algo.lc.dynamicprogramming;

import java.util.Arrays;

/**
 * Coin change II
 *
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 *
 * Return the number of combinations that make up that amount. If that amount
 * of money cannot be made up by any combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 */
public class CoinChange {
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        int[][] memo = new int[coins.length][amount + 1];
        for (int [] row : memo) {
            Arrays.fill(row, -1);
        }
        return changeHelper(0, amount, coins, memo);
    }

    private int changeHelper(int i, int amount, int[] coins, int[][] memo) {
        if (amount == 0) {
            return 1;
        }
        if (i == coins.length) {
            return 0;
        }
        if (memo[i][amount] != -1) {
            return memo[i][amount];
        }
        if (amount >= coins[i]) {
            memo[i][amount] = changeHelper(i, amount - coins[i], coins, memo) +
                                changeHelper(i + 1, amount, coins, memo);
        } else {
            memo[i][amount] = changeHelper(i + 1, amount, coins, memo);
        }
        return memo[i][amount];

    }

    // Bottom-up approach (iterative)
    public int changeBU(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; ++i) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= amount; ++i) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= n; ++i) {
            int coin = coins[i-1];
            for (int j = 1; j <= amount; ++j) {
                if (coin > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
                }
            }
        }
        return dp[n][amount];
    }

    // Bottom-up -- space optimized.
    // Time : O(n * amount), space : O(amount)
    public int changeBUO(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = n - 1; i >= 0; --i) {
            int coin = coins[i];
            for (int j = coin; j <= amount; ++j) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int num = coinChange.change(5, new int[]{1,2,5});
        System.out.println("No of ways : " + num);

        num = coinChange.changeBU(5, new int[]{1,2,5});
        System.out.println("No of ways : " + num);

        num = coinChange.changeBUO(5, new int[]{1,2,5});
        System.out.println("No of ways : " + num);

        num = coinChange.change(10, new int[]{1,3,10});
        System.out.println("No of ways : " + num);

        num = coinChange.changeBU(10, new int[]{1,3,10});
        System.out.println("No of ways : " + num);

        num = coinChange.changeBUO(10, new int[]{1,3,10});
        System.out.println("No of ways : " + num);
    }
}
