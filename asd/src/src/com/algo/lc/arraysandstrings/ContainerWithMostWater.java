package com.algo.lc.arraysandstrings;

/**
 *  Container With Most Water
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3048/
 * You are given an integer array height of length n. There are n vertical lines drawn such
 * that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container,
 * such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 *********************************************/
public class ContainerWithMostWater {
    /**
     * BF method. Time : O(N^2) , Space : O(1)
     * This is very inefficient method.
     */
    public static int
    maxArea(int[] height) {
        int maxWaterArea = 0;
        for (int i = 0; i < height.length; ++i) {
            int start = height[i];
            for (int j = i + 1; j < height.length; ++j) {
                int end = height[j];
                int area = Math.min(start, end) * (j - i);
                maxWaterArea = Math.max(maxWaterArea, area);
            }
        }
        return maxWaterArea;
    }

    /**
     * Best time complexity : O(N)
     * Space : O(1)
     */
    public static int
    maxAreaE(int[] height) {
        int start = 0, end = height.length - 1;
        int maxWaterArea = 0;
        while (start < end) {
            int startHeight = height[start];
            int endHeight = height[end];
            int minHeight = Math.min(startHeight, endHeight);
            int currArea = minHeight * (end - start);
            maxWaterArea = Math.max(maxWaterArea, currArea);
            if (startHeight < endHeight) {
                ++start;
            } else if (endHeight < startHeight) {
                --end;
            } else {
                ++start;
                --end;
            }
        }
        return maxWaterArea;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};

        int maxWaterArea = maxArea(height);
        System.out.println("Max water area : " + maxWaterArea);
        maxWaterArea = maxAreaE(height);
        System.out.println("Max water area : " + maxWaterArea);
    }
}
