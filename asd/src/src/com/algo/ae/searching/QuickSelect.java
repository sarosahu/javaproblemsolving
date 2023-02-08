package com.algo.ae.searching;

/**
 * Quick Select
 *
 * Write a function that takes in an array of distinct integers as well as an integer k
 * and that returns the kth smallest integer in that array.
 *
 * The function should do this in linear time, on average.
 *
 * Sample Input:
 * array = [8, 5, 2, 9, 7, 6, 3]
 * k = 3
 *
 * Sample Output: 5
 */
public class QuickSelect {
    public static int quickselect(int[] array, int k) {
        int pivotIndex = -1;
        int left = 0, right = array.length - 1;
        while (left <= right) {
            // Always choose left index as pivotIndex prior to this call
            pivotIndex = shiftPivotToCorrectPosition(array, left, right, left);
            if (pivotIndex == k - 1) {
                break;
            } else if (pivotIndex > k - 1) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
        return array[pivotIndex];
    }

    public static int shiftPivotToCorrectPosition(int[] array,
                                                  int left,
                                                  int right,
                                                  int pivotIndex) {
        int pivot = array[pivotIndex];
        int firstRight = right;
        swap(array, pivotIndex, firstRight);
        right -= 1;
        while (left <= right) {
            if (array[left] >= pivot) {
                swap(array, left, right);
                --right;
            } else {
                ++left;
            }
        }
        swap(array, left, firstRight);
        return left;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
