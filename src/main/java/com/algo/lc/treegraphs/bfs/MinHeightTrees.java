package com.algo.lc.treegraphs.bfs;

import java.util.*;

/**
 * A tree is an undirected graph in which any two vertices are connected by exactly
 * one path. In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges
 * where edges[i] = [ai, bi] indicates that there is an undirected edge between
 * the two nodes ai and bi in the tree, you can choose any node of the tree as the
 * root. When you select a node x as the root, the result tree has height h. Among
 * all possible rooted trees, those with minimum height (i.e. min(h))  are called
 * minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path
 * between the root and a leaf.
 */
public class MinHeightTrees {
    // This uses BFS traversal, but it is not efficient.
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Graph graph = new Graph(n, false);
        for (int [] edge : edges) {
            graph.addEdge(edge);
        }

        // Do a DFS traversal for each node as a root node.
        int[] minHeight = new int[n];
        for (Node node : graph.getNodes()) {
            bfs(node, minHeight);
            graph.resetState();
        }
        List<Integer> minHeightNodes = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < minHeight.length; ++i) {
            int curr = minHeight[i];
            if (curr < min) {
                min = curr;
            }
        }
        for (int i = 0; i < minHeight.length; ++i) {
            int curr = minHeight[i];
            if (curr == min) {
                minHeightNodes.add(i);
            }
        }
        return minHeightNodes;
    }

    // This algorithm uses topological sort and efficient than above.
    public static List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> minHeightNodes = new ArrayList<>();
        if (n <= 2) {
            for (int i = 0; i < n; ++i) {
                minHeightNodes.add(i);
            }
            return minHeightNodes;
        }
        Graph graph = new Graph(n, false);
        for (int [] edge : edges) {
            graph.addEdge(edge);
        }


        Deque<Node> deque = new LinkedList<>();
        for (Node node : graph.getNodes()) {
            if (node.getEdges().size() <= 1) {
                deque.addFirst(node);
            }
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size > 0) {
                Node curr = deque.pollFirst();
                Iterator<Node> it = curr.getEdges().iterator();
                Node edgeNode = it.next();
                curr.getEdges().remove(edgeNode);
                edgeNode.getEdges().remove(curr);
                if (edgeNode.getEdges().size() == 1) {
                    deque.offer(edgeNode);
                }
                --size;
            }
            if (deque.size() == 2) {
                Node first = deque.peekFirst();
                Node edgeOfFirst = first.getEdges().iterator().next();
                Node last = deque.peekLast();
                if (edgeOfFirst.getId() == last.getId()) {
                    break;
                }
            }
            if (deque.size() == 1) {
                break;
            }
        }

        while (!deque.isEmpty()) {
            Node curr = deque.pollFirst();
            minHeightNodes.add(curr.getId());
        }
        return minHeightNodes;
    }

    private static void bfs(Node node, int[] minHeight) {
        int level = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Node curr = queue.poll();
                curr.setState(Node.State.Visiting);
                for (Node edgeNode : curr.getEdges()) {
                    if (edgeNode.getState() == Node.State.Unvisited) {
                        queue.offer(edgeNode);
                    }
                    //queue.offer(edgeNode);
                }
                curr.setState(Node.State.Visited);
                --size;
            }
            ++level;
        }
        minHeight[node.getId()] = level;
    }

    static class Node {
        private int id;
        //private List<Node> edges = new ArrayList<>();
        private Set<Node> edges = new HashSet<>();
        public enum State {
            Unvisited, Visiting, Visited
        };
        private State state;

        public Node(int id) {
            this.id = id;
            this.state = State.Unvisited;
        }
        public void addEdge(Node node) {
            this.edges.add(node);
        }
        public Set<Node> getEdges() {
            return this.edges;
        }
        public int getId() {
            return this.id;
        }
        public State getState() {
            return this.state;
        }
        public void setState(State state) {
            this.state = state;
        }
    }

    static class Graph {
        private List<Node> nodes = new ArrayList<>();
        private boolean isDirected;
        public Graph(int n, boolean isDirected) {
            this.isDirected = isDirected;
            for (int i = 0; i < n; ++i) {
                Node node = new Node(i);
                this.nodes.add(node);
            }
        }
        public void addEdge(int[] edge) {
            Node src = this.nodes.get(edge[0]);
            Node dest = this.nodes.get(edge[1]);
            src.addEdge(dest);
            if (!this.isDirected) {
                dest.addEdge(src);
            }
        }
        public void resetState() {
            for (Node node : nodes) {
                node.setState(Node.State.Unvisited);
            }
        }
        public List<Node> getNodes() {
            return this.nodes;
        }
    }

    public static void main(String[] args) {
        int[][] edges = {
                {1, 0}, {1, 2}, {1, 3}
        };
        List<Integer> minHeights = findMinHeightTrees(4, edges);
        for (int i : minHeights) {
            System.out.printf("%d, ", i);
        }
        System.out.println();
        List<Integer> minHeights2 = findMinHeightTrees2(4, edges);
        for (int i : minHeights2) {
            System.out.printf("%d, ", i);
        }
        System.out.println();
    }
}
