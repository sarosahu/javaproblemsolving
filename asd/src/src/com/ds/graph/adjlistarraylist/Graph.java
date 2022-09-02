package com.ds.graph.adjlistarraylist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private int numVertices;
    private List<Node> nodes;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        nodes = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; ++i) {
            Node newNode = new Node(i);
            nodes.add(newNode);
        }
    }

    public void addEdge(int sourceVertexIndex, int destVertexIndex) {
        Node destVertex = this.getVertex(destVertexIndex);
        nodes.get(sourceVertexIndex).getEdges().add(destVertex);
    }

    public Node getVertex(int idx) {
        return nodes.get(idx);
    }

    public List<Node> getVertices() {
        return this.nodes;
    }

    private void resetToUnvisited() {
        for (Node node : nodes) {
            node.setState(Node.State.Unvisited);
        }
    }
    public void bfs(int vertexIndex) {
        Node node = nodes.get(vertexIndex);
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.printf("%d -- ", curr.getIndex());
            for (Node edgeNode : curr.getEdges()) {
                if (edgeNode.getState() == Node.State.Unvisited) {
                    edgeNode.setState(Node.State.Visiting);
                    queue.add(edgeNode);
                }
            }
            curr.setState(Node.State.Visited);
        }
        System.out.printf("N%n");
        this.resetToUnvisited();
    }

    public void dfs(Node node) {
        if (node == null) {
            return;
        }
        if (node.getState() == Node.State.Unvisited) {
            node.setState(Node.State.Visiting);
            System.out.printf("%d -- ", node.getIndex());
            for (Node currEdgeNode : node.getEdges()) {
                dfs(currEdgeNode);
            }
            node.setState(Node.State.Visited);
        }
    }
}
