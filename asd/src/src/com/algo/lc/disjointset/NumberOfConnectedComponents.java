package com.algo.lc.disjointset;

import com.ds.graph.adjmatrix.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 323. Number of Connected Components in an Undirected Graph
 *
 * You have a graph of n nodes. You are given an integer n and an array edges
 * where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 * E.g. 1
 * 0 -- 1     3
 *      |     |
 *      2     4
 *
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 *
 * E.g. 2:
 *  * 0 -- 1     3
 *  *      |  /  |
 *  *      2     4
 *
 *  Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 1 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai <= bi < n
 * ai != bi
 * There are no repeated edges.
 */
public class NumberOfConnectedComponents {
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int node) {
            if (node == parent[node]) {
                return parent[node];
            }
            parent[node] = find(parent[node]);
            return parent[node];
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootB] < rank[rootA]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA] += 1;
            }
        }

        public boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
    }

    public static int countComponentsDfs(int n, int[][] edges) {
        Set<Integer> seen = new HashSet<>();
        Graph graph = new Graph(n, false);
        for (int [] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        int nc = 0;
        for (int i = 0; i < n; ++i) {
            if (!seen.contains(i)) {
                nc += 1;
                dfs(i, seen, graph);
            }
        }
        return nc;
    }

    private static void dfs(int nodeIdx,
                            Set<Integer> seen,
                            Graph graph) {
        if (seen.contains(nodeIdx)) {
            return;
        }
        seen.add(nodeIdx);
        for (int neighbour : graph.getAdjacencyMatrix().get(nodeIdx)) {
            dfs(neighbour, seen, graph);
        }
    }

    public static int countComponentsBfs(int n, int[][] edges) {
        Set<Integer> seen = new HashSet<>();
        Graph graph = new Graph(n, false);
        for (int [] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int nc = 0;
        for (int i = 0; i < n; ++i) {
            if (seen.contains(i)) {
                continue;
            }
            ++nc;
            seen.add(i);
            queue.offer(i);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int neighbour : graph.getAdjacencyMatrix().get(node)) {
                    if (!seen.contains(neighbour)) {
                        seen.add(neighbour);
                        queue.offer(neighbour);
                    }
                }
            }
        }
        return nc;
    }

    public static int countComponentsDS(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int nc = n;
        for (int [] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            if (!uf.isConnected(node1, node2)) {
                uf.union(node1, node2);
                --nc;
            }
        }
        return nc;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {2, 1},
                {2, 0},
                {2, 4}
        };
        int count = countComponentsDfs(5, edges);
        System.out.println("Number of connected components : " + count);

        count = countComponentsBfs(5, edges);
        System.out.println("Number of connected components : " + count);

        count = countComponentsDS(5, edges);
        System.out.println("Number of connected components : " + count);
    }
}
