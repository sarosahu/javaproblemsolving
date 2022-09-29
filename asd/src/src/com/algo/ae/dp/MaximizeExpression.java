package com.algo.ae.dp;

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

    public static void main(String[] args) {
        int[] array = {3, 6, 1, -3, 2, 7};
        int maxValue = maximizeExpressionBF(array);
        System.out.println("Maximum value is : " + maxValue);
    }
}
