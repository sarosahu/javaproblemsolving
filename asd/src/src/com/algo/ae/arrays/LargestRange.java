package com.algo.ae.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Largest Range
 *
 * Write a function that takes in an array of integers and returns an array of length 2
 * representing the largest range of integers contained in that array.
 *
 * The first number in the output array should be the first number in the range, while
 * the second number should be the last number in the range.
 *
 * A range of numbers is defined as a set of numbers that come right after each other
 * in the set of real integers. For instance, the output array [2, 6] represents the
 * range {2, 3, 4, 5, 6}, which is a range of length 5. Note that numbers don't need
 * to be sorted or adjacent in the input array in order to form a range.
 *
 * You can assume that there will only be one largest range.
 *
 * Sample Input:
 * array = [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6]
 *
 * Sample Output: [0, 7]
 */
public class LargestRange {

    // Time : O(n) | Space : O(n)
    public static int[] largestRange(int[] array) {
        // This is a naive (BF) approach, time compelxity
        // is Olog(N) -- due to sorting of array
        Arrays.sort(array);
        int [] largeSoFar = new int[] {1, 1};
        int left = 0, right = array.length - 1;
        while (left < right) {
            int currStartNum = array[left];
            int currStartIndex = left;
            while (left < right && (array[left] == array[left + 1] || array[left] == array[left + 1] - 1)) {
                ++left;
            }
            if (left - currStartIndex > largeSoFar[1] - largeSoFar[0]) {
                largeSoFar[0] = array[currStartIndex];
                largeSoFar[1] = array[left];
            }
            ++left;
        }
        return largeSoFar;
    }

    public static int[] largestRangeE(int[] array) {
        // Write your code here.
        Map<Integer, Boolean> hm = new HashMap<>();
        int largeLen = 0;
        for (int i = 0; i < array.length; ++i) {
            hm.put(array[i], false);
        }

        int [] largeRange = {1, 1};

        for (int i = 0; i < array.length; ++i) {
            if (hm.get(array[i]) == true) {
                // Already visited.
                continue;
            }
            int currNum = array[i];
            // true -> visited,
            hm.put(currNum, true);
            int currLen = 1;
            int leftNum = currNum - 1;
            int rightNum = currNum + 1;
            while (hm.containsKey(leftNum)) {
                hm.put(leftNum, true);
                leftNum -= 1;
                ++currLen;
            }
            while (hm.containsKey(rightNum)) {
                hm.put(rightNum, true);
                rightNum += 1;
                ++currLen;
            }

            if (currLen > largeLen) {
                largeLen = currLen;
                largeRange[0] = leftNum + 1;
                largeRange[1] = rightNum -1;
            }
        }

        return largeRange;
    }
}
