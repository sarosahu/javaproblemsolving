package com.algo.lc.treegraphs.singlesourceshortestpath;

import java.util.*;

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
    // BFS approach
    public static int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int [] flight : flights) {
            adj.computeIfAbsent(flight[0], value -> new ArrayList<>())
                    .add(new int[] {flight[1], flight[2]});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {src, 0});
        int stops = 0;
        while (stops <= k && !queue.isEmpty()) {
            int sz = queue.size();
            while (sz > 0) {
                int[] curr = queue.poll();
                int node = curr[0];
                int distance = curr[1];

                if (!adj.containsKey(node)) {
                    continue;
                }
                // Loop over neighbors of popped node.
                for (int[] e : adj.get(node)) {
                    int neighbour = e[0];
                    int price = e[1];
                    if (price + distance >= dist[neighbour]) {
                        continue;
                    }
                    dist[neighbour] = price + distance;
                    queue.offer(new int[] {neighbour, dist[neighbour]});
                }
                sz -= 1;
            }
            ++stops;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
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

    public static void main(String[] args) {
        int [][] flights = {
                {0,1,100},
                {1,2,100},
                {2,0,100},
                {1,3,600},
                {2,3,200}
        };

        int cheapestPrice = findCheapestPriceBFS(4, flights, 0, 3, 1);
        System.out.println("Cheapest price : " + cheapestPrice);
        cheapestPrice = findCheapestPriceBellmanFord(4, flights, 0, 3, 1);
        System.out.println("Cheapest price : " + cheapestPrice);
    }
}
