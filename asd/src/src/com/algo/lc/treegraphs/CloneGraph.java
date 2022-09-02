package com.algo.lc.treegraphs;

import com.ds.graph.adjlistarraylist.Graph;
import com.ds.graph.adjlistarraylist.Node;

import java.util.*;

public class CloneGraph {
    private Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraphR(Node node) {
        if (node == null) {
            return node;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone of the given node
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.getIndex());

        // Key: Original node
        // Value : Cloned node
        this.visited.put(node, cloneNode);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor : node.getEdges()) {
            cloneNode.getEdges().add(cloneGraphR(neighbor));
        }
        return cloneNode;
    }

    public Node cloneGraphL(Node node) {
        if (node == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visited.put(node, new Node(node.getIndex()));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            for (Node edgeNode : curr.getEdges()) {
                if (!visited.containsKey(edgeNode)) {
                    visited.put(edgeNode, new Node(edgeNode.getIndex()));
                    queue.add(edgeNode);
                }
                visited.get(curr).addEdge(visited.get(edgeNode));
            }
        }
        return visited.get(node);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 2);
        Node firstNode = graph.getVertex(0);
        CloneGraph obj = new CloneGraph();
        Node cloneNode = obj.cloneGraphR(firstNode);
        System.out.println("Done");
    }
}
