package com.algo.lc.arraysandstrings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return
 * indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 ************************************************************************/
public class TwoSumEasy {
    // Time: O(N^2)
    public static int[]
    twoSumBF(int[] nums, int target) {
        int[] numPair = new int[2];
        for (int i = 0; i < nums.length; ++i) {
            int first = nums[i];
            for (int j = i + 1; j < nums.length; ++j) {
                int second = nums[j];
                if (first + second == target) {
                    numPair[0] = i;
                    numPair[1] = j;
                    return numPair;
                }
            }
        }
        return numPair;
    }

    // Approach: One-pass Hash Table
    // Time: O(N), Space: O(N)
    public static int[]
    twoSumE(int[] nums, int target) {
        int[] numPair = new int[2];
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int nextNum = target - num;
            if (hm.containsKey(nextNum)) {
                numPair[0] = hm.get(nextNum);
                numPair[1] = i;
                break;
            } else {
                hm.put(num, i);
            }
        }
        return numPair;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] sumPair = twoSumBF(nums, 9);
        System.out.println("Sum pair : ");
        System.out.printf("%d, %d\n", sumPair[0], sumPair[1]);
        int[] sumPair2 = twoSumE(nums, 9);
        System.out.printf("%d, %d\n", sumPair2[0], sumPair2[1]);
    }
}
