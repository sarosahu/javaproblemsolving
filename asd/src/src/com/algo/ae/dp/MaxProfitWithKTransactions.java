package com.algo.ae.dp;

/**
 * Max profit with K transactions
 *
 * You are given an array of positive integers representing the prices of
 * a single stock on various days (each index in the array represents a
 * different day). You are also given an integer k, which represents the
 * number of transactions you are allowed to make. One transaction consists
 * of buying the stock on a given day and selling it on another later day.
 *
 * Write a function that returns the maximum profit that you can make by
 * buying and selling the stock, given k transactions.
 *
 * Note that you can only hold one share of stock at a time; in other words,
 * you can't buy more than one share of the stock on any given day, and you
 * can't buy a share of stock if you are still holding another share. Also,
 * you don't need to use all k transactions that you are allowed.
 *
 * Sample input:
 *
 * prices = [5, 11, 3, 50, 60, 90]
 * k = 2
 *
 * Sample output: 93 // Buy: 5, Sell: 11; Buy: 3, Sell: 90
 */
public class MaxProfitWithKTransactions {
    public static int maxProfitWithKTransactions(int[] prices, int k) {
        if (prices.length == 0) {
            return 0;
        }
        int [][] profits = new int[k+1][prices.length];
        for (int t = 1; t < k + 1; ++t) {
            int maxSoFar = Integer.MIN_VALUE;
            for (int d = 1; d < prices.length; ++d) {
                maxSoFar = Math.max(maxSoFar, profits[t - 1][d - 1] - prices[d - 1]);
                profits[t][d] = Math.max(profits[t][d - 1], maxSoFar + prices[d]);
            }
        }
        return profits[k][prices.length - 1];
    }

    public static void main(String[] args) {
        int[] prices = {5, 11, 3, 50, 60, 90};
        int maxProfit = maxProfitWithKTransactions(prices, 2);
        System.out.println("Max profit : " + maxProfit);
    }
}
