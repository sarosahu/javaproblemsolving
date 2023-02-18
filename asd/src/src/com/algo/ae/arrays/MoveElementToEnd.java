package com.algo.ae.arrays;

import java.util.List;

/**
 * You're given an array of integers and an integer. Write a function that moves all
 * instances of that integer in the array to the end of the array and returns the
 * array.
 *
 * The function should perform this in place (i.e., it should mutate the input array)
 * and doesn't need to maintain the order of the other integers.
 *
 * Sample Input:
 * array = [2, 1, 2, 2, 2, 3, 4, 2]
 * toMove = 2
 *
 * Sample Output: [1, 3, 4, 2, 2, 2, 2, 2]
 * // The numbers 1, 3, and 4 could be ordered differently.
 *
 */
public class MoveElementToEnd {
    Time: O(N) | Space: O(1)
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int left = 0, right = array.size() - 1;
        while (left < right) {
            if (array.get(left) == toMove) {
                if (array.get(right) != toMove) {
                    swap(array, left, right);
                }
                --right;
            } else {
                ++left;
            }
        }
        return array;
    }

    private static void swap(List<Integer> array, int s, int d) {
        int temp = array.get(s);
        array.set(s, array.get(d));
        array.set(d, temp);
    }
}
