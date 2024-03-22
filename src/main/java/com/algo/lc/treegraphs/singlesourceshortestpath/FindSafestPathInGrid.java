package com.algo.lc.treegraphs.singlesourceshortestpath;

import java.util.*;

/**
 * Find safest path in a grid
 *
 */
public class FindSafestPathInGrid {
    int[][] directions = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int m = grid.size();
        int n = grid.get(0).size();

        // No path possible
        if(grid.get(0).get(0) == 1 || grid.get(m-1).get(n-1) == 1) {
            return 0;
        }

        // Pre-populate safety
        int[][] safety = new int[m][n];
        // Queue contains the node (cell row index, cell column index and cell safety value)
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid.get(i).get(j) == 1) {
                    queue.add(new int[]{i,j,0});
                    // since node has thief himself
                    safety[i][j] = 0;
                } else {
                    safety[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // BFS to fill safety for all nodes
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] curr = queue.poll();
                int currx = curr[0];
                int curry = curr[1];
                int currSafety = curr[2];
                for (int[] dir : directions) {
                    int nextx = currx + dir[0];
                    int nexty = curry + dir[1];
                    if (isValid(nextx, nexty, m, n) && currSafety + 1 < safety[nextx][nexty]) {
                        queue.add(new int[]{nextx, nexty, currSafety + 1});
                        safety[nextx][nexty] = currSafety + 1;
                    }
                }
                --size;
            }
        }

        // BFS to find safest path
        // This below queue is maxHeap and the node with max safety
        // is the top most node in this maxHeap.
        Queue<int[]> path = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        path.add(new int[]{0, 0, safety[0][0]});
        boolean [][] seen = new boolean[m][n];
        seen[0][0] = true;

        int minSafety = 0;
        while(!path.isEmpty()){
            int size = path.size();
            while(size > 0) {
                int[] curr = path.poll();
                int currx = curr[0];
                int curry = curr[1];
                int currSafety = curr[2];
                if(currx == m-1 && curry == n-1) {
                    return currSafety;
                }
                for(int[] dir : directions) {
                    int nextx = currx + dir[0];
                    int nexty = curry + dir[1];
                    if(isValid(nextx, nexty, m, n) && !seen[nextx][nexty]) {
                        path.add(new int[]{nextx, nexty, Math.min(currSafety, safety[nextx][nexty])});
                        seen[nextx][nexty] = true;
                    }
                }
                --size;
            }
        }
        return minSafety;
    }

    private boolean isValid(int x, int y, int R, int C) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][][] arr = {
                {
                        {0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}
                },
                {
                        {0, 1, 1}, {1, 1, 1}, {1, 1, 0}
                }
        };

        for (int[][] a : arr) {
            List<List<Integer>> grid = new ArrayList<>();
            for (int i = 0; i < a.length; ++i) {
                grid.add(new ArrayList<>());
                for (int j = 0; j < a[i].length; ++j) {
                    grid.get(i).add(a[i][j]);
                }
            }
            FindSafestPathInGrid obj = new FindSafestPathInGrid();
            int maxSafenessFactor = obj.maximumSafenessFactor(grid);
            System.out.println("Maximum safeness factor : " + maxSafenessFactor);
        }
    }
}
