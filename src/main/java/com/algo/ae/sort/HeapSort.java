package com.algo.ae.sort;

import java.util.Arrays;

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

    public static int[] heapSort2(int[] array) {
        buildMaxHeap(array);
        for (int endIdx = array.length - 1; endIdx > 0; endIdx--) {
            swap(0, endIdx, array);
            heapifyDown(array, 0, endIdx - 1);
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

    public static void heapifyDown(int [] arr, int startIdx, int endIdx) {
        int largest = startIdx;
        int leftChildIdx = 2 * startIdx + 1;
        int rightChildIdx = 2 * startIdx + 2;

        // If left child is larger than root.
        if (leftChildIdx <= endIdx && arr[leftChildIdx] > arr[largest]) {
            largest = leftChildIdx;
        }

        // If right child is larger than root.
        if (rightChildIdx <= endIdx && arr[rightChildIdx] > arr[largest]) {
            largest = rightChildIdx;
        }
        // If largest is not root (startIdx), then swap
        if (largest != startIdx) {
            swap(largest, startIdx, arr);
            heapifyDown(arr, largest, endIdx);
        }
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 2, 5, 6, 10, 9, 8, 1};
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("------------------");
        int[] arr2 = {7, 3, 2, 5, 6, 10, 9, 8, 1};
        System.out.println(Arrays.toString(arr2));
        heapSort2(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
