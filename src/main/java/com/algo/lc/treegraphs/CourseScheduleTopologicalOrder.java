package com.algo.lc.treegraphs;

import com.ds.graph.adjlistlinkedlist.Graph;
import com.ds.graph.adjlistlinkedlist.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * There are a total of numCourses courses you have to take,
 * labeled from 0 to numCourses - 1. You are given an array
 * prerequisites where prerequisites[i] = [ai, bi] indicates
 * that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0
 * you have to first take course 1.
 * Return the ordering of courses you should take to finish all
 * courses. If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 */
public class CourseScheduleTopologicalOrder {
    private boolean hasCycle = false;
    public void topologicalSortUtil(Node node, Stack<Integer> stack) {
        node.state = Node.State.Visiting;
        for (Node edgeNode : node.getEdges()) {
            if (edgeNode.state == Node.State.Unvisited) {
                topologicalSortUtil(edgeNode, stack);
            } else if (edgeNode.state == Node.State.Visiting) {
                this.hasCycle = true;
            }
        }
        node.state = Node.State.Visited;
        stack.push(node.getIndex());
    }

    public int[]
    findOrderTopologicalOrder(int num, int[][] prerequisites) {
        // Create graph of num nodes
        Graph graph = new Graph(num);
        for (int [] prerequisite : prerequisites) {
            graph.addEdge(prerequisite[1], prerequisite[0]);
        }

        Stack<Integer> stack = new Stack<>();
        for (Node node : graph.getNodes()) {
            if (this.hasCycle) {
                break;
            }
            if (node.state == Node.State.Unvisited) {
                topologicalSortUtil(node, stack);
            }
        }
        if (this.hasCycle) {
            return new int[0];
        }
        int[] topoOrder = new int[num];
        int idx = 0;
        while (!stack.isEmpty()) {
            topoOrder[idx++] = stack.peek();
            stack.pop();
        }
        return topoOrder;
    }

    public int[]
    findOrderTopologicalOrderKahn(int num, int[][] prerequisites) {
        // Create graph of num nodes
        Graph graph = new Graph(num);
        for (int [] prerequisite : prerequisites) {
            graph.addEdge(prerequisite[1], prerequisite[0]);
        }
        // Process each node to store the in-degree info of each node.
        int[] inDegree = new int[num];
        for (Node node : graph.getNodes()) {
            for (Node edgeNode : node.getEdges()) {
                int idx = edgeNode.getIndex();
                inDegree[idx] += 1;
            }
        }
        // Create a queue to hold in-degree of 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int[] topoOrder = new int[num];
        int count = 0;
        while (!queue.isEmpty()) {
            int idx = queue.poll();
            Node node = graph.getVertex(idx);
            topoOrder[count++] = idx;
            for (Node edgeNode : node.getEdges()) {
                int edgeIdx = edgeNode.getIndex();
                inDegree[edgeIdx] -= 1;
                if (inDegree[edgeIdx] == 0) {
                    queue.add(edgeIdx);
                }
            }
        }

        if (count != graph.size()) {
            return new int[0];
        }
        return topoOrder;
    }
    public static void main(String[] args) {
        CourseScheduleTopologicalOrder obj = new CourseScheduleTopologicalOrder();
        int numCourses = 4;
        int[][] prereqs = {
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };
        int[] order = obj.findOrderTopologicalOrder(numCourses, prereqs);
        System.out.println("Order : ");
        for (int num : order) {
            System.out.printf("%d ", num);
        }
        System.out.println();

        int[] order2 = obj.findOrderTopologicalOrderKahn(numCourses, prereqs);
        System.out.println("Order : ");
        for (int num : order2) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }
}
