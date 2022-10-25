package com.algo.ae.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Maximize Expression
 *
 * Write a function that takes in an array of integers and returns the largest
 * possible value for the expression array[a] - array[b] + array[c] - array[d],
 * where a, b, c and d are indices of the array and a < b < c < d.
 *
 * If the input array has fewer than 4 elements, your function should return 0.
 *
 * Sample input : array = [3, 6, 1, -3, 2, 7]
 *
 * Sample output: 4
 * Choose a = 1, b = 3, c = 4, and d = 5
 * -> 6 - (-3) + 2 - 7 = 4
 */
public class MaximizeExpression {
    public static int
    maximizeExpressionBF(int[] array) {
        if (array.length < 4) {
            return 0;
        }

        int maxValue = Integer.MIN_VALUE;
        for (int a = 0; a < array.length - 3; ++a) {
            int aVal = array[a];
            for (int b = a + 1; b < array.length - 2; ++b) {
                int bVal = array[b];
                for (int c = b + 1; c < array.length - 1; ++c) {
                    int cVal = array[c];
                    for (int d = c + 1; d < array.length; ++d) {
                        int dVal = array[d];
                        int expressionVal = aVal - bVal + cVal - dVal;
                        maxValue = Math.max(maxValue, expressionVal);
                    }
                }
            }
        }
        return maxValue;
    }

    public static int
    maximizeExpressionE(int[] array) {
        if (array.length < 4) {
            return 0;
        }
        List<Integer> maxA = new ArrayList<>(Arrays.asList(array[0]));
        List<Integer> maxAMinusB = new ArrayList<>(Arrays.asList(Integer.MIN_VALUE));
        List<Integer> maxAMinusBPlusC = new ArrayList<>(Arrays.asList(Integer.MIN_VALUE, Integer.MIN_VALUE));
        List<Integer> maxAMinusBPlusCMinusD = new ArrayList<>(Arrays.asList(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE));

        for (int i = 1; i < array.length; ++i) {
            int currMax = Math.max(maxA.get(i - 1), array[i]);
            maxA.add(currMax);
        }

        for (int i = 1; i < array.length; ++i) {
            int currMax = Math.max(maxAMinusB.get(i - 1), maxA.get(i - 1) - array[i]);
            maxAMinusB.add(currMax);
        }

        for (int i = 2; i < array.length; ++i) {
            int currMax = Math.max(maxAMinusBPlusC.get(i - 1), maxAMinusB.get(i - 1) + array[i]);
            maxAMinusBPlusC.add(currMax);
        }

        for (int i = 3; i < array.length; ++i) {
            int currMax = Math.max(maxAMinusBPlusCMinusD.get(i - 1), maxAMinusBPlusC.get(i - 1) - array[i]);
            maxAMinusBPlusCMinusD.add(currMax);
        }
        return maxAMinusBPlusCMinusD.get(array.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {3, 6, 1, -3, 2, 7};
        int maxValue = maximizeExpressionBF(array);
        System.out.println("Maximum value with BF approach is : " + maxValue);

        maxValue = maximizeExpressionE(array);
        System.out.println("Maximum value with efficient approach is : " + maxValue);
    }
}
