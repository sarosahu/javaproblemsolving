package com.algo.lc.treegraphs.bfs;
/**
 * Alien Dictionary
 *
 * here is a new alien language that uses the English alphabet.
 * However, the order of the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary.
 * Now it is claimed that the strings in words are sorted lexicographically
 * by the rules of this new language.
 *
 * If this claim is incorrect, and the given arrangement of string in words
 * cannot correspond to any order of letters, return "".
 *
 * Otherwise, return a string of the unique letters in the new alien language
 * sorted in lexicographically increasing order by the new language's rules.
 * If there are multiple solutions, return any of them.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {

    public static String alienOrder(String[] words) {
        List<String> wordList = new ArrayList<>();
        wordList.add(words[0]);
        for (int i = 1; i < words.length; ++i) {
            String curr = words[i];
            String prev = wordList.get(wordList.size() - 1);
            if (!curr.equals(prev)) {
                if (prev.startsWith(curr)) {
                    return "";
                }
                wordList.add(curr);
            }
        }

        // Step 1. Fetch unique chars from wordList and store it in a set
        Set<Character> chars = new HashSet<>();
        for (String word : wordList) {
            for (char c : word.toCharArray()) {
                chars.add(c);
            }
        }

        // Step 2 - create a graph
        Graph graph = new Graph(chars);

        // Step 3 - add dependencies
        for (int i = 0; i < wordList.size() - 1; ++i) {
            String curr = words[i];
            String next = words[i + 1];
            addDependencies(graph, curr, next);
        }

        String output = graph.getTopologicalSort();

        return output.length() < chars.size() ? "" : output;
    }

    private static void addDependencies(Graph graph, String s1, String s2) {
        int i = 0;
        while (i < s1.length() && i < s2.length()) {
            if (s1.charAt(i) == s2.charAt(i)) {
                ++i;
            } else {
                graph.addDependencies(new char[] {s1.charAt(i), s2.charAt(i)});
                break;
            }
        }
    }

    static class Node {
        private int indegree;
        private char value;
        List<Node> edges = new ArrayList<>();
        public Node(char value) {
            this.indegree = 0;
            this.value = value;
        }
        public void addEdge(Node node) {
            this.edges.add(node);
        }
        public int getIndegree() {
            return this.indegree;
        }
        public void setIndegree(int indegree) {
            this.indegree = indegree;
        }
        public char getValue() {
            return this.value;
        }
        public List<Node> getEdges() {
            return this.edges;
        }
    }

    static class Graph {
        List<Node> nodes = new ArrayList<>();
        Map<Character, Node> charToNode = new HashMap<>();
        public Graph(Set<Character> charList) {
            charList.forEach(c -> {
                Node node = new Node(c);
                nodes.add(node);
                charToNode.put(c, node);
            });
        }

        public void addDependencies(char[] dependency) {
                char src = dependency[0];
                char dest = dependency[1];
                Node srcNode = charToNode.get(src);
                Node destNode = charToNode.get(dest);
                destNode.setIndegree(destNode.getIndegree() + 1);
                srcNode.addEdge(destNode);
        }

        public String getTopologicalSort() {
            Queue<Node> queue = new LinkedList<>();
            for (Node node : this.nodes) {
                if (node.getIndegree() == 0) {
                    queue.add(node);
                }
            }
            StringBuilder b = new StringBuilder();
            while (!queue.isEmpty()) {
                Node curr = queue.poll();
                b.append(curr.getValue());
                for (Node edgeNode : curr.getEdges()) {
                    edgeNode.setIndegree(edgeNode.getIndegree() - 1);
                    if (edgeNode.getIndegree() == 0) {
                        queue.add(edgeNode);
                    }
                }
            }
            return b.toString();
        }
    }

    public static void main(String[] args) {
        //String[] words = {"wrt","wrf","er","ett","rftt"};
        String[] words = {"abx", "ab"};
        String output = alienOrder(words);
        System.out.println("Output : " + output);
    }
}
