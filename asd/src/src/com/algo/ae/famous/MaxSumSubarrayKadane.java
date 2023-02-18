package com.algo.ae.famous;

/**
 * https://www.algoexpert.io/questions/kadane's-algorithm
 * Write a function that takes in a non-empty array of integers and
 * returns the maximum sum that can be obtained by summing up all of
 * the integers in a non-empty subarray of the input array. A subarray
 * must only contain adjacent numbers.
 */
public class MaxSumSubarrayKadane {
    // Time: O(n), space : O(1)
    public int kadanesAlgorithm(int[] arr) {
        int maxEndingHere = arr[0];
        int maxSoFar = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            int maxEndingPrior = maxEndingHere;
            int currNum = arr[i];
            maxEndingHere = Math.max(maxEndingPrior + currNum, currNum);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4};
        MaxSumSubarrayKadane obj = new MaxSumSubarrayKadane();
        int maxSubarraySum = obj.kadanesAlgorithm(arr);
        System.out.println("Maximum sub-array sum : " + maxSubarraySum);
    }
}
