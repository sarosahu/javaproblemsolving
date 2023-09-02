package com.algo.ae.arrays;

import java.util.Arrays;

/**
 * Smallest Difference
 *
 * Write a function that takes in 2 non-empty arrays of integers, find the
 * pair of numbers(one from each array) whose absolute difference is closest
 * to zero, and returns an array containing these 2 numbers, with the number
 * from the first array in the first position.
 *
 * Note that the absolute difference of 2 integers is the distance between
 * them on the real number line. For example, the absolute difference
 * of -5 and 5 is 10, and the absolute difference of -5 and -4 is 1.
 *
 * You can assume that there will only be one pair of numbers with the
 * smallest difference.
 *
 * Sample Input:
 * arrayOne = [-1, 5, 10, 20, 28, 3]
 * arrayTwo = [26, 134, 135, 15, 17]
 *
 * Sample Output: [28, 26]
 */
public class SmallestDifference {

    // O(nlog(n) + O(mlog(m)) | O(1) space
    // n and m are length of 2 arrays.
    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);
        int idx1 = 0, idx2 = 0;
        int[] smallestPair = new int[2];
        int smallestDiff = Integer.MAX_VALUE;
        while (idx1 < arrayOne.length && idx2 < arrayTwo.length) {
            int firstNum = arrayOne[idx1];
            int secondNum = arrayTwo[idx2];
            int currentDiff = firstNum - secondNum;
            if (currentDiff < 0) {
                ++idx1;
            } else if (currentDiff > 0) {
                ++idx2;
            } else {
                smallestPair[0] = firstNum;
                smallestPair[1] = secondNum;
                break;
            }

            if (smallestDiff > Math.abs(currentDiff)) {
                smallestDiff = Math.abs(currentDiff);
                smallestPair[0] = firstNum;
                smallestPair[1] = secondNum;
            }
        }
        return smallestPair;
    }
}
