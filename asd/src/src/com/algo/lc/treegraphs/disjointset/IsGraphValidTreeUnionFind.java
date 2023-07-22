package com.algo.lc.treegraphs.disjointset;

/**
 * This is the same problem as in IsGraphValidTree.java
 * This will be using Union-Find data structure.
 */
public class IsGraphValidTreeUnionFind {
    static class UnionFind {
        private int[] parent;
        private int[] rank;
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; ++i) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootY] < rank[rootX]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX] += 1;
                }
            }
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public static boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            // If it these two nodes are already connected,
            // that means it contains a cycle => return false
            if (uf.isConnected(v1, v2)) {
                return false;
            } else {
                uf.union(v1, v2);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Two test cases with 5 nodes each.");
        int[][][] edgesList = {
                {
                        {0, 1},
                        {0, 2},
                        {0, 3},
                        {1, 4}
                },
                {
                        {0, 1},
                        {1, 2},
                        {2, 3},
                        {1, 3},
                        {1, 4}
                }
        };

        for (int[][] edges : edgesList) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>");
            boolean isValid = validTree(5, edges);
            if (isValid) {
                System.out.println("Graph is valid tree");
            } else {
                System.out.println("Graph is not valid tree");
            }
            System.out.println("<<<<<<<<<<<<<<<<<<<<<");
        }
    }
}
