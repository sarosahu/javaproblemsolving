package com.algo.ae.arrays;

/**
 * Array Of Products
 *
 * Write a function that takes in a non-empty array of integers and returns an array of
 * the same length, where each element in the output array is equal to the product of
 * every other number in the input array.
 *
 * In other words, the value of output[i] is equal to the product of every number in
 * the input array other than input[i].
 *
 * Note that you're expected to solve this problem without using division.
 *
 * Sample Input: array = [5, 1, 4, 2]
 *
 * Sample Output: [8, 40, 10, 20]
 */
public class ArrayOfProducts {

    // Brute Force approach, time: O(N^2)
    public int[] arrayOfProductsBF(int[] array) {
        // Write your code here.
        int [] result = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            fillProducts(result, array, i);
        }
        return result;
    }

    private void fillProducts(int [] result, int [] array, int index) {
        int product = 1;
        for (int i = 0; i < array.length; ++i) {
            if (i == index) {
                continue;
            }
            product *= array[i];
        }
        result[index] = product;
    }

    // Time : O(N), Space : O(N)
    public static int[] arrayOfProducts(int [] array) {
        int [] result = new int[array.length];
        int [] left = new int[array.length];
        int [] right = new int[array.length];

        int leftRunningProduct = 1;
        for (int i = 0; i < array.length; ++i) {
            left[i] = leftRunningProduct;
            leftRunningProduct *= array[i];
        }

        int rightRunningProduct = 1;
        for (int i = array.length - 1; i >= 0; --i) {
            right[i] = rightRunningProduct;
            rightRunningProduct *= array[i];
        }

        for (int i = 0; i < array.length; ++i) {
            result[i] = left[i] * right[i];
        }

        return result;
    }

    // THis is better than above one, it's not going to allocate 2 extra arrays
    // Time : O(N), Space : O(N)
    public static int[] arrayOfProducts2(int[] array) {
        int [] result = new int[array.length];
        int leftToCurrent = 1;
        for (int i = 0; i < array.length; ++i) {
            result[i] = leftToCurrent;
            leftToCurrent *= array[i];
        }

        int rightToCurrent = 1;
        for (int i = array.length - 1; i >= 0; --i) {
            result[i] *= rightToCurrent;
            rightToCurrent *= array[i];
        }
        return result;
    }
    public static void main(String[] args) {

    }
}
