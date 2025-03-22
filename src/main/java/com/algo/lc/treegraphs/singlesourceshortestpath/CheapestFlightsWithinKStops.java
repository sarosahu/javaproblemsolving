package com.algo.lc.treegraphs.singlesourceshortestpath;

//import java.util.;
import com.algo.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There are n cities connected by some number of flights. You are given an array
 * flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight
 * from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from
 * src to dst with at most k stops. If there is no such route, return -1.
 *
 * Example 1:
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 *
 * Example 2:
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
 *
 * Example 3:
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph is shown above.
 * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */
public class CheapestFlightsWithinKStops {
    static class Node {
        private final int id;
        // Pair {edgeNodeId, price from this node to edgeNodeId}
        private final List<Pair<Integer, Integer>> edges = new ArrayList<>();
        public Node(int id) {
            this.id = id;
        }
        public void addEdge(int nodeId, int price) {
            this.edges.add(new Pair<>(nodeId, price));
        }
        public List<Pair<Integer, Integer>> getEdges() {
            return this.edges;
        }
        public int getId() {
            return this.id;
        }
    }

    static class Graph {
        private final List<Node> nodes = new ArrayList<>();
        public Graph(int n) {
            for (int i = 0; i < n; ++i) {
                Node node = new Node(i);
                nodes.add(node);
            }
        }
        public void addEdges(int[][] edges) {
            for (int[] edge : edges) {
                Node srcNode = this.nodes.get(edge[0]);
                Node destNode = this.nodes.get(edge[1]);
                srcNode.addEdge(edge[1], edge[2]);
            }
        }
        public List<Node> getNodes() {
            return this.nodes;
        }
    }
    // BFS approach
    public static int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int k) {
        Graph graph = new Graph(n);
        graph.addEdges(flights);
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {src, 0});
        dist[src] = 0;

        int stops = 0;
        while (stops <= k && !queue.isEmpty()) {
            int sz = queue.size();
            // Iterate on current level
            while (sz-- > 0) {
                int[] curr = queue.poll();
                Node currNode = graph.getNodes().get(curr[0]);
                int currPrice = curr[1];

                for (var edge : currNode.getEdges()) {
                    int edgeNodeId = edge.getKey();
                    int edgePrice = edge.getValue();
                    if (currPrice + edgePrice >= dist[edgeNodeId]) {
                        continue;
                    }
                    dist[edgeNodeId] = currPrice + edgePrice;
                    queue.offer(new int[] {edgeNodeId, dist[edgeNodeId]});
                }
            }
            ++stops;
        }
        int minPrice = dist[dst];
        return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
    }

    public static int findCheapestPriceBellmanFord(int n, int[][] flights, int src, int dst, int k) {
        // Distance from source to all other nodes.
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Run only k + 1 times, since we want the shortest distance in K hops
        for (int i = 0; i <= k; ++i) {
            // Create a copy of dist array.
            int[] temp = Arrays.copyOf(dist, n);
            for (int[] flight : flights) {
                if (dist[flight[0]] != Integer.MAX_VALUE) {
                    temp[flight[1]] = Math.min(temp[flight[1]], dist[flight[0]] + flight[2]);
                }
            }
            // Copy the temp array into dist.
            dist = temp;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public static int findCheapestPriceDijkstra(int n, int[][] flights, int src, int dst, int k) {
        Graph graph = new Graph(n);
        graph.addEdges(flights);

        Queue<NodeInfo> pq = new PriorityQueue<>((a, b) -> a.relPrice - b.relPrice);
        pq.offer(new NodeInfo(0, src, 0));
        int [] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {
            NodeInfo curr = pq.poll();
            int currPrice = curr.relPrice;
            int currNodeId = curr.node;
            int nStopsToCurrNode = curr.stops;

            // We already have encountered a path with lower cost and fewer stops
            // or the number of stops exceed the limit (no of stops)
            if (nStopsToCurrNode > stops[currNodeId] || nStopsToCurrNode > k + 1) {
                continue;
            }
            stops[currNodeId] = nStopsToCurrNode;
            if (currNodeId == dst) {
                return currPrice;
            }
            Node currNode = graph.getNodes().get(currNodeId);
            for (Pair<Integer, Integer> edge : currNode.getEdges()) {
                int edgeNodeId = edge.getKey();
                pq.offer(new NodeInfo(currPrice + edge.getValue(), edgeNodeId, nStopsToCurrNode + 1));
            }
        }
        return -1;
    }

    static class NodeInfo {
        int relPrice;
        int node;
        int stops;
        public NodeInfo(int relPrice, int node, int stops) {
            this.relPrice = relPrice;
            this.node = node;
            this.stops = stops;
        }
    }

    public static void main(String[] args) {
        int [][] flights = {
                {0,1,100},
                {1,2,100},
                {2,0,100},
                {1,3,600},
                {2,3,200}
        };

        int cheapestPrice = findCheapestPriceBFS(4, flights, 0, 3, 1);
        System.out.println("Cheapest price(BFS) : " + cheapestPrice);
        System.out.println("================");
        cheapestPrice = findCheapestPriceBellmanFord(4, flights, 0, 3, 1);
        System.out.println("Cheapest price(BellmanFord) : " + cheapestPrice);
        System.out.println("================");
        cheapestPrice = findCheapestPriceDijkstra(4, flights, 0, 3, 1);
        System.out.println("Cheapest price(Dijkstra) : " + cheapestPrice);
    }
}
