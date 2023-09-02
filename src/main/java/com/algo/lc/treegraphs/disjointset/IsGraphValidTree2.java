package com.algo.lc.treegraphs.disjointset;

import com.ds.graph.adjmatrix.Graph;

import java.util.*;

/**
 * Same problem as in IsGraphValidTree.java
 * This file has optimized solutions.
 */
public class IsGraphValidTree2 {
    /**
     * Iterative Depth first search.
     */
    public static boolean validTreeDfs1(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        Graph graph = new Graph(n, false);
        for (int [] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        Stack<Integer> stack = new Stack<>();
        // Push first node (index 0)
        stack.push(0);
        Set<Integer> seen = new HashSet<>();
        seen.add(0);

        while (!stack.isEmpty()) {
            int nodeIdx = stack.pop();
            for (int neighbour : graph.getAdjacencyMatrix().get(nodeIdx)) {
                if (seen.contains(neighbour)) {
                    continue;
                }
                seen.add(neighbour);
                stack.push(neighbour);
            }
        }
        return seen.size() == n;
    }

    /**
     * Recrusive DFS
     */
    public static boolean validTreeDfs2(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        Graph graph = new Graph(n, false);
        for (int[] edge: edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        Set<Integer> seen = new HashSet<>();
        dfs(0, graph, seen);

        return seen.size() == n;
    }

    private static void dfs(int nodeIdx, Graph graph, Set<Integer> seen) {
        if (seen.contains(nodeIdx)) {
            return;
        }
        seen.add(nodeIdx);
        for (int neighbour : graph.getAdjacencyMatrix().get(nodeIdx)) {
            dfs(neighbour, graph, seen);
        }
    }

    /**
     * Iterative breadth frist search
     */
    public static boolean validTreeBfs(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        Graph graph = new Graph(n, false);
        for (int [] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        Queue<Integer> queue = new LinkedList<>();
        // Insert the first node index (0)
        queue.offer(0);
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        while (!queue.isEmpty()) {
            int nodeIdx = queue.poll();
            for (int neighbour : graph.getAdjacencyMatrix().get(nodeIdx)) {
                if (seen.contains(neighbour)) {
                    continue;
                }
                queue.offer(neighbour);
                seen.add(neighbour);
            }
        }
        return seen.size() == n;
    }

    public static void main(String[] args) {
        System.out.println("Two test cases with 5 nodes each.");
        int[][][] edgesList = {
                {
                        {0, 1},
                        {0, 2},
                        {0, 3},
                        {1, 4}
                },
                {
                        {0, 1},
                        {1, 2},
                        {2, 3},
                        {1, 3},
                        {1, 4}
                }
        };

        for (int[][] edges : edgesList) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>");
            boolean isValid = validTreeDfs1(5, edges);
            if (isValid) {
                System.out.println("Graph is valid tree");
            } else {
                System.out.println("Graph is not valid tree");
            }
            isValid = validTreeDfs2(5, edges);
            if (isValid) {
                System.out.println("Graph is valid tree");
            } else {
                System.out.println("Graph is not valid tree");
            }

            isValid = validTreeBfs(5, edges);
            if (isValid) {
                System.out.println("Graph is valid tree");
            } else {
                System.out.println("Graph is not valid tree");
            }
            System.out.println("<<<<<<<<<<<<<<<<<<<<<");
        }
    }
}
