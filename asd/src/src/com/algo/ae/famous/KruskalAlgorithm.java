package com.algo.ae.famous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal's Algorithm
 *
 * You're given a list of edges representing a weighted, undirected graph with at
 * least one node.
 *
 * The given list what's called an adjacency list, and it represents a graph. The
 * number of vertices in the graph is equal to the length of edges, where each
 * index i in edges contains vertex i's siblings, in no particular order. Each of
 * these siblings is an array of length 2, with the first value denoting the index
 * in the list that this vertex is connected to, and the second value denoting the
 * weight of the edge. Note that this graph is undirected, meaning that if a vertex
 * appears in the edge list of another vertex, then the inverse will also be true
 * (along with same weight).
 *
 * Write a function implementing Kruskal's Algorithm to return a new edges array
 * that represents a minimum spanning tree. A minimum spanning tree is a tree
 * containing all of the vertices of the original graph and a subset of the edges.
 * These edges should connect all of the vertices with the minimum total edge
 * weight and without generating any cycles.
 *
 * If the graph is not connected, your function should return the minimum spanning
 * forest(i.e. all of the nodes should be able to reach the same nodes as they
 * could in the initial edge list).
 *
 * Note that the graph represented by edges won't contain any self-loops(vertices
 * that have an outbound edge to themselves) and will only have positively
 * weighted edges (i.e. no negative distances).
 *
 * Sample Input:
 * edges = [
 * [[1, 3], [2, 5]],
 * [[0, 3], [2, 10], [3, 12]],
 * [[0, 5], [1, 10]],
 * [[1, 12]]
 * ]
 *
 * Sample Output:
 * [
 *  [[1, 3], [2, 5]],
 *  [[0, 3], [3, 12]],
 *  [[0, 5]],
 *  [[1, 12]]
 * ]
 */

public class KruskalAlgorithm {
    public int[][][] kruskalsAlgorithm(int[][][] edges) {
        List<List<Integer>> sortedEdges = new ArrayList<>();
        for (int sourceIdx = 0; sourceIdx < edges.length; ++sourceIdx) {
            for (int[] edge : edges[sourceIdx]) {
                if (edge[0] > sourceIdx) {
                    sortedEdges.add(Arrays.asList(sourceIdx, edge[0], edge[1]));
                }
            }
        }

        Collections.sort(sortedEdges, (edge1, edge2) -> edge1.get(2) - edge2.get(2));

        int[] parents = new int[edges.length];
        int[] ranks = new int[edges.length];
        List<ArrayList<int[]>> mst = new ArrayList<ArrayList<int[]>>();

        for (int i = 0; i < edges.length; ++i) {
            parents[i] = i;
            ranks[i] = 0;
            mst.add(i, new ArrayList<int[]>());
        }

        for (List<Integer> edge : sortedEdges) {
            int vertex1Root = find(edge.get(0), parents);
            int vertex2Root = find(edge.get(1), parents);

            if (vertex1Root != vertex2Root) {
                mst.get(edge.get(0)).add(new int[] {edge.get(1), edge.get(2)});
                mst.get(edge.get(1)).add(new int[] {edge.get(0), edge.get(2)});
                union(vertex1Root, vertex2Root, parents, ranks);
            }
        }

        int[][][] arrayMst = new int[edges.length][][];

        for (int i = 0; i < mst.size(); ++i) {
            arrayMst[i] = new int[mst.get(i).size()][];
            for (int j = 0; j < mst.get(i).size(); ++j) {
                arrayMst[i][j] = mst.get(i).get(j);
            }
        }
        return arrayMst;
    }

    private int find(int vertex, int[] parents) {
        if (vertex != parents[vertex]) {
            parents[vertex] = find(parents[vertex], parents);
        }
        return parents[vertex];
    }

    private void union(int vertex1Root, int vertex2Root, int[] parents, int [] ranks) {
        if (ranks[vertex1Root] < ranks[vertex2Root]) {
            parents[vertex1Root] = vertex2Root;
        } else if (ranks[vertex2Root] < ranks[vertex1Root]) {
            parents[vertex2Root] = vertex1Root;
        } else {
            parents[vertex2Root] = vertex1Root;
            ranks[vertex1Root]++;
        }
    }
}
