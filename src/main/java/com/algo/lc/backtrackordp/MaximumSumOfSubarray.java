package com.algo.lc.backtrackordp;

public class MaximumSumOfSubarray {
    public static int
    maxSumSubarray(int [] nums) {
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

    public static int
    maxSumSubarrayE(int[] nums) {
        int maxSum = nums[0];
        int maxSumAtPrevIndex = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int currNum = nums[i];
            int currSum = currNum;
            if (maxSumAtPrevIndex >= 0) {
                currSum += maxSumAtPrevIndex;
            }
            maxSum = Math.max(maxSum, currSum);
            maxSumAtPrevIndex = currSum;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] a = {2, -5, 3, 9, -2, 5, -6, 7, -2, 3};
        int maxSum = maxSumSubarrayE(a);
        System.out.println("Maximum sum sub-array : " + maxSum);

        int [] a1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSum = maxSumSubarray(a1);
        System.out.println("Max sum : " + maxSum);
    }
}
