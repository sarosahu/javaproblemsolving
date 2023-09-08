package com.algo.lc.treegraphs.dfs;

import java.util.*;
import java.util.HashMap;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent departure
 * and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical
 * order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 * Example 1:
 * MUC ---> LHR ---> SFO
 * ^                  |
 * |                  v
 * JFK               SJC
 *
 * Input: : tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 *
 * Example 2:
 *  SFO
 *  ^  \\
 *  |   \\
 * JFK-->ATL
 * JFK<--ATL
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but
 * it is larger in lexical order.
 *
 * Constraints:
 *
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi and toi consist of uppercase English letters.
 * fromi != toi
 */
public class ReconstructItinerary {
    private int numFlights = 0;
    private List<String> results = null;
    static class Node {
        private String name;
        List<Node> edges = new ArrayList<>();
        Node(String name) {
            this.name = name;
        }
        public void addEdge(Node node) {
            this.edges.add(node);
        }
        public List<Node> getEdges() {
            return this.edges;
        }
        public String getName() {
            return this.name;
        }
    }
    static class Graph {
        Map<String, Node> vertices = new HashMap<>();
        Map<String, boolean[]> visitBitmap = new HashMap<>();
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
        public void initVisitMap(String name, int size) {
            this.visitBitmap.put(name, new boolean[size]);
        }
        public Map<String, Node> getVertices() {
            return this.vertices;
        }
        public Map<String, boolean[]> getVisitBitmap() {
            return this.visitBitmap;
        }
    }

    /**
     *
     * @param tickets
     * @return
     * Complexity
     *
     * Time Complexity: O(∣E∣)^d where ∣E∣ is the number of total flights and d is the maximum number
     * of flights from an airport.
     *
     * It is tricky to estimate the time complexity of the backtracking algorithm, since the algorithm
     * often has an early stopping depending on the input.
     *
     * To calculate a loose upper bound for the time complexity, let us consider it as a combination
     * problem where the goal is to construct a sequence of a specific order, i.e. ∣V1V2...Vn∣|.
     * For each position Vi, we could have d choices, i.e. at each airport one could have at most d possible
     * destinations. Since the length of the sequence is ∣E∣, the total number of combination would be |E|^d
     *
     * In the worst case, our backtracking algorithm would have to enumerate all possible combinations.
     *
     * Space Complexity: O(∣V∣+∣E∣), where ∣V∣ is the number of airports and ∣E∣ is the number of flights.
     *
     * In the algorithm, we use the graph as well as the visit bitmap, which would require the space of ∣V∣+∣E∣.
     *
     * Since we applied recursion in the algorithm, which would incur additional memory consumption in the
     * function call stack. The maximum depth of the recursion would be exactly the number of flights in the
     * input, i.e. ∣E∣.
     *
     * As a result, the total space complexity of the algorithm would be O(∣V∣+2⋅∣E∣)=O(∣V∣+∣E∣)
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        Graph graph = new Graph();
        // Build the graph.
        for (List<String> ticket : tickets) {
            graph.addEdge(ticket.get(0), ticket.get(1));
        }

        // Order the destination airports for each source airport
        for (Map.Entry<String, Node> entry : graph.getVertices().entrySet()) {
            Node sourceNode = entry.getValue();
            Collections.sort(sourceNode.getEdges(), (a, b) -> a.getName().compareTo(b.getName()));
            graph.initVisitMap(sourceNode.getName(), sourceNode.getEdges().size());
        }

        numFlights = tickets.size();
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        path.add("JFK");
        visited.add("JFK");
        backtrack("JFK", graph, path, visited);
        return this.results;
    }

    private boolean backtrack(String sourceNodeName,
                              Graph graph,
                              List<String> path,
                              Set<String> visited) {
        if (path.size() == this.numFlights + 1) {
            results = new ArrayList<>(path);
            return true;
        }
        boolean found = false;
        if (!graph.getVertices().containsKey(sourceNodeName)) {
            return false;
        }

        int i = 0;
        boolean[] visitMap = graph.getVisitBitmap().get(sourceNodeName);
        for (Node edgeNode : graph.vertices.get(sourceNodeName).getEdges()) {
            if (!visitMap[i]) {
                visitMap[i] = true;
                path.add(edgeNode.name);
                visited.add(edgeNode.name);
                found = backtrack(edgeNode.name, graph, path, visited);
                //Remove the above visited edgeNode from path and visited list
                visited.remove(edgeNode.name);
                path.remove(path.size() - 1);
                visitMap[i] = false;
                if (found) {
                    break;
                }
            }
            ++i;
        }
        return found;
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
        ReconstructItinerary obj = new ReconstructItinerary();
        List<String> itinerary = obj.findItinerary(ticketList);
        System.out.println("Itinerary : ");
        for (String s : itinerary) {
            System.out.println(s);
        }
    }
}
