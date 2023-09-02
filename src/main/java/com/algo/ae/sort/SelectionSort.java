package com.algo.ae.sort;
/**
 * Write a funciton that takes in an array of integers and returns a sorted
 * version of that array. Use the Selection sort algorithm to sort the array.
 *
 * Note: Sort should be in ascending order.
 */
public class SelectionSort {
    public static int[] selectionSort(int[] array) {
        int smallestIndex = -1;
        for (int i = 0; i < array.length; ++i) {
            smallestIndex = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] < array[smallestIndex]) {
                    smallestIndex = j;
                }
            }
            swap(array, i, smallestIndex);
        }
        return array;
    }

    private static void swap(int [] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
