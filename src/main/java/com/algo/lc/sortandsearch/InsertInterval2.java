package com.algo.lc.sortandsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval2 {

    // Time complexity: O(N), Space: O(1)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Insert the interval first before merge processing
        intervals = insertInterval(intervals, newInterval);

        List<int[]> answer = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            int[] curr = intervals[i];
            // Merge until the list gets exhausted or no overlap is found
            while (i + 1 < intervals.length &&
                    doesIntervalsOverlap(curr, intervals[i + 1])) {
                curr = mergeIntervals(curr, intervals[i + 1]);
                ++i;
            }
            answer.add(curr);
        }
        return answer.toArray(new int[answer.size()][0]);
    }

    private int[][] insertInterval(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int idx = 0;
        while (idx < intervals.length) {
            int[] curr = intervals[idx];
            if (newInterval[0] < curr[0]) {
                list.add(newInterval);
                while (idx < intervals.length) {
                    list.add(intervals[idx]);
                    ++idx;
                }
                break;
            } else {
                list.add(curr);
            }
            ++idx;
        }

        // If there is no interval with a greater value of start value,
        // then the interval must be inserted at the end of the list.
        if (list.size() == intervals.length) {
            list.add(newInterval);
        }

        return list.toArray(new int[list.size()][0]);
    }

    private boolean doesIntervalsOverlap(int[] a, int[] b) {
        return Math.min(a[1], b[1]) - Math.max(a[0], b[0]) >= 0;
    }

    private int[] mergeIntervals(int[] a, int[] b) {
        int[] newInterval = {Math.min(a[0], b[0]), Math.max(a[1], b[1])};
        return newInterval;
    }

    public static void main(String[] args) {
        InsertInterval2 obj = new InsertInterval2();
        int[] i1 = {1, 3};
        int[] i2 = {6, 9};
        int[] i3 = {2, 5};
        int[][] intervals = {i1, i2};

        int[][] newInterval = obj.insert(intervals, i3);
        System.out.println(newInterval);
    }
}
