package com.algo.ae.dp;

/**
 * Max Subset Sum No Adjacent
 *
 * Write a function that takes in an array of +ve integers and returns
 * the maximum sum of non-adjacent elements in the array.
 *
 * If the input array is empty, the function should return 0.
 *
 * Sample input: [75, 105, 120, 75, 90, 135]
 *
 * Sample output: 330 (75 + 120 + 135)
 */
public class MaxSubsetSumNoAdjacent {
    public static int maxSubsetSumNoAdjacent(int [] array) {
        if (array.length == 0) {
            return 0;
        } else if (array.length == 1) {
            return array[0];
        }
        int[] maxSums = array.clone();
        maxSums[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length; ++i) {
            maxSums[i] = Math.max(maxSums[i - 1], maxSums[i - 2] + array[i]);
        }
        return maxSums[array.length - 1];
    }

    public static int maxSubsetSumNoAdjacentE(int[] array) {
        if (array.length == 0) {
            return 0;
        } else if (array.length == 1) {
            return array[0];
        }
        int prevToPrevMaxSum = array[0];
        int prevMaxSum = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length; ++i) {
            int currMaxSum = Math.max(prevToPrevMaxSum + array[i], prevMaxSum);
            prevToPrevMaxSum = prevMaxSum;
            prevMaxSum = currMaxSum;
        }
        return prevMaxSum;
    }

    public static void main(String[] args) {
        int[] arr = {7, 10, 12, 7, 9, 14};
        int maxSubsetSum = maxSubsetSumNoAdjacent(arr);
        System.out.println("Maximum subset sum no adjacent " + maxSubsetSum);
    }
}
