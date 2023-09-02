package com.algo.lc.dynamicprogramming;

public class MaximumProductSubarray {
    // Time: O(N^2), space: O(1)
    public int maxProductBF(int[] nums) {
        if (nums.length == 0) return 0;

        int result = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int accu = 1;
            for (int j = i; j < nums.length; j++) {
                accu *= nums[j];
                result = Math.max(result, accu);
            }
        }

        return result;
    }
    // Time : O(N), Space: O(1)
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = maxSoFar;

        for (int i = 1; i < nums.length; ++i) {
            int curr = nums[i];
            int currMax = Math.max(curr, Math.max(maxSoFar * curr, minSoFar * curr));
            minSoFar = Math.min(curr, Math.min(maxSoFar * curr, minSoFar * curr));
            maxSoFar = currMax;
            result = Math.max(maxSoFar, result);
        }
        return result;
    }
}
