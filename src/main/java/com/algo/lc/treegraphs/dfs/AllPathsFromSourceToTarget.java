package com.algo.lc.treegraphs.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths
 * from node 0 to node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e.,
 * there is a directed edge from node i to node graph[i][j]).
 *
 * e.g. 1
 *
 *  0 ---> 1
 *  |      |
 *  v      v
 *  2 ---> 3
 *
 *  intput graph: [[1,2],[3],[3],[]]
 *
 *  Output: [[0, 1, 3], [0, 2, 3]]
 */
public class AllPathsFromSourceToTarget {
    // Backtracking approach:
    // Time : O(2^N)*N, space O(N)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // Source node : 0, target node : graph.length - 1
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        for (int edgeNode : graph[0]) {
            traverseDfs(edgeNode, path, result, graph);
        }
        return result;
    }

    private void traverseDfs(int node,
                             List<Integer> path,
                             List<List<Integer>> result,
                             int [][] graph)
    {
        path.add(node);
        if (node == graph.length - 1) {
            // target path is reached
            result.add(new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
            return;
        }

        for (int next : graph[node]) {
            traverseDfs(next, path, result, graph);
        }
        path.remove(path.size() - 1);
    }
}
