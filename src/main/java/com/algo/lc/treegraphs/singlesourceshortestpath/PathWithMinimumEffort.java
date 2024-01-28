package com.algo.lc.treegraphs.singlesourceshortestpath;

public class PathWithMinimumEffort {
    int [][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    // Dijkstra's algorithm
    public int minimumEffortPath(int[][] heights) {
        return -1;
    }

    private boolean isValidCell(int x, int y, int rowLen, int colLen) {
        return x >= 0 && y >= 0 && x < rowLen && y < colLen;
    }

    static class CellWithDiff {
        int x;
        int y;
        Integer diff;
        public CellWithDiff(int x, int y, Integer diff) {
            this.x = x;
            this.y = y;
            this.diff = diff;
        }
    }
}
