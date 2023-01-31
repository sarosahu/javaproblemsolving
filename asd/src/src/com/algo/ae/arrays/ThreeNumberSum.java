package com.algo.ae.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Three Number Sum
 *
 * Write a function that takes a non-empty array of distinct integers
 * and an integer representing a target sum. The function should find
 * all triplets in the array that sum up to the target sum and return
 * a 2-dimensional array of all these triplets. The numbers in each
 * triplet should be ordered in ascending order with respect to the
 * numbers they hold.
 *
 * If no 3 numbers sum up to the target sum, the function should
 * return an empty array.
 *
 * Sample Input:
 * array = [12, 3, 1, 2, -6, 5, -8, 6]
 * targetSum = 0
 *
 * Sample Output:
 * [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
 */
public class ThreeNumberSum {

    // Brute-force method:O(n^3)
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        List<Integer[]> results = new ArrayList<>();

        for (int i = 0; i < array.length; ++i) {
            int first = array[i];
            for (int j = i + 1; j < array.length; ++j) {
                int second = array[j];
                for (int k = j + 1; k < array.length; ++k) {
                    int third = array[k];

                    if (first + second + third == targetSum) {
                        Integer[] result = new Integer[]{first, second, third};
                        Arrays.sort(result);
                        results.add(result);
                    }
                }
            }
        }
        Collections.sort(results, (a, b) -> a[0].compareTo(b[0]) == 0 ?
                a[1].compareTo(b[1]) : a[0].compareTo(b[0]));
        return results;
    }

    public static List<Integer[]> threeNumberSumE(int[] array, int targetSum) {

        List<Integer[]> list = new ArrayList<Integer[]>();
        Arrays.sort(array);
        for (int i = 0; i < array.length - 2; ++i) {
            int left = i + 1;
            int right = array.length - 1;
            while (left < right) {
                int currentSum = array[i] + array[left] + array[right];
                if (currentSum == targetSum) {
                    Integer[] newTriplet = {array[i], array[left], array[right]};
                    list.add(newTriplet);
                    ++left;
                    --right;
                } else if (currentSum > targetSum) {
                    --right;
                } else {
                    ++left;
                }
            }
        }
        return list;
    }
}
