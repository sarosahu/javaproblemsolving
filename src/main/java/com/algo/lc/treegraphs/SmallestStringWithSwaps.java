package com.algo.lc.treegraphs;

import com.ds.graph.adjmatrix.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs
 * where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 *
 *
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 */
public class SmallestStringWithSwaps {
    static class DisjointSet {
        private int[] parent;
        private int[] rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public int find(int idx) {
            if (idx != parent[idx]) {
                parent[idx] = find(parent[idx]);
            }
            return parent[idx];
        }
        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) {
                return;
            }
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootV] < rank[rootU]) {
                parent[rootV] = rank[rootU];
            } else {
                parent[rootV] = rootU;
                rank[rootU] += 1;
            }
        }
        public boolean isConnected(int u, int v) {
            return find(u) == find(v);
        }
    }

    /**
     * Complexity Analysis
     *
     * Time complexity: O(E+VlogV)
     *
     * Building the adjacency list will take O(E) operations, as we iterate over the list of pairs once,
     * and inserting an element into the adjacency list takes O(1) time.
     *
     * During the DFS traversal, each vertex will only be visited once. This is because we mark each
     * vertex as visited as soon as we see it, and then we only visit vertices that are not marked as
     * visited. When we iterate over the edge list of each vertex, we look at each edge once. This has
     * a total cost of O(V+E).
     *
     * Additionally, we also sort the list indices and characters for each component. In the worst case,
     * all of the vertices in the graph belong to the same component. In that case, sorting two lists of
     * V elements will take O(VlogV) time. Hence the total time complexity is equal to O(E+VlogV).
     *
     * Space complexity: O(E+V)
     *
     * Building the adjacency list will take O(E) space. To track the visited vertices, an array visited
     * of size O(V) is required. In the worst case, indices and characters can take O(V) space. Also, the
     * run-time stack for DFS will use O(V) space i.e., one active function call for each vertex.
     *
     * Additional space is used for sorting the lists indices and characters. The space complexity of the
     * sorting algorithm is language-specific. For instance, in Java, the Arrays.sort() for primitives is
     * implemented as a variant of quicksort algorithm whose space complexity is O(logV). In C++ sort()
     * function provided by STL is a hybrid of Quick Sort, Heap Sort, and Insertion Sort and has a
     * worst-case space complexity of O(logV). Thus, using the inbuilt sort() function might add up to
     * O(logV) to space complexity.
     *
     * The total space required is (E+V+logV) and hence, the space complexity is equal to O(E+V).
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        boolean[] visited = new boolean[s.length()];
        // Build the adjacency list.
        Graph graph = new Graph(s.length(), false);
        for (List<Integer> edge : pairs) {
            int source = edge.get(0);
            int dest = edge.get(1);
            graph.addEdge(source, dest);
        }
        char[] answer = new char[s.length()];
        for (int vertex = 0; vertex < s.length(); ++vertex) {
            // If not covered in DFS yet
            if (!visited[vertex]) {
                List<Character> chars = new ArrayList<>();
                List<Integer> indices = new ArrayList<>();

                dfs(s, vertex, chars, indices, graph, visited);

                // Sort the list of characgters and indices
                Collections.sort(chars);
                Collections.sort(indices);

                // Store the sorted characters corresponding to the index
                for (int index = 0; index < chars.size(); ++index) {
                    answer[indices.get(index)] = chars.get(index);
                }
            }
        }
        return new String(answer);
    }

    private void dfs(String s,
                     int vertex,
                     List<Character> chars,
                     List<Integer> indices,
                     Graph graph,
                     boolean [] visited)
    {
        // Add the character  and index to the list
        chars.add(s.charAt(vertex));
        indices.add(vertex);

        visited[vertex] = true;

        // Traverse the adjacents
        for (int edgeIdx : graph.getAdjacencyMatrix().get(vertex)) {
            if (!visited[edgeIdx]) {
                dfs(s, edgeIdx, chars, indices, graph, visited);
            }
        }
    }

    // Approach 2 : Union-find(disjoint set) data structure
    public String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
        DisjointSet ds = new DisjointSet(s.length());
        for (List<Integer> pair : pairs) {
            ds.union(pair.get(0), pair.get(1));
        }
        Map<Integer, List<Integer>> rootToComponent = new HashMap<>();
        // Group the vertices that are in same component.
        for (int vertex = 0; vertex < s.length(); ++vertex) {
            int root = ds.find(vertex);
            // Add the vertices corresponding to the component root
            rootToComponent.putIfAbsent(root, new ArrayList<>());
            rootToComponent.get(root).add(vertex);
        }

        // String to store answer
        char[] smallestString = new char[s.length()];
        // Iterate over each component
        for (List<Integer> indices : rootToComponent.values()) {
            List<Character> chars = new ArrayList<>();
            for (int index : indices) {
                chars.add(s.charAt(index));
            }
            Collections.sort(chars);

            // Store the sorted characters
            for (int index = 0; index < indices.size(); ++index) {
                smallestString[indices.get(index)] = chars.get(index);
            }
        }
        return new String(smallestString);
    }

    public static void main(String[] args) {
        //String s = "dcab";
        String s = "edbcaf";
        List<List<Integer>> pairs = new ArrayList<>();
        int[][] pairList = {
                {0, 5},
                {1, 4},
                {0, 4},
                {2, 3}
        };

        for (int i = 0; i < pairList.length; ++i) {
            List<Integer> pair = new ArrayList<>();
            pair.add(pairList[i][0]);
            pair.add(pairList[i][1]);
            pairs.add(pair);
        }

        SmallestStringWithSwaps obj = new SmallestStringWithSwaps();
        System.out.println("Original string  : " + s);
        String smallestStr = obj.smallestStringWithSwaps(s, pairs);

        System.out.println("Smallest string  : " + smallestStr);
        System.out.println();
        System.out.println("Original string  : " + s);
        smallestStr = obj.smallestStringWithSwaps2(s, pairs);
        System.out.println("Smallest string 2: " + smallestStr);
    }
}
