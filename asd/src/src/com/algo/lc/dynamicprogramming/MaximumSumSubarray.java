package com.algo.lc.dynamicprogramming;

/**
 *
 */
public class MaximumSumSubarray {

    // Brute force approach: Time: O(N^2), Space : O(1)
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            int currSum = 0;
            for (int j = i; j < nums.length; ++j) {
                currSum += nums[j];
                if (currSum > maxSum) {
                    maxSum = currSum;
                }
            }
        }
        return maxSum;
    }


}
