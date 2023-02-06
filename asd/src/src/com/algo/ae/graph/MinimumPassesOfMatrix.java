package com.algo.ae.graph;

import java.util.*;

/**
 * Write a function that takes in an integer matrix of potentially unequal
 * height and width and returns the minimum number of passes required to
 * convert all -ve integers in the matrix to +ve integers.
 *
 * A -ve integer in the matrix can only be converted to a positive integer
 * if one or more of its adjacent elements is +ve. An adjacent element is
 * an element that is to the left, to the right, above, or below the
 * current element in the matrix. Converting a -ve to a +ve simply involves
 * multiplying it by -1.
 *
 * Note that the 0 value is neither +ve nor -ve, meaning that a 0 can't
 * convert an adjacent -ve to a positive.
 *
 * A single pass through the matrix involves converting all the -ve integers
 * that can be converted at a particular point in time. For example,
 * consider the following input matrix:
 *
 * [
 *  [0, -2, -1],
 *  [-5, 2, 0],
 *  [-6, -2, 0]
 * ]
 *
 * After a first pass, only 3 values can be converted to +ves:
 * [
 *  [0, 2, -1],
 *  [5, 2, 0],
 *  [-6, 2, 0]
 * ]
 *
 * After a second pass, the remaining -ve values can all be converted to +ves:
 * [
 * [0, 2, 1],
 * [5, 2, 0],
 * [6, 2, 0]
 * ]
 *
 * Note that the input matrix will always contain at least one element. If
 * the negative integers in the input matrix can't all be converted to +ves,
 * regardless of how many passes are run, your function should return -1.
 *
 * Sample Input:
 *
 * matrix = [
 *  [0, -1, -3, 2, 0],
 *  [1, -2, -5, -1, -3],
 *  [3, 0, 0, -4, -1],
 * ]
 *
 * Sample Output: 3
 */
public class MinimumPassesOfMatrix {
    public static class Location {
        public int rowPos;
        public int colPos;
        public Location(int rowPos, int colPos) {
            this.rowPos = rowPos;
            this.colPos = colPos;
        }
    }

    public static int minimumPassesOfMatrix(int[][] matrix) {
        int passes = convertNegatives(matrix);
        return (!hasNegative(matrix)) ? passes - 1 : -1;
    }

    public static int convertNegatives(int[][] matrix) {
        // We can have BFS to solve this.
        int [][] visited = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < visited.length; ++i) {
            Arrays.fill(visited[i], 0);
        }

        Queue<Location> queue = getAllPositiveLocations(matrix);
        int passes = 0;

        while (!queue.isEmpty()) {
            int currQueueSize = queue.size();

            while (currQueueSize > 0) {
                Location currLocation = queue.poll();
                List<Location> adjacentLocations = getAdjacentLocations(currLocation, matrix);

                for (Location loc : adjacentLocations) {
                    int row = loc.rowPos;
                    int col = loc.colPos;
                    int val = matrix[row][col];
                    if (val < 0) {
                        matrix[row][col] *= -1;
                        //nextPassQueue.add(new Location(row, col));
                        queue.add(loc);
                    }
                }
                --currQueueSize;
            }
            passes += 1;
        }

        return passes;
    }

    public static Queue<Location>
    getAllPositiveLocations(int[][] matrix) {
        Queue<Location> positiveLocations = new LinkedList<>();
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix[row].length; ++col) {
                int value = matrix[row][col];
                if (value > 0) {
                    positiveLocations.add(new Location(row, col));
                }
            }
        }
        return positiveLocations;
    }

    public static List<Location>
    getAdjacentLocations(Location location, int[][] matrix) {
        List<Location> adjacentLocations = new ArrayList<>();
        int currRow = location.rowPos;
        int currCol = location.colPos;
        if (currRow > 0) {
            adjacentLocations.add(new Location(currRow - 1, currCol));
        }
        if (currRow < matrix.length - 1) {
            adjacentLocations.add(new Location(currRow + 1, currCol));
        }
        if (currCol > 0) {
            adjacentLocations.add(new Location(currRow, currCol - 1));
        }
        if (currCol < matrix[currRow].length - 1) {
            adjacentLocations.add(new Location(currRow, currCol + 1));
        }

        return adjacentLocations;
    }

    public static boolean hasNegative(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
