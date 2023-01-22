package com.algo.ae.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a Node class that has a name and an array of optional
 * children nodes. When put together, nodes form an acyclic tree-like
 * structure.
 *
 * Implement the depthFirstSearch method on the Node class, which takes
 * in an empty array, traverses the tree using the Depth-first Search
 * apporach(specifically navigating the tree from left to right),
 * stores all the nodes' names in the input array and returns it.
 *
 * Sample Input:
 *
 * graph =              A
 *                    / |  \
 *                  B   C    D
 *                /  \      /  \
 *              E     F    G    H
 *                  /   \   \
 *                I      J    K
 *
 * Sample Output:
 * ["A", "B", "E", "F", "I", "J", "C", "D", "G", "K", "H"]
 */
public class DepthFirstSearch {
    static class Node {
        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        /*
         * Time : O(v + e), Space : O(v) -- where v is the no of vertices
         * of the input graph and e is the no of edges of the input graph.
         */
        public List<String> depthFirstSearch(List<String> array) {
            // Write your code here.
            array.add(this.name);
            dfsHelper(children, array);
            return array;
        }

        private void dfsHelper(List<Node> children, List<String> array) {
            for (Node child : children) {
                array.add(child.name);
                dfsHelper(child.children, array);
            }
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }

    public static void main(String[] args) {
        Node node = new Node("A");
        node.children.add(new Node("B"));
        node.children.add(new Node("C"));
        node.children.add(new Node("D"));
        node.children.get(0).children.add(new Node("E"));
        node.children.get(0).children.add(new Node("F"));
        node.children.get(2).children.add(new Node("G"));
        node.children.get(2).children.add(new Node("H"));

        node.children.get(0).children.get(1).children.add(new Node("I"));
        node.children.get(0).children.get(1).children.add(new Node("J"));
        node.children.get(2).children.get(0).children.add(new Node("K"));

        List<String> list = new ArrayList<>();
        node.depthFirstSearch(list);
        for (String code : list) {
            System.out.printf("%s, ", code);
        }
        System.out.println();
    }
}
