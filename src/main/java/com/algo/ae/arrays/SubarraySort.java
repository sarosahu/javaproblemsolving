package com.algo.ae.arrays;

import java.util.Arrays;

/**
 * Write a function that takes in an array of at least two integers and that returns
 * an array of the starting and ending indices of the smallest subarray in the input
 * array that needs to be sorted in place in order for the entire input array to be
 * sorted (in ascending order).
 *
 * If the input array is already sorted, the function should return [-1, -1].
 *
 * Sample Input:
 * array = [1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]
 *
 * Sample Output: [3, 9]
 */
public class SubarraySort {
    public static int[] subarraySort(int[] array) {
        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);
        int left = 0, right = array.length - 1;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != sortedArray[i]) {
                if (left == -1) {
                    left = i;
                }
                right = i;
            }
        }
        return new int[]{left, right};
    }

    // Time: O(N), Space: O(1)
    public static int[] subarraySortE(int[] array) {
        // Write your code here.
        int minOutOfOrder = Integer.MAX_VALUE;
        int maxOutOfOrder = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; ++i) {
            int num = array[i];
            if (isOutOfOrder(i, num, array)) {
                minOutOfOrder = Math.min(minOutOfOrder, num);
                maxOutOfOrder = Math.max(maxOutOfOrder, num);
            }
        }

        if (minOutOfOrder == Integer.MAX_VALUE) {
            return new int[] {-1, -1};
        }

        int left = 0, right = array.length - 1;
        int firstIndex = -1, lastIndex = -1;
        while (left <= right && (firstIndex == -1 || lastIndex == -1)) {
            if (minOutOfOrder < array[left]) {
                firstIndex = left;
            } else {
                ++left;
            }

            if (maxOutOfOrder > array[right]) {
                lastIndex = right;
            } else {
                --right;
            }
        }
        return new int[] {firstIndex, lastIndex};
    }

    private static boolean isOutOfOrder(int i, int num, int[] array) {
        if (i == 0) {
            return num > array[i + 1];
        }
        if (i == array.length - 1) {
            return num < array[i - 1];
        }

        return num > array[i + 1] || num < array[i - 1];
    }
}
