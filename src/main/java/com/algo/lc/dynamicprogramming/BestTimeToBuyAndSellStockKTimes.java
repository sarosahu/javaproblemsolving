package com.algo.lc.dynamicprogramming;

/**
 * 188. Best Time to Buy and Sell Stock IV
 *
 * You are given an integer array prices where prices[i] is the price of a given stock
 * on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions: i.e.
 * you may buy at most k times and sell at most k times.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell
 * the stock before you buy again).
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 *
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 *
 * Constraints:
 *
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */

// This is exactly same as the problem
// ../javaproblemsolving/src/main/java/com/algo/ae/dp/MaxProfitWithKTransactions.java
public class BestTimeToBuyAndSellStockKTimes {
    // Time : O(nk), space : O(nk)
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

    // Time : O(nk), space : O(n)
    public static int maxProfitWithKTransactionsE(int[] prices, int k) {
        if (prices.length == 0) {
            return 0;
        }
        int[] evenProfits = new int[prices.length];
        int[] oddProfits = new int[prices.length];

        int[] currProfits;
        int[] prevProfits;

        for (int t = 1; t <= k; ++t) {
            int maxSoFar = Integer.MIN_VALUE;
            if (t % 2 == 1) {
                currProfits = oddProfits;
                prevProfits = evenProfits;
            } else {
                currProfits = evenProfits;
                prevProfits = oddProfits;
            }

            for (int d = 1; d < prices.length; ++d) {
                maxSoFar = Math.max(maxSoFar, prevProfits[d - 1] - prices[d - 1]);
                currProfits[d] = Math.max(currProfits[d - 1], maxSoFar + prices[d]);
            }
        }
        return k % 2 == 0 ?
                evenProfits[prices.length - 1] : oddProfits[prices.length - 1];
    }
    public static void main(String[] args) {
        int[] prices = {5, 11, 3, 50, 60, 90};
        int maxProfit = maxProfitWithKTransactions(prices, 2);
        System.out.println("Max profit : " + maxProfit);
    }
}
