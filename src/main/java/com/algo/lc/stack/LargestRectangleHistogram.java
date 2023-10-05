package com.algo.lc.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers heights representing the histogram's bar height
 * where the width of each bar is 1, return the area of the largest rectangle
 * in the histogram.
 *
 * Example 1: 6
 *        5  ___
 *        __|__|
 *       |__|__|    3
 *   2   |__|__| 2 ___
 *  ___ 1|__|__|__|__|
 * |__|__|__|__|__|__|
 * |__|__|__|__|__|__|
 * ---------------------
 *
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestRectangleHistogram {
    // Brute force approach
    // At each index, we will find out the previous and next indices
    // where the number is small from the number at current index.
    // Then it is easy to find the area with the height (number at
    // current index) and width (next index - prev index + 1)
    // Time: O(N^2), space: O(1)
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; ++i) {
            int currHeight = heights[i];
            int leftIter = i, rightIter = i;
            while (leftIter - 1 >= 0 && currHeight <= heights[leftIter - 1]) {
                --leftIter;
            }
            while (rightIter + 1 < heights.length && currHeight <= heights[rightIter + 1]) {
                ++rightIter;
            }
            int width = rightIter - leftIter + 1;
            int currArea = width * currHeight;
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }

    // Using single pass stack approach
    // Time: O(N), space : O(N)
    // Note: There is already an algorithm for this problem
    // ae/stack/LargestRectangleUnderSkyline.java
    // Here we have used stack with 2 passes.
    public int largestRectangleArea2(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int length = heights.length;
        int maxArea = 0;
        for (int i = 0; i < length; i++) {
            while ((stack.size() > 1)
                    && (heights[stack.peek()] >= heights[i])) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            stack.push(i);
        }
        while (stack.size() > 1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = length - stack.peek() - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        LargestRectangleHistogram obj = new LargestRectangleHistogram();
        int maxArea = obj.largestRectangleArea(heights);
        System.out.println("Max area 1 : " + maxArea);
        maxArea = obj.largestRectangleArea2(heights);
        System.out.println("Max area 2 : " + maxArea);
    }
}
