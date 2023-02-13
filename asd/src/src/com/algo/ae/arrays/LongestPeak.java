package com.algo.ae.arrays;

/**
 * Longest Peak
 *
 * Write a function that takes in an array of integers and returns the length of the
 * longest peak in the array.
 *
 * A peak is defined as adjacent integers in the array that are strictly increasing
 * until they reach a tip (the highest value in the peak), at which point they
 * become strictly decreasing. At least 3 integers are required to form a peak.
 *
 * For example, the integers 1, 4, 10, 2 form a peak, but the integers 4, 0, 10 don't
 * and neither do the integers 1, 2, 2, 0. Similarly, the integers 1, 2, 3 don't form
 * a peak because there aren't any strictly decreasing integers after the 3.
 *
 * Sample Input
 *
 * array = [1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3]
 *
 * Sample Output: 6 //0, 10, 6, 5, -1, -3
 */
public class LongestPeak {
    static enum State {
        NONE,
        INCREASING,
        DECREASING
    }

    // Time : O(N), Space : O(1) -- N is size of array.
    public static int longestPeak(int[] array) {
        int longestPeak = 0;
        int peakStart = -1, peakEnd = -1;
        State prevState = State.NONE;
        // In each iteration we will verify the previous state,
        // calculate the peak length and update the same at the end.
        for (int i = 0; i < array.length - 1; ++i) {
            int currNum = array[i];
            int nextNum = array[i + 1];
            State currState;
            if (currNum < nextNum) {
                currState = State.INCREASING;
                if (prevState == State.NONE || prevState == State.DECREASING) {
                    peakStart = i;
                }
            } else if (currNum > nextNum) {
                currState = State.DECREASING;
                if (prevState == State.NONE) {
                    continue;
                }
                peakEnd = i + 1;
                // If the question was to find the exact subarray that form peak
                // then we can save peakStart and peakEnd here.
                longestPeak = Math.max(longestPeak, peakEnd - peakStart + 1);
            } else {
                currState = State.NONE;
            }
            prevState = currState;
        }
        return longestPeak;
    }

    // { Another approach
    // Time : O(N), Space : O(N)
    public static int longestPeak2(int[] array) {
        // On this array, at each index, it stores the total numbers
        // those are smaller than the number at this index and are left to it.
        int [] left = new int[array.length];
        for (int i = 1; i < array.length; ++i) {
            if (array[i - 1] < array[i]) {
                left[i] = left[i - 1] + 1;
            }
        }

        // On this array, at each index, it stores the total numbers
        // those are greater than the number at this index and are right to it.
        int [] right = new int[array.length];
        for (int i = array.length - 2; i >= 0; --i) {
            if (array[i + 1] < array[i]) {
                right[i] = right[i + 1] + 1;
            }
        }

        int max = 0;
        // Find the max peak
        for (int i = 1; i < array.length - 1; ++i) {
            if (left[i] != 0 && right[i] != 0) {
                max = Math.max(left[i] + right[i] + 1, max);
            }
        }
        return max;
    }
    // }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};
        int longestPeak = longestPeak(array);
        System.out.println("Longest peak : " + longestPeak);

        longestPeak = longestPeak2(array);
        System.out.println("Longest peak : " + longestPeak);
    }
}
