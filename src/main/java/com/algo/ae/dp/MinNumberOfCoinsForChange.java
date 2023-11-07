package com.algo.ae.dp;

import java.util.Arrays;

/**
 * Min Number Of Coins For Change
 *
 * Given an array of +ve integers representing coin denominations and a single
 * non-negative integer n representing a target amount of money, write a function
 * that returns the smallest number of coins needed to make change for(to sum up to)
 * that target amount using the given coin denominations.
 *
 * Note that you have access to an unlimited amount of coins. In other words, if the
 * denominations are [1, 5, 10], you have access to an unlimited amount of 1s, 5s
 * and 10s.
 * If it's impossible to make change for the target amount, return -1.
 *
 * Sample input:
 * n = 7
 * denoms = [1, 5, 10]
 *
 * Sample output: 3 // 2x1 + 1x5
 */
public class MinNumberOfCoinsForChange {
    // {
    // Note: We can ignore the logic in minNumberOfCoinsForChange() and minNumberOfCoinsForChangeR()
    //       The logic is correct, but we can simplify more which I am doing after these logic
    //       implementations. We can refer coinChangeR() and coinChange().
    // Time: O(nd), space : O(n)
    public static int minNumberOfCoinsForChange(int n, int[] denoms) {
        int [] cache = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            cache[i] = Integer.MAX_VALUE;
        }
        return minNumberOfCoins(n, denoms, cache);
    }

    private static int minNumberOfCoins(int n, int[] denoms, int [] cache) {
        for (int denom : denoms) {
            for (int amount = 0; amount < cache.length; ++amount) {
                if (amount - denom >= 0) {
                    int currMinAtDenom = cache[amount - denom];
                    int currTotalDenoms = currMinAtDenom == Integer.MAX_VALUE ?
                            currMinAtDenom : currMinAtDenom + 1;
                    cache[amount] = Math.min(cache[amount], currTotalDenoms);
                }
            }
        }
        return cache[n] != Integer.MAX_VALUE ? cache[n] : -1;
    }

    public static int minNumberOfCoinsForChangeR(int amount, int[] denoms) {
        int [] cache = new int[amount + 1];
        for (int i = 1; i < amount + 1; ++i) {
            cache[i] = Integer.MAX_VALUE;
        }
        int minNum = minNumberOfCoinsR(amount, denoms, cache);
        if (minNum == Integer.MAX_VALUE) {
            return -1;
        }
        return minNum;
    }

    private static int minNumberOfCoinsR(int remainingAmount, int[] denoms, int [] cache) {
        if (remainingAmount < 0) {
            return -1;
        }
        if (remainingAmount == 0) {
            return 0;
        }
        if (cache[remainingAmount] >= 0 && cache[remainingAmount] != Integer.MAX_VALUE) {
            return cache[remainingAmount];
        }

        for (int denom : denoms) {
            if (remainingAmount - denom >= 0) {
                int minAtCurrDenom = minNumberOfCoinsR(remainingAmount - denom, denoms, cache);
                if (minAtCurrDenom != Integer.MAX_VALUE && minAtCurrDenom + 1 < cache[remainingAmount]) {
                    cache[remainingAmount] = minAtCurrDenom + 1;
                }
            }
        }
        return cache[remainingAmount];
    }
    // }

    /**
     * Top-down approach (Recursion and memoization way):
     * Note: This is there in leetcode and was better than the above one.
     *
     * Complexity Analysis
     * Time complexity : O(S∗n). where S is the amount, n is denomination count.
     * In the worst case the recursive tree of the algorithm has height of S and
     * the algorithm solves only S subproblems because it caches precalculated
     * solutions in a table. Each subproblem is computed with n iterations, one
     * by coin denomination. Therefore there is O(S∗n) time complexity.
     *
     * Space complexity : O(S), where S is the amount to change
     * We use extra space for the memoization table.
     */
    public static int coinChangeR(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChangeHelper(coins, amount, new int[amount]);
    }

    private static int coinChangeHelper(int[] coins, int remainingAmount, int[] cache) {
        if (remainingAmount < 0) {
            return -1;
        }
        if (remainingAmount == 0) {
            return 0;
        }
        if (cache[remainingAmount - 1] != 0) {
            return cache[remainingAmount - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeHelper(coins, remainingAmount - coin, cache);
            if (res >= 0) {
                min = Math.min(min, 1 + res);
            }
        }
        cache[remainingAmount - 1] = min == Integer.MAX_VALUE ? -1 : min;

        return cache[remainingAmount - 1];
    }

    /**
     * Bottom-up approach (Iteration)
     * Complexity Analysis
     * Time complexity : O(S∗n).
     * On each step the algorithm finds the next F(i) in n iterations, where 1≤i≤S.
     * Therefore in total the iterations are S∗n.
     * Space complexity : O(S).
     * We use extra space for the memoization table.
     */
    public static int coinChange(int[] coins, int amount) {
        // The max coin denominations can be amount + 1
        int max = amount + 1;
        // dp[i] represents, the maximum coin denominations if i amount remains.
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; ++i) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * My approach for bottom-up (Iteration)
     *
     */
    public static int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int [][] dp = new int[coins.length + 1][amount + 1];

        //Arrays.sort(coins);

        for (int i = 1; i < dp.length; ++i) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < dp[0].length; ++i) {
            dp[0][i] = max;
        }

        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[i].length; ++j) {
                int coinVal = coins[i - 1];
                if (j < coinVal) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], 1 + dp[i][j - coinVal]);
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1] == max ? -1 : dp[dp.length - 1][dp[0].length - 1];
    }

    /**
     * My implementation (Bottom - up) (optimized)
     */
    public int coinChange3(int[] coins, int amount) {
        int max = amount + 1;
        int [] dp = new int[amount + 1];

        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= coins.length; ++i) {
            for (int j = 1; j <= amount; ++j) {
                int coinVal = coins[i - 1];
                if (j >= coinVal) {
                    dp[j] = Math.min(dp[j], 1 + dp[j - coinVal]);
                }
            }
        }

        return dp[amount] >= max ? -1 : dp[amount];
    }
    public static void main(String[] args) {
        int[] denoms = {1, 5, 10};
        int [] denoms2 = {186,419,83,408};
        int[][] denomsList = {
                {1, 5, 10},
                {186, 419, 83, 408}
        };
        int[] amounts = {7, 6249};
        for (int i = 0; i < denomsList.length; ++i) {
            int minNumCoins = minNumberOfCoinsForChange(amounts[i], denomsList[i]);
            System.out.println("Min number coins for change : " + minNumCoins);

            minNumCoins = minNumberOfCoinsForChangeR(amounts[i], denomsList[i]);
            System.out.println("Min number of coins for change : " + minNumCoins);

            minNumCoins = coinChangeR(denomsList[i], amounts[i]);
            System.out.println("Min number of coins for change : " + minNumCoins);

            minNumCoins = coinChange(denomsList[i], amounts[i]);
            System.out.println("Min number of coins for change : " + minNumCoins);

            minNumCoins = coinChange2(denomsList[i], amounts[i]);
            System.out.println("Min number of coins for change : " + minNumCoins);
        }
    }
}
