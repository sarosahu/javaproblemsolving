package com.algo.lc.treegraphs;

import com.algo.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private int target;
    private int[][] graph;
    private List<List<Integer>> results;
    private Map<Integer, List<List<Integer>>> memo;

    // Backtracking approach:
    // Time : O(2^N)*N, space O(N)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.target = graph.length - 1;
        this.graph = graph;
        this.results = new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        // Source node is 0, add it first to any path.
        path.add(0);
        // Start the backtracking, starting from source 0.
        this.backtrack(0, path);

        return this.results;
    }

    private void backtrack(int currNode, List<Integer> path) {
        if (currNode == this.target) {
            // target path is reached
            this.results.add(new ArrayList<Integer>(path));
            return;
        }

        // Explore the neighbour nodes
        for (int next : graph[currNode]) {
            path.add(next);
            backtrack(next, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> allPathsSourceTargetTopDownDp(int[][] graph) {
        this.target = graph.length - 1;
        this.graph = graph;
        this.memo = new HashMap<>();

        return this.allPathsToTarget(0);
    }

    private List<List<Integer>> allPathsToTarget(int currNode) {
        // Memoization : check the result in cache first
        if (memo.containsKey(currNode)) {
            return memo.get(currNode);
        }
        List<List<Integer>> currResults = new ArrayList<>();

        // Base case
        if (currNode == this.target) {
            List<Integer> path = new ArrayList<>();
            path.add(currNode);
            currResults.add(path);
            return currResults;
        }
        // Iterate through the paths starting from each neighbor
        for (int next : this.graph[currNode]) {
            for (List<Integer> path : allPathsToTarget(next)) {
                List<Integer> newPath = new ArrayList<>();
                newPath.add(currNode);
                newPath.addAll(path);
                currResults.add(newPath);
            }
        }
        memo.put(currNode, currResults);
        return currResults;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {1, 2},
                {3},
                {3},
                {}
        };
        AllPathsFromSourceToTarget obj = new AllPathsFromSourceToTarget();
        List<List<Integer>> results = obj.allPathsSourceTarget(graph);
        Utils.print2DList(results);
        System.out.println();
        List<List<Integer>> results2 = obj.allPathsSourceTargetTopDownDp(graph);
        Utils.print2DList(results2);
    }
}
