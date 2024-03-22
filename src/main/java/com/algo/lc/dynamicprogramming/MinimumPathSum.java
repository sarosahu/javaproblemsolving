package com.algo.lc.dynamicprogramming;

/**
 * 64. Minimum Path Sum
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left
 * to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Ex 1:
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *
 * Example 2:
 *
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        // Row wise
        for (int i = 1; i < grid.length; ++i) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // Column wise
        for (int i = 1; i < grid[0].length; ++i) {
            dp[0][i] = dp[0][i - 1] +grid[0][i];
        }
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[i].length; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public int minPathSumO1(int[][] grid) {
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for (int i = 1; i < grid[0].length; ++i) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                dp[j] = j > 0 ? Math.min(dp[j - 1], dp[j]) + grid[i][j]
                        : dp[j] + grid[i][j];
            }
        }
        return dp[grid[0].length - 1];
    }
}
