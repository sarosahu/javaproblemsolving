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
        return countSubArrayInversions(array, 0, array.length);
    }

    public int countSubArrayInversions(int[] array, int start, int end) {
        if (end - start <= 1) {
            return 0;
        }

        int middle = start + (end - start) / 2;
        int leftInversions = countSubArrayInversions(array, start, middle);
        int rightInversions = countSubArrayInversions(array, middle, end);

        int mergedArrayInversions = mergeSortAndCountInversions(array, start, middle, end);

        return leftInversions + rightInversions + mergedArrayInversions;
    }

    public int mergeSortAndCountInversions(int[] array, int start, int middle, int end) {
        List<Integer> sortedArray = new ArrayList<>();
        int left = start;
        int right = middle;
        int inversions = 0;

        while (left < middle && right < end) {
            if (array[left] <= array[right]) {
                sortedArray.add(array[left]);
                ++left;
            } else {
                inversions += middle - left;
                sortedArray.add(array[right]);
                ++right;
            }
        }

        for (int idx = left; idx < middle; ++idx) {
            sortedArray.add(array[idx]);
        }

        for (int idx = right; idx < end; ++idx) {
            sortedArray.add(array[idx]);
        }

        for (int idx = 0; idx < sortedArray.size(); ++idx) {
            int num = sortedArray.get(idx);
            array[start + idx] = num;
        }
        return inversions;
    }
}
