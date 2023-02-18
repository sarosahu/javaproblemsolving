package com.algo.ae.arrays;

/**
 * Monotonic Array
 *
 * Write a function that takes in an array of integers and returns a boolean representing
 * whether the array is monotonic.
 *
 * An array is said to be monotonic if its elements, from left to right, are entirely
 * non-increasing or entirely non-decreasing.
 *
 * Non-increasing elements aren't necessarily exclusively decreasing; they simply
 * don't increase. Similarly, non-decreasing  elements aren't necessarily exclusively
 * increasing; they simply don't decrease.
 *
 * Note that empty arrays and arrays of one element are monotonic.
 *
 * Sample Input:
 * array = [-1, -5, -10, -1100, -1101, -1102, -9001]
 *
 * Sample Output: true
 */
public class MonotonicArray {
    // Time: O(N), space : O(1)
    public static boolean isMonotonic(int[] array) {
        boolean increasing = true, decreasing = true;
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i] < array[i+1]) {
                decreasing = false;
            } else if (array[i] > array[i+1]) {
                increasing = false;
            }
        }
        return increasing || decreasing;
    }
}
