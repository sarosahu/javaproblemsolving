package com.algo.lc.treegraphs.singlesourceshortestpath;

import java.util.*;

public class PathWithMinimumEffort {
    int [][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    // Dijkstra's algorithm (My approach)
    public int minimumEffortPath2(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        Queue<Cell> queue = new PriorityQueue<>(
                Comparator.comparing(a -> a.diff)
        );
        boolean[][] visited = new boolean[row][col];
        queue.add(new Cell(0, 0, 0));
        int maxSoFar = 0;
        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            //maxSoFar = Math.max(curr.diff, maxSoFar);
            visited[curr.x][curr.y] = true;
            maxSoFar = Math.max(maxSoFar, curr.diff);
            if (curr.x == row - 1 && curr.y == col - 1) {
                return maxSoFar;
            }
            for (int[] direction : directions) {
                int adjx = curr.x + direction[0];
                int adjy = curr.y + direction[1];
                if (!isValidCell(adjx, adjy, row, col) || visited[adjx][adjy]) {
                    continue;
                }
                int currVal = heights[curr.x][curr.y];
                int adjVal = heights[adjx][adjy];
                int currDiff = Math.abs(adjVal - currVal);
                queue.add(new Cell(adjx, adjy, currDiff));
            }
        }
        return maxSoFar;
    }

    // Dijkstra's algorithm
    public int minimumEffortPath(int[][] heights) {
        int rowLen = heights.length;
        int colLen = heights[0].length;
        int[][] diffMatrix = new int[rowLen][colLen];
        for (int[] eachRow : diffMatrix) {
            Arrays.fill(eachRow, Integer.MAX_VALUE);
        }
        diffMatrix[0][0] = 0;
        Queue<Cell> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.diff)
        );
        boolean[][] visited = new boolean[rowLen][colLen];
        minHeap.add(new Cell(0, 0, diffMatrix[0][0]));

        while (!minHeap.isEmpty()) {
            Cell thisCell = minHeap.poll();

            visited[thisCell.x][thisCell.y] = true;
            if (thisCell.x == rowLen - 1 && thisCell.y == colLen - 1) {
                return thisCell.diff;
            }
            for (int[] direction : directions) {
                int adjx = thisCell.x + direction[0];
                int adjy = thisCell.y + direction[1];
                if (!isValidCell(adjx, adjy, rowLen, colLen) || visited[adjx][adjy]) {
                    continue;
                }
                int currVal = heights[thisCell.x][thisCell.y];
                int adjVal = heights[adjx][adjy];
                int currDiff = Math.abs(adjVal - currVal);
                int newDiff = Math.max(currDiff, thisCell.diff);
                if (newDiff < diffMatrix[adjx][adjy]) {
                    diffMatrix[adjx][adjy] = newDiff;
                    minHeap.add(new Cell(adjx, adjy, newDiff));
                }
            }
        }
        return diffMatrix[rowLen - 1][colLen - 1];
    }

    public int minimumEffortPathBfs(int[][] heights) {
        int left = 0;
        int right = 1000000;
        int result = right;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (canReachDestination(heights, mid)) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // BFS to check if we can reach destination with max absolute diff of K
    boolean canReachDestination(int[][] heights, int k) {
        int row = heights.length;
        int col = heights[0].length;
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        Deque<Cell> queue = new ArrayDeque<>();
        queue.addLast(new Cell(0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Cell curr = queue.removeFirst();
            //visited[curr.x][curr.y] = true;
            if (curr.x == row - 1 && curr.y == col - 1) {
                return true;
            }
            for (int[] dir : directions) {
                int adjx = curr.x + dir[0];
                int adjy = curr.y + dir[1];
                if (!isValidCell(adjx, adjy, row, col) || visited[adjx][adjy]) {
                    continue;
                }
                int currVal = heights[curr.x][curr.y];
                int adjVal = heights[adjx][adjy];
                int currDiff = Math.abs(currVal - adjVal);
                if (currDiff <= k) {
                    visited[adjx][adjy] = true;
                    queue.addLast(new Cell(adjx, adjy));
                }
            }
        }
        return false;
    }

    private boolean isValidCell(int x, int y, int rowLen, int colLen) {
        return x >= 0 && y >= 0 && x < rowLen && y < colLen;
    }

    static class Cell {
        int x;
        int y;
        Integer diff;
        public Cell(int x, int y, Integer diff) {
            this.x = x;
            this.y = y;
            this.diff = diff;
        }

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };
        PathWithMinimumEffort obj = new PathWithMinimumEffort();
        int minEffort = obj.minimumEffortPath(heights);
        System.out.println("Minimum effort : " + minEffort);

        minEffort = obj.minimumEffortPath2(heights);
        System.out.println("Minimum effort : " + minEffort);

        minEffort = obj.minimumEffortPathBfs(heights);
        System.out.println("Minimum effort : " + minEffort);
    }
}
