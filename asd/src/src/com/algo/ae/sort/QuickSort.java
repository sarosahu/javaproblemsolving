package com.algo.ae.sort;

/**
 * Quick Sort
 *
 * Write a function that takes in an array of integers and returns a sorted version
 * of that array. Use the Quick Sort algorithm to sort the array.
 * Sample Input: array = [8, 5, 2, 9, 5, 6, 3]
 *
 * Sample Output:array = [2, 3, 5, 5, 6, 8, 9]
 *
 */

public class QuickSort {
    public static int[] quickSort(int[] array) {
        // Write your code here.
        quickSort(array, 0, array.length - 1);
        return array;
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIdx = start;
        int pivot = array[pivotIdx];
        int left = start + 1;
        int right = end;
        while (left <= right) {
            if (array[left] > pivot && array[right] < pivot) {
                swap(array, left, right);
            }
            if (array[left] <= pivot) {
                ++left;
            }
            if (array[right] >= pivot) {
                --right;
            }
        }
        swap(array, right, pivotIdx);
        quickSort(array, start, right - 1);
        quickSort(array, right + 1, end);
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
