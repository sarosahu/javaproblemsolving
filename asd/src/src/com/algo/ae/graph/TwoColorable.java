package com.algo.ae.graph;

import java.util.Arrays;
import java.util.Stack;

/**
 * Two-Colorable
 *
 * You're given a list of edges representing a connected, unweighted, undirected graph
 * with at least one node. Write a function that returns a boolean representing if the
 * given graph is two-colorable.
 *
 * A graph is two-colorable(also called bipartite) if all of the nodes can be assigned
 * one of two colors such that no nodes of the same color are connected by an edge.
 *
 * The given list is what's called an adjacency list, and it represents a graph. The
 * number of vertices in the graph is equal to the length of edges, where each index
 * i in edges contains vertex i's siblings, in no particular order. Each individual
 * edge is represented by a +ve integer that denotes an index in the list that this
 * vertex is connected to. Note that this graph is undirected, meaning that if a
 * vertex appears in the edge list of another vertex, then the inverse will also be
 * true.
 *
 * Also note that this graph may contain self-loops. A self-loop is an edge that has
 * the same destination and origin; in other words, it's an edge that connects a
 * vertex to itself. Any self-loop should make a graph not 2-colorable.
 *
 * Sample Input:
 * edges = [
 *  [1, 2],
 *  [0, 2],
 *  [0, 1]
 * ]
 *
 * Sample Output: False
 * Nodes 1 and 2 must be different colors that node 0. However, nodes 1 and 2 are
 * also connected, meaning they must also have different colors, which is
 * impossible with only 2 available colors.
 */
public class TwoColorable {

    static enum Color {
        NONE,
        BLUE,
        GREEN
    }

    // Time: O(v + e), Space : O(V) -- v is no of vertices, e -> no of edges
    public boolean twoColorableR(int[][] edges) {
        Color[] colors = new Color[edges.length];
        Arrays.fill(colors, Color.NONE);
        int currNode = 0;
        colors[currNode] = Color.BLUE;
        boolean isColorable = traverseDfs(currNode, edges, colors);

        return isColorable;
    }

    public boolean traverseDfs(int node, int [][] edges, Color[] colors) {
        Color currentColor = colors[node];
        boolean isColorable = true;
        for (int connection : edges[node]) {
            if (colors[connection] == Color.NONE) {
                colors[connection] = currentColor == Color.BLUE ? Color.GREEN : Color.BLUE;
                isColorable = traverseDfs(connection, edges, colors);
            } else if (colors[connection] == currentColor) {
                return false;
            } else {
                continue;
            }
        }
        return isColorable;
    }

    // Time: O(v + e), Space : O(V) -- v is no of vertices, e -> no of edges
    public boolean twoColorableL(int[][] edges) {
        int[] colors = new int[edges.length];
        // Color value of 0 means, it's unvisited and not colored yet.
        // Color value 1, 2 means different colors.
        Arrays.fill(colors, 0);
        colors[0] = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int connection : edges[node]) {
                if (colors[connection] == 0) {
                    colors[connection] = colors[node] == 1 ? 2 : 1;
                    stack.push(connection);
                } else if (colors[connection] == colors[node]) {
                    return false;
                }
            }
        }
        return true;
    }
}
