package com.algo.lc.dynamicprogramming;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 *
 * E.g. 1
 * Input :
 * 1, 0, 1, 0, 0
 * 1, 0, 1, 1, 1
 * 1, 1, 1, 1, 1
 * 1, 0, 0, 1, 0
 *
 * output : 4
 *
 * Ex. 2:
 * Input:
 * 0, 1
 * 1, 0
 *
 * output: 1
 */
public class MaximalSquare {

    /**
     * Time: O(M*N), space: O(M*N)
     */
    public static int maximalSquare1(char[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        result[i][j] = 1;
                    } else {
                        if (result[i - 1][j - 1] > 0 &&
                                matrix[i - 1][j] == '1' && matrix[i][j - 1] == '1') {
                            result[i][j] = 1 +
                                    Math.min(result[i-1][j-1],
                                            Math.min(result[i-1][j], result[i][j-1]));
                        } else {
                            result[i][j] = 1;
                        }
                    }
                    max = Math.max(result[i][j] * result[i][j], max);
                }
            }
        }
        return max;
    }

    /**
     * Time: O(M*N), space: O(N)
     */
    public static int maximalSquare2(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int [] dp = new int[cols + 1];
        int maxsq = 0, prev = 0;
        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= cols; ++j) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsq = Math.max(maxsq, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsq * maxsq;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'}
        };

        int maxsq = maximalSquare1(matrix);
        System.out.println("Maximum square length : " + maxsq);
        maxsq = maximalSquare2(matrix);
        System.out.println("Maximum square length : " + maxsq);
    }
}
