package com.algo.ae.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Square of Zeros
 *
 * Write a function that takes in a squre-shaped nxn 2-dimensional array of
 * only 1s and 0s and returns a boolean representing whether the input matrix
 * contains a square whose borders are made of only 0s.
 *
 * Note that 1x1 squre doesn't count as a valid squre for the purpose of this
 * question. In other words, a singular 0 in the input matrix doesn't constitute
 * a square whose borders are made up only 0s; a square of zeros has to be at
 * least 2x2.
 *
 * Sample input:
 * matrix = [
 *  [1, 1, 1, 0, 1, 0],
 *  [0, 0, 0, 0, 0, 1],
 *  [0, 1, 1, 1, 0, 1],
 *  [0, 0, 0, 1, 0, 1],
 *  [0, 1, 1, 1, 0, 1],
 *  [0, 0, 0, 0, 0, 1],
 * ]
 *
 * Sample output: true
 */

public class SquaresOfZeros {
    // BF approach
    // Time : O(N^4), Space : O(N^3)
    public static boolean squareOfZeroesBF(List<List<Integer>> matrix) {
        int lastIdx = matrix.size() - 1;
        Map<String, Boolean> cache = new HashMap<>();
        return hasSquareOfZeros(matrix, 0, 0, lastIdx, lastIdx, cache);
    }

    public static boolean hasSquareOfZeros(
            List<List<Integer>> matrix, int r1, int c1, int r2, int c2,
            Map<String, Boolean> cache) {

        if (r1 >= r2 || c1 >= c2) {
            return false;
        }
        String key = String.valueOf(r1) + '-'
                + String.valueOf(c1) + '-'
                + String.valueOf(r2) + '-'
                + String.valueOf(c2);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        cache.put(key,
                isSquareOfZeroesBF(matrix, r1, c1, r2, c2)
                        || hasSquareOfZeros(matrix, r1 + 1, c1 + 1, r2 - 1, c2 - 1, cache)
                        || hasSquareOfZeros(matrix, r1, c1 + 1, r2 - 1, c2, cache)
                        || hasSquareOfZeros(matrix, r1 + 1, c1, r2, c2 - 1, cache)
                        || hasSquareOfZeros(matrix, r1 + 1, c1 + 1, r2, c2, cache)
                        || hasSquareOfZeros(matrix, r1, c1, r2 - 1, c2 - 1, cache));

        return cache.get(key);
    }

    public static boolean isSquareOfZeroesBF(
            List<List<Integer>> matrix, int r1, int c1, int r2, int c2) {

        for (int row = r1; row <= r2; ++row) {
            if (matrix.get(row).get(c1) != 0 || matrix.get(row).get(c2) != 0) {
                return false;
            }
        }

        for (int col = c1; col <= c2; ++col) {
            if (matrix.get(r1).get(col) != 0 || matrix.get(r2).get(col) != 0) {
                return false;
            }
        }
        return true;
    }

    //Time: O(N^3), Space: O(N^3)
    public static boolean squareOfZeroesE1(List<List<Integer>> matrix) {
        List<List<InfoMatrixItem>> infoMatrix = preComputedNumOfZeroesE1(matrix);
        int lastIdx = matrix.size() - 1;
        Map<String, Boolean> cache = new HashMap<String, Boolean>();

        return hasSquareOfZeroesE1(infoMatrix, 0, 0, lastIdx, lastIdx, cache);
    }

    // r1 is the top row, c1 is the left column
    // r2 is the bottom row, c2 is the right column
    public static boolean hasSquareOfZeroesE1(
            List<List<InfoMatrixItem>> matrix,
            int r1,
            int c1,
            int r2,
            int c2,
            Map<String, Boolean> cache) {

        if (r1 >= r2 || c1 >= c2) {
            return false;
        }

        String key = String.valueOf(r1) + '-' +
                String.valueOf(c1) + '-' +
                String.valueOf(r2) + '-' +
                String.valueOf(c2);

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        cache.put(key,
                isSquareOfZeroesE1(matrix, r1, c1, r2, c2)
                        || hasSquareOfZeroesE1(matrix, r1 + 1, c1 + 1, r2 - 1, c2 - 1, cache)
                        || hasSquareOfZeroesE1(matrix, r1, c1 + 1, r2 - 1, c2, cache)
                        || hasSquareOfZeroesE1(matrix, r1 + 1, c1, r2, c2 - 1, cache)
                        || hasSquareOfZeroesE1(matrix, r1 + 1, c1 + 1, r2, c2, cache)
                        || hasSquareOfZeroesE1(matrix, r1, c1, r2 - 1, c2 - 1, cache));

        return cache.get(key);
    }

