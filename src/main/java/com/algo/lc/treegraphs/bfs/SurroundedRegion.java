package com.algo.lc.treegraphs.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 *
 * Connect: A cell is connected to adjacent cells horizontally or vertically.
 * Region: To form a region connect every 'O' cell.
 * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and
 *           none of the region cells are on the edge of the board.
 *
 * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board.
 * You do not need to return anything.
 *
 *
 *
 * Example 1:
 *
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 *
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *
 * Explanation:
 *
 *
 * In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.
 *
 * Example 2:
 *
 * Input: board = [["X"]]
 *
 * Output: [["X"]]
 *
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegion {
    protected int ROWS = 0;
    protected int COLS = 0;
    protected int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.ROWS = board.length;
        this.COLS = board[0].length;

        List<Pair<Integer, Integer>> borders = new LinkedList<>();
        // Step 1 -- Construct the list of boarder cells
        for (int r = 0; r < this.ROWS; ++r) {
            borders.add(new Pair(r, 0));
            borders.add(new Pair(r, this.COLS - 1));
        }
        for (int c = 0; c < this.COLS; ++c) {
            borders.add(new Pair(0, c));
            borders.add(new Pair(this.ROWS - 1, c));
        }

        // Step 2 -- mark the escaped cells
        for (Pair<Integer, Integer> pair : borders) {
            //this.dfs(board, pair.first, pair.second);
            this.bfs(board, pair.first, pair.second);
        }

        // Step 3 -- flip the cells to their correct final states
        for (int r = 0; r < this.ROWS; ++r) {
            for (int c = 0; c < this.COLS; ++c) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                }
                if (board[r][c] == 'E') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    protected void bfs(char[][] board, int r, int c) {
        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(r, c));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.pollFirst();
            int row = pair.first, col = pair.second;
            if (board[row][col] != 'O') continue;

            board[row][col] = 'E';
            System.out.println("row : " + r + ", col : " + c);
            /*if (col < this.COLS - 1) queue.offer(new Pair<>(row, col + 1));
            if (row < this.ROWS - 1) queue.offer(new Pair<>(row + 1, col));
            if (col > 0) queue.offer(new Pair<>(row, col - 1));
            if (row > 0) queue.offer(new Pair<>(row - 1, col));*/
            for (int[] dir : dirs) {
                int nr = row + dir[0];
                int nc = col + dir[1];
                System.out.println("nr : " + nr + ", nc : " + nc);
                if (!isValid(nr, nc, board) || board[nr][nc] != 'O') {
                    System.out.println("Not valid\n");
                    continue;
                }
                queue.offer(new Pair<>(nr, nc));
                System.out.println("Added to queue.\n");
            }
        }
    }

    private boolean isValid(int row, int col, char[][] board) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        };
        System.out.println("Before -->");
        for (char[] a : board) {
            System.out.println(Arrays.toString(a));
        }
        SurroundedRegion obj = new SurroundedRegion();
        obj.solve(board);
        System.out.println("\nAfter -->");
        for (char[] a : board) {
            System.out.println(Arrays.toString(a));
        }
    }
}

class Pair<U, V> {
    public U first;
    public V second;
    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}
