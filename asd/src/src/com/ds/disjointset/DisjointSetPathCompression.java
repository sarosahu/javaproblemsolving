package com.ds.disjointset;

public class DisjointSetPathCompression {
    private int[] root;
    public DisjointSetPathCompression(int size) {
        root = new int[size];
        for (int i = 0; i < size; ++i) {
            root[i] = i;
        }
    }

    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        root[x] = find(root[x]);

        return root[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        DisjointSetPathCompression dspc = new DisjointSetPathCompression(10);
        dspc.union(1, 2);
        dspc.union(2, 5);
        dspc.union(5, 6);
        dspc.union(6, 7);
        dspc.union(3, 8);
        dspc.union(8, 9);
        System.out.println(dspc.connected(1, 5));
        System.out.println(dspc.connected(5, 7));
        System.out.println(dspc.connected(4, 9));
        dspc.union(9, 4);
        System.out.println(dspc.connected(4, 9));
    }
}
