package com.algo.lc.dynamicprogramming;

public class BestTimeToBuyAndSellStockOnce {
    public int maxProfit(int[] prices) {
        /*
         * Time Limit Exceeded
        int maxProfit = 0;
        for (int i = 0; i < prices.length; ++i) {
            int currPrice = prices[i];
            for (int j = i + 1; j < prices.length; ++j) {
                int nextPrice = prices[j];
                int currProfit = nextPrice - currPrice;
                if (currProfit > 0 && currProfit > maxProfit) {
                    maxProfit = currProfit;
                }
            }
        }
        return maxProfit;
        */
        int maxProfit = 0;
        if (prices.length == 0) {
            return maxProfit;
        }
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            int currPrice = prices[i];
            minPrice = Math.min(currPrice, minPrice);
            if (currPrice - minPrice > maxProfit) {
                maxProfit = currPrice - minPrice;
            }
        }
        return maxProfit;
    }
}
