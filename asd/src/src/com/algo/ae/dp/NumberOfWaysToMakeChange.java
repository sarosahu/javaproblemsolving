package com.algo.ae.dp;

/**
 * Number of Ways To Make Change
 * Given an array of distinct +ve integers representing coin denominations and
 * a single non-negative integer n representing a target amount of money, write
 * a function that returns the number of ways to make change for that target
 * amount using the given coin denominations.
 *
 * Note that an unlimited amount of coins is at your disposal.
 *
 * Sample input:
 * n = 6
 * denoms = [1, 5]
 *
 * Sample output:
 * 2 // 1 x 1 + 1 x 5 and 6 x 1
 */
public class NumberOfWaysToMakeChange {
    public static int numberOfWaysToMakeChange(int n, int[] denoms) {
        int [] ways = new int [n + 1];
        ways[0] = 1;

        for (int denom : denoms) {
            for (int amount = 1; amount <= n; ++amount) {
                if (denom <= amount) {
                    ways[amount] += ways[amount-denom];
                }
            }
        }
        return ways[n];
    }

    public static void main(String[] args) {
        int[] denoms = {1, 5, 10, 25};
        int numWays = numberOfWaysToMakeChange(10, denoms);
        System.out.println("Number of ways : " + numWays);
    }
}
