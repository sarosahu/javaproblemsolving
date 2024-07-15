package com.algo.lc.dynamicprogramming;

/**
 * 70. Climbing Stairs
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 *
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {
    // Brute-force --> time: O(2^N), space: O(N)
    public int climbStairsBF(int n) {
         if (n == 0 || n == 1) {
         return 1;
         }
         return climbStairsBF(n - 1) + climbStairsBF(n - 2);
    }
    // } BF

    // { Memoization technique --> time: O(N), Space : O(N)
    public int climbStairsMemoization(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        return numWays(n, memo);
    }
    private int numWays(int n, int[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = numWays(n - 1, memo) + numWays(n - 2, memo);
        return memo[n];
    }
    // } Memoization technique

    // { Fibonacci number technique --> time : O(N), space : O(1)
    public int climbStairsE(int n) {
        int n0 = 1, n1 = 1;
        for (int i = 2; i <= n; ++i) {
            int numWaysAtI = n0 + n1;
            n0 = n1;
            n1 = numWaysAtI;
        }
        return n1;
    }

    public static void main(String[] args) {
        ClimbingStairs obj = new ClimbingStairs();

        System.out.println("Total number of ways with 40 stairs : " + obj.climbStairsBF(40));
        System.out.println("Total number of ways with 40 stairs : " + obj.climbStairsMemoization(40));
        System.out.println("Total number of ways with 40 stairs : " + obj.climbStairsE(40));
    }
}
