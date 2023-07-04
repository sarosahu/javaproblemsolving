package com.algo.lc.disjointset;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected
 * directly with city b, and city b is connected directly with city c, then city a is connected
 * indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the
 * jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 */
public class NumberOfProvinces {
    static class UnionFind {
        int[] parent;
        int[] rank;
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; ++i) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootY] < rank[rootX]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX] += 1;
            }
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
    public int numberOfProvinces(int[][] isConnected) {
        int n = isConnected.length;
        int numComponents = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                numComponents += 1;
                dfs(i, isConnected, visited);
                // We can do BFS , please uncomment below line and comment above line
                // to make use of BFS
                bfs(i, isConnected, visited);
            }
        }
        return numComponents;
    }

    public int numberOfProvinces2(int[][] isConnected) {
        int n = isConnected.length;
        int numComponents = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                numComponents += 1;
                dfs(i, isConnected, visited);
                // We can do BFS , please uncomment below line and comment above line
                // to make use of BFS
                bfs(i, isConnected, visited);
            }
        }
        return numComponents;
    }

    public int numberOfProvinces3(int[][] isConnected) {
        int n = isConnected.length;
        int numComponents = n;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (isConnected[i][j] == 1 && !uf.isConnected(i, j)) {
                    uf.union(i, j);
                    numComponents--;
                }
            }
        }
        return numComponents;
    }
    private void dfs(int node, int[][] isConnected, boolean[] visited) {
        visited[node] = true;

        for (int i = 0; i < isConnected.length; ++i) {
            if (isConnected[node][i] == 1 && !visited[i]) {
                dfs(i, isConnected, visited);
            }
        }
    }

    private void bfs(int node, int[][] isConnected, boolean[] visited) {
        visited[node] = true;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            int currNode = queue.poll();

            for (int i = 0; i < isConnected.length; ++i) {
                if (isConnected[currNode][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}
