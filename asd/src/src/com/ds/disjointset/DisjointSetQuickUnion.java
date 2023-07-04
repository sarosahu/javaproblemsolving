package com.ds.disjointset;

public class DisjointSetQuickUnion {
    private int[] root;

    DisjointSetQuickUnion(int size) {
        root = new int[size];
        for (int i = 0; i < root.length; ++i) {
            root[i] = i;
        }
    }

    public int find(int x) {
        while (x != root[x]) {
            x = root[x];
        }
        return x;
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
        DisjointSetQuickUnion ds = new DisjointSetQuickUnion(10);
        // 1-2-5-6-7   3-8-9 4
        ds.union(1, 2);
        ds.union(2, 5);
        ds.union(5, 6);
        ds.union(6, 7);
        ds.union(3, 8);
        ds.union(8, 9);
        System.out.println("1 and 5 connected ?" +  ds.connected(1, 5));
        System.out.println("5 and 7 connected ?" +  ds.connected(5, 7));
        System.out.println("4 and 9 connected ?" +  ds.connected(4, 9));

        ds.union(9, 4);
        System.out.println("4 and 9 connected ?" +  ds.connected(4, 9));
    }
}
