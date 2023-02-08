package com.algo.ae.searching;

/**
 * Search For Range
 *
 * Write a function that takes in a sorted array of integers as well as a target
 * integer. The function should use a variation of Binary Search Algorithm to
 * find a range of indices in between which the target number is contained in
 * the array and should return this range in the form of array.
 *
 * The first number in the output array should represent the first index at
 * which the target number is located, while the second number should represent
 * the last index at which the target number is located. The function should
 * return -1, -1, if the integer isn't contained in the array.
 */
public class SearchForRange {
    public static int[] searchForRange(int[] array, int target) {
        int[] finalRange = {-1, -1};
        alteredBinSearch(array, target, 0, array.length - 1, finalRange, true);
        alteredBinSearch(array, target, 0, array.length - 1, finalRange, false);
        return finalRange;
    }

    public static void
    alteredBinSearch(int[] array, int target, int left, int right,
                     int[] finalRange, boolean goLeft)
    {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > array[mid]) {
                left = mid + 1;
            } else if (target < array[mid]) {
                right = mid - 1;
            } else {
                if (goLeft) {
                    if (mid == 0 || array[mid - 1] != target) {
                        finalRange[0] = mid;
                        break;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    if (mid == array.length - 1 || array[mid + 1] != target) {
                        finalRange[1] = mid;
                        break;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }
        return;
    }
}
