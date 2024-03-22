package com.algo.lc.sortandsearch.bsearch;

/**
 * 410. Split Array Largest Sum
 *
 * Given an integer array nums and an integer k, split nums into k non-empty
 * subarrays such that the largest sum of any subarray is minimized.
 *
 * Return the minimized largest sum of the split.
 *
 * A subarray is a contiguous part of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= min(50, nums.length)
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        // Find the sum of all elements and the maximum element
        int sum = 0;
        int maxElement = Integer.MIN_VALUE;
        for (int e : nums) {
            sum += e;
            maxElement = Math.max(maxElement, e);
        }

        // Define the left and right boundary of binary search
        int left = maxElement;
        int right = sum;
        int minLargestSplitSum = 0;
        while (left < right) {
            // Find the mid value
            int maxSumAllowed = left + (right - left) / 2;

            // Find the minimum splits. If "splitsRequired <= m" move towards
            // left i.e., smaller values
            if (minSubarraysRequired(nums, maxSumAllowed) <= m) {
                right = maxSumAllowed;
            } else {
                // Move towards right if splitsRequired > m
                left = maxSumAllowed + 1;
            }
        }
        return left;
    }

    private int minSubarraysRequired(int[] nums, int maxSumAllowed) {
        int currSum = 0;
        int splitsRequired = 0;

        for (int e : nums) {
            // Add element only if the sum doesn't exceed maxSumAllowed
            if (currSum + e <= maxSumAllowed) {
                currSum += e;
            } else {
                // If the element addition makes sum more than maxSumAllowed
                // INcrement the splits required and reset sum
                currSum = e;
                splitsRequired++;
            }
        }

        // Return the number of subarrays, which is number of splits + 1
        return splitsRequired + 1;
    }

    public static void main(String[] args) {
        int [] arr = {7,2,5,10,8};
        SplitArrayLargestSum obj = new SplitArrayLargestSum();
        int minimizedLargestSum = obj.splitArray(arr, 2);
        System.out.println("Minimized largest sum : " + minimizedLargestSum);
    }
}
