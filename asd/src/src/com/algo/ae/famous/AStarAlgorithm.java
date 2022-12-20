package com.algo.ae.famous;

import java.util.*;

/**
 * A* Algorithm
 *
 * You are given a 2 dimensional array containing 0 s and 1 s, where each 0
 * represents a free space and each 1 represents an obstacle (a space that
 * cannot be passed through). You can think of this array as a grid-shaped
 * graph. You are also given 4 integers startRow, startCol, endRow and endCol
 * representing the positions of a start node and an end node in the graph.
 *
 * Write a function that finds the shortest path between the start node and
 * end node using the A* search algorithm and returns it.
 *
 * The shortest path should be returned as an array of node positions, where
 * each node position is an array of 2 elements: the [row, col] of the
 * respective node in the graph. The output array should contain the start
 * node's position, the end node's position, and all the positions of the
 * remaining nodes in the shortest path, and these node positions should be
 * ordered from start node to end node.
 *
 * If there is no path from start node to end node, your function should
 * return  an empty array.
 *
 * Note that:
 *
 *  - From each node in the graph, you can only travel in 4 directions:
 *    up, left, down and right; you can't travel diagonally.
 *  - The distance between all neighboring nodes in the graph is the
 *    same; you can treat it as a distance of 1.
 *  - The start node and end node are guaranteed to be located in empty
 *    spaces(cells containing 0).
 *  - The start node and end node will never be out of bounds and will
 *    never overlap.
 *  - There will be at most one shortest path from the start node to
 *    the end node.
 *
 * Sample input:
 *
 * startRow = 0
 * startCol = 1
 * endRow = 4
 * endCol = 3
 * graph = [
 *      [0, 0, 0, 0, 0],
 *      [0, 1, 1, 1, 0],
 *      [0, 0, 0, 0, 0],
 *      [1, 0, 1, 1, 1],
 *      [0, 0, 0, 0, 0],
 * ]
 *  Sample output: [
 *  [0, 1], [0, 0], [1, 0], [2, 0], [2, 1], [3, 1], [4, 1], [4, 2], [4, 3]
 *  ]
 */

public class AStarAlgorithm {
    public int[][] aStarAlgorithm(int startRow,
                                  int startCol,
                                  int endRow,
                                  int endCol,
                                  int[][] graph) {
        List<List<Node>> nodes = initializeNodes(graph);
        Node startNode = nodes.get(startRow).get(startCol);
        Node endNode = nodes.get(endRow).get(endCol);
        startNode.distFromStart = 0;
        startNode.distToEnd = calculateManhattanDistance(startNode, endNode);

        Queue<Node> pq = new PriorityQueue<>(new NodeComparator());
        pq.add(startNode);

        while (!pq.isEmpty()) {
            Node currMinDistNode = pq.poll();
            if (currMinDistNode == endNode) {
                break;
            }

            List<Node> neighbors = getNeighboringNodes(currMinDistNode, nodes);

            for (Node neighbor : neighbors) {
                // If obstacle, no need to visit
                if (neighbor.value == 1) {
                    continue;
                }

                int tentativeDistToNeighbor = currMinDistNode.distFromStart + 1;
                if (tentativeDistToNeighbor >= neighbor.distFromStart) {
                    continue;
                }

                neighbor.cameFrom = currMinDistNode;
                neighbor.distFromStart = tentativeDistToNeighbor;
                neighbor.distToEnd = calculateManhattanDistance(neighbor, endNode);
                pq.add(neighbor);
            }
        }
        return reconstructPath(endNode);
    }

    List<List<Node>> initializeNodes(int[][] graph) {
        List<List<Node>> nodes = new ArrayList<>();

        for (int i = 0; i < graph.length; ++i) {
            List<Node> nodeList = new ArrayList<>();
            nodes.add(nodeList);
            for (int j = 0; j < graph[i].length; ++j) {
                nodes.get(i).add(new Node(i, j, graph[i][j]));
            }
        }
        return nodes;
    }

    List<Node> getNeighboringNodes(Node node, List<List<Node>> nodes) {
        List<Node> neighbors = new ArrayList<>();

        int numRows = nodes.size();
        int numCols = numRows == 0 ? 0 : nodes.get(0).size();

        int row = node.row;
        int col = node.col;

        // Down
        if (row < numRows - 1) {
            neighbors.add(nodes.get(row + 1).get(col));
        }

        // Up
        if (row > 0) {
            neighbors.add(nodes.get(row - 1).get(col));
        }

        // Left
        if (col > 0) {
            neighbors.add(nodes.get(row).get(col - 1));
        }

        // Right
        if (col < numCols - 1) {
            neighbors.add(nodes.get(row).get(col + 1));
        }

        return neighbors;
    }

    int calculateManhattanDistance(Node currNode, Node endNode) {
        return Math.abs(currNode.row - endNode.row) + Math.abs(currNode.col - endNode.col);
    }

    int[][] reconstructPath(Node endNode) {
        if (endNode.cameFrom == null) {
            return new int[][] {};
        }

        Node currNode = endNode;
        List<List<Integer>> path = new ArrayList<>();

        while (currNode != null) {
            List<Integer> nodeData = new ArrayList<>();
            nodeData.add(currNode.row);
            nodeData.add(currNode.col);
            path.add(nodeData);
            currNode = currNode.cameFrom;
        }

        int [][] res = new int[path.size()][2];
        Collections.reverse(path);
        for (int i = 0; i < res.length; ++i) {
            res[i][0] = path.get(i).get(0);
            res[i][1] = path.get(i).get(1);
        }
        return res;
    }

    static class Node {
        String id;
        int row;
        int col;
        int value;
        int distFromStart;
        int distToEnd;
        Node cameFrom;

        Node(int row, int col, int value) {
            this.id = String.valueOf(row) + '-' + String.valueOf(col);
            this.row = row;
            this.col = col;
            this.value = value;
            this.distFromStart = Integer.MAX_VALUE;
            this.distToEnd = Integer.MAX_VALUE;
            this.cameFrom = null;
        }

        public int fullCost() {
            return this.distFromStart + this.distToEnd;
        }
    }

    static class NodeComparator implements Comparator<Node> {
        public int compare(Node s1, Node s2) {
            if (s1.fullCost() < s2.fullCost()) {
                return -1;
            } else if (s1.fullCost() > s2.fullCost()) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0},
        };
        AStarAlgorithm algorithm = new AStarAlgorithm();
        int[][] shortestPath = algorithm.aStarAlgorithm(0, 1, 4, 3, graph);
        System.out.println("The shortest path : ");
        for (int[] path : shortestPath) {
            System.out.println(path[0] + "," + path[1]);
        }
        System.out.println();
    }
}
