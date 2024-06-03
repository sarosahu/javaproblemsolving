package com.algo.lc.topintq.array;
/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 *
 *
 * Follow up:
 *
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */

import com.algo.util.Utils;

public class RotateArray {
    public static void rotate(int[] nums, int k) {

        if (nums.length == 1) {
            return;
        }
        while (k > 0) {
            int lastItem = nums[nums.length - 1];
            for (int i = nums.length - 1; i > 0; --i) {
                int prevItem = nums[i - 1];
                nums[i] = prevItem;
            }
            nums[0] = lastItem;
            --k;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void reverse(int[] nums, int startIdx, int endIdx) {
        while (startIdx < endIdx) {
            swap(nums, startIdx, endIdx);
            ++startIdx;
            --endIdx;
        }
    }
    public static void rotateF(int[] nums, int k) {
        if (nums.length == 1) {
            return;
        }
        if (nums.length < k) {
            k %= nums.length;
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        Utils.print1DArray(nums);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rotateF(array, 3);
    }
}
