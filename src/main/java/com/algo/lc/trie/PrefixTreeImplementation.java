package com.algo.lc.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * PrefixTreeImplementation
 *
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to
 * efficiently store and retrieve keys in a dataset of strings. There are various
 * applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie
 * (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted
 * string word that has the prefix "prefix", and false otherwise.
 */
public class PrefixTreeImplementation {
    static class Trie {
        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = this.root;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (!node.hasChild(c)) {
                    node.addChild(c);
                }
                node = node.getChildNode(c);
            }
            node.setEnd(true);
        }

        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        public TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (node.hasChild(c)) {
                    node = node.getChildNode(c);
                } else {
                    return null;
                }
            }
            return node;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }
    }

    static class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private boolean isEnd = false;

        public Map<Character, TrieNode> getChildren() {
            return this.children;
        }
        public boolean isEnd() {
            return this.isEnd;
        }
        public void setEnd(boolean val) {
            this.isEnd = val;
        }
        public boolean hasChild(char c) {
            return this.children.containsKey(c);
        }
        public void addChild(char c) {
            this.children.put(c, new TrieNode());
        }
        public TrieNode getChildNode(char c) {
            return this.children.get(c);
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("add");
        trie.insert("addition");

        String searchString = "app";
        boolean isFound = trie.search(searchString);
        if (isFound) {
            System.out.printf("%s is found from trie\n", searchString);
        } else {
            System.out.printf("%s is not found from trie\n", searchString);
        }

        searchString = "appl";
        isFound = trie.search(searchString);
        if (isFound) {
            System.out.printf("%s is found from trie\n", searchString);
        } else {
            System.out.printf("%s is not found from trie\n", searchString);
        }

        boolean isPrefixFound = trie.startsWith(searchString);
        if (isPrefixFound) {
            System.out.printf("Prefix %s is found from trie\n", searchString);
        } else {
            System.out.printf("Prefix %s is not found from trie\n", searchString);
        }
    }
}
