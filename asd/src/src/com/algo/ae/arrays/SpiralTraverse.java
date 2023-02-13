package com.algo.ae.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Spiral Traverse
 *
 * Write a function that takes in an nxm two-dimensional array (that can be square-shaped
 * when n == m and returns a one-dimensional array of all the array's elements in spiral order.
 *
 * Sprial order starts at the top left corner of the two-dimensional array, goes to the
 * right and proceeds in a sprial pattern all the way until every element has been visited.
 *
 * Sample Input:
 * array = [
 *  [1,   2,  3, 4],
 *  [12, 13, 14, 5],
 *  [11, 16, 15, 6],
 *  [10,  9,  8, 7]
 *
 *
 * Sample Output:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 * ]
 */
public class SpiralTraverse {
    public static List<Integer> spiralTraverse(int[][] array) {
        if (array.length == 0) {
            return new ArrayList<Integer>();
        }
        int rowNum = array.length;
        int colNum = array[0].length;
        List<Integer> list = new ArrayList<Integer>();
        for (int rowIter = 0, colIter = 0; rowIter < rowNum && colIter < colNum; ++rowIter, ++colIter) {
            for (int col = colIter; col < colNum - 1; ++col) {
                list.add(array[rowIter][col]);
            }

            for (int row = rowIter; row < rowNum - 1; ++row) {
                list.add(array[row][colNum - 1]);
            }

            for (int col = colNum - 1; col > colIter; --col) {
                if (rowIter == rowNum - 1 && col < colNum - 1) {
                    break;
                }
                list.add(array[rowNum - 1][col]);
            }

            for (int row = rowNum - 1; row > rowIter; --row) {
                if (colIter == colNum - 1 && row < rowNum - 1) {
                    break;
                }
                list.add(array[row][colIter]);
            }
            --rowNum;
            --colNum;
        }
        if (array.length == array[0].length && array.length % 2 == 1) {
            list.add(array[array.length/2][array.length/2]);
        }
        return list;
    }

    public static List<Integer> spiralTraverse2(int[][] array) {
        if (array.length == 0) {
            return new ArrayList<Integer>();
        }

        List<Integer> result = new ArrayList<>();
        int startRow = 0, endRow = array.length - 1;
        int startCol = 0, endCol = array[0].length - 1;

        while (startRow <= endRow && startCol <= endCol) {
            for (int col = startCol; col <= endCol; ++col) {
                result.add(array[startRow][col]);
            }

            for (int row = startRow + 1; row <= endRow; ++row) {
                result.add(array[row][endCol]);
            }

            for (int col = endCol - 1; col >= startCol; --col) {
                // Handle the edge case when there's a single row in the middle
                // of the matrix. In this case, we don't want to double-count
                // the values in this row, which we've already counted in the
                //first for loop above.

                if (startRow == endRow) {
                    break;
                }
                result.add(array[endRow][col]);
            }

            for (int row = endRow - 1; row > startRow; --row) {
                // Handle the edge case when there's a single column in the middle
                // of the matrix. In this case, we don't want to double-count the
                // values in this column, which we've already counted in the second
                // for loop above. See test case 9 for an example of this edge case.
                if (startCol == endCol) {
                    break;
                }
                result.add(array[row][startCol]);
            }
            ++startRow;
            --endRow;
            ++startCol;
            --endCol;
        }
        return result;
    }
}
