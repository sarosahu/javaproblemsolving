package com.algo.ae.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Remove Islands
 *
 * You're given 2-dimensional array (a matrix) of potentially unequal height
 * and width containing only 0s and 1s. The matrix represents a 2-toned image,
 * where each 1 represents blank and each 0 represents white. An island is
 * defined as any number of 1s that are horizontally or vertically adjacent
 * (but not diagonally adjacent) and that don't touch the border of the
 * image. In other words, a group of horizontally or vertically adjacent 1s
 * isn't an island if any of those 1s are in first row, last row, first column
 * or last column of input matrix.
 *
 * Note that an island can twist. In other words, it doesn't have to be a
 * straight vertical line or straight horizontal line; it can be L-shaped,
 * for example.
 *
 * You can think of islands as patches of black that don't touch the border
 * of the 2-toned image.
 *
 * Write a function that returns a modified version of the input matrix, where
 * all of the islands are removed. You remove an island by replacing it with 0s.
 *
 * Naturally, you're allowed to mutate the input matrix.
 *
 * Sample Input:
 * matrix =
 * [
 *  [1, 0, 0, 0, 0, 0],
 *  [0, 1, 0, 1, 1, 1],
 *  [0, 0, 1, 0, 1, 0],
 *  [1, 1, 0, 0, 1, 0],
 *  [1, 0, 1, 1, 0, 0],
 *  [1, 0, 0, 0, 0, 1],
 * ]
 *
 * Sample Output:
 * [
 *  [1, 0, 0, 0, 0, 0],
 *  [0, 0, 0, 1, 1, 1],
 *  [0, 0, 0, 0, 1, 0],
 *  [1, 1, 0, 0, 1, 0],
 *  [1, 0, 0, 0, 0, 0],
 *  [1, 0, 0, 0, 0, 1],
 * ]
 */
public class RemoveIslands {

    // Time: O(wh), Space: O(wh) where w and h
    // are the width and height of the input matrix
    public int[][] removeIslands(int[][] matrix) {
        boolean[][] onesConnectedToBorder =
                new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            onesConnectedToBorder[i][matrix[0].length - 1] = false;
        }

        // Find all the 1s that are not islands
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix[row].length; ++col) {
                boolean rowIsBorder = row == 0 || row == matrix.length - 1;
                boolean colIsBorder = col == 0 || col == matrix[row].length - 1;
                boolean isBorder = rowIsBorder || colIsBorder;

                if (!isBorder || matrix[row][col] != 1) {
                    continue;
                }

                findOnesConnectedToBorder(matrix, row, col, onesConnectedToBorder);
            }
        }

        for (int row = 1; row < matrix.length - 1; ++row) {
            for (int col = 1; col < matrix[row].length - 1; ++col) {
                if (onesConnectedToBorder[row][col]) {
                    continue;
                }
                matrix[row][col] = 0;
            }
        }
        return matrix;
    }

    public void findOnesConnectedToBorder(int[][] matrix,
                                          int startRow, int startCol,
                                          boolean[][] onesConnectedToBorder)
    {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {startRow, startCol});

        while (!stack.isEmpty()) {
            int[] currPosition = stack.pop();
            int currRow = currPosition[0];
            int currCol = currPosition[1];

            boolean alreadyVisited = onesConnectedToBorder[currRow][currCol];
            if (alreadyVisited) {
                continue;
            }
            onesConnectedToBorder[currRow][currCol] = true;

            int[][] neighbors = getNeighbors(matrix, currRow, currCol);

            for (int [] neighbor : neighbors) {
                int row = neighbor[0];
                int col = neighbor[1];

                if (matrix[row][col] != 1) {
                    continue;
                }
                stack.push(neighbor);
            }
        }
    }

    public int[][] getNeighbors(int[][] matrix, int row, int col) {
        int nrows = matrix.length;
        int ncols = matrix[row].length;
        List<int[]> temp = new ArrayList<>();

        if (row - 1 >= 0) {
            temp.add(new int[] {row - 1, col});
        }

        if (row + 1 < nrows) {
            temp.add(new int[] {row + 1, col});
        }

        if (col - 1 >= 0) {
            temp.add(new int[] {row, col - 1});
        }

        if (col + 1 < ncols) {
            temp.add(new int[] {row, col + 1});
        }

        int [][] neighbors = new int[temp.size()][2];
        for (int i = 0; i < temp.size(); ++i) {
            neighbors[i] = temp.get(i);
        }

        return neighbors;
    }

    // Time: O(wh), Space: O(wh) where w and h
    // are the width and height of the input matrix
    public int[][] removeIslands2(int[][] matrix) {
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix[row].length; ++col) {
                boolean rowIsBorder = row == 0 || row == matrix.length - 1;
                boolean colIsBorder = col == 0 || col == matrix[row].length - 1;
                boolean isBorder = rowIsBorder || colIsBorder;

                if (!isBorder || matrix[row][col] != 1) {
                    continue;
                }

                changeOnesConnectedToBorderToTwos(matrix, row, col);
            }
        }

        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix[row].length; ++col) {
                int color = matrix[row][col];

                if (color == 1) {
                    matrix[row][col] = 0;
                } else if (color == 2) {
                    matrix[row][col] = 1;
                }
            }
        }

        return matrix;
    }

    public void changeOnesConnectedToBorderToTwos(int[][] matrix,
                                                  int startRow,
                                                  int startCol) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {startRow, startCol});

        while (stack.size() > 0) {
            int[] currPosition = stack.pop();
            int currRow = currPosition[0];
            int currCol = currPosition[1];

            matrix[currRow][currCol] = 2;

            int[][] neighbors = getNeighbors(matrix, currRow, currCol);
            for (int[] neighbor : neighbors) {
                int row = neighbor[0];
                int col = neighbor[1];

                if (matrix[row][col] != 1) {
                    continue;
                }
                stack.push(neighbor);
            }
        }
    }
}
