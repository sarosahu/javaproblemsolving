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

    // TLE
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int R = grid.size();
        int C = grid.get(0).size();
        if (grid.get(0).get(0) == 1 || grid.get(R - 1).get(C - 1) == 1) {
            return 0;
        }
        // Locate the nearest thief
        int[] nextNearestTheifLocation = findNextNearestTheifLocation(grid, 0, 0);
        int minSafeness = nextNearestTheifLocation[0] + nextNearestTheifLocation[1];

        Queue<Cell> maxHeap = new PriorityQueue<>((a, b) -> b.diff - a.diff);
        maxHeap.offer(new Cell(0, 0, minSafeness));

        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;
        while (!maxHeap.isEmpty()) {
            Cell curr = maxHeap.poll();
            minSafeness = Math.min(minSafeness, curr.diff);
            if (curr.x == R - 1 && curr.y == C - 1) {
                break;
            }
            //minSafeness = Math.min(minSafeness, curr.diff);
            for (int [] dir : directions) {
                int adjx = curr.x + dir[0];
                int adjy = curr.y + dir[1];
                if (isValid(adjx, adjy, R, C) && !visited[adjx][adjy]) {
                    int[] nearestThiefLocation = findNextNearestTheifLocation(grid, adjx, adjy);
                    int dist = Math.abs(nearestThiefLocation[0] - adjx) + Math.abs(nearestThiefLocation[1] - adjy);
                    maxHeap.offer(new Cell(adjx, adjy, dist));
                    visited[adjx][adjy] = true;
                }
            }
        }
        return minSafeness;
    }

    private int[]
    findNextNearestTheifLocation(List<List<Integer>> grid, int x, int y) {
        if (grid.get(x).get(y) == 1) {
            return new int[] {x, y};
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean [][] visited = new boolean[grid.size()][grid.get(0).size()];
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : directions) {
                int adjx = dir[0] + curr[0];
                int adjy = dir[1] + curr[1];
                if (isValid(adjx, adjy, grid.size(), grid.get(0).size()) && !visited[adjx][adjy]) {
                    if(grid.get(adjx).get(adjy) == 1) {
                        return new int[] {adjx, adjy};
                    }
                    visited[adjx][adjy] = true;
                    queue.offer(new int[] {adjx, adjy});
                }
            }
        }
        return new int[] {-1, -1};
    }

    public int maximumSafenessFactor2(List<List<Integer>> grid) {
        int R = grid.size();
        int C = grid.get(0).size();
        if (grid.get(0).get(0) == 1 || grid.get(R - 1).get(C - 1) == 1) {
            return 0;
        }
        // Locate the thieves
        List<int[]> thiefLocations = findThiefLocation(grid, R, C);
        int minSafeness = Integer.MAX_VALUE;
        for (int[] loc : thiefLocations) {
            int dist = loc[0] + loc[1];
            minSafeness = Math.min(minSafeness, dist);
        }
        Queue<Cell> maxHeap = new PriorityQueue<>((a, b) -> b.diff - a.diff);
        maxHeap.offer(new Cell(0, 0, Integer.MAX_VALUE));

        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;
        while (!maxHeap.isEmpty()) {
            Cell curr = maxHeap.poll();
            if (curr.x == R - 1 && curr.y == C - 1) {
                break;
            }
            minSafeness = Math.min(minSafeness, curr.diff);
            for (int [] dir : directions) {
                int adjx = curr.x + dir[0];
                int adjy = curr.y + dir[1];
                if (isValid(adjx, adjy, R, C) && !visited[adjx][adjy]) {
                    int currMaxSafeness = Integer.MAX_VALUE;
                    for (int[] loc : thiefLocations) {
                        int dist = Math.abs(adjx - loc[0]) + Math.abs(adjy - loc[1]);
                        currMaxSafeness = Math.min(currMaxSafeness, dist);
                    }
                    maxHeap.offer(new Cell(adjx, adjy, currMaxSafeness));
                    visited[adjx][adjy] = true;
                }
            }
        }
        return minSafeness;
    }

    private boolean isValid(int x, int y, int R, int C) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private List<int[]> findThiefLocation(List<List<Integer>> grid, int R, int C) {
        List<int[]> locations = new ArrayList<>();
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (grid.get(i).get(j) == 1) {
                    locations.add(new int[]{i, j});
                }
            }
        }
        return locations;
    }

    static class Cell {
        int x;
        int y;
        int diff;
        public Cell(int x, int y, int diff) {
            this.x = x;
            this.y = y;
            this.diff = diff;
        }
    }

    public static void main(String[] args) {
        /*int[][] arr = {
                {0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}
        };*/

        int[][] arr = {
                {0, 1, 1}, {1, 1, 1}, {1, 1, 0}
        };
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < arr[i].length; ++j) {
                grid.get(i).add(arr[i][j]);
            }
        }
        FindSafestPathInGrid obj = new FindSafestPathInGrid();
        int maxSafenessFactor = obj.maximumSafenessFactor(grid);
        System.out.println("Maximum safeness factor : " + maxSafenessFactor);
    }
}
