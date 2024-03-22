package com.algo.lc.treegraphs;

import java.util.*;

/**
 * 684. Redundant Connection
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with
 * one additional edge added. The added edge has two different vertices chosen from 1 to n,
 * and was not an edge that already existed. The graph is represented as an array edges of
 * length n where edges[i] = [ai, bi] indicates that there is an edge between nodes
 * ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes.
 * If there are multiple answers, return the answer that occurs last in the input.
 *
 * Ex 1:
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 *
 * Ex 2:
 * Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 * Output: [1,4]
 *
 * Constraints:
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 *
 * There are no repeated edges.
 * The given graph is connected.
 */
public class RedundantConnection {
    Set<Integer> seen = new HashSet();

    public int[] findRedundantConnection(int[][] edges) {
        Graph graph = new Graph(edges);
        for (int[] edge : edges) {
            seen.clear();
            Node srcNode = graph.nodes.get(edge[0]);
            Node destNode = graph.nodes.get(edge[1]);

            if (dfs(graph, srcNode, destNode)) {
                return edge;
            }
            srcNode.addEdge(destNode);
            destNode.addEdge(srcNode);
        }
        return new int[0];
    }

    private boolean dfs(Graph graph, Node src, Node dest) {
        seen.add(src.id);
        if (src.id == dest.id) {
            return true;
        }
        for (Node edgeNode : src.getEdges()) {
            if (!seen.contains(edgeNode.id)) {
                if (dfs(graph, edgeNode, dest)) {
                    return true;
                }
            }
        }
        return false;
    }

    static class Node {
        int id;
        List<Node> edges = new ArrayList<>();
        public Node(int id) {
            this.id = id;
        }
        public List<Node> getEdges() {
            return this.edges;
        }
        public void addEdge(Node node) {
            this.edges.add(node);
        }
    }

    static class Graph {
        Map<Integer, Node> nodes = new HashMap<>();
        public Graph(int [][] edges) {
            for (int[] edge : edges) {
                int src = edge[0];
                int dest = edge[1];
                if (!nodes.containsKey(src)) {
                    nodes.put(src, new Node(src));
                }
                if (!nodes.containsKey(dest)) {
                    nodes.put(dest, new Node(dest));
                }
            }
        }
    }

    // { Union Find approach
    public int[] findRedundantConnection2Optimized(int[][] edges) {
        Set<Integer> nodes = new HashSet<>();
        for (int [] edge : edges) {
            nodes.add(edge[0]);
            nodes.add(edge[1]);
        }
        UnionFind uf = new UnionFind(nodes.size());
        for (int[] edge : edges) {
            if (!uf.union(edge[0] - 1, edge[1] - 1)) {
                return edge;
            }
        }
        return new int[0];
    }
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

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootY] < rank[rootX]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX] += 1;
            }
            return true;
        }
    }
    // } Union-Find approach

    public static void main(String[] args) {
        RedundantConnection obj = new RedundantConnection();
        int[][] edges = {
            {1, 2}, {1, 3}, {2, 3}
        };
        int [] edge = obj.findRedundantConnection(edges);
        System.out.println("Redundant edge : " );
        for (int nodeId : edge) {
            System.out.printf("%d, ", nodeId);
        }
        System.out.println();

        edge = obj.findRedundantConnection2Optimized(edges);
        System.out.println("Redundant edge : " );
        for (int nodeId : edge) {
            System.out.printf("%d, ", nodeId);
        }
        System.out.println();
    }
}
