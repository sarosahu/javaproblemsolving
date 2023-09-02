package com.ds.graph.adjlistlinkedlistweight2;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private int id;
    private List<Edge> edges;

    public enum State {
        Unvisited, Visiting, Visited
    };
    public Node.State state;
    public Node(int id) {
        this.id = id;
        edges = new LinkedList<>();
        state = Node.State.Unvisited;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public List<Edge> getEdges() {
        return this.edges;
    }

    public int getId() {
        return this.id;
    }
}
