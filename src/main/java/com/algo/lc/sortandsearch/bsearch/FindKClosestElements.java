package com.algo.lc.sortandsearch.bsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 658. Find K Closest Elements
 * Given a sorted integer array arr, two integers k and x,
 * return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 *
 * Output: [1,2,3,4]
 * >>>>>>>>>>>>>>>>>
 * Example 2:
 *
 * Input: arr = [1,1,2,3,4,5], k = 4, x = -1
 *
 * Output: [1,1,2,3]
 * >>>>>>>>>>>>>>>>>
 */

public class FindKClosestElements {
    public static List<Integer> findKClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        // initialize binary search bounds
        int l = 0, r = arr.length - k;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        for (int i = l; i < l + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 7, 9, 12, 20, 25, 30};
        int k = 5; int x = 11;
        List<Integer> res = findKClosestElements(arr, k, x);
        System.out.println(res);
    }
}
