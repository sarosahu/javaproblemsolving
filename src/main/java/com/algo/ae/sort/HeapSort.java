package com.algo.ae.sort;

/**
 * Heap Sort
 *
 * Write a function that takes in an array of integers and returns a sorted version
 * of that array. Use the Heap Sort algorithm to sort the array.
 *
 * Sample Input: array = [8, 5, 2, 9, 5, 6, 3]
 *
 * Sample Output:array = [2, 3, 5, 5, 6, 8, 9]
 */
public class HeapSort {
    public static int[] heapSort(int[] array) {
        buildMaxHeap(array);
        for (int endIdx = array.length - 1; endIdx > 0; endIdx--) {
            swap(0, endIdx, array);
            siftDown(0, endIdx - 1, array);
        }
        return array;
    }

    public static void buildMaxHeap(int[] array) {
        int firstParentIdx = (array.length - 2) / 2;
        for (int currIdx = firstParentIdx; currIdx >= 0; --currIdx) {
            siftDown(currIdx, array.length - 1, array);
        }
    }

    public static void siftDown(int currIdx, int endIdx, int[] heap) {
        int childOneIdx = currIdx * 2 + 1;
        while (childOneIdx <= endIdx) {
            int childTwoIdx = currIdx * 2 + 2 <= endIdx ? currIdx * 2 + 2 : -1;
            int idxToSwap = childOneIdx;
            if (childTwoIdx != -1 && heap[childTwoIdx] > heap[childOneIdx]) {
                idxToSwap = childTwoIdx;
            }

            if (heap[idxToSwap] > heap[currIdx]) {
                swap(currIdx, idxToSwap, heap);
                currIdx = idxToSwap;
                childOneIdx = currIdx * 2 + 1;
            } else {
                return;
            }
        }
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
