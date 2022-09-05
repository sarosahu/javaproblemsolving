package com.algo.ae.famous;

import java.util.*;

public class DijkstrasAlgorithm {
    public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
        // Write your code here.
        int[] result = new int[edges.length];
        Arrays.fill(result, Integer.MAX_VALUE);

        // Initialize the graph.
        Map<Integer, Node> nodes = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            nodes.put(i, new Node(i, 0, 0));
        }

        Queue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.totalDistance)
        );

        result[start] = 0;
        Node startNode = nodes.get(start);
        pq.offer(startNode);
        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            // Index, totalDistance and distance from Node with start index.
            int currNodeIdx = currNode.idx;
            int currTotalDistance = currNode.totalDistance;
            int currNumEdges = currNode.numEdges;

            int[][] edgesToCurrNode = edges[currNodeIdx];
            for (int[] edge : edgesToCurrNode) {
                if (edge.length == 0) {
                    continue;
                }
                int edgeNodeIdx = edge[0];
                int totalDistanceToEdgeNode = edge[1];
                int totalDistanceFromCurrNodeToEdgeNode =
                        currTotalDistance + totalDistanceToEdgeNode;
                int numEdgesToEdgeNode = currNumEdges + 1;
                int currShortestDistanceToEdgeNode = result[edgeNodeIdx];

                if (totalDistanceFromCurrNodeToEdgeNode < currShortestDistanceToEdgeNode) {
                    result[edgeNodeIdx] = totalDistanceFromCurrNodeToEdgeNode;
                    Node edgeNode = nodes.get(edgeNodeIdx);
                    edgeNode.totalDistance = totalDistanceFromCurrNodeToEdgeNode;
                    edgeNode.numEdges = numEdgesToEdgeNode;
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
        public int totalDistance;
        public int numEdges;

        public Node(int idx, int totalDistance, int numEdges) {
            this.idx = idx;
            this.totalDistance = totalDistance;
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
