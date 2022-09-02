package com.ds.graph.adjlistlinkedlistweight2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private int numVertices;
    private List<Node> nodes;
    private List<Edge> allEdges;
    private boolean isDirected = false;

    public Graph(int numVertices, boolean isDirected) {
        this.numVertices = numVertices;
        nodes = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; ++i) {
            Node newNode = new Node(i);
            nodes.add(newNode);
        }
        this.allEdges = new ArrayList<>();
        this.isDirected = isDirected;
    }

    public void addEdge(int u, int v, int weight) {
        Node uNode = nodes.get(u);
        Node vNode = nodes.get(v);
        Edge edge = new Edge(uNode, vNode, weight);
        allEdges.add(edge);
        uNode.addEdge(edge);
        if (!isDirected) {
            vNode.addEdge(edge);
        }
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

    public List<Edge> getAllEdges() {
        return this.allEdges;
    }

    public void resetToUnvisited() {
        for (Node node : nodes) {
            node.state = Node.State.Unvisited;
        }
    }

    public void doBFS(int id) {
        Node node = nodes.get(id);
        node.state = Node.State.Visiting;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.printf("%d -- ", curr.getId());
            for (Edge edge : curr.getEdges()) {
                Node edgeNode = edge.getDestNode();
                if (edgeNode.state == Node.State.Unvisited) {
                    edgeNode.state = Node.State.Visiting;
                    queue.add(edgeNode);
                }
            }
            curr.state = Node.State.Visited;
        }
        System.out.printf("N%n");
        this.resetToUnvisited();
    }

    public void doDFS(int id) {
        Node node = nodes.get(id);
        if (node == null) {
            return;
        }
        if (node.state == Node.State.Unvisited) {
            node.state = Node.State.Visiting;
            System.out.printf("%d -- ", node.getId());
            for (Edge edge : node.getEdges()) {
                Node edgeNode = edge.getDestNode();
                doDFS(edgeNode.getId());
            }
            node.state = Node.State.Visited;
        }
    }
}
