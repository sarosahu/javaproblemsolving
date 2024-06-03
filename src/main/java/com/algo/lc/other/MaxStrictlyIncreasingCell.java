package com.algo.lc.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MaxStrictlyIncreasingCell {
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Map<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(mat[i][j], x -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        int[][] cellMax = new int[m][n];
        int[] rowMax = new int[m], colMax = new int[n];
        int max = 0;
        for (int val : map.keySet()) {
            for (int[] cell : map.get(val)) {
                int i = cell[0], j = cell[1];
                cellMax[i][j] = 1 + Math.max(rowMax[i], colMax[j]);
                max = Math.max(max, cellMax[i][j]);
            }

            for (int[] cell : map.get(val)) {
                int i = cell[0], j = cell[1];
                rowMax[i] = Math.max(rowMax[i], cellMax[i][j]);
                colMax[j] = Math.max(colMax[j], cellMax[i][j]);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 1, 6},
                {-9, 5, 7}
        };
        MaxStrictlyIncreasingCell obj = new MaxStrictlyIncreasingCell();
        int max = obj.maxIncreasingCells(matrix);
        System.out.println("Maximum count : " + max);
    }
}
