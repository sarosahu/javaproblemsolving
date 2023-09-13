package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Same question as in SolveSudoku.java
 *
 * Here I will be using backtracking and it should be pretty simple
 */
public class SolveSudoku2 {
    private int N = 9;
    private boolean isSolved = false;
    private List<List<Integer>> board;
    public List<List<Integer>> solveSudoku(List<List<Integer>> board) {
        this.board = board;
        backtrack(0, 0);
        return this.board;
    }

    public void backtrack(int row, int col) {
        if (this.board.get(row).get(col) == 0) {
            // Iterate from 1 to 9 and check if any of them leads to correct position
            for (int d = 1; d < 10; ++d) {
                if (isValidAtPosition(d, row, col)) {
                    this.board.get(row).set(col, d);
                    placeNextPosition(row, col);
                    if (!isSolved) {
                        undoPrevPosition(row, col);
                    } else {
                        break;
                    }
                }
            }
        } else {
            placeNextPosition(row, col);
        }
    }

    private void undoPrevPosition(int row, int col) {
        this.board.get(row).set(col, 0);
    }

    public boolean isValidAtPosition(int digit, int row, int col) {
        boolean isRowValid = !board.get(row).contains(digit);
        if (!isRowValid) {
            return false;
        }

        boolean isColumnValid = true;
        for (int r = 0; r < board.size(); ++r) {
            if (board.get(r).get(col) == digit) {
                isColumnValid = false;
                break;
            }
        }
        if (!isColumnValid) {
            return false;
        }

        // Check subgrid constraints
        int subgridRowStart = (row / 3) * 3;
        int subgridColStart = (col / 3) * 3;

        for (int rowIdx = 0; rowIdx < 3; ++rowIdx) {
            for (int colIdx = 0; colIdx < 3; ++colIdx) {
                int rowToCheck = subgridRowStart + rowIdx;
                int colToCheck = subgridColStart + colIdx;
                int currValue = board.get(rowToCheck).get(colToCheck);

                if (currValue == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeNextPosition(int row, int col) {
        if (row == N - 1 && col == N - 1) {
            isSolved = true;
        } else {
            if (col == N - 1) {
                backtrack(row + 1, 0);
            } else {
                backtrack(row, col + 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> board = new ArrayList<>();
        int [][]digitsList = {
                {7, 8, 0, 4, 0, 0, 1, 2, 0},
                {6, 0, 0, 0, 7, 5, 0, 0, 9},
                {0, 0, 0, 6, 0, 1, 0, 7, 8},
                {0, 0, 7, 0, 4, 0, 2, 6, 0},
                {0, 0, 1, 0, 5, 0, 9, 3, 0},
                {9, 0, 4, 0, 6, 0, 0, 0, 5},
                {0, 7, 0, 3, 0, 0, 0, 1, 2},
                {1, 2, 0, 0, 0, 7, 4, 0, 0},
                {0, 4, 9, 2, 0, 6, 0, 0, 7}
        };
        int i = 0;
        for (int [] digits : digitsList) {
            board.add(new ArrayList<>());
            for (int d : digits) {
                board.get(i).add(d);
            }
            ++i;
        }

        SolveSudoku2 obj = new SolveSudoku2();
        obj.solveSudoku(board);

        System.out.println("Solved sudoku");
        for (List<Integer> row : board) {
            for (int d : row) {
                System.out.printf("%d, ", d);
            }
            System.out.println();
        }
    }
}
