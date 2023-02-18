package com.algo.ae.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Merge Overlapping Intervals
 *
 * Write a function that takes in a non-empty array of arbitrary intervals, merges any
 * overlapping intervals, and returns the new intervals in no particular order.
 *
 * Each interval "interval" is an array of 2 integers, with interval[0] as the start
 * of the interval and interval[1] as the end of the interval.
 *
 * Note that back-to-back intervals aren't considered to be overlapping. For example
 * [1, 5] and [6, 7] aren't overlapping; however, [1,6] and [6,7] are indeed
 * overlapping.
 *
 * Also note that the start of any particular interval will always be less than or
 * equal to the end of that interval.
 *
 * Sample Input:
 * intervals = [[1, 2], [3, 5], [4, 7], [6, 8], [9, 10]]
 *
 * Sample Output: [[1, 2],[3, 8], [9, 10]]
 */
public class MergeOverlappingIntervals {
    public int[][] mergeOverlappingIntervals(int[][] intervals) {
        // Time: O(nlogn), space : O(n)
        int [][] sortedIntervals = intervals.clone();
        Arrays.sort(sortedIntervals, Comparator.comparingInt(a -> a[0]));

        int [] prevInterval = sortedIntervals[0];
        List<int[]> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(prevInterval);
        for (int i = 1; i < sortedIntervals.length; ++i) {
            int [] currInterval = sortedIntervals[i];
            int currStart = currInterval[0];
            int currEnd = currInterval[1];
            int prevEnd = prevInterval[1];

            if (prevEnd >= currStart) {
                prevInterval[1] = Math.max(currEnd, prevEnd);
            } else {
                prevInterval = currInterval;
                mergedIntervals.add(prevInterval);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
