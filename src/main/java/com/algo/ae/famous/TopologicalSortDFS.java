package com.algo.ae.famous;

import java.util.*;

/**
 * You're given a list of arbitrary jobs that need to be completed; these jobs
 * are represented by distinct integers. You're also given a list of dependencies.
 * A dependency is represented as a pair of jobs where the first job is a
 * prerequisite of the second one. In other words, the second job depends on
 * the first one; it can only be completed once the first job is completed.
 *
 * Write a function that takes in a list of jobs and a list of dependencies and
 * returns a list containing a valid order in which the given jobs can be
 * completed. If no such order exists, the function should return an empty array.
 *
 * Sample Input:
 * jobs = [1, 2, 3, 4]
 * deps = [[1, 2], [1, 3], [3, 2], [4, 2], [4, 3]]
 *
 * Sample Output:
 * [1, 4, 3, 2] or [4, 1, 3, 2]
 */

public class TopologicalSortDFS {
    enum State {
        Unvisited, Visiting, Visited
    }

    static class Node {
        int id;
        List<Node> edges = new ArrayList<>();
        State state = State.Unvisited;
        public Node(int id) {
            this.id = id;
        }

        public void addEdge(Node edge) {
            edges.add(edge);
        }

        public List<Node> getEdges() {
            return this.edges;
        }

        public int getId() {
            return this.id;
        }
    }

    static class Graph {
        List<Node> nodes = new ArrayList<>();
        Map<Integer, Node> nodesTable = new HashMap<>();

        public Graph(List<Integer> jobs) {
            for (int i = 0; i < jobs.size(); ++i) {
                nodes.add(new Node(jobs.get(i)));
                nodesTable.put(jobs.get(i), nodes.get(i));
            }
        }

        public Graph(List<Integer> jobs, List<int[]> dependencies) {
            for (int i = 0; i < jobs.size(); ++i) {
                nodes.add(new Node(jobs.get(i)));
                nodesTable.put(jobs.get(i), nodes.get(i));
            }

            for (int [] dep : dependencies) {
                // Add directed edge from source (dep[0]) to destination (dep[1])
                this.addEdge(dep[0], dep[1]);
            }
        }

        // Add edge from source to destination.
        private void addEdge(int source, int destination) {
            Node sourceNode = this.getNode(source);
            Node destinationNode = this.getNode(destination);
            sourceNode.addEdge(destinationNode);
        }

        public Map<Integer, Node> getNodes() {
            return this.nodesTable;
        }

        public Node getNode(int id) {
            return this.nodesTable.get(id);
        }

        public List<Node> getNodeList() {
            return this.nodes;
        }
    }

    // Time : O(j + d), space : O(j + d)
    public static List<Integer> topologicalSort(List<Integer> jobs, List<int[]> deps) {
        // Write your code here.
        Graph g = new Graph(jobs, deps);
        return getOrderedJobs(g);
    }

    // DFS approach
    public static List<Integer> getOrderedJobs(Graph graph) {
        List<Node> nodes = graph.getNodeList();
        Stack<Integer> stack = new Stack<>();
        for (Node node : nodes) {
            if (node.state == State.Unvisited) {
                boolean containsCycle = dfs(node, stack);
                if (containsCycle) {
                    return new ArrayList<>();
                }
            }
        }
        // Here traverse the stack and push to orderedJobs.
        List<Integer> orderedJobs = new ArrayList<>();
        while (!stack.isEmpty()) {
            orderedJobs.add(stack.peek());
            stack.pop();
        }
        return orderedJobs;
    }

    // If any node during this visit is found to be in a 'Visiting' state
    // then it returns true --> means it has a cycle found
    public static boolean dfs(Node node, Stack<Integer> stack) {
        if (node.state == State.Visiting) {
            return true;
        }
        if (node.state == State.Visited) {
            return false;
        }
        node.state = State.Visiting;
        for (Node edgeNode : node.getEdges()) {
            boolean containsCycle = dfs(edgeNode, stack);
            if (containsCycle) {
                return true;
            }
        }
        node.state = State.Visited;
        stack.push(node.getId());
        return false;
    }

    public static void main(String[] args) {
        List<Integer> jobs = List.of(1, 2, 3, 4);
        // [[1, 2], [1, 3], [3, 2], [4, 2], [4, 3]]
        List<int[]> deps = List.of(
                new int[]{1, 2}
                ,new int[]{1, 3}
                ,new int[]{3, 2}
                ,new int[]{4, 2}
                ,new int[]{4, 3}
                //,new int[]{2, 1} // For making a cycle
        );
        List<Integer> orderedJobs = topologicalSort(jobs, deps);
        System.out.println("Ordered jobs : " + orderedJobs);
    }
}
