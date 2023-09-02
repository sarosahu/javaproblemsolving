package com.algo.lc.backtrackordp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
    // Time: O(NlogN)
    public static List<Integer>
    longestIncreasingSubsequence(int[] a) {
        List<Integer> result = new ArrayList<>();
        if (a.length <= 1) {
            if (a.length == 1) {
                result.add(a[0]);
            }
            return result;
        }
        int[] indices = new int[a.length + 1];
        indices[0] = -1;
        int[] seq = new int[a.length];
        int maxLen = 0;

        for (int i = 0; i < a.length; ++i) {
            int currNum = a[i];
            int maxLenWithCurrNum = binarySearch(currNum, 1, maxLen, a, indices);
            seq[i] = indices[maxLenWithCurrNum - 1];
            indices[maxLenWithCurrNum] = i;
            maxLen = Math.max(maxLenWithCurrNum, maxLen);
        }

        buildSequences(a, seq, indices[maxLen], maxLen, result);
        return result;
    }

    public static int
    binarySearch(int num,
                 int left,
                 int right,
                 int[] a,
                 int[] indices) {
        if (left > right) {
            return left;
        }
        int mid = left + (right - left)/2;
        if (num < a[indices[mid]]) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
        return binarySearch(num, left, right, a, indices);
    }

    public static void
    buildSequences(int[] a, int[] seq, int currIndex, int maxLen, List<Integer> result) {
        while (maxLen > 0) {
            result.add(a[currIndex]);
            currIndex = seq[currIndex];
            --maxLen;
        }
        Collections.sort(result);
    }

    public static void main(String[] args) {
        int[] a = {5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35};
        List<Integer> result = longestIncreasingSubsequence(a);
        System.out.println("Result : ");
        for (int num : result) {
            System.out.printf("%d, ", num);
        }
        System.out.println();
    }
}
