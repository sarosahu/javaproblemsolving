package com.algo.lc.treegraphs.disjointset;

import com.ds.graph.adjmatrix.Graph;

import java.util.*;

/**
 * Graph Valid Tree
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges
 * where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the
 * graph.
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 * Example 1:
 *          0
 *        / | \
 *      /   |   \
 *     1    2    3
 *     |
 *     4
 *
 * Input n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 *
 * Example 2:
 * 0 --1 ----- 2
 *     | \    /
 *     4   3
 * Input : n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 */
public class IsGraphValidTree {
    /**
     * Approach 1: Graph Theory + Iterative Depth-First Search
     * Using Stack data structure
     * Time : O(N + E), Space : O(N + E)
     * N is number of nodes and E is number of edges.
     */
    public static boolean validTreeDfs1(int n, int[][] edges) {
        //List<List<Integer>> adjList = new ArrayList<>(n);
        Graph graph = new Graph(n, false);
        for (int [] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            for (int neighbour : graph.getAdjacencyMatrix().get(node)) {
                if (parent.get(node) == neighbour) {
                    continue;
                }
                if (parent.containsKey(neighbour)) {
                    // Contains cycle, hence no valid tree
                    return false;
                }
                parent.put(neighbour, node);
                stack.push(neighbour);
            }
        }
        return parent.size() == n;
    }

    /**
     * Approach 1: Graph Theory + Recursive Depth-First Search
     * Time : O(N + E), Space : O(N + E)
     * N is number of nodes and E is number of edges.
     */
    public static boolean validTreeDfs2(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        Graph graph = new Graph(n, false);
        for (int [] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        Set<Integer> seen = new HashSet<>();

        // We return true if no cycles were detected
        // And the entire graph has been reached.
        return dfs2(0, -1, graph, seen) && seen.size() == n;
    }

    private static boolean dfs2(int node,
                                int parent,
                                Graph graph,
                                Set<Integer> seen) {

        seen.add(node);

        for (int neighbour : graph.getAdjacencyMatrix().get(node)) {
            if (parent != neighbour) {
                boolean result = dfs2(neighbour, node, graph, seen);
                if (!result) {
                    return false;
                }
            }
        }
        return true;
    }

    // Time : O(N + E), Space : O(N + E)
    // N is number of nodes and E is number of edges.
    public static boolean validTreeBfs(int n, int[][] edges) {
        Graph graph = new Graph(n, false);
        for (int [] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour : graph.getAdjacencyMatrix().get(node)) {
                if (parent.get(node) == neighbour) {
                    continue;
                }
                if (parent.containsKey(neighbour)) {
                    return false;
                }
                queue.offer(neighbour);
                parent.put(neighbour, node);
            }
        }
        return parent.size() == n;
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
            System.out.println(">>>>>>>>>>>>>>>>>>>");
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
