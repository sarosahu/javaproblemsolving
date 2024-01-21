package com.algo.lc.treegraphs.singlesourceshortestpath;

import java.util.*;

/**
 * You are given a network of n nodes, labeled from 1 to n. You are also given times,
 * a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the
 * source node, vi is the target node, and wi is the time it takes for a signal to
 * travel from source to target.
 *
 * We will send a signal from a given node k. Return the minimum time it takes for all
 * the n nodes to receive the signal. If it is impossible for all the n nodes to receive
 * the signal, return -1.
 *
 * Example 1:
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 *
 *
 * Example 2:
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 *
 * Example 3:
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 *
 * Constraints:
 *
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class NetworkDelayTime {
    static class EdgeNode {
        Node node;
        int dist;
        EdgeNode(Node node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
    static class Node {
        int id;
        List<EdgeNode> edgeNodes = new ArrayList<>();
        int relDist = Integer.MAX_VALUE;
        Node(int id) {
            this.id = id;
        }
    }

    static class Graph {
        List<Node> nodes = new ArrayList<>();
        Graph(int n) {
            for (int i = 0; i < n; ++i) {
                nodes.add(new Node(i + 1));
            }
        }

        public void addEdge(int src, int dest, int distance) {
            Node srcNode = nodes.get(src);
            Node destNode = nodes.get(dest);
            srcNode.edgeNodes.add(new EdgeNode(destNode, distance));
        }
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        Graph g = new Graph(n);
        for (int [] edge : times) {
            g.addEdge(edge[0] - 1, edge[1] - 1, edge[2]);
        }
        Node startNode = g.nodes.get(k - 1);
        startNode.relDist = 0;
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.relDist));
        pq.add(startNode);
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            for (EdgeNode edgeNode : curr.edgeNodes) {
                Node destNode = edgeNode.node;
                int timeDist = edgeNode.dist;
                if (curr.relDist + timeDist < destNode.relDist) {
                    destNode.relDist = curr.relDist + timeDist;
                    pq.add(destNode);
                }
            }
        }
        int minTime = -1;
        for (Node node : g.nodes) {
            minTime = Math.max(minTime, node.relDist);
        }
        return minTime == Integer.MAX_VALUE || minTime == -1 ? -1 : minTime;
    }

    public static void main(String[] args) {
        int [][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        int minTime = networkDelayTime(times, 4, 2);
        System.out.println("Minimum time : " + minTime);
    }
}
