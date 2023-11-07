package com.algo.lc.dynamicprogramming;

import java.util.Arrays;

/**
 * Maximum Score from Performing Multiplication Operations
 *
 * You are given two 0-indexed integer arrays nums and multipliers of size
 * n and m respectively, where n >= m.
 *
 * You begin with a score of 0. You want to perform exactly m operations.
 * On the ith operation (0-indexed) you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Note that multipliers[0] corresponds to the first operation, multipliers[1]
 * to the second operation, and so on.
 * Remove x from nums.
 * Return the maximum score after performing m operations.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 *
 *
 * Example 2:
 *
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 * Constraints:
 *
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 300
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 *
 */
public class MaximumScoreFromMultiplication {
    // {
    // BF approach with recursion
    // Time: O(2^M), space: O(M)
    public static int maximumScoreBF(int[] nums, int[] multipliers) {
        return maximumScoreBFHelper(nums, multipliers, 0, nums.length - 1, 0);
    }

    private static int maximumScoreBFHelper(int[] nums, int[] multipliers, int left, int right, int op) {
        if (op == multipliers.length) {
            return 0;
        }
        int leftResult = nums[left] * multipliers[op] +
                maximumScoreBFHelper(nums, multipliers, left + 1, right, op + 1);
        int rightResult = nums[right] * multipliers[op] +
                maximumScoreBFHelper(nums, multipliers, left, right - 1, op + 1);
        int max = Math.max(leftResult, rightResult);
        return max;
    }
    // }
    // {
    // Top down recursion with memoization
    // Time complexity: O(M^2), space: O(M^2)
    public static int maximumScoreTD(int[] nums, int[] multipliers) {
        Integer[][] memo = new Integer[multipliers.length + 1][multipliers.length + 1];
        return dp(nums, multipliers, memo, 0, 0);
    }

    private static int dp(int[] nums, int[] multipliers, Integer [][] memo, int op, int left) {
        int n = nums.length;
        if (op == multipliers.length) {
            return 0;
        }

        // If already computed, return
        if (memo[op][left] != null) {
            return memo[op][left];
        }
        int right = (n - 1) - (op - left);
        int leftResult = nums[left] * multipliers[op] + dp(nums, multipliers, memo, op + 1,left + 1);
        int rightResult = nums[right] * multipliers[op] + dp(nums, multipliers, memo, op + 1, left);
        memo[op][left] = Math.max(leftResult, rightResult);

        return memo[op][left];
    }
    // }

    // {
    // Bottom-up approach with iteration
    // Time complexity: O(M^2), space: O(M^2)
    public int maximumScoreBU(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int [][] dp = new int[m + 1][m + 1];

        for (int i = 0; i <= m; ++i) {
            Arrays.fill(dp[i], 0);
        }
        for (int op = m - 1; op >= 0; --op) {
            for (int left = op; left >= 0; --left) {
                int right = n - 1 - (op - left);
                dp[op][left] = Math.max(multipliers[op]*nums[left] + dp[op + 1][left + 1],
                        multipliers[op]*nums[right] + dp[op + 1][left]);
            }
        }

        return dp[0][0];
    }
    // }

    public static void main(String[] args) {
        int [] nums = {-5,-3,-3,-2,7,1};
        int [] multipliers = {-10,-5,3,4,6};

        int maxScore = maximumScoreBF(nums, multipliers);
        System.out.println("Max score : " + maxScore);

        maxScore = maximumScoreTD(nums, multipliers);
        System.out.println("Max score from top-down (memo): " + maxScore);
    }
}
