package com.algo.lc.treegraphs.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FindIfPathExist {
    static class Node {
        private int id;
        List<Node> edges = new ArrayList<>();
        public enum State {Unvisited, Visiting, Visited};
        private State state;
        public Node(int id) {
            this.id = id;
            this.state = State.Unvisited;
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
        private List<Node> vertices = new ArrayList<>();
        public Graph(int n) {
            for (int i = 0; i < n; ++i) {
                vertices.add(new Node(i));
            }
        }

        public void addEdge(int src, int dest) {
            Node srcNode = this.vertices.get(src);
            Node destNode = this.vertices.get(dest);
            srcNode.addEdge(destNode);
            destNode.addEdge(srcNode);
        }
        public List<Node> getVertices() {
            return this.vertices;
        }
    }

    /**
     * Time: O(n + m), Space : O(n + m)
     **/
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Edge case if source == destination, then return true;
        if (source == destination) {
            return true;
        }
        Graph graph = new Graph(n);
        for (int [] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        Node src = graph.getVertices().get(source);
        Node dest = graph.getVertices().get(destination);

        for (Node edgeNode : src.getEdges()) {
            if (edgeNode.getState() == Node.State.Unvisited) {
                boolean found = isFoundDfs(edgeNode, dest, graph);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * DFS approach : iterative way (using Stack)
     * Time: O(n + m), Space : O(n + m)
     **/
    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        Graph graph = new Graph(n);
        for (int [] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        Node src = graph.getVertices().get(source);
        Node dest = graph.getVertices().get(destination);

        Stack<Node> stack = new Stack<>();
        src.setState(Node.State.Visiting);
        stack.push(src);
        while (!stack.isEmpty()) {
            Node currNode = stack.pop();
            if (currNode.getId() == dest.getId()) {
                return true;
            }
            // Add all unvisited neighbors of the current node to the stack
            for (Node edgeNode : currNode.getEdges()) {
                if (edgeNode.getState() == Node.State.Unvisited) {
                    edgeNode.setState(Node.State.Visiting);
                    stack.push(edgeNode);
                }
            }
            currNode.setState(Node.State.Visited);
        }
        return false;
    }
    private boolean isFoundDfs(Node node, Node destNode, Graph graph) {
        boolean found = false;
        node.state = Node.State.Visiting;
        if (node.getId() == destNode.getId()) {
            return true;
        }
        for (Node neighbor : node.getEdges()) {
            if (neighbor.state == Node.State.Unvisited) {
                found = isFoundDfs(neighbor, destNode, graph);
                if (found) {
                    break;
                }
            }
        }
        node.state = Node.State.Visited;
        return found;
    }
}
