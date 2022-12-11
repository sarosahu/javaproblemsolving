package com.algo.ae.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * You're given 2-dimensional array(a matrix) of potentially unequal height and width
 * containing 0 s and 1 s. Each 0 represents land, and each 1 represents part of a
 * river. A river consists of any number of 1 s that are either horizontally or
 * vertically adjacent (but not diagonally adjacent). The number of adjacent 1 s
 * forming a river determine its size.
 *
 * Note that a river can twist. In other words, it doesn't have to be a straight
 * vertical line or a straight horizontal line; it can be L-shaped, for example.
 *
 * Write a function that returns an array of the sizes of all rivers represented
 * in the input matrix. The sizes don't need to be in any particular order.
 *
 * Sample input:
 *
 * matrix = [
 *      [1, 0, 0, 1, 0],
 *      [1, 0, 1, 0, 0],
 *      [0, 0, 1, 0, 1],
 *      [1, 0, 1, 0, 1],
 *      [1, 0, 1, 1, 0],
 * ]
 *
 * Sample output:
 * [1, 2, 2, 2, 5] // The numbers could be ordered differently
 */
public class RiverSizes {
    public static List<Integer> riverSizes(int[][] matrix) {
        boolean [][] visited = new boolean[matrix.length][matrix[0].length];
        initialize(visited);
        List<Integer> sizeList = new ArrayList<>();
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (visited[i][j] == false && matrix[i][j] == 1) {
                    sizeList.add(1);
                    dfs(matrix, i, j, visited, sizeList);
                }
            }
        }
        return sizeList;
    }

    public static void dfs(int[][] matrix,
                           int row,
                           int col,
                           boolean [][] visited,
                           List<Integer> sizeList) {
        visited[row][col] = true;

        int[][] locations = {
                {1, 0},
                {0, -1},
                {-1, 0},
                {0, 1}
        };

        for (int[] location : locations) {
            int newRow = row + location[0];
            int newCol = col +location[1];
            if (newRow < 0 || newRow >= matrix.length || newCol < 0 || newCol >= matrix[row].length
                    || matrix[newRow][newCol] == 0 || visited[newRow][newCol] == true) {
                continue;
            }
            sizeList.set(sizeList.size() - 1, sizeList.get(sizeList.size() - 1) + 1);
            dfs(matrix, newRow, newCol, visited, sizeList);
        }
    }

    public static void initialize(boolean[][] array) {
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                array[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {

    }
}
