package com.algo.lc.sortandsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert Interval
 *
 * You are given an array of non-overlapping intervals "intervals" where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order
 * by starti. You are also given an interval newInterval = [start, end] that represents the start
 * and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by
 * starti and intervals still does not have any overlapping intervals (merge overlapping intervals
 * if necessary).
 *
 * Return intervals after the insertion.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 *
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * Constraints:
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 */

public class InsertInterval {
    // This is my way of implementation
    // Time: O(N), space: O(1)
    // I feel this looks a bit of complicated. Somebody can't get it by looking at this code.
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> newIntervals = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            int[] curr = intervals[i];
            int currStart = curr[0];
            int currEnd = curr[1];

            if (newInterval[0] < currStart) {
                if (newInterval[1] < currStart) {
                    newIntervals.add(newInterval);
                    addNextIntervals(intervals, newIntervals, i);
                    break;
                } else {
                    newInterval[1] = newInterval[1] > currEnd ? newInterval[1] : currEnd;
                }
            } else if (newInterval[0] > currStart) {
                if (newInterval[1] < currEnd) {
                    newIntervals.add(curr);
                    addNextIntervals(intervals, newIntervals, i + 1);
                    break;
                } else {
                    if (newInterval[0] > currEnd) {
                        newIntervals.add(curr);
                    } else {
                        newInterval[0] = currStart;
                        newInterval[1] = newInterval[1] > currEnd ? newInterval[1] : currEnd;
                    }
                }
            } else {
                newInterval[1] = newInterval[1] > currEnd ? newInterval[1] : currEnd;
            }
        }
        if (newIntervals.isEmpty()) {
            newIntervals.add(newInterval);
        } else {
            if (newInterval[0] > newIntervals.get(newIntervals.size() - 1)[1]) {
                newIntervals.add(newInterval);
            }
        }

        return newIntervals.toArray(new int[newIntervals.size()][0]);
    }

    private void addNextIntervals(int[][] intervals, List<int[]> newIntervals, int idx) {
        for (int i = idx; i < intervals.length; ++i) {
            newIntervals.add(intervals[i]);
        }
    }
}
