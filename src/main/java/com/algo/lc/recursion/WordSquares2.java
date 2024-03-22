package com.algo.lc.recursion;

import java.util.*;

/**
 * Word Squares
 *
 * Please refer the class WordSquares for question.
 */
public class WordSquares2 {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        List<Integer> wordList = new ArrayList<>();
    }

    int N = 0;
    String[] words = null;
    TrieNode trie = new TrieNode();

    /**
     * Complexity Analysis
     *
     * Time complexity: O(N⋅26^L⋅L), where N is the number of input words and L is the length
     * of a single word.
     *
     * Basically, the time complexity is same with the Approach #1 (O(N⋅26^L)), except that
     * instead of the constant-time access for the function getWordsWithPrefix(prefix), we
     * now need O(L).
     * Space Complexity: O(N⋅L+N⋅L/2)=O(N⋅L), where NNN is the number of words and L is the
     * length of a single word.
     *
     * The first half of the space complexity (i.e. N⋅L is the word indice that we store in
     * the Trie, where we store L times the index for each word.
     *
     * The second half of the space complexity (i.e. N⋅L/2N) is the space took by the
     * prefixes of all words. In the worst case, we have no overlapping among the prefixes.
     *
     * Overall, this approach has the same space complexity as the previous approach.
     * Yet, in running time, it would consume less memory thanks to all the optimization
     * that we have done.
     */
    public List<List<String>> wordSquares(String[] words) {
        this.words = words;
        this.N = words[0].length();

        List<List<String>> results = new ArrayList<>();
        this.buildTrie(words);

        for (String word : words) {
            List<String> wordSquares = new LinkedList<>();
            wordSquares.add(word);
            this.backtracking(1, wordSquares, results);
        }
        return results;
    }

    private void backtracking(int step,
                              List<String> wordSquares,
                              List<List<String>> results)
    {
        if (step == N) {
            results.add(new ArrayList<>(wordSquares));
            return;
        }
        StringBuilder prefix = new StringBuilder();
        for (String word : wordSquares) {
            prefix.append(word.charAt(step));
        }

        for (Integer wordIndex : this.getWordsWithPrefix(prefix.toString())) {
            wordSquares.add(this.words[wordIndex]);
            this.backtracking(step + 1, wordSquares, results);
            wordSquares.remove(wordSquares.size() - 1);
        }
    }

    private void buildTrie(String[] words) {
        for (int wordIndex = 0; wordIndex < words.length; ++wordIndex) {
            String word = words[wordIndex];

            TrieNode node = this.trie;
            for (Character c : word.toCharArray()) {
                if (node.children.containsKey(c)) {
                    node = node.children.get(c);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(c, newNode);
                    node = newNode;
                }
                node.wordList.add(wordIndex);
            }
        }
    }

    private List<Integer> getWordsWithPrefix(String prefix) {
        TrieNode node = this.trie;
        for (Character letter : prefix.toCharArray()) {
            if (node.children.containsKey(letter)) {
                node = node.children.get(letter);
            } else {
                // returns an empty list.
                return new ArrayList<Integer>();
            }
        }
        return node.wordList;
    }

    public static void main(String[] args) {
        WordSquares2 obj = new WordSquares2();
        String[] words = {"area","lead","wall","lady","ball"};
        List<List<String>> lists = obj.wordSquares(words);
        for (List<String> l : lists) {
            for (String w : l) {
                System.out.printf("%s ", w);
            }
            System.out.println();
        }
    }
}
