package com.algo.lc.treegraphs.singlesourceshortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinCostToReachCityWithDiscounts {
    static class Node {
        int id;
        class Edge {
            int targetNodeId;
            int cost;
            Edge(int targetNodeId, int cost) {
                this.targetNodeId = targetNodeId;
                this.cost = cost;
            }
        }
        List<Edge> edges = new ArrayList<>();
        Node(int id) {
            this.id = id;
        }
        public List<Edge> getEdges() {
            return this.edges;
        }
        public void addEdge(int id, int cost) {
            this.edges.add(new Edge(id, cost));
        }
    }

    static class Graph {
        List<Node> nodes = new ArrayList<>();
        boolean isDirected = false;
        Graph(int n, boolean isDirected) {
            this.isDirected = isDirected;
            for (int i = 0; i < n; ++i) {
                nodes.add(new Node(i));
            }
        }

        public void addEdges(int[][] highways) {
            for (int[] highway : highways) {
                Node src = this.nodes.get(highway[0]);
                Node dest = this.nodes.get(highway[1]);
                int cost = highway[2];
                src.addEdge(dest.id, cost);
                if (!isDirected) {
                    dest.addEdge(src.id, cost);
                }
            }
        }
    }

    public static int minimumCost(int n, int[][] highways, int discounts) {
        Graph graph = new Graph(n, false);
        graph.addEdges(highways);
        // PriorityQueue contains :
        // array {cost, targetNode, discountsUsed}
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[][] dist = new int[n][discounts + 1];
        for (int [] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        pq.offer(new int[] {0, 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currCost = curr[0];
            Node currNode = graph.nodes.get(curr[1]);
            if (currNode.id == n - 1) {
                return currCost;
            }
            int discountUsed = curr[2];

            for (Node.Edge edge : currNode.getEdges()) {
                int targetNodeId = edge.targetNodeId;

                int costToTarget = edge.cost;
                int finalCostToTarget = currCost + costToTarget;

                // Case 1: Move to the neighbor without using a discount
                if (dist[targetNodeId][discountUsed] > finalCostToTarget) {
                    dist[targetNodeId][discountUsed] = finalCostToTarget;
                    pq.offer(new int[] {finalCostToTarget, targetNodeId, discountUsed});
                }

                // Case 2: Move to the neighbor using a discount if available
                if (discountUsed + 1 <= discounts &&
                        dist[targetNodeId][discountUsed + 1] > costToTarget/2 + currCost) {
                    dist[targetNodeId][discountUsed + 1] = costToTarget/2 + currCost;
                    pq.offer(new int[] {costToTarget/2 + currCost, targetNodeId, discountUsed + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] highways = {{0,1,4},{2,1,3},{1,4,11},{3,2,3},{3,4,2}};
        int n = 5, discounts = 1;
        int minCost = minimumCost(n, highways, discounts);
        System.out.println("Minimum cost : " + minCost);
    }
}
