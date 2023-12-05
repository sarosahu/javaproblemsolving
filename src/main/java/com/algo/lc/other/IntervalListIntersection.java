package com.algo.lc.other;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersection {
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int l1 = firstList.length, l2 = secondList.length;
        int i = 0, j = 0;
        List<int[]> result = new ArrayList<>();
        int lastLargeNum = -1;
        while (i < l1 && j < l2) {
            int[] first = firstList[i];
            int[] second = secondList[j];
            //int small = Math.min(first[0], second[0]);
            if (lastLargeNum >= 0) {
                int small = Math.min(first[0], second[0]);
                if (lastLargeNum == small) {
                    result.add(new int[]{small, small});
                }
            }
            int commonFirst = Math.max(first[0], second[0]);
            int commonSecond = Math.min(first[1], second[1]);
            if (commonFirst <= commonSecond) {
                result.add(new int[]{commonFirst, commonSecond});
                if (first[1] == commonSecond) {
                    ++i;
                } else {
                    ++j;
                }
                //++i;
                //++j;
            } else if (first[1] == commonSecond) {
                ++i;
            } else {
                ++j;
            }
            lastLargeNum = Math.max(first[1], second[1]);
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] first = {
            {8, 15}
        };
        int[][] second = {
                {2, 6}, {8, 10}, {12, 20}
        };
        int result[][] = intervalIntersection(first, second);
        for (int[] arr : result) {
            System.out.println(arr[0] + ", " + arr[1]);
        }
    }
}
