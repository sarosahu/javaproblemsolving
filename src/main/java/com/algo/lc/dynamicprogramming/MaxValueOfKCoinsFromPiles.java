package com.algo.lc.dynamicprogramming;

import java.util.List;

public class MaxValueOfKCoinsFromPiles {
    public static int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int nPiles = piles.size();
        int [][] dp = new int[nPiles + 1][k + 1];
        for (int pileIdx = 1; pileIdx <= nPiles; ++pileIdx) {
            // j = coins
            for (int coinIdx = 0; coinIdx <= k; ++coinIdx) {
                int currSum = 0;
                for (int currCoinIdx = 0;
                     currCoinIdx <= Math.min(piles.get(pileIdx - 1).size(), coinIdx);
                     currCoinIdx++) {

                    if (currCoinIdx > 0) {
                        currSum += piles.get(pileIdx - 1).get(currCoinIdx - 1);
                    }
                    dp[pileIdx][coinIdx] =
                            Math.max(dp[pileIdx][coinIdx], dp[pileIdx - 1][coinIdx - currCoinIdx] + currSum);
                }
            }
        }
        return dp[nPiles][k];
    }

    public static void main(String[] args) {
        List<List<Integer>> piles1 = List.of(
                List.of(2, 7, 10, 5),
                List.of(3, 20, 8, 2),
                List.of(1, 11, 5, 15),
                List.of(1, 15, 9, 10)
        );
        List<List<Integer>> piles = List.of(
                List.of(2, 7, 10, 5),
                List.of(5, 13, 8, 2),
                List.of(15, 5, 1, 15),
                List.of(1, 15, 9, 10)
        );
        System.out.println("Maximum value of k coins : " + maxValueOfCoins(piles, 4));
    }
}
