package com.algo.lc.treegraphs.disjointset;

public class FindIfPathExist {
    static class UnionFind {
        private int[] root;
        private int[] rank;
        public UnionFind(int n) {
            this.root = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; ++i) {
                this.root[i] = i;
                this.rank[i] = 1;
            }
        }
        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootY] > rank[rootX]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
            }
        }
    }

    /**
     * Let nnn be the number of nodes and mmm be the number of edges.
     *
     * Time complexity: O(m⋅α(n))
     *
     * The amortized complexity for performing mmm union find operations is O(m⋅α(n)) time where α(alphaα)
     * is the Inverse Ackermann Function.
     * To sum up, the overall time complexity is O(m⋅α(n)).
     *
     * Space complexity: O(n)
     *
     * We used two arrays root and rank to save the root and rank of each node in the DSU data structure,
     * each of them takes O(n)O(n)O(n) space.
     * To sum up, the overall time complexity is O(n).
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        UnionFind uf = new UnionFind(n);
        for (int [] edge: edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.find(source) == uf.find(destination);
    }
}
