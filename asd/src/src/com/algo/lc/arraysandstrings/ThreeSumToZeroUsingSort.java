package com.algo.lc.arraysandstrings;

import java.util.*;

/**
 * 3Sum
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3049/
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 ***********************************************************************************/
public class ThreeSumToZeroUsingSort {
    // Approach 1: Two Pointers, Time: O(n^2)
    public static List<List<Integer>>
    threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum2(nums, i, res);
            }
        }
        return res;
    }

    public static void
    twoSum2(int[] nums, int pivotIndex, List<List<Integer>> res) {
        int pivot = nums[pivotIndex];
        int left = pivotIndex + 1, right = nums.length - 1;
        int targetSum = 0 - pivot;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == targetSum) {
                res.add(Arrays.asList(nums[pivotIndex], nums[left], nums[right]));
                ++left;
                --right;
                while (left < right && nums[left] == nums[left - 1]) {
                    ++left;
                }
            } else if (sum > targetSum) {
                --right;
            } else {
                ++left;
            }
        }
    }

    // Approach 2: HashSet
    public static List<List<Integer>>
    threeSumUsingHashSet(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumUsingHashSet(nums, i, res);
            }
        }
        return res;
    }

    public static void
    twoSumUsingHashSet(int[] nums, int pivotIdx, List<List<Integer>> res) {
        Set<Integer> seen = new HashSet<>();
        int firstNum = nums[pivotIdx];
        for (int i = pivotIdx + 1; i < nums.length; ++i) {
            int secondNum = nums[i];
            int targetThirdNum = 0 - firstNum - secondNum;
            if (seen.contains(targetThirdNum)) {
                res.add(Arrays.asList(firstNum, secondNum, targetThirdNum));
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                    ++i;
                }
            }
            seen.add(nums[i]);
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        System.out.println("Result of threeSum, here are the triplets:");
        for (List<Integer> triplet : result) {
            System.out.printf("%d, %d, %d\n", triplet.get(0), triplet.get(1), triplet.get(2));
        }

        List<List<Integer>> result2 = threeSumUsingHashSet(nums);
        System.out.println("Result of threeSum, here are the triplets:");
        for (List<Integer> triplet : result2) {
            System.out.printf("%d, %d, %d\n", triplet.get(0), triplet.get(1), triplet.get(2));
        }
    }
}
