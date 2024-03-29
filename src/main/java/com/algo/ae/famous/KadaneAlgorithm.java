package com.algo.ae.famous;

/**
 * Kadane's Algorithm
 *
 * Write a function that takes in a non-empty array of integers and returns
 * the maximum sum that can be obtained by summing up all of the integers
 * in a non-empty subarray of the input array. A subarray must only contain
 * adjacent numbers.
 *
 * Sample Input:
 * array = [3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4]
 *
 * Sample Output:
 * 19 // [1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1]
 */
public class KadaneAlgorithm {
    public static int nonKadanesAlgorithm(int[] array) {
        // Brute force algorithm (Note: this is not Kadane's algorithm)
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) {
            int currSum = array[i];
            int sum = currSum;
            maxSum = sum > maxSum ? sum : maxSum;
            for (int j = i+1; j < array.length; ++j) {
                sum += array[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    public static int kadanesAlgorithm(int[] array) {
        int maxEndingHere = array[0];
        int maxSoFar = array[0];
        for (int i = 1; i < array.length; ++i) {
            int num = array[i];
            maxEndingHere = Math.max(num, maxEndingHere + num);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}
