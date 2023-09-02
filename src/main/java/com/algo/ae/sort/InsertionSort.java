package com.algo.ae.sort;
/**
 * Write a funciton that takes in an array of integers and returns a sorted
 * version of that array. Use the Insertion sort algorithm to sort the array.
 *
 * Note: Sort should be in ascending order.
 */
public class InsertionSort {
    public static int[] insertionSort(int[] array) {
        if (array.length == 0) {
            return new int[] {};
        }
        for (int i = 1; i < array.length; ++i) {
            int j = i;
            while (j > 0 && array[j] < array[j - 1]) {
                swap(array, j - 1, j);
                j -= 1;
            }
        }
        return array;
    }

    private static void swap(int [] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
