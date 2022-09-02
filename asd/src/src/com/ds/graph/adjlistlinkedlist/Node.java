package com.ds.graph.adjlistlinkedlist;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private int index;
    //private LinkedList<Node> edges;
    private List<Node> edges;
    public enum State {
        Unvisited, Visiting, Visited
    };
    public State state;
    public Node(int index) {
        this.index = index;
        edges = new LinkedList<>();
        state = State.Unvisited;
    }

    public List<Node> getEdges() {
        return this.edges;
    }
    public int getIndex() {
        return this.index;
    }
}
