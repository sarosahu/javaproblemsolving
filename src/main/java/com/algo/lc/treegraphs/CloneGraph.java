package com.algo.lc.treegraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {
    static class Node {
        int val;
        List<Node> neighbors;
        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }
    }

    static class Graph {
        List<Node> vertices = new ArrayList<>();
        public Graph(int n) {
            for (int i = 1; i <=n; ++i) {
                vertices.add(new Node(i));
            }
        }

        public void addEdge(int sourceIdx, int destIndex) {
            vertices.get(sourceIdx).neighbors.add(vertices.get(destIndex));
        }

    }

    // This map contains node value to cloned node.
    private final Map<Integer, Node> idToNode = new HashMap<>();

    /**
     *
     * @param node
     * @return
     * Complexity Analysis
     *
     * Time Complexity : O(N+M), where N is a number of nodes (vertices) and M is a number of edges.
     *
     * Space Complexity : O(N). This space is occupied by the visited dictionary and in addition to
     * that, space would also be occupied by the queue since we are adopting the BFS approach here.
     * The space occupied by the queue would be equal to O(W) where W is the width of the graph. Overall,
     * the space complexity would be O(N).
     */
    public Node cloneGraphL(Node node) {
        if (node == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        idToNode.put(node.val, new Node(node.val));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            for (Node edgeNode : curr.neighbors) {
                if (!idToNode.containsKey(edgeNode.val)) {
                    idToNode.put(edgeNode.val, new Node(edgeNode.val));
                    queue.add(edgeNode);
                }
                idToNode.get(curr.val).neighbors.add(idToNode.get(edgeNode.val));
            }
        }
        return idToNode.get(node.val);
    }

    /**
     *
     * @param node
     * @return : Cloned node of parameter node
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N+M), where N is a number of nodes (vertices) and MMM is a number of edges.
     * Space Complexity: O(N). This space is occupied by the visited hash map and in addition
     * to that, space would also be occupied by the recursion stack since we are adopting a recursive
     * approach here. The space occupied by the recursion stack would be equal to O(H) where H is the
     * height of the graph. Overall, the space complexity would be O(N).
     */
    public Node cloneGraphR(Node node) {
        if (node == null) {
            return null;
        }
        cloneNodeHelper(node);

        return idToNode.get(node.val);
    }

    private void cloneNodeHelper(Node node) {
        if (idToNode.containsKey(node.val)) {
            return;
        }
        idToNode.put(node.val, new Node(node.val));
        Node currClonedNode = idToNode.get(node.val);
        for (Node neighbor : node.neighbors) {
            cloneNodeHelper(neighbor);
            currClonedNode.neighbors.add(idToNode.get(neighbor.val));
        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph(2);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);

        Node firstNode = graph.vertices.get(0);
        CloneGraph obj = new CloneGraph();

        Node cloneNode = obj.cloneGraphR(firstNode);
        System.out.println("Done");
    }
}
