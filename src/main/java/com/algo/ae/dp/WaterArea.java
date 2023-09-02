package com.algo.ae.dp;

/**
 * Water Area
 *
 * You're given an array of non-negative integers where each non-zero integer
 * represents the height of a pillar of width 1. Imagine water being poured
 * over all of the pillars; write a function that returns the surface area
 * of the water trapped between the pillars viewed from the front.
 * Note that spilled water should be ignored.
 *
 * Sample Input: [0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3]
 *
 * Sample Output: 48
 */
public class WaterArea {
    // Time: O(N), Space : O(N)
    public static int waterArea(int[] heights) {
        int [] leftMaxIndex = new int[heights.length];
        int leftMax = 0;
        for (int i = 0; i < leftMaxIndex.length; ++i) {
            leftMaxIndex[i] = leftMax;
            leftMax = Math.max(heights[i], leftMax);
        }
        int [] rightMaxIndex = new int[heights.length];
        int rightMax = 0;
        for (int i = rightMaxIndex.length - 1; i >= 0; --i) {
            rightMaxIndex[i] = rightMax;
            rightMax = Math.max(heights[i], rightMax);
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; ++i) {
            int minHeight = Math.min(leftMaxIndex[i], rightMaxIndex[i]);
            int currentHeight = heights[i];
            int currentWaterTrappedArea = 0;
            if (currentHeight < minHeight) {
                currentWaterTrappedArea = minHeight - currentHeight;
            }
            maxArea += currentWaterTrappedArea;
        }
        return maxArea;
    }

    // Time: O(N), Space : O(N)
    public static int waterArea2(int[] heights) {
        int [] maxes = new int[heights.length];
        int leftMax = 0;
        for (int i = 0; i < heights.length; ++i) {
            maxes[i] = leftMax;
            leftMax = Math.max(heights[i], leftMax);
        }
        int rightMax = 0;
        int maxArea = 0;
        for (int i = heights.length - 1; i >= 0; --i) {
            int currentHeight = heights[i];
            int minHeight = Math.min(maxes[i], rightMax);
            int currentWaterTrappedArea = 0;
            if (currentHeight < minHeight) {
                currentWaterTrappedArea = minHeight - currentHeight;
            }
            maxArea += currentWaterTrappedArea;
            rightMax = Math.max(currentHeight, rightMax);
        }
        return maxArea;
    }

    // Time: O(N), Space : O(1)
    public static int waterAreaE(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }

        int leftIdx = 0, rightIdx = heights.length - 1;
        int leftMax = heights[leftIdx];
        int rightMax = heights[rightIdx];
        int area = 0;

        while (leftIdx < rightIdx) {
            int currLeftHeight = heights[leftIdx];
            int currRightHeight = heights[rightIdx];
            if (currLeftHeight < currRightHeight) {
                ++leftIdx;
                leftMax = Math.max(leftMax, heights[leftIdx]);
                area += leftMax - heights[leftIdx];
            } else {
                --rightIdx;
                rightMax = Math.max(rightMax, heights[rightIdx]);
                area += rightMax - heights[rightIdx];
            }
        }
        return area;
    }
}
