package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Staircase Traversal
 *
 * You're given 2 positive integers representing the height of a staircase and the
 * maximum number of steps that you can advance up the staircase at a time. Write
 * a function that returns the number of ways in which you can climb the staircase.
 *
 * For example, if you were given a staircase of height = 3 and maxSteps = 2, you
 * could climb the staircase in 3 ways. You could take 1 step, 1 step, then 1 step,
 * you could also take 1 step, then 2 steps, and you could take 2 steps, then 1 step.
 *
 * Note that maxSteps <= height will always be true.
 *
 * Sample Input:
 * height = 4
 * maxSteps = 2
 *
 * Sample Output: 5
 * // You can climb the staircase in the following ways:
 * // 1, 1, 1, 1
 * // 1, 1, 2
 * // 1, 2, 1
 * // 2, 1, 1
 * // 2, 2
 */
public class StaircaseTraversal {
    // {
    // Time: O(k^n) , Space: O(n), -- n is height of the staircase
    // k is number of maximum allowed steps.
    public int staircaseTraversalNE(int height, int maxSteps) {
        return numWays(height, maxSteps);
    }

    public int numWays(int height, int maxSteps) {
        if (height <= 1) {
            return 1;
        }

        int nways = 0;
        for (int step = 1; step <= Math.min(maxSteps, height); ++step) {
            nways += numWays(height - step, maxSteps);
        }

        return nways;
    }
    // }

    // {
    // Time: O(n * k), Space: O(n)
    public int staircaseTraversalE1(int height, int maxSteps) {
        // Write your code here.
        int[] memo = new int[height + 1];
        memo[0] = 1;
        memo[1] = 1;

        for (int currHeight = 2; currHeight <= height; ++currHeight) {
            int totalStepsHere = 0;
            for (int prevStep = 1; prevStep <= Math.min(currHeight, maxSteps); ++prevStep) {
                totalStepsHere += memo[currHeight - prevStep];
            }
            memo[currHeight] = totalStepsHere;
        }
        return memo[height];
    }
    // }

    // {
    public int staircaseTraversalE2(int height, int maxSteps) {
        // Write your code here.
        //return numberOfWays(height, maxSteps);
        int[] memo = new int[height + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        memo[1] = 1;
        return numberOfWaysE2(height, maxSteps, memo);
    }

    /**
     * Time complexity : O(maxSteps * height)
     * Space complexity : O(height)
     */
    int numberOfWaysE2(int height, int maxSteps, int[] memo) {
        if (memo[height] > 0) {
            return memo[height];
        }
        int numWays = 0;
        for (int step = 1; step <= Math.min(maxSteps, height); ++step) {
            numWays += numberOfWaysE2(height - step, maxSteps, memo);
        }
        memo[height] = numWays;
        return numWays;
    }
    // }

    // Sliding window approach, Time: O(N), Space : O(N)
    // {
    public int staircaseTraversal(int height, int maxSteps) {
        int currNumWays = 0;
        List<Integer> waysToTop = new ArrayList<>();
        waysToTop.add(1);

        for (int currHeight = 1; currHeight <= height; ++currHeight) {
            int startWindow = currHeight - maxSteps - 1;
            int endWindow = currHeight - 1;

            if (startWindow >= 0) {
                currNumWays -= waysToTop.get(startWindow);
            }

            currNumWays += waysToTop.get(endWindow);
            waysToTop.add(currNumWays);
        }
        return waysToTop.get(height);
    }
    // }
}
