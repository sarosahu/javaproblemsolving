package com.algo.ae.sort;

import java.util.Arrays;

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
//    // {{{
//    public static int[] quickSort(int[] array) {
//        // Write your code here.
//        quickSort(array, 0, array.length - 1);
//        return array;
//    }
//
//    public static void quickSort(int[] array, int start, int end) {
//        if (start >= end) {
//            return;
//        }
//        int pivotIdx = start;
//        int pivot = array[pivotIdx];
//        int left = start + 1;
//        int right = end;
//        while (left <= right) {
//            if (array[left] > pivot && array[right] < pivot) {
//                swap(array, left, right);
//            }
//            if (array[left] <= pivot) {
//                ++left;
//            }
//            if (array[right] >= pivot) {
//                --right;
//            }
//        }
//        swap(array, right, pivotIdx);
//        quickSort(array, start, right - 1);
//        quickSort(array, right + 1, end);
//    }
//    // }}}

    // {{{
    /**
     *
     * @param array
     * Note: This approach is elegant, I like it.
     */
    public static void quickSort(int[] array) {
        // Write your code here.
        quickSort(array, 0, array.length - 1);
    }
    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIdx = partition(arr, start, end);
        quickSort(arr, start, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int pos = start;
        for (int i = start; i < end; ++i) {
            if (arr[i] < pivot) {
                swap(arr, i, pos);
                ++pos;
            }
        }
        // Now swap the pos with end, so that pivot position is fixed
        swap(arr, pos, end);

        return pos;
    }
    // }}}

    public static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int [] arr1 = {100, 2, 90, 80, 1, -5, 20, 3, 60, 50, 99};
        quickSort(arr1);
        System.out.println(Arrays.toString(arr1));

        int [] arr2 = {100, 90, 80, 70, 60, 10, 10, 90, 80, 30, 20, 10};
        quickSort(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
