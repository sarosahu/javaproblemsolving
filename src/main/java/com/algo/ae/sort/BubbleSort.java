package com.algo.ae.sort;

/**
 * Write a funciton that takes in an array of integers and returns a sorted
 * version of that array. Use the Bubble sort algorithm to sort the array.
 *
 * Note: Sort should be in ascending order.
 */
public class BubbleSort {
    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array.length - 1 - i; ++j) {
                if (array[j] > array[j+1]) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }

    private static void swap(int [] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static int[] bubbleSortE(int[] array) {
        // If array is already sorted, we don't need any further processing.
        // And in that time complexity will be O(N).
        boolean isSorted = false;
        int counter = 0;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1 - counter; ++i) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    isSorted = false;
                }
            }
            ++counter;
        }
        return array;
    }
}
