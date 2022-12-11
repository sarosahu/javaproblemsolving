package com.algo.ae.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Largest rectangle under skyline
 *
 * Write a function that takes in an array of positive integers representing
 * the heights of adjacent buildings and returns the area of the largest
 * rectangle that can be created by any number of adjacent buildings, including
 * just one building. Note that all buildings have the same width of 1 unit.
 *
 * For example, given buildings = [2, 1, 2], the area of the largest rectangle
 * that can be created is 3, using all 3 buildings. Since the minimum height of
 * the 3 buildings is 1, you can create a rectangle that has a height of 1 and a
 * width of 3 (the number of buildings). You could also create rectangles of area
 * 2 by using only the first building or the last building, but these clearly
 * wouldn't be the largest rectangles. Similarly, you could create rectangle of
 * area 2 by using the first and second building or the second and third building.
 *
 * To clarify, the width of a created rectangle is the number of buildings used
 * to create the rectangle, and it's height is the height of the smallest building
 * used to create it.
 *
 * Note that if no rectangles can be created, your function should return 0.
 *
 * Sample input: buildings = [1, 3, 3, 2, 4, 1, 5, 3, 2]
 *
 * Sample output: 9
 *
 */
public class LargestRectangleUnderSkyline {
    // Two stack approach
    public static int largestRectangleUnderSkyline(List<Integer> buildings) {
        // Left to right processing to get the left most index of smaller
        // than the number at current index.
        Stack<Integer> minLeftIndexStack = new Stack<>();
        int[] minLeftIndexList = new int[buildings.size()];
        for (int i = 0; i < buildings.size(); ++i) {
            int currHeight = buildings.get(i);

            while (!minLeftIndexStack.isEmpty() &&
                    buildings.get(minLeftIndexStack.peek()) >= currHeight) {
                minLeftIndexStack.pop();
            }
            if (minLeftIndexStack.isEmpty()) {
                minLeftIndexList[i] = 0;
            } else {
                minLeftIndexList[i] = minLeftIndexStack.peek() + 1;
            }
            minLeftIndexStack.push(i);
        }

        // Right to left processing to get the right most index of smaller
        // than the number at current index.
        Stack<Integer> minRightIndexStack = new Stack<>();
        int[] minRightIndexList = new int[buildings.size()];
        for (int i = buildings.size() - 1; i >= 0; --i) {
            int currHeight = buildings.get(i);

            while (!minRightIndexStack.isEmpty() &&
                    buildings.get(minRightIndexStack.peek()) >= currHeight) {
                minRightIndexStack.pop();
            }
            if (minRightIndexStack.isEmpty()) {
                minRightIndexList[i] = buildings.size() - 1;
            } else {
                minRightIndexList[i] = minRightIndexStack.peek() - 1;
            }
            minRightIndexStack.push(i);
        }
        int maxArea = 0;
        for (int i = 0; i < buildings.size(); ++i) {
            int currArea = (minRightIndexList[i] - minLeftIndexList[i] + 1) * buildings.get(i);
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        List<Integer> heights = Arrays.asList(2, 1, 5, 6, 2, 3);
        int maxArea = largestRectangleUnderSkyline(heights);
        System.out.println("Max area of rectangle under skyline: " + maxArea);
    }
}
