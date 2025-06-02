package com.algo.ctci.graph;

import java.util.*;

/**
 * Chapter 4 (4.7)
 */
public class BuildOrder {
    static class Node {
        private String project;
        private int index;
        private List<Node> edges = new ArrayList<>();
        public enum State {
            UnVisited, Visiting, Visited
        };
        private State state;
        Node (String project, int index) {
            this.project = project;
            this.index = index;
            this.state = State.UnVisited;
        }
        // Adding edge from this node to edgeNode.
        public void addEdge(Node edgeNode) {
            this.edges.add(edgeNode);
        }
        public int getIndex() {
            return this.index;
        }
        public String getProject() {
            return this.project;
        }
        public List<Node> getEdges() {
            return this.edges;
        }
    }

    static class Graph {
        private List<Node> nodes = new ArrayList<>();
        private Map<String, Node> nodeMap = new HashMap<>();
        Graph(String[] projects) {
            //int i = 0;
            for (int i = 0; i < projects.length; ++i) {
                Node node = new Node(projects[i], i);
                nodes.add(node);
                nodeMap.put(projects[i], node);
            }
        }
        public Map<String, Node> getNodeMap() {
            return this.nodeMap;
        }

        public List<Node> getNodes() {
            return this.nodes;
        }

        public void addDependencies(String[][] dependencies) {
            for (String[] dependency : dependencies) {
                String source = dependency[0];
                String dest = dependency[1];
                Node sourceNode = nodeMap.get(source);
                Node destNode = nodeMap.get(dest);
                sourceNode.addEdge(destNode);
            }
        }
    }

    public static List<String>
    buildOrder(String[] projects, String[][] dependencies) {
        Graph graph = new Graph(projects);
        graph.addDependencies(dependencies);
        int[] inDegree = new int[projects.length];
        for (Node node : graph.getNodes()) {
            for (Node edgeNode : node.getEdges()) {
                inDegree[edgeNode.getIndex()]++;
            }
        }
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; ++i) {
            if (inDegree[i] == 0) {
                queue.add(graph.getNodes().get(i));
            }
        }
        List<String> output = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            output.add(currNode.getProject());
            for (Node edgeNode : currNode.getEdges()) {
                int edgeNodeIndex = edgeNode.getIndex();
                inDegree[edgeNodeIndex]--;
                if (inDegree[edgeNodeIndex] == 0) {
                    queue.offer(edgeNode);
                }
            }
        }
        return output;
    }

    public static List<String>
    buildOrderDfs(String[] projects, String[][] dependencies) {
        Graph graph = new Graph(projects);
        graph.addDependencies(dependencies);
        Stack<Node> stack = new Stack<>();

        for (Node node : graph.getNodes()) {
            if (node.state == Node.State.UnVisited) {
                buildOrderDfsUtil(node, stack);
            }
        }

        List<String> output = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node currNode = stack.pop();
            output.add(currNode.getProject());
        }
        return output;
    }

    private static void buildOrderDfsUtil(Node node, Stack<Node> stack) {
        node.state = Node.State.Visiting;
        for (Node edgeNode : node.getEdges()) {
            if (edgeNode.state == Node.State.UnVisited) {
                buildOrderDfsUtil(edgeNode, stack);
            }
        }
        stack.push(node);
        node.state = Node.State.Visited;
    }

    public static void main(String[] args) {
        String[] projects = {
                "a", "b", "c", "d", "e", "f"
        };

        String[][] dependencies = {
                {"a", "d"}, {"f", "b"}, {"b", "d"}, {"f", "a"}, {"d", "c"}
        };

        List<String> buildOrder = buildOrder(projects, dependencies);
        for (String order : buildOrder) {
            System.out.printf("%s ", order);
        }
        System.out.println();

        List<String> buildOrder2 = buildOrderDfs(projects, dependencies);
        for(String order : buildOrder2) {
            System.out.printf("%s ", order);
        }
        System.out.println();
    }
}
