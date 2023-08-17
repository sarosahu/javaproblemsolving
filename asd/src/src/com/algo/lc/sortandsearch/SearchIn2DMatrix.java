package com.algo.lc.sortandsearch;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer
 * matrix matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * Example 1:
 * [
 * 1,  4,  7,  11,  15
 * 2,  5,  8,  12,  19
 * 3,  6,  9,  16,  22
 * 10, 13, 14, 17,  24
 * 18, 21, 23, 26,  30
 * ],
 * target = 116
 *
 * output = true
 */
public class SearchIn2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {

        int leftX = 0, leftY = 0;
        int rightX = matrix.length - 1, rightY = matrix[matrix.length - 1].length - 1;

        while (leftX <= rightX && leftY <= rightY) {
            if (target < matrix[leftX][leftY] || target > matrix[rightX][rightY]) {
                return false;
            }
            if (matrix[leftX][rightY] < target) {
                leftX++;
            } else if (matrix[leftX][rightY] > target) {
                --rightY;
            } else {
                return true;
            }

            if (target < matrix[rightX][leftY]) {
                --rightX;
            } else if (target > matrix[rightX][leftY]) {
                ++leftY;
            } else {
                return true;
            }
        }
        return false;
    }
    // This is slightly better than above, time complexity remains same
    // Time: O(M + N), Space: O(1)
    // M and N are number of rows and columns respectively.
    public boolean searchMatrix2(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }
}
