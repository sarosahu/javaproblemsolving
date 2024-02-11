package com.algo.lc.treegraphs.disjointset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 737. Sentence Similarity II

 * We can represent a sentence as an array of words, for example, the sentence
 * "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].
 *
 * Given two sentences sentence1 and sentence2 each represented as a string array and given an
 * array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two
 * words xi and yi are similar.
 *
 * Return true if sentence1 and sentence2 are similar, or false if they are not similar.
 *
 * Two sentences are similar if:
 *
 * They have the same length (i.e., the same number of words)
 * sentence1[i] and sentence2[i] are similar.
 * Notice that a word is always similar to itself, also notice that the similarity relation is
 * transitive. For example, if the words a and b are similar, and the words b and c are similar,
 * then a and c are similar.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"],
 * similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
 * Output: true
 * Explanation: The two sentences have the same length and each word i of sentence1 is
 * also similar to the corresponding word in sentence2.
 *
 *
 * Example 2:
 *
 * Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"],
 * similarPairs = [["manga","onepiece"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
 * Output: true
 * Explanation: "leetcode" --> "platform" --> "anime" --> "manga" --> "onepiece".
 * Since "leetcode is similar to "onepiece" and the first two words are the same, the two sentences are similar.
 *
 * Example 3:
 *
 * Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"],
 * similarPairs = [["manga","hunterXhunter"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
 * Output: false
 * Explanation: "leetcode" is not similar to "onepiece".
 *
 *
 * Constraints:
 *
 * 1 <= sentence1.length, sentence2.length <= 1000
 * 1 <= sentence1[i].length, sentence2[i].length <= 20
 * sentence1[i] and sentence2[i] consist of lower-case and upper-case English letters.
 * 0 <= similarPairs.length <= 2000
 * similarPairs[i].length == 2
 * 1 <= xi.length, yi.length <= 20
 * xi and yi consist of English letters.
 */
public class AreSentencesSimilarTwo {
    public boolean
    areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }

        UnionFind uf = new UnionFind();
        for (List<String> pair : similarPairs) {
            // Create the graph using hashed values of the similarPairs
            uf.addWord(pair.get(0));
            uf.addWord(pair.get(1));
            uf.union(pair.get(0), pair.get(1));
        }
        for (int i = 0; i < sentence1.length; ++i) {
            String word1 = sentence1[i];
            String word2 = sentence2[i];
            if (word1.equals(word2)) {
                continue;
            }
            if (uf.isWordPresent(word1) && uf.isWordPresent(word2) &&
                    uf.find(word1).value.equals(uf.find(word2).value)) {
                continue;
            }
            return false;
        }
        return true;
    }

    static class UnionFind {
        Map<String, Node> nodes = new HashMap<>();

        public void addWord(String x) {
            if (!nodes.containsKey(x)) {
                nodes.put(x, new Node(x));
            }
        }

        public boolean isWordPresent(String x) {
            if (nodes.containsKey(x)) {
                return true;
            }
            return false;
        }

        // The input string is assumed to be present.
        public Node find(String x) {
            Node curr = nodes.get(x);
            if (curr.parent != curr) {
                curr.parent = find(curr.parent.value);
            }
            return curr.parent;
        }

        public void union(String x, String y) {
            Node parentx = find(x);
            Node parenty = find(y);
            if (parentx.value.equals(parenty.value)) {
                return;
            }
            if (parentx.rank > parenty.rank) {
                parenty.parent = parentx;
            } else if (parenty.rank > parentx.rank) {
                parentx.parent = parenty;
            } else {
                parenty.parent = parentx;
                parentx.rank += 1;
            }
        }
    }

    static class Node {
        String value;
        Node parent;
        int rank = 0;
        public Node(String value) {
            this.value = value;
            this.parent = this;
        }
    }

    public static void main(String[] args) {
        String[] sentence1 = {"great","acting","skills"};
        String[] sentence2 = {"fine","drama","talent"};
        List<List<String>> similarParis = Arrays.asList(
                Arrays.asList("great","good"),
                Arrays.asList("fine","good"),
                Arrays.asList("drama","acting"),
                Arrays.asList("skills","talent")
        );
        AreSentencesSimilarTwo obj = new AreSentencesSimilarTwo();
        boolean areSimilar = obj.areSentencesSimilarTwo(sentence1, sentence2, similarParis);
        if (areSimilar) {
            System.out.printf("Sentence [%s] and [%s] are similar.", Arrays.asList(sentence1), Arrays.asList(sentence2));
        } else {
            System.out.printf("Sentence [%s] and [%s] are not similar.", Arrays.asList(sentence1), Arrays.asList(sentence2));
        }
    }
}
