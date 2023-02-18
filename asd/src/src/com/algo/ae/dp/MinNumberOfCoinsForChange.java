package com.algo.ae.dp;

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
    // Time: O(nd), space : O(n)
    public static int minNumberOfCoinsForChange(int n, int[] denoms) {
        int [] cache = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            cache[i] = Integer.MAX_VALUE;
        }
        return minNumberOfCoins(n, denoms, cache);
    }

    private static int minNumberOfCoins(int n, int[] denoms, int [] cache) {
        int toCompare = 0;
        for (int denom : denoms) {
            for (int amount = 0; amount < cache.length; ++amount) {
                if (amount - denom >= 0) {
                    int currMinAtDenom = cache[amount - denom];
                    toCompare = currMinAtDenom == Integer.MAX_VALUE ?
                            currMinAtDenom : currMinAtDenom + 1;
                    cache[amount] = Math.min(cache[amount], toCompare);
                }
            }
        }
        return cache[n] != Integer.MAX_VALUE ? cache[n] : -1;
    }

    public static int minNumberOfCoinsForChangeR(int n, int[] denoms) {
        int [] cache = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            cache[i] = Integer.MAX_VALUE;
        }
        int minNum = minNumberOfCoinR(n, denoms, cache);
        if (minNum == Integer.MAX_VALUE) {
            return -1;
        }
        return minNum;
    }

    private static int minNumberOfCoinR(int amount, int[] denoms, int [] cache) {
        if (cache[amount] >= 0 && cache[amount] != Integer.MAX_VALUE) {
            return cache[amount];
        }

        for (int denom : denoms) {
            if (amount - denom >= 0) {
                int minAtCurrDenom = minNumberOfCoins(amount - denom, denoms, cache);
                if (minAtCurrDenom != Integer.MAX_VALUE && minAtCurrDenom + 1 < cache[amount]) {
                    cache[amount] = minAtCurrDenom + 1;
                }
            }
        }
        return cache[amount];
    }

    public static void main(String[] args) {
        int[] denoms = {1, 5, 10};
        int minNumCoins = minNumberOfCoinsForChange(7, denoms);
        System.out.println("Min number coins for change : " + minNumCoins);

        minNumCoins = minNumberOfCoinsForChangeR(7, denoms);
        System.out.println("Min number of coins for change : " + minNumCoins);
    }
}
