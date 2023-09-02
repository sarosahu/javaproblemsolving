package com.algo.ae.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Max Sum Increasing Subsequence
 *
 * Write a function that takes in a non-empty array of integers and returns
 * the greatest sum that can be generated from a strictly-increasing
 * subsequence in the array as well as an array of the numbers in that
 * sequence.
 *
 * A subsequence of an array is a set of numbers that aren't necessarily
 * adjacent in the array, but that are in the same order as they appear
 * in that array. For instance, the numbers [1, 3, 4] form a subsequence
 * of the array [1, 2, 3, 4], and so do the numbers [2, 4]. Note that
 * a single number in an array and array itself are both valid
 * subsequences of the array.
 *
 * You can assume that there will only be one increasing subsequence with
 * the greatest sum.
 *
 * Sample Input:
 * array = [10, 70, 20, 30, 50, 11, 30]
 *
 * Sample output:
 * [110, [10, 20, 30, 50]]
 */
public class MaxSumIncreasingSubsequence {
    // Time: O(n^2), space : O(n)
    public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
        // Write your code here.
        int [] maxSumToIndexList = Arrays.copyOf(array, array.length);
        int [] prevIndexList = new int[array.length];
        Arrays.fill(prevIndexList, -1);

        int maxSumIndex = 0;
        for (int i = 1; i < array.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (array[j] < array[i] && maxSumToIndexList[j] + array[i] > maxSumToIndexList[i]) {
                    maxSumToIndexList[i] = maxSumToIndexList[j] + array[i];
                    prevIndexList[i] = j;
                }
            }
            if (maxSumToIndexList[i] > maxSumToIndexList[maxSumIndex]) {
                maxSumIndex = i;
            }
        }

        int maxSum = maxSumToIndexList[maxSumIndex];
        List<Integer> subsequenceList = new ArrayList<>();
        while (maxSumIndex >= 0) {
            subsequenceList.add(array[maxSumIndex]);
            maxSumIndex = prevIndexList[maxSumIndex];
        }
        Collections.reverse(subsequenceList);
        return new ArrayList<List<Integer>>() {
            {
                add(List.of(maxSum));
                add(subsequenceList);
            }
        };
    }

    public static void main(String[] args) {
        int[] array = {10, 70, 20, 30, 50, 11, 30};
        List<List<Integer>> sequences = maxSumIncreasingSubsequence(array);
        System.out.println(sequences.get(0).get(0));
        for (int num : sequences.get(1)) {
            System.out.printf("%d, ", num);
        }
        System.out.println();
    }
}
