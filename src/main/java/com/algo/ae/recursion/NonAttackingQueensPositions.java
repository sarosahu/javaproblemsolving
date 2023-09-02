package com.algo.ae.recursion;

import java.util.*;

public class NonAttackingQueensPositions {
    private int size;
    public List<int[]> nonAttackingQueens(int n) {
        // Each index of `columnPlacements` represents a row of the chessboard,
        // and the value at each index is the column (on the relevant row)
        // where a queen is currently placed.
        int[] columnPlacements = new int[n];
        List<int[]> placements = new ArrayList<>();
        saveNonAttackingPlacements(0, columnPlacements, n, placements);
        return placements;
    }

    public void saveNonAttackingPlacements(int row,
                                           int[] columnPlacements,
                                           int boardSize,
                                           List<int[]> placements)
    {
        if (row == boardSize) {
            placements.add(Arrays.copyOf(columnPlacements, columnPlacements.length));
            return;
        }

        int validPlacements = 0;
        for (int col = 0; col < boardSize; ++col) {
            if (isNonAttackingPlacement(row, col, columnPlacements)) {
                columnPlacements[row] = col;
                saveNonAttackingPlacements(row + 1, columnPlacements, boardSize, placements);
            }
        }
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

    public List<int[]> nonAttackingQueens2(int n) {
        // Each index of `columnPlacements` represents a row of the chessboard,
        // and the value at each index is the column (on the relevant row)
        // where a queen is currently placed.
        this.size = n;
        int[] columnPlacements = new int[n];
        List<int[]> placements = new ArrayList<>();
        backtrack(0, columnPlacements, new HashSet<>(), new HashSet<>(), new HashSet<>(), placements);
        return placements;
    }

    private void backtrack(int row,
                           int[] columnPlacements,
                           Set<Integer> diagonals,
                           Set<Integer> antiDiagonals,
                           Set<Integer> cols,
                           List<int[]> placements) {
        // Base case - N queens have been placed
        if (row == size) {
            placements.add(Arrays.copyOf(columnPlacements, columnPlacements.length));
            return;
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
            columnPlacements[row] = col;
            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);

            // Move on to the next row with the updated board state
            backtrack(row + 1, columnPlacements, diagonals, antiDiagonals, cols, placements);

            // "Remove" the queen from the board since we have already
            // explored all valid paths using the above function call
            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
        }
    }

    public static void main(String[] args) {
        NonAttackingQueensPositions obj = new NonAttackingQueensPositions();
        List<int[]> placements = obj.nonAttackingQueens(4);
        System.out.println("Number of placements for non attacking queens : " + placements.size());
        for (int [] placement : placements) {
            System.out.println(Arrays.toString(placement));
        }

        List<int[]> placements2 = obj.nonAttackingQueens2(4);
        System.out.println("Number of placements for non attacking queens : " + placements.size());
        for (int [] placement : placements2) {
            System.out.println(Arrays.toString(placement));
        }
    }
}
