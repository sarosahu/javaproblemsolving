package com.algo.lc.treegraphs.singlesourceshortestpath;

import java.util.*;

/**
 * 1102. Path With Maximum Minimum Value

 * Given an m x n integer matrix grid, return the maximum score of a path
 * starting at (0, 0) and ending at (m - 1, n - 1) moving in the 4 cardinal
 * directions.
 *
 * The score of a path is the minimum value in that path.
 *
 * For example, the score of the path 8 → 4 → 5 → 9 is 4.
 *
 * Example 1:
 * Input: grid = [[5,4,5],[1,2,6],[7,4,6]]
 * Output: 4
 *
 * Input: grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
 * Output: 2
 *
 * Input: grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
 * Output: 3
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * 0 <= grid[i][j] <= 10^9
 */
public class PathWithMaximumMinValue {
    int[][] directions = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    // { MaxHeap approach with BFS
    // Time: O(m*n*log(m*n), space : O(m * n)
    public int maximumMinimumPath(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        boolean[][] visited = new boolean [rowLen][colLen];
        Queue<int[]> queue = new PriorityQueue<>(
                (a, b) -> grid[b[0]][b[1]] - grid[a[0]][a[1]]
        );

        queue.offer(new int[]{0, 0});
        int minVal = Integer.MAX_VALUE;
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int currVal = grid[x][y];
            minVal = Math.min(minVal, currVal);
            if (x == rowLen - 1 && y == colLen - 1) {
                break;
            }
            visited[x][y] = true;
            for (int[] dir : directions) {
                int adjx = x + dir[0];
                int adjy = y + dir[1];
                if (!isValid(adjx, adjy, rowLen, colLen) || visited[adjx][adjy]) {
                    continue;
                }

                queue.offer(new int[]{adjx, adjy});
                visited[adjx][adjy] = true;
            }
        }
        return minVal;
    }
    // }

    // { Binary search with BFS
    // Note: I looked at this solution, but I still don't understand
    // it completely.
    // TODO: Understand it
    public int maximumMinimumPath2(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        int left = 0, right = Math.min(grid[0][0], grid[R - 1][C - 1]);

        while (left < right) {
            // Get the middle value between left and right boundaries
            int mid = (left + right + 1)/2;
            // Check if we can find a path of value equals middle and
            // cut the search space by half.
            if (pathExists(grid, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target
        // value, that is the maximum value of a path.
        return left;
    }
    private boolean pathExists(int[][]grid, int val) {
        int R = grid.length, C = grid[0].length;

        // Maintain the visited status of each cell. Initialize the status of
        // all the cells as false (unvisited).
        boolean[][] visited = new boolean[R][C];

        // Put the starting cell grid[0][0] in the deque and mark it as visited
        Queue<int[]> deque = new LinkedList<>();
        deque.offer(new int[]{0, 0});
        visited[0][0] = true;

        // While we still have cells of value larger than or equal to val.
        while (!deque.isEmpty()) {
            int[] curr = deque.poll();
            int currRow = curr[0];
            int currCol = curr[1];
            // If the current cell is bottom-right cell, return true
            if (currRow == R - 1 && currCol == C - 1) {
                return true;
            }

            for (int [] dir : directions) {
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];
                // Check if the current cell has any unvisited neighbors with value
                // larger than or equal to val.
                if(isValid(newRow, newCol, R, C) &&
                        !visited[newRow][newCol] &&
                        grid[newRow][newCol] >= val) {
                    // If so, we put this neighbor to the deque and mark it as visited
                    visited[newRow][newCol] = true;
                    deque.offer(new int[] {newRow, newCol});
                }
            }
        }
        return false;
    }
    // }

    // { Union-Find approach
    // Time complexity: O(n⋅m⋅log(n⋅m)), Space: O(n * m)
    public int maximumMinimumPathUF(int[][] grid) {
        int R = grid.length, C = grid[0].length;

        // Sort all the cells by their values.
        List<int[]> vals = new ArrayList<>();

        // Initialize the root of all the cells and mark
        // all the cells as false (unvisited).
        boolean[][] visited = new boolean[R][C];

        // Root of all the R * C cells
        UnionFind uf = new UnionFind(R, C);

        // Initialize the root of all the cells.
        for (int row = 0; row < R; ++row) {
            for (int col = 0; col < C; ++col) {
                vals.add(new int[]{row, col});
            }
        }

        // Sort all the cells by values from large to small.
        Collections.sort(vals, (a, b) -> grid[b[0]][b[1]] - grid[a[0]][a[1]]);

        // Iteration over the sorted cells.
        for (int [] curr : vals) {
            int currRow = curr[0], currCol = curr[1];
            int currPos = currRow * C + currCol;

            // Mark the current cell as visited.
            visited[currRow][currCol] = true;
            for (int[] dir : directions) {
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];
                int newPos = newRow * C + newCol;

                // Check if the current cell has any unvisited neighbors
                // with the value larger than or equal to val.
                if (isValid(newRow, newCol, R, C) &&
                        visited[newRow][newCol]) {
                    uf.union(currPos, newPos);
                }
            }

            // Check if the top-left cell is connected with bottom-right cell.
            if (uf.find(0) == uf.find(R * C  - 1)) {
                // If so return the value of the current cell.
                return grid[currRow][currCol];
            }
        }
        return -1;
    }

    static class UnionFind {
        private int[] root;
        private int[] rank;
        public UnionFind(int R, int C) {
            rank = new int[R * C];
            root = new int[R * C];
            for (int i = 0; i < root.length; ++i) {
                root[i] = i;
            }
        }

        // Find the root of x.
        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        // Union the roots of x and y.
        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootY] > rank[rootX]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
            }
        }
    }
    // }

    public boolean isValid(int x, int y, int rowLen, int colLen) {
        return x >= 0 && x < rowLen && y >= 0 && y < colLen;
    }

    public static void main(String[] args) {
        int[][] score = {
                {5, 4, 5},
                {1, 2, 6},
                {7, 4, 6}
        };
        PathWithMaximumMinValue obj = new PathWithMaximumMinValue();
        int maxVal = obj.maximumMinimumPath(score);
        System.out.println("Maximum value : " + maxVal);
    }
}
