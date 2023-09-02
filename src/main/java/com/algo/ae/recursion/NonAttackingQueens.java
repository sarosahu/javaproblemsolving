package com.algo.ae.recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * Non-Attacking Queens
 *
 * Write a function that takes in a positive integer n and returns the number
 * of non-attacking placements of n queens on an nxn chessboard.
 *
 * A non-attacking placement is one where no queen can attack another queen in
 * a single turn. In other words, it's a placement where no queen can move to
 * the same position as another queen in a single turn.
 *
 * In chess, queens can move any number of squares horizontally, vertically or
 * diagonally in a single turn.
 *
 * +--+--+--+--+
 * |  | Q|  |  |
 * +--+--+--+--+
 * |  |  |  | Q|
 * +--+--+--+--+
 * | Q|  |  |  |
 * +--+--+--+--+
 * |  |  | Q|  |
 * +--+--+--+--+
 *
 * The chessboard above is an example of a non-attacking placement of 4 queens
 * on a 4X4 chessboard. For reference, there are only 2 non-attacking placements
 * of 4 queens on 4X4 chessboard.
 *
 * Sample Input: n = 4
 *
 * Sample output: 2
 */
public class NonAttackingQueens {

    private int size;
    // {
    // TODO: Also check the solution to Eight queens problem from CTCI book
    // }
    // Lower Bound : O(N) time | O(N) space -- N is input number
    public int nonAttackingQueens(int n) {
        // Each index of `columnPlacements` represents a row of the chessboard,
        // and the value at each index is the column (on the relevant row)
        // where a queen is currently placed.
        int[] columnPlacements = new int[n];
        return getNumberOfNonAttackingPlacements(0, columnPlacements, n);
    }

    public int getNumberOfNonAttackingPlacements(int row,
                                                 int[] columnPlacements,
                                                 int boardSize)
    {
        if (row == boardSize) {
            return 1;
        }

        int validPlacements = 0;
        for (int col = 0; col < boardSize; ++col) {
            if (isNonAttackingPlacement(row, col, columnPlacements)) {
                columnPlacements[row] = col;
                validPlacements +=
                        getNumberOfNonAttackingPlacements(row + 1, columnPlacements, boardSize);
            }
        }
        return validPlacements;
    }

    private boolean isNonAttackingPlacement(int row,
                                           int col,
                                           int[] columnPlacements)
    {
        for (int prevRow = 0; prevRow < row; ++prevRow) {
            int columnToCheck = columnPlacements[prevRow];
            boolean sameColumn = (columnToCheck == col);
            boolean onDiagonal = Math.abs(columnToCheck - col) == (row - prevRow);

            if (sameColumn || onDiagonal) {
                return false;
            }
        }
        return true;
    }

    public int nonAttackingQueens2(int n) {
        // Each index of `columnPlacements` represents a row of the chessboard,
        // and the value at each index is the column (on the relevant row)
        // where a queen is currently placed.
        this.size = n;
        //int[] columnPlacements = new int[n];
        return backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    private int backtrack(int row, Set<Integer> diagonals, Set<Integer> antiDiagonals, Set<Integer> cols) {
        // Base case - N queens have been placed
        if (row == size) {
            return 1;
        }

        int solutions = 0;
        for (int col = 0; col < size; col++) {
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;
            // If the queen is not placeable
            if (cols.contains(col) || diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal)) {
                continue;
            }

            // "Add" the queen to the board
            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);

            // Move on to the next row with the updated board state
            solutions += backtrack(row + 1, diagonals, antiDiagonals, cols);

            // "Remove" the queen from the board since we have already
            // explored all valid paths using the above function call
            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
        }

        return solutions;
    }
    public static void main(String[] args) {
        NonAttackingQueens obj = new NonAttackingQueens();
        int numPlacements = obj.nonAttackingQueens(4);
        System.out.println("Number of placements for non attacking queens : " + numPlacements);

        numPlacements = obj.nonAttackingQueens2(4);
        System.out.println("Number of placements for non attacking queens : " + numPlacements);
    }

}
