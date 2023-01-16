package com.algo.ae.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoNumberSum {

    // Time O(N^2), space : O(1)
    public static int[] twoNumberSum(int[] array, int targetSum) {
        for (int i = 0; i < array.length; ++i) {
            int first = array[i];
            for (int j = i+1; j < array.length; ++j) {
                int second = array[j];
                if (first+second == targetSum) {
                    return new int[] {first, second};
                }
            }
        }
        return new int[0];
    }

    // Time O(N), space : O(N)
    public static int[] twoNumberSumF(int[] array, int targetSum) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < array.length; ++i) {
            int currNum = array[i];
            if (numSet.contains(targetSum - currNum)) {
                return new int[]{currNum, targetSum - currNum};
            }
            numSet.add(currNum);
        }
        return new int[0];
    }

    // Time O(NlogN), space : O(1)
    public static int[] twoNumberSum2(int[] array, int targetSum) {
        Arrays.sort(array);
        int leftIndex = 0, rightIndex = array.length - 1;
        while (leftIndex < rightIndex) {
            int sum = array[leftIndex] + array[rightIndex];
            if (sum == targetSum) {
                return new int[] {array[leftIndex], array[rightIndex]};
            } else if (sum < targetSum) {
                ++leftIndex;
            } else {
                --rightIndex;
            }
        }
        return new int[0];
    }
    public static void main(String[] args) {
        int [] array = {3, 5, -4, 8, 11, 1, -1, 6};
        int [] nums = twoNumberSum(array, 10);

        if (nums.length == 0) {
            System.out.println("Empty array -- no pair of numbers having sum = 10 not found");
        } else {
            System.out.println("Number pair : (" + nums[0] + "," + nums[1] + ")");
        }

        nums = twoNumberSumF(array, 10);
        if (nums.length == 0) {
            System.out.println("Empty array -- no pair of numbers having sum = 10 not found");
        } else {
            System.out.println("Number pair : (" + nums[0] + "," + nums[1] + ")");
        }

        nums = twoNumberSum2(array, 10);
        if (nums.length == 0) {
            System.out.println("Empty array -- no pair of numbers having sum = 10 not found");
        } else {
            System.out.println("Number pair : (" + nums[0] + "," + nums[1] + ")");
        }
    }
}
