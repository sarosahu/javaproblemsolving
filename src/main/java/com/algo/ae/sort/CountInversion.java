package com.algo.ae.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function that takes in an array of integers and returns the number
 * of inversions in the array. An inversion occurs if for any valid indices i
 * and j, i < j and array[i] > array[j].
 *
 * For example, given array = [3, 4, 1, 2], there are 4 inversions. The following
 * paris of indices represent inversions: [0, 2], [0, 3], [1, 2], [1, 3].
 *
 * Intuitively, the number of inversions is a measure of how unsorted the array is.
 *
 * Sample Input:
 * array = [2, 3, 3, 1, 9, 5, 6]
 *
 * Sample Output: 5
 */
public class CountInversion {
    public int countInversions(int[] array) {
        // Write your code here.
        return countSubArrayInversions(array, 0, array.length - 1);
    }

    public int countSubArrayInversions(int[] array, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        int leftInversions = countSubArrayInversions(array, start, mid);
        int rightInversions = countSubArrayInversions(array, mid + 1, end);

        int mergedArrayInversions = mergeSortAndCountInversions(array, start, mid, end);

        return leftInversions + rightInversions + mergedArrayInversions;
    }

    public int mergeSortAndCountInversions(int[] arr, int start, int mid, int end) {
        List<Integer> sortedArray = new ArrayList<>();
        int left = start;
        int right = mid + 1;
        int inversions = 0;

        while (left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                sortedArray.add(arr[left]);
                ++left;
            } else {
                inversions += mid - left + 1;
                sortedArray.add(arr[right]);
                ++right;
            }
        }

        for (int idx = left; idx <= mid; ++idx) {
            sortedArray.add(arr[idx]);
        }

        for (int idx = right; idx <= end; ++idx) {
            sortedArray.add(arr[idx]);
        }

        for (int idx = 0; idx < sortedArray.size(); ++idx) {
            int num = sortedArray.get(idx);
            arr[start + idx] = num;
        }
        return inversions;
    }

    public static void main(String[] args) {
        CountInversion obj = new CountInversion();
        int[] a = {2, 3, 3, 1, 9, 5, 6};
        int count = obj.countInversions(a);
        System.out.println("Inversion counts: " + count);
    }
}
