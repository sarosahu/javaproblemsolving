package com.algo.ae.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given a Node class that has a name and an array of optional
 * children nodes. When put together, nodes form an acyclic tree-like
 * structure.
 *
 * Implement the breadthFirstSearch method on the Node class, which takes
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
 * ["A", "B", "C, "D", "E", "F", "G", "H", "I", "J", "K"]
 */
public class BreadthFirstSearch {

    static class Node {
        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        // Time: O(v+e), Space: O(v)
        public List<String> breadthFirstSearch(List<String> array) {
            // Write your code here.
            Queue<Node> q = new LinkedList<>();
            q.add(this);
            while (!q.isEmpty()) {
                Node curr = q.poll();
                array.add(curr.name);
                for(Node child : curr.getChildren()) {
                    q.add(child);
                }
            }
            return array;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }

        public List<Node> getChildren() {
            return children;
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
        node.breadthFirstSearch(list);
        for (String code : list) {
            System.out.printf("%s, ", code);
        }
        System.out.println();
    }
}
