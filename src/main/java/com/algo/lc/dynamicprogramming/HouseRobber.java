package com.algo.lc.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * House robber :
 * You are a professional robber planning to rob houses along a street. Each house has
 * a certain amount of money stashed, the only constraint stopping you from robbing each
 * of them is that adjacent houses have security systems connected and it will automatically
 * contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the
 * maximum amount of money you can rob tonight without alerting the police
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 *
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {
    private int[] nums;
    private Map<Integer, Integer> memo;

    /**
     * Top down recursive approach
     * Time: O(N), Space: O(N)
     */
    public int rob(int[] nums) {
        this.nums = nums;
        memo = new HashMap<>();
        return robTopDown(nums.length - 1);
    }

    private int robTopDown(int idx) {
        if (idx == 0) {
            return nums[0];
        } else if (idx == 1) {
            return Math.max(nums[0], nums[1]);
        }
        if (!memo.containsKey(idx)) {
            int maxProfitDownOne = robTopDown(idx - 1);
            int maxProfitDownTwo = robTopDown(idx - 2) + nums[idx];
            memo.put(idx, Math.max(maxProfitDownOne, maxProfitDownTwo));
        }
        return memo.get(idx);
    }

    /**
     * Bottom up approach : Tabulation
     * Time: O(N), Space: O(N)
     */
    public int robBottomUp(int[] nums) {
        this.nums = nums;
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    /**
     * Bottom up approach (optimized one)
     * Time Complexity
     *
     * Time Complexity: O(N) since we have a loop from N−2⋯0 and we use the
     * precalculated values of our dynamic programming table to calculate the
     * current value in the table which is a constant time operation.
     *
     * Space Complexity: O(1) since we are not using a table to store our values.
     * Simply using two variables will suffice for our calculations.
     */
    public int robBottomUpOptimized(int[] nums) {
        this.nums = nums;
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int maxProfitDownTwo = nums[0];
        int maxProfitDownOne = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; ++i) {
            int temp = maxProfitDownOne;
            maxProfitDownOne = Math.max(maxProfitDownOne, maxProfitDownTwo + nums[i]);
            maxProfitDownTwo = temp;
        }
        return maxProfitDownOne;
    }

    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        HouseRobber houseRobber = new HouseRobber();
        int maxProfit = houseRobber.rob(nums);
        System.out.println("Maximum profit : " + maxProfit);

        maxProfit = houseRobber.robBottomUp(nums);
        System.out.println("Maximum profit : " + maxProfit);

        maxProfit = houseRobber.robBottomUpOptimized(nums);
        System.out.println("Maximum profit : " + maxProfit);
    }
}
