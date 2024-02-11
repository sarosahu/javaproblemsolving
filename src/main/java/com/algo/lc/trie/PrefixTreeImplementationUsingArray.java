package com.algo.lc.trie;

public class PrefixTreeImplementationUsingArray {
    static class Trie {
        private final TrieNode root = new TrieNode();
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
            TrieNode node = this.searchPrefix(word);
            return node != null && node.isEnd();
        }

        public TrieNode searchPrefix(String word) {
            TrieNode node = this.root;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (!node.hasChild(c)) {
                    return null;
                }
                node = node.getChildNode(c);
            }
            return node;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = this.searchPrefix(prefix);
            return node != null;
        }
    }


    static class TrieNode {
        private TrieNode [] children = new TrieNode[26];
        private boolean isEnd;

        public boolean hasChild(char c) {
            return this.children[c - 'a'] != null;
        }

        public TrieNode getChildNode(char c) {
            return this.children[c - 'a'];
        }

        public void addChild(char c) {
            this.children[c - 'a'] = new TrieNode();
        }

        public boolean isEnd() {
            return this.isEnd;
        }
        public void setEnd(boolean val) {
            this.isEnd = val;
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
