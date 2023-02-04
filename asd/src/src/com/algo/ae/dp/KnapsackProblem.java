package com.algo.ae.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Knapsack Problem
 *
 * You're given an array of arrays where each subarray holds 2 integer
 * values and represents an item; the first integer is the item's value,
 * and the second integer is the item's weight. You're also given an
 * integer representing the maximum capacity of a knapsack that you have.
 *
 * Your goal is to fit items in your knapsack without having the sum of
 * their weights exceed the knapsack's capacity, all the while maximizing
 * their combined value. Note that you only have one of each item at
 * your disposal.
 *
 * Write a function that returns the maximized combined value of the items
 * that you should pick as well as an array of the indices of each item
 * picked.
 *
 * If there are multiple combinations of items that maximize the total
 * value in the knapsack, your function can return any of them.
 *
 * Sample Input:
 * items = [[1, 2], [4, 3], [5, 6], [6, 7]]
 * capacity = 10
 *
 * Sample output: [10, [1, 3]] // items [4, 3] and [6, 7]
 */
public class KnapsackProblem {
    public static List<List<Integer>>
    knapsackProblem(int[][] items, int capacity) {

        int[][] matrix = new int[items.length + 1][capacity + 1];
        Arrays.fill(matrix[0], 0);
        for (int i = 0; i < matrix.length; ++i) {
            matrix[i][0] = 0;
        }

        for (int i = 1; i <= items.length; ++i) {
            int currWeight = items[i-1][1];
            int currValue = items[i-1][0];

            for (int c = 0; c <= capacity; ++c) {
                if (currWeight > c) {
                    matrix[i][c] = matrix[i-1][c];
                } else {
                    matrix[i][c] = Math.max(matrix[i-1][c], matrix[i-1][c - currWeight] + currValue);
                }
            }
        }
        int maxValue = matrix[items.length][capacity];
        return getKnapsackItems(matrix, items, maxValue);
    }

    public static List<List<Integer>> getKnapsackItems(
            int[][] matrix, int[][] items, int maxValue) {
        List<List<Integer>> seq = new ArrayList<>();
        List<Integer> totalValue = new ArrayList<>();
        totalValue.add(maxValue);
        seq.add(totalValue);
        seq.add(new ArrayList<>());
        List<Integer> indexList = seq.get(1);
        int i = matrix.length - 1;
        int c = matrix[0].length - 1;
        while (i > 0) {
            if (matrix[i][c] != matrix[i-1][c]) {
                indexList.add(0, i - 1);
                c -= items[i-1][1];
            }
            if (c == 0) {
                break;
            }
            i--;
        }
        return seq;
    }
}
