package com.algo.lc.treegraphs.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
     */
    public boolean validPath(int n, int [][] edges, int source, int destination) {
        // Store all edges in graph.
        Graph graph = new Graph(n);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        Node src = graph.getVertices().get(source);
        Node dest = graph.getVertices().get(destination);
        src.setState(Node.State.Visiting);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(src);
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            if (currNode.getId() == dest.getId()) {
                return true;
            }

            // Iterate through all the neighbors (that is in Unvisited state)
            for (Node neighbor : currNode.getEdges()) {
                if (neighbor.getState() == Node.State.Unvisited) {
                    neighbor.setState(Node.State.Visiting);
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }
}
