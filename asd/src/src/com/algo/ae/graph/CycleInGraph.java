package com.algo.ae.graph;

import java.util.Arrays;

/**
 * Cycle In Graph
 *
 * You're given a list of edges representing an unweighted, directed graph with
 * at least one node. Write a function that returns a boolean representing if
 * the given graph contains a cycle.
 *
 * For the purpose of this question, a cycle is defined as any number of vertices,
 * including just one vertex, that are connected in a closed chain. A cycle can
 * also be defined as a chain of at least one vertex in which the first vertex
 * is the same as the last.
 *
 * The given list is what's called an adjacency list, and it represents a graph.
 * The number of vertices in the graph is equal to the length of edges, where each
 * index i in edges contains vertex i's outbound edges, in no particular order.
 * Each individual edge is represented by a +ve integer that denotes an index
 * (a destination vertex) in the list that this vertex is connected to. Note that
 * these edges are directed, meaning that you can only travel from a particular
 * vertex to its destination, not the other way around(unless the destination
 * vertex itself has an outbound edge to the original vertex).
 *
 * Also note that this graph may contain self-loops. A self-loop is an edge that
 * has the same destination and origin; in other words, it's an edge that
 * connects a vertex to itself. For the purpose of this question, a self-loop is
 * considered a cycle.
 *
 * Sample Input:
 * edges = [
 *  [1, 3],
 *  [2, 3, 4],
 *  [0],
 *  [],
 *  [2, 5],
 *  [],
 * ]
 *
 * Sample Output: true
 * There are multiple cycles in this graph:
 * - 1) 0 -> 1 -> 2 -> 0
 * - 2) 0 -> 1 -> 4 -> 2 -> 0
 * - 3) 1 -> 2 -> 0 -> 1
 * These are just 3 examples; there are more.
 */

public class CycleInGraph {
    static enum State {
        Unvisited, Visiting, Visited
    }

    // Approach 1
    // Time: O(v + e), Space : O(v)
    // v is total no of vertices, e is total no of edges
    public boolean cycleInGraph(int[][] edges) {
        int size = edges.length;
        State [] states = new State[size];
        Arrays.fill(states, State.Unvisited);
        for (int i = 0; i < size; ++i) {
            int currVertex = i;

            if (states[currVertex] == State.Unvisited && traverseDFS(currVertex, edges, states) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean traverseDFS(int fromIndex, int [][] edges, State [] states) {
        // Mark this vertex as visiting.
        states[fromIndex] = State.Visiting;

        for (int edgeVertex : edges[fromIndex]) {
            // If this edgeVertex is still in "Visiting" state, then we have cycle.
            if (states[edgeVertex] == State.Visiting) {
                return true;
            }
            if (states[edgeVertex] == State.Unvisited && traverseDFS(edgeVertex, edges, states) == true) {
                return true;
            }
        }

        // Mark this vertex as visited.
        states[fromIndex] = State.Visited;
        return false;
    }

    // Approach 2
    // Time: O(v + e), Space : O(v)
    // v is total no of vertices, e is total no of edges
    public boolean cycleInGraph2(int[][] edges) {
        int numNodes = edges.length;
        boolean[] visited = new boolean[numNodes];
        boolean[] currentlyInStack = new boolean[numNodes];
        Arrays.fill(visited, false);
        Arrays.fill(currentlyInStack, false);

        for (int node = 0; node < numNodes; ++node) {
            if (visited[node]) {
                continue;
            }

            boolean containsCycle = isNodeInCycle(node, edges, visited, currentlyInStack);
            if (containsCycle) {
                return true;
            }
        }
        return false;
    }

    public boolean isNodeInCycle(int node,
                                 int [][] edges,
                                 boolean[] visited,
                                 boolean[] currentlyInStack)
    {
        visited[node] = true;
        currentlyInStack[node] = true;
        boolean hasCycle = false;

        int[] neighbors = edges[node];
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                hasCycle = isNodeInCycle(neighbor, edges, visited, currentlyInStack);
            }

            if (hasCycle) {
                return true;
            } else if (currentlyInStack[neighbor]) {
                return true;
            }
        }

        currentlyInStack[node] = false;
        return false;
    }
}
