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

        public Graph(List<Integer> jobs, List<int[]> deps) {
            for (int i = 0; i < jobs.size(); ++i) {
                nodes.add(new Node(jobs.get(i)));
                nodesTable.put(jobs.get(i), nodes.get(i));
            }
            // Add edges from dependency list
            for (int [] dep : deps) {
                this.addEdge(dep[0], dep[1]);
            }
        }

        // Add directed edge from source to destination and
        // update the dependency number by 1 for dest node.
        private void addEdge(int src, int dest) {
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

    // Time : O(j + d), space : O(j + d)
    public static List<Integer> topologicalSort(List<Integer> jobs, List<int[]> deps) {
        Graph g = new Graph(jobs, deps);
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

            // Now deduct dependency # for the edge node by 1
            for (Node edgeNode : currNode.getEdges()) {
                int numDependenciesOfEdgeNode = edgeNode.getNumDependencies();
                edgeNode.setNumDependencies(numDependenciesOfEdgeNode - 1);
                if (edgeNode.getNumDependencies() == 0) {
                    nodesWithNoDependencies.add(edgeNode);
                }
            }
        }

        // After traversing the above queue, if any of the nodes have dependency left
        // that means it has a cycle and it this case return empty list.
        boolean graphHasCycle = false;
        for (Node node : graph.getNodeList()) {
            if (node.getNumDependencies() > 0) {
                graphHasCycle = true;
                break;
            }
        }
        return graphHasCycle ? List.of() : orderedJobs;
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
        System.out.println("Ordered jobs : " + orderedJobs.toString());
    }

}
