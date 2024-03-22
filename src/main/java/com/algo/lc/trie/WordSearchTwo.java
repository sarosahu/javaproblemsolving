package com.algo.lc.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 212. Word Search II
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells
 * are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Ex 1:
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 * Ex 2:
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 */
public class WordSearchTwo {
    int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    public List<String> findWords(char[][] board, String[] words) {
        // Step 1. Construct a trie
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        List<String> result = new ArrayList<>();
        TrieNode root = trie.root;
        // Step 2. Backtracking starting for each cell in the board.
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(board, row, col, root, result);
                }
            }
        }
        return result;
    }

    private void backtracking(char[][] board,
                              int row,
                              int col,
                              TrieNode root,
                              List<String> result) {
        char c = board[row][col];
        TrieNode curr = root.children.get(c);
        // Check if there is any match
        if (curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }

        // mark the current letterbefore exploration
        board[row][col] = '#';

        // Explore neighbor cells in around-clock directions: up, right, down, left
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (!isValidCell(newRow, newCol, board.length, board[0].length)) {
                continue;
            }
            if (curr.children.containsKey(board[newRow][newCol])) {
                backtracking(board, newRow, newCol, curr, result);
            }
        }

        // End of exploration
        board[row][col] = c;

        // Optimization: incrementally remove the leaf nodes
        if (curr.children.isEmpty()) {
            root.children.remove(c);
        }
    }

    private boolean isValidCell(int row, int col, int R, int C) {
        if (row < 0 || col < 0 || row >= R || col >= C) {
            return false;
        }
        return true;
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }
    static class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.word = word;
        }
    }
}
