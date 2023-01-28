package com.algo.ae.searching;

/**
 * Search in sorted matrix
 *
 * You're given 2 dimensional array (matrix) of distinct integers and
 * a target integer. Each row in the matrix is sorted, and each column
 * is also sorted; the matrix doesn't necessarily have the same height
 * and width.
 *
 * Write a function that returns an array of the row and column indices
 * of the target integer if it's contained in the matrix, otherwise
 * [-1, -1].
 *
 * Sample input:
 * matrix = [
 * [1, 4, 7, 12, 15, 1000],
 * [2, 5, 19, 31, 32, 1001],
 * [3, 8, 24, 33, 35, 1002],
 * [40, 41, 42, 44, 45, 1003],
 * [99, 100, 103, 106, 128, 1004]
 * ]
 * and target = 44
 *
 * Sample output: [3, 3]
 */
public class SearchInSortedMatrix {
    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        int r = 0, c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) {
                return new int[]{r, c};
            } else if (matrix[r][c] < target) {
                ++r;
            } else {
                --c;
            }
        }
        return new int[] {-1, -1};
    }
}
