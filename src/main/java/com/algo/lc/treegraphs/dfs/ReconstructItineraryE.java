package com.algo.lc.treegraphs.dfs;

import java.util.*;

public class ReconstructItineraryE {
    private LinkedList<String> result = null;
    static class Node {
        private String name;
        private LinkedList<Node> edges = new LinkedList<>();
        Node(String name) {
            this.name = name;
        }
        public void addEdge(Node node) {
            this.edges.add(node);
        }
        public LinkedList<Node> getEdges() {
            return this.edges;
        }
        public String getName() {
            return this.name;
        }
    }
    static class Graph {
        Map<String, Node> vertices = new HashMap<>();

        public void addEdge(String from, String to) {
            if (!vertices.containsKey(from)) {
                vertices.put(from, new Node(from));
            }
            Node fromNode = vertices.get(from);
            if (!vertices.containsKey(to)) {
                vertices.put(to, new Node(to));
            }
            Node toNode = vertices.get(to);
            fromNode.addEdge(toNode);
        }

        public Map<String, Node> getVertices() {
            return this.vertices;
        }
    }

    /**
     *
     * @param tickets
     * @return
     *
     * Time Complexity: O(∣E∣log(∣E∣/∣V∣)) where ∣E∣ is the number of edges (flights) in the input.
     *
     * As one can see from the above algorithm, during the DFS process, we would traverse each edge once.
     * Therefore, the complexity of the DFS function would be ∣E∣.
     *
     * However, before the DFS, we need to sort the outgoing edges for each vertex. And this, unfortunately,
     * dominates the overall complexity.
     *
     * It is though tricky to estimate the complexity of sorting, which depends on the structure of the input graph.
     *
     * In the worst case where the graph is not balanced, i.e. the connections are concentered in a single airport.
     * Imagine the graph is of star shape, in this case, the JFK airport would assume half of the flights (since we
     * still need the return flight). As a result, the sorting operation on this airport would be exceptionally
     * expensive, i.e. NlogN, where N=∣E∣/2. And this would be the final complexity as well, since it dominates
     * the rest of the calculation.
     *
     *
     * Space Complexity: O(∣V∣+∣E∣), where ∣V∣ is the number of airports and ∣E∣ is the number of flights.
     *
     * In the algorithm, we use the graph, which would require the space of ∣V∣+∣E∣.
     *
     * Since we applied recursion in the algorithm, which would incur additional memory consumption in the
     * function call stack. The maximum depth of the recursion would be exactly the number of flights in the input,
     * i.e. ∣E∣.
     *
     * As a result, the total space complexity of the algorithm would be O(∣V∣+2⋅∣E∣)=O(∣V∣+∣E∣).
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        Graph graph = new Graph();
        // Step 1: Build the graph.
        for (List<String> ticket : tickets) {
            String origin = ticket.get(0);
            String dest = ticket.get(1);
            graph.addEdge(origin, dest);
        }

        // Step 2: Order the destination airports for each source airport
        graph.getVertices().forEach((key, value) ->
                Collections.sort(value.getEdges(), (a, b) -> a.getName().compareTo(b.getName())));

        this.result = new LinkedList<String>();

        // Step 3: post-order DFS
        this.dfs("JFK", graph);

        return this.result;
    }

    protected void dfs(String origin, Graph graph) {
        // Visit all the outgoing edges first.
        if (graph.getVertices().containsKey(origin)) {
            Node originNode = graph.getVertices().get(origin);
            LinkedList<Node> edgesOfOrigin = originNode.getEdges();
            while(!edgesOfOrigin.isEmpty()) {
                // while we visit the edge, we trim it off from graph
                String dest = edgesOfOrigin.pollFirst().getName();
                dfs(dest, graph);
            }
        }
        // Add the airport to the head of the itinerary
        this.result.offerFirst(origin);
    }

    public static void main(String[] args) {
        String[][] tickets = {
                {"JFK","SFO"},
                {"JFK","ATL"},
                {"SFO","ATL"},
                {"ATL","JFK"},
                {"ATL","SFO"}
        };
        List<List<String>> ticketList = new ArrayList<>();
        for (String [] ticket : tickets) {
            ticketList.add(Arrays.asList(ticket));
        }
        ReconstructItineraryE obj = new ReconstructItineraryE();
        List<String> itinerary = obj.findItinerary(ticketList);
        System.out.println("Itinerary : ");
        for (String s : itinerary) {
            System.out.println(s);
        }
    }
}
