package com.ds.graph.adjlistlinkedlistweight2;

public class Edge {
    private boolean isDirected = false;
    private Node srcNode;
    private Node destNode;
    private int weight = -1;

    public Edge(Node srcNode, Node destNode) {
        this.srcNode = srcNode;
        this.destNode = destNode;
    }

    public Edge(Node srcNode, Node destNode, boolean isDirected, int weight) {
        this(srcNode, destNode, weight);
        this.isDirected = isDirected;
    }

    public Edge(Node srcNode, Node destNode, int weight) {
        this(srcNode, destNode);
        this.weight = weight;
    }

    public Node getSrcNode() {
        return this.srcNode;
    }

    public Node getDestNode() {
        return this.destNode;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean isDirected() {
        return this.isDirected;
    }
}
