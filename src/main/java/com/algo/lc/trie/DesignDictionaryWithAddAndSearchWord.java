package com.algo.lc.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Add and Search Word - Data structure design
 *
 * Solution
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 */
public class DesignDictionaryWithAddAndSearchWord {
    static class WordDictionary {
        private TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                TrieNode child = node.getChild(c);
                if (child == null) {
                    node.addChild(c);
                    child = node.getChild(c);
                }
                node = child;
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            return searchInNode(word, root);
        }

        // Returns true if the word is in the node.
        private boolean searchInNode(String word, TrieNode node) {
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (!node.children.containsKey(c)) {
                    // If the current character is '.'
                    // check all possible nodes at this level
                    if (c == '.') {
                        for (char cc : node.children.keySet()) {
                            TrieNode child = node.children.get(cc);
                            if (searchInNode(word.substring(i + 1), child)) {
                                return true;
                            }
                        }
                    }
                    return false;
                } else {
                    // If the character is found
                    // go down to the next level in trie
                    node = node.getChild(c);
                }
            }
            return node.isEnd;
        }
    }
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd = false;

        public void addChild(char c) {
            if (!children.containsKey(c)) {
                children.put(c, new TrieNode());
            }
        }
        public TrieNode getChild(char c) {
            if (children.containsKey(c)) {
                return children.get(c);
            }
            return null;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();

        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }
}
