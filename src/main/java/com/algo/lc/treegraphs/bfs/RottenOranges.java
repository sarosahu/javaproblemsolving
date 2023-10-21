package com.algo.lc.treegraphs.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 *
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 *
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
 * because rotting only happens 4-directionally.
 *
 * Example 3:
 *
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 */
public class RottenOranges {
    static class Cell {
        int row;
        int col;
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        Queue<Cell> queue = new LinkedList<>();
        int freshCount = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                int val = grid[i][j];
                if (val == 2) {
                    queue.offer(new Cell(i, j));
                } else if (val == 1) {
                    ++freshCount;
                }
            }
        }
        if (freshCount == 0) {
            return 0;
        }
        int minuteElapsed = -1;
        int[][] directions = {
                {-1, 0}, {0, 1}, {1, 0}, {0, -1}
        };
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Cell currCell = queue.poll();
                int row = currCell.row;
                int col = currCell.col;
                for (int [] direction : directions) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];
                    if (nextRow < 0 ||
                            nextRow >= grid.length ||
                            nextCol < 0 ||
                            nextCol >= grid[0].length ||
                            grid[nextRow][nextCol] == 0 ||
                            grid[nextRow][nextCol] == 2)
                    {
                        continue;
                    }
                    grid[nextRow][nextCol] = 2;
                    queue.add(new Cell(nextRow, nextCol));
                    --freshCount;
                }
            }
            minuteElapsed += 1;
        }
        return freshCount == 0 ? minuteElapsed : -1;
    }
}
