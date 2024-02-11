package com.algo.lc.dynamicprogramming;

public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int [][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < dp[0].length; ++i) {
            dp[0][i] = matrix[0][i];
        }
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 0; j < dp[i].length; ++j) {
                int minAbove = dp[i - 1][j];
                if (isValid(i - 1, j - 1, matrix)) {
                    minAbove = Math.min(minAbove, dp[i - 1][j - 1]);
                }
                if (isValid(i - 1, j + 1, matrix)) {
                    minAbove = Math.min(minAbove, dp[i - 1][j + 1]);
                }
                dp[i][j] = minAbove + matrix[i][j];
            }
        }
        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < matrix[0].length; ++j) {
            minSum = Math.min(dp[matrix.length - 1][j], minSum);
        }
        return minSum;
    }

    private boolean isValid(int i, int j, int[][] matrix) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {10, -98, 44},
            {-20, 65, 34},
            {-100, -1, 74}
        };
        MinimumFallingPathSum obj = new MinimumFallingPathSum();
        int minFallingPathSum = obj.minFallingPathSum(matrix);
        System.out.println("Minimum falling path sum : " + minFallingPathSum);
    }
}
