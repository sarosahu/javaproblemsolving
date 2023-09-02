package com.algo.ae.searching;

/**
 * Index Equals Value
 *
 * Write a function that takes in a sorted array of distinct integers and returns the
 * first index in the array that is equal to the value at that index. In other words,
 * your function should return the minimum index where index == array[index].
 *
 * If there is no such index, your function should return -1.
 *
 * Sample Input:
 * array = [-5, -3, 0, 3, 4, 5, 9]
 *
 * Sample Output: 3
 */
public class IndexEqualsValue {
    public int indexEqualsValue(int[] array) {
        int left = 0, right = array.length - 1;
        int idx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = array[mid];
            if (midVal < mid) {
                left = mid + 1;
            } else if (midVal > mid) {
                right = mid - 1;
            } else {
                idx = mid;
                right = mid - 1;
            }
        }
        return idx;
    }
}
