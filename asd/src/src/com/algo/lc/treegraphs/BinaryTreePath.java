package com.algo.lc.treegraphs;

import com.ds.bintree.BinTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePath {
    /*
    public List<List<Integer>>
    binaryTreePaths(BinTreeNode<Integer> root) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        List<Integer> newPath = null;
        Stack<BinTreeNode<Integer>> stk = new Stack<>();
        while (root != null || !stk.isEmpty()) {
            if (root != null) {
                stk.push(root);
                path.add(root.data);
                root = root.leftNode;
            } else {
                root = stk.pop();
                newPath = new ArrayList<>(path);
                newPath.remove(newPath.size() - 1);
                root = root.rightNode;
                if (root == null) {
                    results.add(new ArrayList<>(path));
                    //path.remove(path.size() - 1);
                    path = newPath;
                }
            }
        }
        return results;
    }
    */

    public void
    constructPaths(BinTreeNode<Integer> root, List<Integer> path, List<List<Integer>> paths) {
        if (root == null) {
            return;
        }
        path.add(root.data);
        if (root.leftNode == null && root.rightNode == null) {
            paths.add(path);
        } else {
            //List<Integer> newPath = new ArrayList<>(path);
            constructPaths(root.leftNode, new ArrayList<>(path), paths);
            constructPaths(root.rightNode, new ArrayList<>(path), paths);
        }
    }

    public List<List<Integer>>
    binaryTreePathsR(BinTreeNode<Integer> root) {
        List<List<Integer>> paths = new ArrayList<>();
        constructPaths(root, new ArrayList<>(), paths);
        return paths;
    }
    public List<List<Integer>>
    binaryTreePaths(BinTreeNode<Integer> root) {
        List<List<Integer>> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }

        Stack<BinTreeNode<Integer>> nodeStk = new Stack<>();
        Stack<List<Integer>> pathStk = new Stack<>();

        nodeStk.add(root);
        List<Integer> rootPath = new ArrayList<>();
        rootPath.add(root.data);
        pathStk.add(rootPath);

        BinTreeNode<Integer> node;
        List<Integer> path;
        while (!nodeStk.isEmpty()) {
            node = nodeStk.pop();
            path = pathStk.pop();
            if ((node.leftNode == null) && (node.rightNode == null)) {
                paths.add(path);
            } else {
                if (node.leftNode != null) {
                    nodeStk.add(node.leftNode);
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(node.leftNode.data);
                    pathStk.add(newPath);
                }
                if (node.rightNode != null) {
                    nodeStk.add(node.rightNode);
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(node.rightNode.data);
                    pathStk.add(newPath);
                }
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        BinaryTreePath obj = new BinaryTreePath();
        BinTreeNode<Integer> root = new BinTreeNode<>(1);
        root.leftNode = new BinTreeNode<>(2);
        root.rightNode = new BinTreeNode<>(3);
        root.leftNode.rightNode = new BinTreeNode<>(4);
        root.rightNode.leftNode = new BinTreeNode<>(5);
        root.rightNode.rightNode = new BinTreeNode<>(6);

        List<List<Integer>> results = obj.binaryTreePathsR(root);
        for (List<Integer> path : results) {
            for (int num : path) {
                System.out.printf("%d - ", num);
            }
            System.out.println();
        }
        System.out.println();
    }
}
