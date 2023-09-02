package com.algo.ae.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Four Number Sum
 *
 * Write a function that takes in a non-empty array of distinct integers and an integer
 * representing a target sum. The function should find all quadruplets in the array that
 * sum up to the target sum and return a 2 dimensional array of all these quadruplets in
 * no particular order.
 *
 * If no four numbers sum up to the target sum, the function should return an empty array.
 *
 * Sample Input:
 * array = [7, 6, 4, -1, 1, 2], targetSum = 16
 *
 * Sample Output:
 * [[7, 6, 4, -1], [7, 6, 1, 2]]
 */
public class FourNumberSum {
    // This is the brute force method and the time complexity is very bad O(n4)
    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        List<Integer[]> list = new ArrayList<Integer[]>();
        for (int i = 0; i < array.length; ++i) {
            for (int j = i + 1; j < array.length; ++j) {
                for (int k = j + 1; k < array.length; ++k) {
                    for (int a = k + 1; a < array.length; ++a) {
                        int sum = array[i] + array[j] + array[k] + array[a];
                        if (sum == targetSum) {
                            list.add(new Integer[]{array[i], array[j], array[k], array[a]});
                        }
                    }
                }
            }
        }
        return list;
    }

    // Average : O(N^2) time | O(N^2) space
    // Worst : O(N^3) time | O(N^2) space
    public static List<Integer[]> fourNumberSumE(int[] array, int targetSum) {
        // Write your code here.
        // I referred the solution.
        Map<Integer, List<Integer[]>> allPairSums = new HashMap<>();
        List<Integer[]> quadruplets = new ArrayList<>();

        for (int i = 0; i < array.length; ++i) {
            for (int j = i + 1; j < array.length; ++j) {
                int currSum = array[i] + array[j];
                int diff = targetSum - currSum;
                if (allPairSums.containsKey(diff)) {
                    // Process to construct the new quadruplets and add it to the list.
                    for (Integer[] pair : allPairSums.get(diff)) {
                        Integer[] newQuadruplets = {pair[0], pair[1], array[i], array[j]};
                        quadruplets.add(newQuadruplets);
                    }
                }
            }

            for (int k = 0; k < i; ++k) {
                int currSum = array[k] + array[i];
                Integer[] currPair = {array[k], array[i]};
                if (allPairSums.containsKey(currSum)) {
                    allPairSums.get(currSum).add(currPair);
                } else {
                    List<Integer[]> newListSumPair = new ArrayList<>();
                    newListSumPair.add(currPair);
                    allPairSums.put(currSum, newListSumPair);
                }
            }

        }
        return quadruplets;
    }
}
