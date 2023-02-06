package com.algo.ae.graph;

import java.util.*;

/**
 * You're given a 2-dimensional array (a matrix) of potentially unequal height and width
 * containing letters; this matrix represents a boggle board. You're also given a list
 * of words.
 *
 * Write a function that returns an array of all the words contained in the boggle board.
 * The final words don't need to be in any particular order.
 *
 * A word is constructed in the boggle board by connecting adjacent (horizontally,
 * vertically, or diagonally) letters, without using any single letter at a given position
 * more than once; while a word can of course have repeated letters, those repeated letters
 * must come from different positions in the boggle board in order for the word to be
 * contained in the board. Note that 2 or more words are allowed to overlap and use the
 * same letters in the boggle board.
 *
 * Sample Input:
 *
 * board = [
 *     ["t", "h", "i", "s", "i", "s", "a"],
 *     ["s", "i", "m", "p", "l", "e", "x"],
 *     ["b", "x", "x", "x", "x", "e", "b"],
 *     ["x", "o", "g", "g", "l", "x", "o"],
 *     ["x", "x", "x", "D", "T", "r", "a"],
 *     ["R", "E", "P", "E", "A", "d", "x"],
 *     ["x", "x", "x", "x", "x", "x", "x"],
 *     ["N", "O", "T", "R", "E", "-", "P"],
 *     ["x", "x", "D", "E", "T", "A", "E"]
 * ],
 * words = [
 *  "this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"
 * ]
 *
 * Sample Output:
 *
 * ["this", "is", "a", "simple", "boggle", "board", "NOTRE-PEATED"]
 * // The words coudl be ordered differently.
 */
public class BoggleBoard {
    static class TrieNode {
        String word = "";
        Map<Character, TrieNode> children = new HashMap<>();

        public boolean hasChildren(char c) {
            return this.children.containsKey(c);
        }

        public void addChild(char c, TrieNode childNode) {
            this.children.put(c, childNode);
        }

        public TrieNode getChild(char c) {
            return this.children.get(c);
        }
    }

    static class Trie {
        TrieNode root;
        char endSymbol;

        public Trie() {
            this.root = new TrieNode();
            this.endSymbol = '*';
        }

        public void add(String str) {
            TrieNode node = this.root;
            for (int i = 0; i < str.length(); ++i) {
                char letter = str.charAt(i);
                if (!node.hasChildren(letter)) {
                    TrieNode newNode = new TrieNode();
                    node.addChild(letter, newNode);
                }
                node = node.children.get(letter);
            }
            node.addChild(this.endSymbol, null);
            node.word = str;
        }
    }

    public static List<String> boggleBoard(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        Set<String> finalWords = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                explore(i, j, board, trie.root, visited, finalWords);
            }
        }
        List<String> finalWordsArray = new ArrayList<>();
        finalWordsArray.addAll(finalWords);

        return finalWordsArray;
    }

    public static void explore(int row, int col, char[][] board,
                               TrieNode trieNode,
                               boolean[][] visited,
                               Set<String> finalWords) {

        if (visited[row][col] == true) {
            return;
        }
        char letter = board[row][col];
        if (!trieNode.hasChildren(letter)) {
            return;
        }
        visited[row][col] = true;
        trieNode = trieNode.getChild(letter);
        if (trieNode.hasChildren('*')) {
            finalWords.add(trieNode.word);
        }
        List<Integer[]> neighbors = getNeighbors(row, col, board);
        for (Integer[] neighbor : neighbors) {
            int nextRow = neighbor[0];
            int nextCol = neighbor[1];
            explore(nextRow, nextCol, board, trieNode, visited, finalWords);
        }
        visited[row][col] = false;
    }

    public static List<Integer[]> getNeighbors(int i, int j, char[][] board) {
        List<Integer[]> neighbors = new ArrayList<>();
        if (i > 0 && j > 0) {
            neighbors.add(new Integer[]{i - 1, j - 1});
        }
        if (i > 0 && j < board[0].length - 1) {
            neighbors.add(new Integer[]{i - 1, j + 1});
        }
        if (i < board.length - 1 && j < board[0].length - 1) {
            neighbors.add(new Integer[]{i + 1, j + 1});
        }
        if (i < board.length - 1 && j > 0) {
            neighbors.add(new Integer[]{i + 1, j - 1});
        }
        if (i > 0) {
            neighbors.add(new Integer[]{i - 1, j});
        }
        if (i < board.length - 1) {
            neighbors.add(new Integer[]{i + 1, j});
        }
        if (j > 0) {
            neighbors.add(new Integer[]{i, j - 1});
        }
        if (j < board[0].length - 1) {
            neighbors.add(new Integer[]{i, j + 1});
        }
        return neighbors;
    }
}
