package com.algo.ae.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Zero Sum Subarray
 *
 * You're given a list of integers nums. Write a function that returns a boolean representing
 * whether there exists a zero-sum subarray of nums.
 *
 * A zero-sum subarray is any subarray where all of the values add up zero. A subarray is any
 * contiguous section of the array. For the puroses of this problem, a subarray can be as small
 * as one element and as long as original array.
 *
 * Sample Input:
 * nums = [-5, -5, 2, 3, -2]
 *
 * Sample Output: True
 */
public class ZeroSumSubarray {
    public static boolean zeroSumSubarray(int[] nums) {
        Set<Integer> sums = new HashSet<>();
        int currSum = 0;
        for (int num : nums) {
            currSum += num;
            if (currSum == 0 || sums.contains(currSum)) {
                return true;
            }
            sums.add(currSum);
        }
        return false;
    }

    public static void main(String[] args) {
        int nums[] = {0};
        boolean hasZeroSum = zeroSumSubarray(nums);
        System.out.println("Has zero sum subarray : " + (hasZeroSum ? "Yes" : "No"));
    }
}
