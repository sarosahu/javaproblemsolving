package com.algo.ae.arrays;

/**
 * Sorted Squared Array
 *
 * Write a function that takes in a non-empty array of integers that are sorted in
 * ascending order and returns a new array of the same length with the squares of
 * the original integers, also sorted in ascending order.
 *
 * Sample Input:
 * array = [1, 2, 3, 5, 6, 8, 9]
 *
 * Sample Output:
 * array = [1, 4, 9, 25, 36, 64, 81]
 */
public class SortedSquaredArray {
    public int[] sortedSquaredArray(int[] array) {

        // which is also of O(n) time and O(n) space complexity.
        int smallIndex = 0, largerIndex = array.length - 1;
        int [] newArray = new int[array.length];
        for (int i = array.length-1; i >= 0; --i) {
            int smallValue = array[smallIndex];
            int largeValue = array[largerIndex];

            if (Math.abs(smallValue) > Math.abs(largeValue)) {
                newArray[i] = smallValue * smallValue;
                smallIndex++;
            } else {
                newArray[i] = largeValue * largeValue;
                largerIndex--;
            }
        }
        return newArray;
    }
}
