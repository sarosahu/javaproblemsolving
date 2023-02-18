package com.algo.ae.famous;

import java.util.*;

public class TopologicalSortDFS {
    static class Node {
        int id;
        List<Node> edges = new ArrayList<>();
        public enum State {
            Unvisited, Visiting, Visited
        };
        State state;
        public Node(int id) {
            this.id = id;
            this.state = State.Unvisited;
        }

        public void addEdge(Node edge) {
            edges.add(edge);
        }

        public List<Node> getEdges() {
            return this.edges;
        }

        public int getId() {
            return this.id;
        }
    }

    static class Graph {
        List<Node> nodes = new ArrayList<>();
        Map<Integer, Node> nodesTable = new HashMap<>();

        public Graph(List<Integer> jobs) {
            for (int i = 0; i < jobs.size(); ++i) {
                nodes.add(new Node(jobs.get(i)));
                nodesTable.put(jobs.get(i), nodes.get(i));
            }
        }

        // Add edge from source to destination.
        public void addEdge(int source, int destination) {
            Node sourceNode = this.getNode(source);
            Node destinationNode = this.getNode(destination);
            sourceNode.addEdge(destinationNode);
        }

        public Map<Integer, Node> getNodes() {
            return this.nodesTable;
        }

        public Node getNode(int id) {
            return this.nodesTable.get(id);
        }

        public List<Node> getNodeList() {
            return this.nodes;
        }
    }

    // Time : O(j + d), space : O(j + d)
    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        // Write your code here.
        Graph g = new Graph(jobs);
        for (int i = 0; i < deps.size(); ++i) {
            Integer[] dep = deps.get(i);
            int source = dep[0];
            int dest = dep[1];
            g.addEdge(source, dest);
        }
        return getOrderedJobs(g);
    }

    public static List<Integer> getOrderedJobs(Graph graph) {
        List<Node> nodes = graph.getNodeList();
        Stack<Integer> stack = new Stack<>();
        for (Node node : nodes) {
            if (node.state == Node.State.Unvisited) {
                boolean containsCycle = depthFirstTraverse(node, stack);
                if (containsCycle) {
                    return new ArrayList<>();
                }
            }
        }
        // Here traverse the stack and push to orderedJobs.
        List<Integer> orderedJobs = new ArrayList<>();
        while (!stack.isEmpty()) {
            orderedJobs.add(stack.peek());
            stack.pop();
        }
        return orderedJobs;
    }

    public static boolean depthFirstTraverse(Node node, Stack<Integer> stack) {
        if (node.state == Node.State.Visiting) {
            return true;
        }
        if (node.state == Node.State.Visited) {
            return false;
        }
        node.state = Node.State.Visiting;
        for (Node edgeNode : node.getEdges()) {
            boolean containsCycle = depthFirstTraverse(edgeNode, stack);
            if (containsCycle) {
                return true;
            }
        }
        node.state = Node.State.Visited;
        stack.push(node.getId());
        return false;
    }
}
