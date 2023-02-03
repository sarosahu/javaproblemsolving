package com.algo.ae.dp;

import java.util.Arrays;

/**
 * Min Number Of Jumps
 *
 * You're given non-empty array of +ve integers where each integer represents
 * the maximum number of steps you can take forward in the array. For example,
 * it the element at index 1 is 3, you can go from index 1 to index 2, 3 or 4.
 *
 * Write a function that returns the minimum number of jumps needed to reach
 * the final index.
 *
 * Note that jumping from index i to index i + x always constitutes one jump,
 * no matter how long x is.
 *
 * Sample Input:
 * array = [3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3]
 *
 * Sample Output:
 * 4 // 3--> (4 or 2) --> (2 or 3) --> 7 --> 3
 */
public class MinNumberOfJumps {
    // Time: O(N^2), Space : O(N)
    // Dynamic programming approach.
    public static int minNumberOfJumps(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return 0;
        }
        if (array[0] >= array.length - 1) {
            return 1;
        }
        // Keep this array to calculate the minimum jumps
        // required to move to end position from the current position.
        int [] minJumps = new int[array.length];
        Arrays.fill(minJumps, Integer.MAX_VALUE);
        minJumps[array.length - 1] = 0;

        for (int i = array.length - 2; i >= 0; --i) {
            int currJumpVal = array[i];
            if (i + currJumpVal >= array.length - 1) {
                minJumps[i] = 1;
            } else {
                for (int j = i + 1; j <= i + currJumpVal; ++j) {
                    minJumps[i] = Math.min(1 + minJumps[j], minJumps[i]);
                }
            }
        }
        return minJumps[0];
    }

    // TODO: Understand this logic, may be watch the video explanation
    public static int minNumberOfJumpsE(int[] array) {
        // Write your code here.
        if (array.length == 1) {
            return 0;
        }
        int maxReach = array[0];
        int steps = maxReach;
        int jumps = 0;
        for (int i = 1; i < array.length; ++i) {
            maxReach = Math.max(maxReach, i + array[i]);
            --steps;
            if (steps == 0) {
                jumps += 1;
                if (i == array.length - 1) {
                    break;
                }
                steps = maxReach - i;
            }
        }
        if (steps > 0) {
            jumps += 1;
        }
        return jumps;
    }
}
