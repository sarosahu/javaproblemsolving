package com.algo.lc.treegraphs.mst;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class OptimizeWaterDistribution {
    /*
     * Edge is always related to a particular node in a graph.
     * Hence a node's edge is represented as its destination node and
     * the corresponding weight.
     */
    static class Edge {
        private int destId;
        private int weight;
        public Edge(int destId, int weight) {
            this.destId = destId;
            this.weight = weight;
        }
        public int getDestId() {
            return this.destId;
        }
        public int getWeight() {
            return this.weight;
        }
    }

    static class Node {
        private int id;
        private List<Edge> edges = new ArrayList<>();
        public Node(int id) {
            this.id = id;
        }
        public void addEdge(Edge edge) {
            this.edges.add(edge);
        }
        public int getId() {
            return this.id;
        }
        public List<Edge> getEdges() {
            return this.edges;
        }
    }

    static class Graph {
        private List<Node> nodes = new ArrayList<>();
        private boolean directed = false;
        public Graph(int size, boolean directed) {
            this.directed = directed;
            for (int i = 0; i < size; ++i) {
                nodes.add(new Node(i));
            }
        }

        public void addEdge(int sourceId, int destId, int cost) {
            nodes.get(sourceId).addEdge(new Edge(destId, cost));
            if (!directed) {
                nodes.get(destId).addEdge(new Edge(sourceId, cost));
            }
        }

        public List<Node> getNodes() {
            return this.nodes;
        }
    }

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // Min Heap to maintain the order of edges to be visited.
        Queue<Edge> edgesHeap =
                new PriorityQueue<>(n, (a, b) -> a.getWeight() - b.getWeight());

        // Representation of graph
        // Houses start with index 1. We also need a virtual house(node)
        // to start from index 0, hence total nodes count -> n + 1
        Graph graph = new Graph(n + 1, false);

        // For wells we need a virtual house to connect to the
        // acutal houses, this is just to make calculation simple
        // Here we use the house 0 (virtual node) to create respective edges to
        // actual houses.
        for (int i = 0; i < wells.length; ++i) {
            Edge virtualEdge = new Edge(i + 1, wells[i]);
            graph.getNodes().get(0).addEdge(virtualEdge);

            // Add this virtual edge to min heap
            edgesHeap.add(virtualEdge);
        }

        // Construct the bidirectional edges to the graph
        for (int i = 0; i < pipes.length; ++i) {
            int house1 = pipes[i][0];
            int house2 = pipes[i][1];
            int cost = pipes[i][2];
            graph.addEdge(house1, house2, cost);
        }

        // Kick off the exploration from the virtual vertex 0
        Set<Integer> mstSet = new HashSet<>();
        mstSet.add(0);
        int totalCost = 0;
        while (mstSet.size() < n + 1) {
            Edge currEdge = edgesHeap.poll();
            int cost = currEdge.getWeight();
            int nextHouse = currEdge.getDestId();
            if (mstSet.contains(nextHouse)) {
                continue;
            }

            // adding the new vertex into the set.
            mstSet.add(nextHouse);
            totalCost += cost;

            // Expanding the candidates of edge to choose from in the next round
            Node nextNode = graph.getNodes().get(nextHouse);
            for (Edge neighbourEdge : nextNode.getEdges()) {
                int neighbourHouse = neighbourEdge.getDestId();
                if (!mstSet.contains(neighbourHouse)) {
                    edgesHeap.add(neighbourEdge);
                }
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
