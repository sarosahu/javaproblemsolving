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
public class TopologicalSort {
    static class Node {
        int id;
        List<Node> edges = new ArrayList<>();
        int numDependencies;

        public Node(int id) {
            this.id = id;
            this.numDependencies = 0;
        }

        public void addEdge(Node node) {
            edges.add(node);
        }

        public List<Node> getEdges() {
            return this.edges;
        }

        public int getId() {
            return this.id;
        }

        public int getNumDependencies() {
            return this.numDependencies;
        }

        public void setNumDependencies(int val) {
            this.numDependencies = val;
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

        // Add edge from source to destination.
        public void addEdge(int src, int dest) {
            Node srcNode = this.getNode(src);
            Node destNode = this.getNode(dest);
            srcNode.addEdge(destNode);
            destNode.setNumDependencies(destNode.getNumDependencies() + 1);
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

    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        Graph g = new Graph(jobs);
        for (int i = 0; i < deps.size(); ++i) {
            Integer[] dependency = deps.get(i);
            int src = dependency[0];
            int dest = dependency[1];
            g.addEdge(src, dest);
        }
        return getOrderedJobs(g);
    }

    public static List<Integer> getOrderedJobs(Graph graph) {
        List<Integer> orderedJobs = new ArrayList<>();
        Queue<Node> nodesWithNoDependencies = new LinkedList<>();

        for (Node node : graph.getNodeList()) {
            if (node.getNumDependencies() == 0) {
                nodesWithNoDependencies.add(node);
            }
        }

        while (!nodesWithNoDependencies.isEmpty()) {
            Node currNode = nodesWithNoDependencies.poll();
            orderedJobs.add(currNode.getId());

            // Now remove dependency.
            for (Node edgeNode : currNode.getEdges()) {
                int numDependenciesOfEdgeNode = edgeNode.getNumDependencies();
                edgeNode.setNumDependencies(numDependenciesOfEdgeNode - 1);
                if (edgeNode.getNumDependencies() == 0) {
                    nodesWithNoDependencies.add(edgeNode);
                }
            }
        }

        boolean graphHasCycle = false;
        for (Node node : graph.getNodeList()) {
            if (node.getNumDependencies() > 0) {
                graphHasCycle = true;
            }
        }
        return graphHasCycle ? new ArrayList<Integer>() : orderedJobs;
    }

}
