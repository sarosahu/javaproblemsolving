package com.algo.ae.famous;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * Dijkstra's Algorithm
 *
 * You're given an integer start and a list of pair of integers.
 *
 * The list is what's called an adjacency list, and it represents a graph. The number
 * of vertices in the graph is equal to the length of edges, where each index i in
 * edges contains vertex i's outbound edges, in no particular order. Each individual
 * edge is represented by a pair of two numbers, [destination, distance], where the
 * destination is a positive integer denoting the destination vertex and the distance
 * is a positive integer representing the length of the edge (the distance from vertex
 * i to vertex destination). Note that these degree are directed, meaning that you can
 * only travel from a particular vertex to its destination -- not the other way around
 * (unless the destination vertex itself has an outbound edge to the original vertex).
 *
 * Write a function that computes the lengths of the shortest paths between start and all
 * of the other vertices in the graph using Dijkstra's algorithm and returns them in an
 * array. Each index i in the output array should represent the length of the shortest
 * path between start and vertex i. If no path is found from start to vertex i, then
 * output[i] should be -1.
 *
 * Note that the graph represented by edges won't contain any self-loops (vertices that
 * have an outbound edge to themselves) and will only have positively weighted edges
 * (i.e., no negative distances).
 *
 * Sample Input:
 * start = 0,
 * edges = [
 *     [
 *       [1, 7]
 *     ],
 *     [
 *       [2, 6],
 *       [3, 20],
 *       [4, 3]
 *     ],
 *     [
 *       [3, 14]
 *     ],
 *     [
 *       [4, 2]
 *     ],
 *     [],
 *     []
 * ]
 *
 *
 * Sample Output: [0, 7, 13, 27, 10, -1].
 *
 */

public class DijkstrasAlgorithm2 {
    // Time: O((v + e) * log(v)) | Space : O(v) -- where v is no of vertices
    // and e is the number of edges in the input graph.
    public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
        // Write your code here.
        int[] result = new int[edges.length];
        Arrays.fill(result, Integer.MAX_VALUE);

        // Initialize the graph.
        Map<Integer, Node> nodes = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            nodes.put(i, new Node(i, 0, 0));
        }

        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.relDist));

        result[start] = 0;
        Node startNode = nodes.get(start);
        pq.offer(startNode);
        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            // Index, totalDistance and distance from Node with start index.
            int currNodeIdx = currNode.idx;

            int[][] edgesToCurrNode = edges[currNodeIdx];
            for (int[] edge : edgesToCurrNode) {
                if (edge.length == 0) {
                    continue;
                }
                if (currNode.relDist + edge[1] < result[edge[0]]) {
                    result[edge[0]] = currNode.relDist + edge[1];
                    Node edgeNode = nodes.get(edge[0]);
                    edgeNode.relDist = currNode.relDist + edge[1];
                    edgeNode.numEdges = currNode.numEdges + 1;
                    pq.add(edgeNode);
                }
            }
        }
        for (int i = 0; i < result.length; ++i) {
            if (result[i] == Integer.MAX_VALUE) {
                result[i] = -1;
            }
        }
        return result;
    }

    public static class Node {
        public int idx;
        public int relDist;
        public int numEdges;

        public Node(int idx, int relDist, int numEdges) {
            this.idx = idx;
            this.relDist = relDist;
            this.numEdges = numEdges;
        }
    }

    public static void main(String[] args) {
        int start = 0;
        int[][][] edges = {
                {{1, 7}},
                {{2, 6}, {3, 20}, {4, 3}},
                {{3, 14}},
                {{4, 2}},
                {{}},
                {{}},
        };
        DijkstrasAlgorithm obj = new DijkstrasAlgorithm();
        int[] minDistances = obj.dijkstrasAlgorithm(start, edges);
        for (int distance : minDistances) {
            System.out.printf("%d ", distance);
        }
        System.out.println();
    }
}
