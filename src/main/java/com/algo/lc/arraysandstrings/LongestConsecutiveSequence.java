package com.algo.lc.arraysandstrings;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int maxCount = 1;
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currNum = num;
                int currCount = 1;
                while (numSet.contains(currNum + 1)) {
                    currNum += 1;
                    currCount += 1;
                }
                maxCount = Math.max(maxCount, currCount);
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence obj = new LongestConsecutiveSequence();
        int [] arr = {0,3,7,2,5,8,4,6,0,1};
        int maxCount = obj.longestConsecutive(arr);
        System.out.println("Longest consecutive sequence : " + maxCount);

        int [] arr2 = {100,4,200,1,3,2};
        maxCount = obj.longestConsecutive(arr2);
        System.out.println("Longest consecutive sequence : " + maxCount);
    }
}
