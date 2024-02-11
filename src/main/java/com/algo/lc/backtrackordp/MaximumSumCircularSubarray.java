package com.algo.lc.backtrackordp;

/**
 * Given a circular integer array nums of length n, return the maximum
 * possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning
 * of the array. Formally, the next element of nums[i] is nums[(i + 1) % n]
 * and the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most
 * once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there
 * does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 */
public class MaximumSumCircularSubarray {
    public static int maxSubarraySumCircular(int[] nums) {
        final int n = nums.length;
        final int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        int suffixSum = nums[n - 1];

        for (int i = n - 2; i >= 0; --i) {
            suffixSum += nums[i];
            rightMax[i] = Math.max(rightMax[i + 1], suffixSum);
        }

        int maxSum = nums[0];
        int specialSum = nums[0];
        int curMax = 0;
        for (int i = 0, prefixSum = 0; i < n; ++i) {
            // This is Kadane's algorithm.
            curMax = Math.max(curMax, 0) + nums[i];
            maxSum = Math.max(maxSum, curMax);

            prefixSum += nums[i];
            if (i + 1 < n) {
                specialSum = Math.max(specialSum, prefixSum + rightMax[i + 1]);
            }
        }

        return Math.max(maxSum, specialSum);
    }

    public static int maxSubarraySumCircularO(int[] nums) {
        int curMax = 0;
        int curMin = 0;
        int maxSum = nums[0];
        int minSum = nums[0];
        int totalSum = 0;

        for (int num: nums) {
            // Normal Kadane's
            curMax = Math.max(curMax, 0) + num;
            maxSum = Math.max(maxSum, curMax);

            // Kadane's but with min to find minimum subarray
            curMin = Math.min(curMin, 0) + num;
            minSum = Math.min(minSum, curMin);

            totalSum += num;
        }

        if (totalSum == minSum) {
            return maxSum;
        }

        return Math.max(maxSum, totalSum - minSum);
    }

    public static void main(String[] args) {
        int[] nums = {1, -5, 3, -1, 2, 2, -3, 4, -6, 7};
        int maxSum = maxSubarraySumCircular(nums);
        System.out.println("Max circular subarray sum : " + maxSum);

        maxSum = maxSubarraySumCircularO(nums);
        System.out.println("Max circular subarray sum : " + maxSum);
    }

}
