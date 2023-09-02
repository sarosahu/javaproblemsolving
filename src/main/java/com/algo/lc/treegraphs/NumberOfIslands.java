package com.algo.lc.treegraphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Number of Islands
 *
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands {
    /**
     * Intuition
     *
     * Treat the 2d grid map as an undirected graph and there is an edge between two
     * horizontally or vertically adjacent nodes of value '1'.
     *
     * Algorithm
     *
     * Linear scan the 2d grid map, if a node contains a '1', then it is a root node
     * that triggers a Depth First Search. During DFS, every visited node should be
     * set as '0' to mark as visited node. Count the number of root nodes that trigger
     * DFS, this number would be the number of islands since each DFS starting at some
     * root identifies an island.
     *
     * Complexity Analysis
     *
     * Time complexity : O(M×N) where M is the number of rows and N is the number of columns.
     *
     * Space complexity : worst case O(M×N) in case that the grid map is filled with lands
     * where DFS goes by M×N deep.
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int numIslands = 0;
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[r].length; ++c) {
                char val = grid[r][c];
                if (val == '1') {
                    ++numIslands;
                    dfs(grid, r, c);
                }
            }
        }
        return numIslands;
    }

    private void dfs(char[][]grid, int row, int col) {
        int nrow = grid.length;
        int ncol = grid[0].length;

        if (row < 0 || col < 0 || row >= nrow || col >= ncol || grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';

        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    /**
     * BFS approach
     *
     * Linear scan the 2d grid map, if a node contains a '1', then it is a root
     * node that triggers a Breadth First Search. Put it into a queue and set its
     * value as '0' to mark as visited node. Iteratively search the neighbors of
     * enqueued nodes until the queue becomes empty.
     *
     * Complexity Analysis
     *
     * Time complexity : O(M×N) where M is the number of rows and N is the number of columns.
     *
     * Space complexity : O(min(M,N)) because in worst case where the grid is filled with
     * lands, the size of queue can grow up to min(M,N).
     */
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int numIslands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++numIslands;
                    grid[r][c] = '0'; // Mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.poll();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add((row - 1) * nc + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            neighbors.add((row + 1) * nc + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(row * nc + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col + 1] == '1') {
                            neighbors.add(row * nc + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return numIslands;
    }
}
