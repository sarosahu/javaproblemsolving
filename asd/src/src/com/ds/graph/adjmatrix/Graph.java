package com.ds.graph.adjmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    // Number of vertices
    private int nVertices;

    // Adjacency matrix
    private ArrayList<ArrayList<Integer> > adj;

    // Is directed or undirected ?
    boolean directed;

    public Graph(int nVertices, boolean directed) {
        this.directed = directed;
        this.nVertices = nVertices;
        adj = new ArrayList<>(this.nVertices);

        for (int i = 0; i < nVertices; ++i) {
            adj.add(new ArrayList<>());
        }
    }

    // FUnction to add an edge to the graph
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        // Considering bidirectional edge (undirected graph)
        if (!this.directed) {
            adj.get(v).add(u);
        }
    }

    // Function to perform BFS on the graph
    public void bfs(int start) {
        boolean[] visited = new boolean[this.nVertices];
        Arrays.fill(visited, false);
        List<Integer> queue = new ArrayList<>();
        queue.add(start);

        // Set the source as visited
        visited[start] = true;

        while (!queue.isEmpty()) {
            int vis = queue.get(0);

            // Print the current node
            System.out.printf("%d -- ", vis);

            // Remove the first item from List q.
            queue.remove(0);

            ArrayList<Integer> currVisitedNode = adj.get(vis);
            // For every adjacent vertex to the current vertex
            for (Integer adjNodeIdx : currVisitedNode) {
                if (!visited[adjNodeIdx]) {
                    // Push the adjacent node to the queue
                    queue.add(adjNodeIdx);

                    // Mark the node visited
                    visited[adjNodeIdx] = true;
                }
            }
        }
    }

    private void dfsUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.printf("%d -- ", v);
        for (Integer adjNodeIdx : adj.get(v)) {
            if (!visited[adjNodeIdx]) {
                dfsUtil(adjNodeIdx, visited);
            }
        }
    }

    public void dfs(int startIdx) {
        boolean visited[] = new boolean[this.getnVertices()];
        dfsUtil(startIdx, visited);
    }

    public void print(ArrayList<ArrayList<Integer> > adj) {
        for (int i = 0; i < adj.size(); ++i) {
            System.out.printf("%d --> ", i);
            for (int j = 0; j < adj.get(i).size(); ++j) {
                System.out.printf("[%d] ", adj.get(i).get(j));
            }
            System.out.println();
        }
    }

    public ArrayList<ArrayList<Integer> > getAdjacencyMatrix() {
        return this.adj;
    }

    public int getnVertices() {
        return this.nVertices;
    }
}
