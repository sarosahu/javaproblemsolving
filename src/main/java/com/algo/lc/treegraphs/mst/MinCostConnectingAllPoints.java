package com.algo.lc.treegraphs.mst;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Min Cost to Connect All Points
 *
 * You are given an array points representing integer coordinates of some points
 * on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance
 * between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if
 * there is exactly one simple path between any two points.
 *
 * Example 1:
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 *
 * Example 2:
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 *
 * Constraints:
 *
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * All pairs (xi, yi) are distinct.
 */
public class MinCostConnectingAllPoints {
    static class Edge {
        int point1;
        int point2;
        public int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }
    /**
     * Kruskal's algorithm
     *
     */
    static class UnionFind {
        int root[];
        int rank[];

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; ++i) {
                root[i] = i;
                rank[i] = 1;
            }
        }
        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootY] > rank[rootX]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
    public static int minCostConnectPointsKruskal(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        int size = points.length;
        Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
        UnionFind uf = new UnionFind(size);

        for (int i = 0; i < size; ++i) {
            int[] coordinate1 = points[i];
            for (int j = i + 1; j < size; ++j) {
                int [] coordinate2 = points[j];
                int distance = Math.abs(coordinate1[0] - coordinate2[0]) +
                                Math.abs(coordinate1[1] - coordinate2[1]);
                Edge edge = new Edge(i, j, distance);
                pq.add(edge);
            }
        }
        int result = 0;
        int count = size - 1;
        while (!pq.isEmpty() && count > 0) {
            Edge currEdge = pq.poll();
            if (!uf.connected(currEdge.point1, currEdge.point2)) {
                uf.union(currEdge.point1, currEdge.point2);
                result += currEdge.cost;
                --count;
            }
        }
        return result;
    }

    /**
     * Prim's algorithm
     *
     */

    public static int minCostConnectPointsPrims(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        int size = points.length;
        Queue<Edge> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        boolean[] visited = new boolean[size];
        int result = 0;
        int count = size - 1;

        // Add all edges from point[0] vertex
        int [] coordinate1 = points[0];
        for (int i = 1; i < size; ++i) {
            int [] coordinate2 = points[i];
            int cost = Math.abs(coordinate1[0] - coordinate2[0]) +
                        Math.abs(coordinate1[1] - coordinate2[1]);
            Edge edge = new Edge(0, i, cost);
            pq.add(edge);
        }

        visited[0] = true;

        while (!pq.isEmpty() && count > 0) {
            Edge currEdge = pq.poll();
            //int point1 = currEdge.point1;
            int point2 = currEdge.point2;
            int cost = currEdge.cost;

            if (!visited[point2]) {
                result += cost;
                visited[point2] = true;
                int [] source = points[point2];
                for (int j = 0; j < size; ++j) {
                    int[] dest = points[j];
                    if (!visited[j]) {
                        int distance = Math.abs(source[0] - dest[0]) +
                                Math.abs(source[1] - dest[1]);
                        pq.add(new Edge(point2, j, distance));
                    }
                }
                --count;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        int minCost = minCostConnectPointsPrims(points);
        System.out.println("Minimum cost to connect all points (using Prim's algo) : " + minCost);
        minCost = minCostConnectPointsKruskal(points);
        System.out.println("Minimum cost to connect all points (using Kruskal's algo) : " + minCost);
    }
}
