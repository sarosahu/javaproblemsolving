package com.algo.lc.treegraphs;

import java.util.ArrayList;
import java.util.List;

/**
 * 947. Most Stones Removed with Same Row or Column
 *
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point
 * may have at most one stone.
 *
 * A stone can be removed if it shares either the same row or the same column as another stone
 * that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the
 * ith stone, return the largest possible number of stones that can be removed.
 *
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0].
 * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 *
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Explanation: One way to make 3 moves is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,0].
 * 2. Remove stone [2,0] because it shares the same column as [0,0].
 * 3. Remove stone [0,2] because it shares the same row as [0,0].
 * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 *
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 * Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 *
 *
 * Constraints:
 *
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 10^4
 * No two stones are at the same coordinate point.
 *
 */
public class RemoveStones {

    // { DFS approach using graph
    public int removeStones(int[][] stones) {
        Graph graph = new Graph(stones);
        int numComponent = 0;
        for (Node node : graph.nodes) {
            if (!node.visited) {
                ++numComponent;
                dfs(node);
            }
        }
        return stones.length - numComponent;
    }
    private void dfs(Node node) {
        node.visited = true;
        for (Node edgeNode : node.getEdges()) {
            if (!edgeNode.visited) {
                dfs(edgeNode);
            }
        }
    }
    static class Cell {
        int x;
        int y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Node {
        int id;
        Cell cell = new Cell(-1, -1);
        List<Node> edges = new ArrayList<>();
        boolean visited = false;
        public Node(int id) {
            this.id = id;
        }
        public List<Node> getEdges() {
            return this.edges;
        }
        public void addEdge(Node node) {
            this.edges.add(node);
        }
    }

    static class Graph {
        List<Node> nodes = new ArrayList<>();
        public Graph(int [][] stones) {
            for (int i = 0; i < stones.length; ++i) {
                int[] stone = stones[i];
                Node node = new Node(i);
                node.cell.x = stone[0];
                node.cell.y = stone[1];
                nodes.add(node);
            }

            // Connect the edges
            for (int i = 0; i < nodes.size(); ++i) {
                Node curr = nodes.get(i);
                for (int j = i + 1; j < nodes.size(); ++j) {
                    Node next = nodes.get(j);
                    if (curr.cell.x == next.cell.x || curr.cell.y == next.cell.y) {
                        curr.addEdge(next);
                        next.addEdge(curr);
                    }
                }
            }
        }
    }
    // } DFS approach using graph

    // { DisjointSet approach
    public int removeStones2(int[][] stones) {
        DisjointSet ds = new DisjointSet(stones);

        int numComponent = stones.length;
        for (int i = 0; i < stones.length; ++i) {
            for (int j = i + 1; j < stones.length; ++j) {
                if (lyingOnSameRowOrColumn(stones[i], stones[j])) {
                    // Decrement the components if union involving merging
                    if (ds.union(i, j)) {
                        numComponent -= 1;
                    }
                }
            }
        }
        return stones.length - numComponent;
    }

    private boolean lyingOnSameRowOrColumn(int [] a, int [] b) {
        return a[0] == b[0] || a[1] == b[1];
    }

    static class DisjointSet {
        private int [] group;
        private int [] size;

        public DisjointSet(int[][] stones) {
            group = new int[stones.length];
            size = new int[stones.length];
            // Initialize group to itself and size as 1
            for (int i = 0; i < stones.length; ++i) {
                group[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (x == group[x]) {
                return x;
            }
            group[x] = find(group[x]);
            return group[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }

            if (size[rootX] > size[rootY]) {
                group[rootY] = rootX;
                size[rootX] += size[rootY];
            } else {
                group[rootX] = rootY;
                size[rootY] += size[rootX];
            }
            return true;
        }
    }
    // } DisjointSet approach

    public static void main(String[] args) {
        int[][] stones = {
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 2},
                {2, 1},
                {2, 2}
        };
        RemoveStones obj = new RemoveStones();
        int numStonesToRemove = obj.removeStones(stones);
        System.out.println("Number of stones that can be removed (using DFS): " + numStonesToRemove);

        numStonesToRemove = obj.removeStones2(stones);
        System.out.println("Number of stones that can be removed (using DisjointSet): " + numStonesToRemove);
    }
}
