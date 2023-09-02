package com.algo.ae.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Longest Increasing Subsequence
 *
 * Given a non-empty array of integers, write a function that returns the longest
 * strictly-increasing subsequence in the array.
 *
 * A subsequence of an array is a set of numbers that aren't necessarily adjacent in the
 * array but that are in the same order as they appear in the array. For instance, the
 * numbers [1, 3, 4] form a subsequence of the array [1, 2, 3, 4], and so do the numbers
 * [2, 4]. Note that a single number in an array and the array itself are both valid
 * subsequences of the array.
 *
 * You can assume that there will only be one longest increasing subsequence.
 *
 * Sample Input:
 * array = [5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35]
 *
 * Sample Output: [-24, 2, 3, 5, 6, 35]
 */
public class LongestIncreasingSubsequence {
    // O(N^2) time and O(N) space
    public static List<Integer> longestIncreasingSubsequence(int[] array) {
        // Write your code here.
        int[] prev = new int[array.length];
        Arrays.fill(prev, -1);
        int[] maxLis = new int[array.length];
        Arrays.fill(maxLis, 1);
        int maxLisIndex = 0;
        for (int i = 1; i < array.length; ++i) {
            int currTarget = array[i];
            for (int j = 0; j < i; ++j) {
                int currVisiting = array[j];
                if (currVisiting < currTarget && maxLis[j] + 1 > maxLis[i]) {
                    maxLis[i] = maxLis[j] + 1;
                    prev[i] = j;
                }
            }
            if (maxLis[i] > maxLis[maxLisIndex]) {
                maxLisIndex = i;
            }
        }
        int maxSubseqNumber = maxLis[maxLisIndex];
        List<Integer> results = new ArrayList<>();
        while (maxLisIndex >= 0 && maxSubseqNumber > 0) {
            results.add(array[maxLisIndex]);
            maxLisIndex = prev[maxLisIndex];
            --maxSubseqNumber;
        }
        Collections.sort(results);
        return results;
    }

    // Time: O(NlogN), Space: O(N)
    public static List<Integer> longestIncreasingSubsequenceE(int[] array) {
        int[] sequences = new int[array.length];
        int[] indices = new int[array.length + 1];
        Arrays.fill(indices, Integer.MIN_VALUE);
        int length = 0;

        for (int i = 0; i < array.length; ++i) {
            int num = array[i];
            int newLength = binarySearch(1, length, indices, array, num);
            sequences[i] = indices[newLength - 1];
            indices[newLength] = i;
            length = Math.max(length, newLength);
        }
        return buildSequence(array, sequences, indices[length]);
    }

    public static int binarySearch(int startIdx,
                                   int endIdx,
                                   int [] indices,
                                   int[] array,
                                   int num) {
        if (startIdx > endIdx) {
            return startIdx;
        }

        int mid = startIdx + (endIdx - startIdx) / 2;
        if (array[indices[mid]] < num) {
            startIdx = mid + 1;
        } else {
            endIdx = mid - 1;
        }
        return binarySearch(startIdx, endIdx, indices, array, num);
    }

    public static List<Integer> buildSequence(int[] array,
                                              int[] sequences,
                                              int currIdx) {
        List<Integer> sequence = new ArrayList<>();
        while (currIdx != Integer.MIN_VALUE) {
            sequence.add(0, array[currIdx]);
            currIdx = sequences[currIdx];
        }
        return sequence;
    }
}
