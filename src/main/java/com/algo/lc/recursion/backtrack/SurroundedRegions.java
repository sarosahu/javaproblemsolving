package com.algo.lc.recursion.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SurroundedRegions {
    private int R = 0;
    private int C = 0;
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public void solve(char[][] board) {
        for (int i = 1; i < board.length - 1; ++i) {
            for (int j = 1; j < board[i].length - 1; ++j) {
                if (board[i][j] == '0') {
                    backtrack(i, j, board);
                }
            }
        }
    }

    public void solve2(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.R = board.length;
        this.C = board[0].length;

        List<int[]> borders = new LinkedList<>();
        for (int r = 0; r < this.R; ++r) {
            //if (board[r][0] == '0') {
                borders.add(new int[] {r, 0});
            //}
            //if (board[r][this.C - 1] == '0') {
                borders.add(new int[] {r, this.C - 1});
            //}
        }
        for (int c = 0; c < this.C; ++c) {
            //if (board[0][c] == '0') {
                borders.add(new int[] {0, c});
            //}
            //if (board[this.R - 1][c] == '0') {
                borders.add(new int[] {this.R - 1, c});
            //}
        }

        for (int[] border : borders) {
            this.dfs(board, border[0], border[1]);
        }

        // Flip the cells to their correct final states
        for (int i = 0; i < this.R; ++i) {
            for (int j = 0; j < this.C; ++j) {
                if (board[i][j] == '0') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'E') {
                    board[i][j] = '0';
                }
            }
        }
    }

    private boolean backtrack(int row, int col, char[][] board) {
        board[row][col] = 'X';
        for (int [] dir : dirs) {
            int i = row + dir[0];
            int j = col + dir[1];
            if (!isValid(i, j, board) || board[i][j] == 'X') {
                continue;
            }
            if (i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) {
                board[row][col] = '0';
                return false;
            }
            if (!backtrack(i, j, board)) {
                board[row][col] = '0';
                return false;
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char[][] board) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
            return true;
        }
        return false;
    }

    private void dfs(char[][] board, int r, int c) {
        if (board[r][c] != '0') {
            return;
        }
        board[r][c] = 'E';
        for (int [] dir : dirs) {
            int i = r + dir[0];
            int j = c + dir[1];
            if (isValid(i, j, board)) {
                this.dfs(board, i, j);
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions obj = new SurroundedRegions();
        char [][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', '0', '0', 'X'},
                {'X', 'X', '0', 'X'},
                {'X', '0', '0', 'X'},
        };
        System.out.println("Before");
        for (char [] row : board) {
            System.out.println(Arrays.toString(row));
        }
        obj.solve2(board);
        System.out.println("After");
        for (char [] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        char [][] board2 = {
                {'X', 'X', 'X', 'X'},
                {'X', '0', '0', 'X'},
                {'X', 'X', '0', 'X'},
                {'X', '0', '0', 'X'},
        };
        System.out.println("Before");
        for (char [] row : board2) {
            System.out.println(Arrays.toString(row));
        }
        obj.solve(board2);
        System.out.println("After");
        for (char [] row : board2) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        char [][] board3 = {
                {'X', 'X', 'X', 'X'},
                {'X', '0', '0', 'X'},
                {'X', 'X', '0', 'X'},
                {'X', '0', 'X', 'X'},
        };
        System.out.println("Before");
        for (char [] row : board3) {
            System.out.println(Arrays.toString(row));
        }
        obj.solve(board3);
        System.out.println("After");
        for (char [] row : board3) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        char [][] board4 = {
                {'X', 'X', 'X', 'X'},
                {'X', '0', '0', 'X'},
                {'X', 'X', '0', 'X'},
                {'X', '0', 'X', 'X'},
        };
        System.out.println("Before");
        for (char [] row : board4) {
            System.out.println(Arrays.toString(row));
        }
        obj.solve2(board4);
        System.out.println("After");
        for (char [] row : board4) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
