package com.algo.ae.sort;

/**
 * Merge Sort
 *
 * Write a function that takes in an array of integers and returns a sorted version
 * of that array. Use the Merge Sort algorithm to sort the array.
 * Sample Input: array = [8, 5, 2, 9, 5, 6, 3]
 *
 * Sample Output:array = [2, 3, 5, 5, 6, 8, 9]
 */
public class MergeSort {
    // Best/average/worst : O(NlogN) time, Space O(N)
    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }

        int [] auxArray = array.clone();
        mergeSort(array, 0, array.length - 1, auxArray);

        return array;
    }

    public static void mergeSort(int[] mainArray,
                                 int startIdx,
                                 int endIdx,
                                 int [] auxArray)
    {
        if (startIdx == endIdx) {
            return;
        }

        int midIdx = (startIdx + endIdx) / 2;
        mergeSort(auxArray, startIdx, midIdx, mainArray);
        mergeSort(auxArray, midIdx + 1, endIdx, mainArray);
        doMerge(mainArray, startIdx, midIdx, endIdx, auxArray);
    }

    public static void doMerge(int[] mainArray,
                               int startIdx,
                               int midIdx,
                               int endIdx,
                               int [] auxArray)
    {
        int k = startIdx;
        int i = startIdx;
        int j = midIdx + 1;

        while (i <= midIdx && j <= endIdx) {
            if (auxArray[i] <= auxArray[j]) {
                mainArray[k++] = auxArray[i++];
            } else {
                mainArray[k++] = auxArray[j++];
            }
        }

        while (i <= midIdx) {
            mainArray[k++] = auxArray[i++];
        }

        while (j <= endIdx) {
            mainArray[k++] = auxArray[j++];
        }
    }
}
