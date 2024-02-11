package com.algo.lc.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Replace Words
 *
 * In English, we have a concept called root, which can be followed by some other
 * word to form another longer word - let's call this word successor. For example,
 * when the root "an" is followed by the successor word "other", we can form a new
 * word "another".
 *
 * Given a dictionary consisting of many roots and a sentence consisting of words
 * separated by spaces, replace all the successors in the sentence with the root
 * forming it. If a successor can be replaced by more than one root, replace it
 * with the root that has the shortest length.
 *
 * Return the sentence after the replacement.
 *
 * Example 1:
 *
 * Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 *
 * Example 2:
 *
 * Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * Output: "a a b c"
 */
public class ReplaceWords {
    private TrieNode root = new TrieNode();
    public String replaceWords(List<String> dictionary, String sentence) {
        // Create a Trie for dictionary.
        for (String str : dictionary) {
            insert(root, str);
        }
        StringBuilder sb = new StringBuilder();
        int start = 0, end = 0;
        for (int i = 0; i < sentence.length(); ++i) {
            char c = sentence.charAt(i);
            end = i;
            if (c == ' ' || i == sentence.length() - 1) {
                String currWord;
                if (c == ' ') {
                    currWord = sentence.substring(start, end);
                } else {
                    currWord = sentence.substring(start, end + 1);
                }
                start = i + 1;
                // Check this currWord if it's prefix is present in dictionary.
                String prefix = getPrefixIfPresent(root, currWord);
                if (prefix != null) {
                    sb.append(prefix);
                } else {
                    sb.append(currWord);
                }
                if (c == ' ') {
                    sb.append(' ');
                }
            }
        }
        return sb.toString();
    }

    public void insert(TrieNode root, String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            node.addChild(c);
            node = node.getChild(c);
        }
        node.setEnd(true);
    }

    public String getPrefixIfPresent(TrieNode root, String s) {
        TrieNode node = root;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            TrieNode child = node.getChild(c);
            if (child == null) {
                return null;
            }
            sb.append(c);
            node = child;
            if (node.isEnd()) {
                return sb.toString();
            }
        }
        return null;
    }

    static class TrieNode {
        private Map<Character, TrieNode> children = new HashMap<>();
        private boolean isEnd = false;

        public TrieNode getChild(char c) {
            if (this.children.containsKey(c)) {
                return this.children.get(c);
            }
            return null;
        }

        public boolean isEnd() {
            return this.isEnd;
        }
        public void setEnd(boolean val) {
            this.isEnd = val;
        }
        public void addChild(char c) {
            if (!this.children.containsKey(c)) {
                this.children.put(c, new TrieNode());
            }
        }
    }

    public static void main(String[] args) {
        ReplaceWords obj = new ReplaceWords();
        List<String> dictionary = Arrays.asList("cat", "bat", "rat");
        String sentence = "the cattle was rattled by the battery";
        String replacedSentence = obj.replaceWords(dictionary, sentence);
        System.out.println("Original: " + sentence);
        System.out.println("Replaced: " + replacedSentence);
    }
}
