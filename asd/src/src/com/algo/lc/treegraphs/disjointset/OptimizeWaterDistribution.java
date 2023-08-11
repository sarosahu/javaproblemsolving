package com.algo.lc.treegraphs.disjointset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimizeWaterDistribution {
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; ++i) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        /**
         * Return the group(parent) id that the person x belongs to.
         * While finding that, all the children's parent pointing to the root.
         */
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        /**
         * Joining a and b into same group.
         */
        public boolean union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);
            if (parentA == parentB) {
                return false;
            }

            if (rank[parentA] < rank[parentB]) {
                parent[parentA] = parentB;
            } else if (rank[parentB] < rank[parentA]) {
                parent[parentB] = parentA;
            } else {
                parent[parentB] = parentA;
                rank[parentA] += 1;
            }
            return true;
        }
    }

    /**
     *
     * @param n
     * @param wells
     * @param pipes
     * @return : minimum cost
     *
     * Note: Here we use Krushkal's algorithm using Union-Find data structure(Disjoint set)
     *
     * Let N be the number of houses, and M be the number of pipes from the input.
     *
     * Time Complexity: O((N+M)⋅log(N+M))
     *
     * Space Complexity: O(N+M)
     */
    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        List<int[]> sortedEdges = new ArrayList<>();

        // Add virtual vertex (index 0) along with new edges.
        for (int i = 0; i < wells.length; ++i) {
            sortedEdges.add(new int[]{0, i + 1, wells[i]});
        }

        // Add the existing edges.
        for (int i = 0; i < pipes.length; ++i) {
            int[] edge = pipes[i];
            sortedEdges.add(edge);
        }

        // Sort the edges based on their cost
        Collections.sort(sortedEdges, (a, b) -> a[2] - b[2]);

        // Iterate through the sorted edges using UnionFind
        UnionFind uf = new UnionFind(n + 1);
        int totalCost = 0;
        for (int [] edge : sortedEdges) {
            int house1 = edge[0];
            int house2 = edge[1];
            int cost = edge[2];
            // Determine if we should add the new edge to the final MST
            if (uf.union(house1, house2)) {
                totalCost += cost;
            }
        }
        return totalCost;
    }

    public static void main(String[] args) {
        int n = 3;
        int [] wells = {1, 2, 2};
        int [][] pipes = {
                {1, 2, 1},
                {2, 3, 1}
        };
        int minCost = minCostToSupplyWater(n, wells, pipes);
        System.out.println("Minimum cost : " + minCost);
    }
}
