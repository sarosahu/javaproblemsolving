package com.algo.lc.treegraphs.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 1059. All Paths from Source Lead to Destination
 *
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge
 * between nodes ai and bi, and two nodes source and destination of this graph, determine
 * whether or not all paths starting from source eventually, end at destination, that is:
 *
 * - At least one path exists from the source node to the destination node
 * - If a path exists from the source node to a node with no outgoing edges, then that node
 *   is equal to destination.
 * - The number of possible paths from source to destination is a finite number.
 *
 * Return true if and only if all roads from source lead to destination.
 *
 * Example 1:
 *  n = 3, Source : 0, Destination : 2
 *                0 --> 2
 *                |
 *                |
 *                v
 *                1
 *  Output: false
 *
 *  Example 2:
 *  Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
 * Output: false
 * Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
 *
 * Example 3:
 * Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
 * Output: true
 *
 * Constraints:
 *
 * 1 <= n <= 104
 * 0 <= edges.length <= 104
 * edges.length == 2
 * 0 <= ai, bi <= n - 1
 * 0 <= source <= n - 1
 * 0 <= destination <= n - 1
 *
 * Note: The given graph may have self-loops and parallel edges.
 */
public class AllPathsFromSourceLeadsToDestination {
    enum State {Unvisited, Visiting, Visited};

    static class Node {
        private int id;
        private List<Node> edges = new ArrayList<>();

        State state = State.Unvisited;
        public Node(int id) {
            this.id = id;
        }
        public int getId() {
            return this.id;
        }
        public List<Node> getEdges() {
            return this.edges;
        }
        public void addEdge(Node node) {
            this.edges.add(node);
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
        public Graph(int n) {
            for (int i = 0; i < n; ++i) {
                nodes.add(new Node(i));
            }
        }

        public void addEdge(int sourceIdx, int destIdx) {
            Node sourceNode = nodes.get(sourceIdx);
            Node destNode = nodes.get(destIdx);
            sourceNode.addEdge(destNode);
        }

        public List<Node> getNodes() {
            return this.nodes;
        }
    }

    /**
     *
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @return
     *
     * Complexity Analysis
     *
     * Time Complexity: Typically for an entire DFS over an input graph, it takes O(V+E) where V
     * represents the number of vertices in the graph and likewise, EEE represents the number of
     * edges in the graph. In the worst case EEE can be O(V^2) in case each vertex is connected to
     * every other vertex in the graph. However even in the worst case, we will end up discovering
     * a cycle very early on and prune the recursion tree. If we were to traverse the entire graph,
     * then the complexity would be O(V^2)as the O(E) part would dominate. However, due to pruning
     * and backtracking in case of cycle detection, we end up with an overall time complexity of O(V).
     * 
     * Space Complexity: O(V+E) where O(E) is occupied by the adjacency list and O(V) is occupied by
     * the recursion stack and the node states.
     */
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        Graph graph = new Graph(n);

        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        Node destNode = graph.getNodes().get(destination);

        if (destNode.getEdges().size() > 0) {
            return false;
        }
        return traverseDfs(graph, source, destination);
    }

    public boolean traverseDfs(
            Graph graph,
            int curr,
            int dest) {

        Node currNode = graph.getNodes().get(curr);

        if (currNode.getState() == State.Visiting) {
            return false;
        } else if (currNode.getState() == State.Visited) {
            return true;
        }

        // If this is a leaf node, it should be equal to the destination
        if (currNode.getEdges().isEmpty()) {
            return curr == dest;
        }

        // Now we are processing this node ==> mark its state as Visiting
        currNode.setState(State.Visiting);
        for (Node edgeNode : currNode.getEdges()) {

            boolean found = traverseDfs(graph, edgeNode.getId(), dest);
            if (!found) {
                return false;
            }
        }

        // Recursive processing done for this node (currNode). We mark its state as Visited
        currNode.setState(State.Visited);
        return true;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 3}
        };
        AllPathsFromSourceLeadsToDestination obj = new AllPathsFromSourceLeadsToDestination();
        boolean found = obj.leadsToDestination(4, edges, 0, 3);
        if (found) {
            System.out.println("Found the path.");
        } else {
            System.out.println("Not found");
        }
    }
}