    public static boolean isSquareOfZeroesE1(
            List<List<InfoMatrixItem>> infoMatrix, int r1, int c1, int r2, int c2) {

        int squareLength = c2 - c1 + 1;
        boolean hasTopBorder = infoMatrix.get(r1).get(c1).numZeroesRight >= squareLength;
        boolean hasLeftBorder = infoMatrix.get(r1).get(c1).numZeroesBelow >= squareLength;
        boolean hasBottomBorder = infoMatrix.get(r2).get(c1).numZeroesRight >= squareLength;
        boolean hasRightBorder = infoMatrix.get(r1).get(c2).numZeroesBelow >= squareLength;

        return hasTopBorder && hasLeftBorder && hasBottomBorder && hasRightBorder;
    }

    public static List<List<InfoMatrixItem>>
    preComputedNumOfZeroesE1(List<List<Integer>> matrix)
    {
        List<List<InfoMatrixItem>> infoMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.size(); ++i) {
            List<InfoMatrixItem> inner = new ArrayList<InfoMatrixItem>();
            List<Integer> currArray = matrix.get(i);
            for (int j = 0; j < currArray.size(); ++j) {
                int numZeroes = currArray.get(j) == 0 ? 1 : 0;
                inner.add(new InfoMatrixItem(numZeroes, numZeroes));
            }
            infoMatrix.add(inner);
        }

        int lastIdx = matrix.size() - 1;
        for (int row = lastIdx; row >= 0; --row) {
            List<Integer> currRow = matrix.get(row);
            for (int col = lastIdx; col >= 0; --col) {
                if (currRow.get(col) == 1) {
                    continue;
                }

                if (row < lastIdx) {
                    infoMatrix.get(row).get(col).numZeroesBelow +=
                            infoMatrix.get(row + 1).get(col).numZeroesBelow;
                }

                if (col < lastIdx) {
                    infoMatrix.get(row).get(col).numZeroesRight +=
                            infoMatrix.get(row).get(col + 1).numZeroesRight;
                }
            }
        }
        return infoMatrix;
    }

    static class InfoMatrixItem {
        public int numZeroesBelow;
        public int numZeroesRight;

        public InfoMatrixItem(int numZeroesBelow, int numZeroesRight) {
            this.numZeroesBelow = numZeroesBelow;
            this.numZeroesRight = numZeroesRight;
        }
    }

    // O(N^3) time | O(N^2) space
    public static boolean squareOfZeroes(List<List<Integer>> matrix) {
        List<List<InfoMatrixItem>> infoMatrix = preComputedNumOfZeroes(matrix);
        int n = matrix.size();

        for (int topRow = 0; topRow < n; ++topRow) {
            for (int leftCol = 0; leftCol < n; ++leftCol) {
                int squareLength = 2;
                while (squareLength <= n - leftCol && squareLength <= n - topRow) {
                    int bottomRow = topRow + squareLength - 1;
                    int rightCol = leftCol + squareLength - 1;

                    if (isSquareOfZeroes(infoMatrix, topRow, leftCol, bottomRow, rightCol)) {
                        return true;
                    }
                    ++squareLength;
                }
            }
        }
        return false;
    }

    public static boolean isSquareOfZeroes(List<List<InfoMatrixItem>> infoMatrix,
                                           int r1, int c1, int r2, int c2)
    {
        int squareLength = c2 - c1 + 1;
        boolean hasTopBorder = infoMatrix.get(r1).get(c1).numZeroesRight >= squareLength;
        boolean hasLeftBorder = infoMatrix.get(r1).get(c1).numZeroesBelow >= squareLength;
        boolean hasBottomBorder = infoMatrix.get(r2).get(c1).numZeroesRight >= squareLength;
        boolean hasRightBorder = infoMatrix.get(r1).get(c2).numZeroesBelow >= squareLength;

        return hasTopBorder && hasLeftBorder && hasBottomBorder && hasRightBorder;
    }

    public static List<List<InfoMatrixItem>>
    preComputedNumOfZeroes(List<List<Integer>> matrix) {
        List<List<InfoMatrixItem>> infoMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.size(); ++i) {
            List<InfoMatrixItem> inner = new ArrayList<>();
            for (int j = 0; j < matrix.get(i).size(); ++j) {
                int numZeroes = matrix.get(i).get(j) == 0 ? 1 : 0;
                inner.add(new InfoMatrixItem(numZeroes, numZeroes));
            }
            infoMatrix.add(inner);
        }

        int lastIdx = matrix.size() - 1;
        for (int row = lastIdx; row >= 0; --row) {
            for (int col = lastIdx; col >= 0; --col) {
                if (matrix.get(row).get(col) == 1) {
                    continue;
                }
                if (row < lastIdx) {
                    infoMatrix.get(row).get(col).numZeroesBelow +=
                            infoMatrix.get(row + 1).get(col).numZeroesBelow;
                }
                if (col < lastIdx) {
                    infoMatrix.get(row).get(col).numZeroesRight +=
                            infoMatrix.get(row).get(col + 1).numZeroesRight;
                }
            }
        }

        return infoMatrix;
    }
}
