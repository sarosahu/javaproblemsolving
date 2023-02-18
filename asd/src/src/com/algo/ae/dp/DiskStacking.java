package com.algo.ae.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Disk Stacking
 *
 * You're given a non-empty array of arrays where each subarray holds three integers and
 * represents a disk. These integers denote each disk's width, depth, and height, respectively.
 * Your goal is to stack up the disks and to maximize the total height of the stack. A disk
 * must have a strictly smaller width, depth and height than any other disk below it.
 *
 * Write a function that returns an array of the disks in the final stack, starting with the
 * top disk and ending with the bottom disk. Note that you can't rotate disks; in other words,
 * the integers in each subarray must represent width, depth, height at all times.
 *
 * You can assume that there will only be one stack with the greatest total height.
 *
 * Sample Input:
 * disks = {
 *   "disks": [
 *     [2, 1, 2],
 *     [3, 2, 3],
 *     [2, 2, 8],
 *     [2, 3, 4],
 *     [1, 3, 1],
 *     [4, 4, 5]
 *   ]
 * }
 *
 * Sample Output: [[2, 1, 2], [3, 2, 3], [4, 4, 5]]
 *
 * // 10 (2 + 3 + 5) is the tallest height we can get by
 * // stacking disks following the rules laid out above.
 */
public class DiskStacking {
    static class Disk {
        Integer width;
        Integer depth;
        Integer height;

        public Disk(Integer w, Integer d, Integer h) {
            this.width = w;
            this.depth = d;
            this.height = h;
        }
    }

    // Time: O(n^2), O(n) space
    public static List<Integer[]> diskStacking(List<Integer[]> disks) {
        // Write your code here.
        List<Disk> diskList = new ArrayList<>();
        for (int i = 0; i < disks.size(); ++i) {
            Integer[] currDisk = disks.get(i);
            diskList.add(new Disk(currDisk[0], currDisk[1], currDisk[2]));
        }
        diskList.sort((d1, d2) -> d1.height.compareTo(d2.height));
        int [] maxHeightAtIndex = new int[disks.size()];
        int [] prev = new int[disks.size()];
        for (int i = 0; i < diskList.size(); ++i) {
            maxHeightAtIndex[i] = diskList.get(i).height;
            prev[i] = -1;
        }
        int maxHeight = Integer.MIN_VALUE;
        int maxHeightIndex = 0;
        for (int i = 1; i < diskList.size(); ++i) {
            Disk currDisk = diskList.get(i);
            for (int j = 0; j < i; ++j) {
                Disk disk = diskList.get(j);
                if (disk.width >= currDisk.width || disk.depth >= currDisk.depth || disk.height >= currDisk.height) {
                    continue;
                }
                if (maxHeightAtIndex[j] + currDisk.height >= maxHeightAtIndex[i]) {
                    maxHeightAtIndex[i] = maxHeightAtIndex[j] + currDisk.height;
                    prev[i] = j;
                }
            }
            if (maxHeightAtIndex[maxHeightIndex] <= maxHeightAtIndex[i]) {
                maxHeightIndex = i;
            }
        }
        List<Integer[]> newDisks = new ArrayList<>();

        while (maxHeightIndex != -1) {
            int idx = maxHeightIndex;
            Disk currDisk = diskList.get(idx);
            newDisks.add(new Integer[]{currDisk.width, currDisk.depth, currDisk.height});
            maxHeightIndex = prev[maxHeightIndex];
        }
        Collections.reverse(newDisks);
        return newDisks;
    }
}
