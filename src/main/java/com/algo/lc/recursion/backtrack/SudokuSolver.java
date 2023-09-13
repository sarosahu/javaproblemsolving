package com.algo.lc.recursion.backtrack;

import java.util.Arrays;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 *
 * It is guaranteed that the input board has only one solution.
 */
public class SudokuSolver {
    // box size
    private int n = 3;
    // row size
    private int N = n * n;

    int [][] rows = new int[N][N + 1];
    int [][] columns = new int[N][N + 1];
    int [][] boxes = new int[N][N + 1];

    char [][] board;

    boolean isSolved = false;

    public void solveSudoku(char[][] board) {
        this.board = board;

        // init rows, columns and boxes
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }
        backtrack(0, 0);
    }

    public void backtrack(int row, int col) {
        // If cell is empty
        if (board[row][col] == '.') {
            // Iterate over all numbers from 1 to 9
            for (int d = 1; d < 10; ++d) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    if (!isSolved) {
                        removeNumber(d, row, col);
                    } else {
                        break;
                    }
                }
            }
        } else {
            placeNextNumbers(row, col);
        }
    }

    public boolean couldPlace(int d, int row, int col) {
        // Check if cell(row, col) could be placed with a number d
        int idx = (row / n) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    public void placeNumber(int d, int row, int col) {
        /*
         * Place a number d in (row, col) cell
         */
        int idx = (row / n ) * n + col / n;

        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char)(d + '0');
    }

    public void removeNumber(int d, int row, int col) {
        /*
         * Place a number d in (row, col) cell
         */
        int idx = (row / n ) * n + col / n;

        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }

    public void placeNextNumbers(int row, int col) {
        if (col == N - 1 && row == N - 1) {
            isSolved = true;
        } else {
            if (col == N - 1) {
                backtrack(row + 1, 0);
            } else {
                backtrack(row, col + 1);
            }
        }
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
        SudokuSolver obj = new SudokuSolver();
        obj.solveSudoku(board);
        obj.print2DArray();
    }
}
