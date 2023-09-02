package com.algo.ae.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Non-Constructible Change
 *
 * Given an array of positive integers representing the values of coins in
 * your possession, write a function that returns the minimum amount of change
 * (the minimum sum of money) that you cannot create. The given coins can have
 * any positive integer value and aren't necessarily unique(i.e. you can have
 * multiple coins of the same value).
 *
 * For example, if you're given  coins = [1, 2, 5], the minimum amount of change
 * that you can't create is 4. If you're given no coins, the minimum amount of
 * change that you can't create is 1.
 *
 * Sample Input:
 *
 * coins = [5, 7, 1, 1, 2, 3, 22]
 *
 * Sample Output: 20
 */
public class NonConstructibleChange {

    // This was my initial implementation and inefficient
    public int nonConstructibleChange(int[] coins) {
        if (coins.length == 0) {
            return 1;
        }
        Arrays.sort(coins);
        ArrayList<Integer> allCoinChanges = new ArrayList<Integer>();
        for (int i = 0; i < coins.length; ++i) {
            int nextCoin = coins[i];
            if (allCoinChanges.size() > 0) {
                int currDenomSize = allCoinChanges.size();
                int lastDenom = allCoinChanges.get(currDenomSize - 1);
                for (int j = 0; j < currDenomSize; ++j) {
                    if (nextCoin > lastDenom) {
                        allCoinChanges.add(nextCoin);
                    }
                    if (nextCoin + allCoinChanges.get(j) > lastDenom) {
                        allCoinChanges.add(nextCoin + allCoinChanges.get(j));
                        lastDenom = nextCoin + allCoinChanges.get(j);
                    }
                }
            } else {
                allCoinChanges.add(nextCoin);
            }
        }
        return checkForMinChange(allCoinChanges);
    }

    public int checkForMinChange(ArrayList<Integer> denoms) {
        if (denoms.size() == 0) {
            return -1;
        }
        if (denoms.get(0) > 1) {
            return 1;
        }
        int min = denoms.get(0);
        for (int i = 0; i < denoms.size(); ++i) {
            if (min+1 < denoms.get(i)) {
                return min+1;
            }
            min = denoms.get(i);
        }
        return min+1;
    }

    // Time: O(NlogN), Space: O(1)
    // I referred this from the solutions.
    // It took a lot of time to understand.
    public int nonConstructibleChangeE(int[] coins) {
        Arrays.sort(coins);
        int minChange = 0;
        for (int i = 0; i < coins.length; ++i) {
            if (minChange + 1 < coins[i]) {
                return minChange + 1;
            }
            minChange += coins[i];
        }
        return minChange + 1;
    }
}
