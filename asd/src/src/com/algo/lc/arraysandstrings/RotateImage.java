package com.algo.lc.arraysandstrings;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * 1 2 3    7 4 1
 * 4 5 6 => 8 5 2
 * 7 8 9    9 6 3
 *
 *
 * Example 2:
 *
 *
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * Constraints:
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {
    /**
     * Complexity Analysis
     *
     * Let M be the number of cells in the matrix.
     *
     * Time complexity : O(M), as each cell is getting read once and written once.
     * Space complexity : O(1) because we do not use any other additional data structures.
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n/2; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }

    /**
     * Let M be the number of cells in the grid.
     *
     * Time complexity : O(M). We perform two steps; transposing the matrix, and then reversing each row.
     * Transposing the matrix has a cost of O(M) because we're moving the value of each cell once.
     * Reversing each row also has a cost of O(M), because again we're moving the value of each cell once.
     *
     * Space complexity : O(1) because we do not use any other additional data structures.
     */
    public static void rotate2(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }

    public static void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }

    public static void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n/2; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }

    public static void printTwoDArray(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            printOneDArray(matrix[i]);
        }
        System.out.println();
    }
    public static void printOneDArray(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.printf("%d, ", arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("Original matrix : ");
        printTwoDArray(matrix);

        rotate(matrix);
        System.out.println("After rotating matrix with rotate(): ");
        printTwoDArray(matrix);

        rotate2(matrix);
        System.out.println("After rotating matrix once again with rotate2(): ");
        printTwoDArray(matrix);
    }
}
