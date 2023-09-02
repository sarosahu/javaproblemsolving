package com.algo.ae.heaps;

import java.util.*;

/**
 * Laptop Rentals
 *
 * You're given a list of time intervals during which students at a school need a laptop.
 * These time intervals are represented by a pair of integers [start, end], where
 * 0 <= start < end. However, start and end don't represent real times; therefore, they
 * may be greater than 24.
 *
 * No two students can use a laptop at the same time, but immediately after a student is
 * done using a laptop, another student can use that same laptop. For example, if one
 * student rents a laptop during the time interval [0, 2], another student can rent the
 * same laptop during any time interval starting with 2.
 *
 * Write a function that returns the minimum number of laptops that the school needs to
 * rent such that all students will always have access to a laptop when they need one.
 *
 * Sample Input:
 * times =
 * [
 *      [0, 2],
 *      [1, 4],
 *      [4, 6],
 *      [0, 4],
 *      [7, 8],
 *      [9, 11],
 *      [3, 10],
 * ]
 *
 * Sample Output: 3
 */
public class LaptopRentals {
    // Time: O(Nlog(N)) , Space: O(N), -- N is number of time intervals
    public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
        // Write your code here.
        if (times.size() == 0) {
            return 0;
        }
        // Sort the arraylist by start time.
        Collections.sort(times, (a, b) -> Integer.compare(a.get(0), b.get(0)));
        Queue<Integer> minHeap = new PriorityQueue<>();
        // Insert the end time of first interval
        minHeap.add(times.get(0).get(1));
        for (int i = 1; i < times.size(); ++i) {
            ArrayList<Integer> currInterval = times.get(i);
            int start = currInterval.get(0);
            int end = currInterval.get(1);
            if (start >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(end);
        }
        return minHeap.size();
    }

    public int laptopRentals2(ArrayList<ArrayList<Integer>> times) {
        if (times.size() == 0) {
            return 0;
        }

        int usedLaptops = 0;
        List<Integer> startTimes = new ArrayList<>();
        List<Integer> endTimes = new ArrayList<>();

        for (ArrayList<Integer> interval : times) {
            startTimes.add(interval.get(0));
            endTimes.add(interval.get(1));
        }

        Collections.sort(startTimes);
        Collections.sort(endTimes);

        int startIter = 0, endIter = 0;

        while (startIter < times.size()) {
            int startTime = startTimes.get(startIter);
            int endTime = endTimes.get(endIter);
            if (startTime >= endTime) {
                usedLaptops -= 1;
                endIter += 1;
            }

            usedLaptops += 1;
            startIter += 1;
        }

        return usedLaptops;
    }
}
