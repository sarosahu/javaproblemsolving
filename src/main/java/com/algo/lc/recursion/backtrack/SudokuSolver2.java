package com.algo.lc.recursion.backtrack;

import java.util.Arrays;

/**
 * This question is same as in SudokuSolver.java
 */
public class SudokuSolver2 {
    // box size
    int n = 3;
    // row size
    int N = n * n;
    char[][] board;
    boolean isSolved = false;

    public void solveSudoku(char[][] board) {
        this.board = board;
        backtrack(0, 0);
    }

    public void backtrack(int row, int col) {
        // if the cell is empty
        if (board[row][col] == '.') {
            // iterate over all numbers from 1 to 9
            for (int d = 1; d < 10; d++) {
                if (isValidAtPosition(d, row, col)) {
                    board[row][col] = (char)(d + '0');
                    placeNextNumbers(row, col);
                    // if sudoku is solved, there is no need to backtrack
                    // since the single unique solution is promised
                    if (!isSolved) {
                        board[row][col] = '.';
                    } else {
                        break;
                    }
                }
            }
        }
        else {
            placeNextNumbers(row, col);
        }
    }

    /*
    Call backtrack function in recursion
    to continue to place numbers
    till the moment we have a solution
    */
    public void placeNextNumbers(int row, int col) {
        // if we're in the last cell
        // that means we have the solution
        if ((col == N - 1) && (row == N - 1)) {
            isSolved = true;
        }
        else {
            // if we're in the end of the row
            // go to the next row
            if (col == N - 1) {
                backtrack(row + 1, 0);
            } else {
                // go to the next column
                backtrack(row, col + 1);
            }
        }
    }

    public boolean isValidAtPosition(int d, int row, int col) {
        char digit = (char) (d + '0');
        boolean isRowValid = true;
        for (int c = 0; c < board[row].length; ++c) {
            if (board[row][c] == digit) {
                isRowValid = false;
                break;
            }
        }
        if (!isRowValid) {
            return false;
        }

        boolean isColumnValid = true;
        for (int r = 0; r < board.length; ++r) {
            if (board[r][col] == digit) {
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
                int currValue = board[rowToCheck][colToCheck];

                if (currValue == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print2DArray() {
        /*
        System.out.println("ROWS:");
        for (int i = 0; i < rows.length; ++i) {
            System.out.println(Arrays.toString(rows[i]));
        }
        System.out.println();
        System.out.println("Columns:");
        for (int i = 0; i < columns.length; ++i) {
            System.out.println(Arrays.toString(columns[i]));
        }
        System.out.println();
        System.out.println("Boxes:");
        for (int i = 0; i < boxes.length; ++i) {
            System.out.println(Arrays.toString(boxes[i]));
        }
        */
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Solved board:");
        for (int i = 0; i < board.length; ++i) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    public static void main(String[] args) {
        char [][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Unsolved board:");
        for (int i = 0; i < board.length; ++i) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        SudokuSolver2 obj = new SudokuSolver2();
        obj.solveSudoku(board);
        obj.print2DArray();
    }
}
