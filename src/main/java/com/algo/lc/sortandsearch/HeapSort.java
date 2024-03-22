package com.algo.lc.sortandsearch;

import java.util.Arrays;

public class HeapSort {
    public void heapSort(int[] arr) {
        
        int n = arr.length;
        for (int i = n/2 - 1; i >= 0; --i) {
            maxHeapify(arr, n, i);
        }

        for (int i = arr.length - 1; i > 0; --i) {
            // swap last element with first element
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            maxHeapify(arr, i, 0);
        }
    }

    private void maxHeapify(int[] arr, int heapSize, int parentIdx) {
        int left = 2 * parentIdx + 1;
        int right = 2 * parentIdx + 2;
        int largest = parentIdx;

        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        // swap with parentIdx and largest
        if (parentIdx != largest) {
            int tmp = arr[parentIdx];
            arr[parentIdx] = arr[largest];
            arr[largest] = tmp;
            maxHeapify(arr, heapSize, largest);
        }
    }
    /*private void maxHeapify(int[] arr, int heapSize, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != index) {
            int temp = arr[index];
            arr[index] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, heapSize, largest);
        }
    }*/

    public static void main(String[] args) {
        int[] arr = {7, 3, 2, 5, 6, 10, 9, 8, 1};
        System.out.println(Arrays.toString(arr));
        HeapSort obj = new HeapSort();
        obj.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
