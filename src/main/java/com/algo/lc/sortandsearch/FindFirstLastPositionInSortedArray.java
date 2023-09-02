package com.algo.lc.sortandsearch;

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting
 * and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */
public class FindFirstLastPositionInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] pair = {-1, -1};
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                pair[0] = mid;
                pair[1] = mid;
                break;
            } else if (nums[mid] < target) {
                ++left;
            } else {
                --right;
            }
        }

        if (pair[0] != -1 && pair[1] != -1) {
            int firstPos = searchHelper(nums, target, left, pair[0] - 1, true);
            if (firstPos != -1) {
                pair[0] = firstPos;
            }
            int lastPos = searchHelper(nums, target, pair[1] + 1, right, false);
            if (lastPos != -1) {
                pair[1] = lastPos;
            }
        }
        return pair;
    }

    public int searchHelper(int[] nums, int target, int left, int right, boolean isFirst) {
        int idx = -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                idx = mid;
                if (isFirst) {
                    --right;
                } else {
                    ++left;
                }
            } else if (nums[mid] < target) {
                ++left;
            } else {
                --right;
            }
        }
        return idx;
    }
}
