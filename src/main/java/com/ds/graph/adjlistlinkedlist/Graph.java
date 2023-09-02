package com.ds.graph.adjlistlinkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private final int numVertices;
    private final List<Node> nodes;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        nodes = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; ++i) {
            Node newNode = new Node(i);
            nodes.add(newNode);
        }
    }

    public void addEdge(int sourceNodeIndex, int destinationNodeIndex) {
        Node destinationNode = nodes.get(destinationNodeIndex);
        nodes.get(sourceNodeIndex).getEdges().add(destinationNode);
    }

    public Node getVertex(int idx) {
        return nodes.get(idx);
    }

    public int size() {
        return this.numVertices;
    }

    public List<Node> getNodes() {
        return this.nodes;
    }
    public void resetToUnvisited() {
        for (Node node : nodes) {
            node.state = Node.State.Unvisited;
        }
    }
    public void doBFS(int nodeIndex) {
        Node node = nodes.get(nodeIndex);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            System.out.printf("%d -- ", currNode.getIndex());
            for (Node adjNode : currNode.getEdges()) {
                if (adjNode.state == Node.State.Unvisited) {
                    adjNode.state = Node.State.Visiting;
                    queue.add(adjNode);
                }
            }
            currNode.state = Node.State.Visited;
        }
        System.out.printf("N%n");
        this.resetToUnvisited();
    }

    public void doDFS(Node node) {
        if (node == null) {
            return;
        }
        if (node.state == Node.State.Unvisited) {
            node.state = Node.State.Visiting;
            System.out.printf("%d -- ", node.getIndex());
            for (Node adjNode : node.getEdges()) {
                doDFS(adjNode);
            }
            node.state = Node.State.Visited;
        }
    }
}
