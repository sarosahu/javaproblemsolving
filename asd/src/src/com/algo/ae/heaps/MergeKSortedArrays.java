package com.algo.ae.heaps;
/**
 * Sort K-Sorted array.
 * Write a function that takes in a non-negative integer k and a k-sorted array
 * of integers and returns the sorted version of that array. Your function
 * can either sort the array in place or create an entire new array.
 *
 * A k-sorted array is a partially sorted array in which all elements are
 * at most k positions away from their sorted position. For example the
 * array [3, 1, 2, 2] is k-sorted with k = 3, because each element in the
 * array is at most 3 positions away from its sorted position.
 */

import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedArrays {
    // Time: Nlog(k)
    public void sortKSortedArray(int[] array, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i <= k && i < array.length; ++i) {
            minHeap.add(array[i]);
        }
        int j = 0;
        for (int i = k + 1; i < array.length; ++i) {
            int currMinElement = minHeap.poll();
            array[j++] = currMinElement;
            minHeap.add(array[i]);
        }
        while (!minHeap.isEmpty()) {
            array[j++] = minHeap.poll();
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 4, 7, 6, 5};
        int k = 3;
        MergeKSortedArrays obj = new MergeKSortedArrays();
        System.out.println("Original array: ");
        for (int a : arr) {
            System.out.printf("%d ", a);
        }
        System.out.println();
        obj.sortKSortedArray(arr, k);
        for (int a : arr) {
            System.out.printf("%d ", a);
        }
        System.out.println();
    }

}
