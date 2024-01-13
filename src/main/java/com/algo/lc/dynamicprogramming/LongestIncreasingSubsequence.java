package com.algo.lc.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 300. Longest Increasing Subsequence
 *
 * Given an integer array nums, return the length of the longest strictly increasing
 * subsequence.
 *
 * Note: A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {
    public static int lengthOfLongestIncreasingSubsequence(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int [] a = new int[nums.length];
        Arrays.fill(a, 1);
        int maxLen = 1;
        for (int i = 1; i < nums.length; ++i) {
            int currNum = nums[i];
            for (int j = i - 1; j >= 0; --j) {
                if (nums[j] < currNum) {
                    a[i] = Math.max(a[j] + 1, a[i]);
                }
            }
            maxLen = Math.max(a[i], maxLen);
        }

        return maxLen;
    }

    public static int lengthOfLongestIncreasingSubsequence2(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        int i = 1;
        while (i < nums.length) {
            int currNum = nums[i];
            if (currNum > sub.get(sub.size() - 1)) {
                sub.add(nums[i]);
            } else {
                // Find the first number in nums[] that is > nums[i]
                int j = 0;
                while (sub.get(j) < currNum) {
                    ++j;
                }
                sub.set(j, currNum);
            }
            ++i;
        }
        return sub.size();
    }

    public static int lengthOfLongestIncreasingSubsequence3(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        int i = 1;
        while (i < nums.length) {
            int currNum = nums[i];
            if (currNum > sub.get(sub.size() - 1)) {
                sub.add(nums[i]);
            } else {
                // Find the index of first number in nums[] that is > nums[i]
                int j = binarySearch(sub, currNum);
                sub.set(j, currNum);
            }
            ++i;
        }
        return sub.size();
    }

    private static int binarySearch(List<Integer> list, int k) {
        int left = 0, right = list.size() - 1;
        int first = -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            int midNum = list.get(mid);
            if (midNum == k) {
                return mid;
            } else if (midNum > k) {
                first = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18, 20};
        int maxLen = lengthOfLongestIncreasingSubsequence(nums);
        System.out.println("Max length of longest increasing subsequence : " + maxLen);

        maxLen = lengthOfLongestIncreasingSubsequence2(nums);
        System.out.println("Max length of longest increasing subsequence : " + maxLen);

        maxLen = lengthOfLongestIncreasingSubsequence3(nums);
        System.out.println("Max length of longest increasing subsequence : " + maxLen);
    }
}
