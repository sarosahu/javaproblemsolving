package com.ds.graph.adjlistarraylist;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int index;
    private List<Node> edges;
    public enum State {
        Unvisited, Visiting, Visited
    };
    private State state;
    public Node(int index) {
        this.index = index;
        edges = new ArrayList<>();
        state = State.Unvisited;
    }

    public List<Node> getEdges() {
        return this.edges;
    }
    public int getIndex() {
        return this.index;
    }
    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return this.state;
    }

    public void addEdge(Node node) {
        this.edges.add(node);
    }
}
