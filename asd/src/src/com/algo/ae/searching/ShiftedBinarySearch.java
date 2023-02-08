package com.algo.ae.searching;

/**
 * Shifted Binary Search
 *
 * Write a function that takes in a sorted array of distinct integers as well as
 * a target integer. The caveat is that  the integers in the array have been
 * shifted by some amount; in other words, they've been moved to the left or to
 * the right by one or more positions. For example, [1, 2, 3, 4] might have turned
 * into [3, 4, 1, 2].
 *
 * The function should use a variation of the Binary Search Algorithm to determine
 * if the target integer is contained in the array and should return its index
 * if it is, otherwise -1.
 *
 * Sample Output:
 * {
 *   array: [45, 61, 71, 72, 73, 0, 1, 21, 33, 37],
 *   target: 33
 * }
 *
 * Sample Output: 8
 */
public class ShiftedBinarySearch {
    public static int shiftedBinarySearch(int[] array, int target) {
        // Write your code here.
        return binSearch(array, target, 0, array.length - 1);
    }

    public static int binSearch(int[] array,
                                int target,
                                int left,
                                int right)
    {
        while (left <= right) {
            int mid = left + (right - left)/2;
            int midValue = array[mid];
            if (target == midValue) {
                return mid;
            } else {
                if (target < midValue) {
                    if (target >= array[left]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (target <= array[right] || midValue > array[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }
        return -1;
    }
}
