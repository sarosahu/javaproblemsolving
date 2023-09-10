package com.algo.lc.treegraphs.dfs;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceLeadsToDestination {
    enum State {Unvisited, Visiting, Visited};

    static class Node {
        private int id;
        private List<Node> edges = new ArrayList<>();

        State state = State.Unvisited;
        public Node(int id) {
            this.id = id;
        }
        public int getId() {
            return this.id;
        }
        public List<Node> getEdges() {
            return this.edges;
        }
        public void addEdge(Node node) {
            this.edges.add(node);
        }
        public State getState() {
            return this.state;
        }
        public void setState(State state) {
            this.state = state;
        }
    }

    static class Graph {
        private List<Node> nodes = new ArrayList<>();
        public Graph(int n) {
            for (int i = 0; i < n; ++i) {
                nodes.add(new Node(i));
            }
        }

        public void addEdge(int sourceIdx, int destIdx) {
            Node sourceNode = nodes.get(sourceIdx);
            Node destNode = nodes.get(destIdx);
            sourceNode.addEdge(destNode);
        }

        public List<Node> getNodes() {
            return this.nodes;
        }
    }

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        Graph graph = new Graph(n);

        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        Node destNode = graph.getNodes().get(destination);

        if (destNode.getEdges().size() > 0) {
            return false;
        }
        return traverseDfs(graph, source, destination);
    }

    public boolean traverseDfs(
            Graph graph,
            int curr,
            int dest) {

        Node currNode = graph.getNodes().get(curr);

        if (currNode.getState() == State.Visiting) {
            return false;
        } else if (currNode.getState() == State.Visited) {
            return true;
        }

        // If this is a leaf node, it should be equal to the destination
        if (currNode.getEdges().isEmpty()) {
            return curr == dest;
        }

        // Now we are processing this node ==> mark its state as Visiting
        currNode.setState(State.Visiting);
        for (Node edgeNode : currNode.getEdges()) {

            boolean found = traverseDfs(graph, edgeNode.getId(), dest);
            if (!found) {
                return false;
            }
        }

        // Recursive processing done for this node (currNode). We mark its state as Visited
        currNode.setState(State.Visited);
        return true;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 3}
        };
        AllPathsFromSourceLeadsToDestination obj = new AllPathsFromSourceLeadsToDestination();
        boolean found = obj.leadsToDestination(4, edges, 0, 3);
        if (found) {
            System.out.println("Found the path.");
        } else {
            System.out.println("Not found");
        }
    }
}
