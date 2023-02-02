package com.algo.ae.dp;

/**
 * Number of Ways To Traverse Graph
 *
 * You're given 2 positive integers representing the width and height of
 * grid-shaped, rectangular graph. Write a function that returns the
 * number of ways to reach the bottom right corner of the graph when
 * starting at the top left corner. Each move you take must either
 * go down or right. In other words, you can never move up or left
 * in the graph. For example, given the graph illustrated below, with
 * width = 2 and height = 3, there are 3 ways to reach the bottom
 * right corner when start at the top left corner.
 *  _ _
 * |_|_|
 * |_|_|
 * |_|_|
 *
 * 1. down, down, right
 * 2. right, down, down
 * 3. down, right, down
 *
 * You may assume that width * height >= 2. In other words, the graph
 * will never be 1X1 grid.
 *
 * Sample input: Width = 4, height = 3
 * Sample output: 10
 */
public class NumberOfWaysToTraverseGraph {

    // Time: O(2^(n + m) , space: O(n + m)
    // n is width, m is height
    public int numberOfWaysToTraverse(int width, int height) {
        if (width == 1 || height == 1) {
            return 1;
        }
        return numberOfWaysToTraverse(width - 1, height) +
                numberOfWaysToTraverse(width, height - 1);
    }

    // Time: O(n*m) , space: O(n*m)
    // n is width, m is height
    public int numberOfWaysToTraverseGraph(int width, int height) {
        int [][] grid = new int[height][width];
        for (int i = 0; i < grid.length; ++i) {
            grid[i][0] = 1;
        }
        for (int i = 1; i < grid[0].length; ++i) {
            grid[0][i] = 1;
        }

        for (int i = 1; i < grid.length; ++i) {
            for (int j = 1; j < grid[i].length; ++j) {
                grid[i][j] = grid[i-1][j] + grid[i][j-1];
            }
        }
        return grid[height-1][width-1];
    }
}
