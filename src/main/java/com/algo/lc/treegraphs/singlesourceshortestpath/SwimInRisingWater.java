package com.algo.lc.treegraphs.singlesourceshortestpath;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 778. Swim in Rising Water
 *
 * You are given an n x n integer matrix grid where each value grid[i][j]
 * represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t.
 * You can swim from a square to another 4-directionally adjacent square if and
 * only if the elevation of both squares individually are at most t. You can
 * swim infinite distances in zero time. Of course, you must stay within the
 * boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1)
 * if you start at the top left square (0, 0).
 *
 * E.g. 1
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 *
 * E.g. 2
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n2
 * Each value grid[i][j] is unique.
 */
public class SwimInRisingWater {
    int [][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public int swimInWater(int[][] grid) {
        Queue<Cell> minHeap = new PriorityQueue<>((a, b) -> a.depth - b.depth);
        int rowLen = grid.length, colLen = grid[0].length;
        minHeap.add(new Cell(0, 0, grid[0][0]));
        int maxSoFar = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        while (!minHeap.isEmpty()) {
            Cell curr = minHeap.poll();
            visited[curr.x][curr.y] = true;
            maxSoFar = Math.max(maxSoFar, grid[curr.x][curr.y]);
            if (curr.x == rowLen - 1 && curr.y == colLen - 1) {
                break;
            }
            for (int[] dir : directions) {
                int adjx = curr.x + dir[0];
                int adjy = curr.y + dir[1];
                if (adjx < 0 || adjx >= rowLen || adjy < 0 || adjy >= colLen
                        || visited[adjx][adjy]) {
                    continue;
                }
                minHeap.add(new Cell(adjx, adjy, grid[adjx][adjy]));
            }
        }
        return maxSoFar;
    }

    static class Cell {
        int x;
        int y;
        int depth;
        public Cell(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
