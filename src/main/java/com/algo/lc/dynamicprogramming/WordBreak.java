package com.algo.lc.dynamicprogramming;

import java.util.*;

public class WordBreak {
    /**
     * Time: O(N^3)
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> cache = new HashSet<>();
        for (String str : wordDict) {
            cache.add(str);
        }
        int[] table = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            String curr = s.substring(0, i + 1);
            if (cache.contains(curr)) {
                table[i] = i + 1;
            }

            for (int j = 0; j < i && table[i] == 0; ++j) {
                if (table[j] != 0) {
                    String next = s.substring(j + 1, i + 1);
                    if (cache.contains(next)) {
                        table[i] = i - j;
                    }
                }
            }
        }
        if (table[s.length() - 1] > 0) {
            return true;
        }
        return false;
    }

    /**
     *
     * Time complexity: O(n3+m⋅k
     * There are O(n) nodes. Because of seen, we never visit a node more than once.
     * At each node, we iterate over the nodes in front of the current node, of which
     * there are O(n). For each node end, we create a substring, which also costs O(n).
     *
     * Therefore, handling a node costs O(n^2), so the BFS could cost up to O(n^3).
     * Finally, we also spent O(m⋅k) to create the set words.
     *
     * Space: O(n + m.k)
     * We use O(n) space for queue and seen. We use O(m⋅k) space for the set words.
     */
    public static boolean wordBreakBfs(String s, List<String> wordDict) {
        Set<String> cache = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean [] seen = new boolean[s.length() + 1];
        queue.offer(0);
        while (!queue.isEmpty()) {
            int currStart = queue.poll();
            if (currStart == s.length()) {
                return true;
            }
            for (int end = currStart + 1; end <= s.length(); ++end) {
                if (seen[end]) {
                    continue;
                }
                String currWord = s.substring(currStart, end);
                if (cache.contains(currWord)) {
                    seen[end] = true;
                    queue.offer(end);
                }
            }
        }
        return false;
    }

    public static boolean wordBreakTopDown(String s, List<String> wordDict) {
        Set<String> cache = new HashSet<>(wordDict);
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return wordBreakTopDown(s.length() - 1, s, cache, memo);
    }

    private static boolean wordBreakTopDown(int idx,
                                 String s,
                                 Set<String> cache,
                                 int[] memo) {

        if (idx < 0) {
            return true;
        }
        if (memo[idx] != -1) {
            return memo[idx] == 1;
        }

        for(int i = idx; i >= 0; --i) {
            String curr = s.substring(i, idx + 1);
            if (cache.contains(curr) && wordBreakTopDown(i - 1, s, cache, memo)) {
                memo[idx] = 1;
                return true;
            }
        }
        memo[idx] = 0;
        return false;
    }

    /**
     * Top-down dynamic programming approach
     *
     * Given n as the length of s, m as the length of wordDict, and k as the average
     * length of the words in wordDict -
     * Time: O(n.m.k)
     * There are n states of dp(i). Because of memoization, we only calculate each state once.
     * To calculate a state, we iterate over m words, and for each word perform some substring
     * operations which costs O(k). Therefore, calculating a state costs O(m⋅k), and we need to
     * calculate O(n) states.
     *
     * Space: O(n)
     * The data structure we use for memoization and the recursion call stack can use up to O(n) space.
     */
    public static boolean wordBreakTopDownO(String s, List<String> wordDict) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return wordBreakTopDownO(s.length() - 1, s, wordDict, memo);
    }

    private static boolean wordBreakTopDownO(int idx,
                                    String s,
                                    List<String> wordDict,
                                    int[] memo)
    {
        // Base case
        if (idx < 0) {
            return true;
        }
        if (memo[idx] != -1) {
            return memo[idx] == 1;
        }
        for (String word : wordDict) {
            // Handle out of bound case
            if (idx - word.length() + 1 < 0) {
                continue;
            }
            String curr = s.substring(idx - word.length() + 1, idx + 1);
            if (curr.equals(word) && wordBreakTopDownO(idx - word.length(), s, wordDict, memo)) {
                memo[idx] = 1;
                return true;
            }
        }
        memo[idx] = 0;
        return false;
    }

    /**
     * Bottom-up dynamic programming
     *
     * Given n as the length of s, m as the length of wordDict, and k as the average
     * length of the words in wordDict -
     * Time: O(n.m.k)
     * There are n states of dp(i). Because of memoization, we only calculate each state once.
     * To calculate a state, we iterate over m words, and for each word perform some substring
     * operations which costs O(k). Therefore, calculating a state costs O(m⋅k), and we need to
     * calculate O(n) states.
     *
     * Space: O(n)
     * We use an array dp of length n.
     */
    public static boolean wordBreakBU(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            for (String word : wordDict) {
                // Handle out of bounds case
                if (i < word.length() - 1) {
                    continue;
                }

                if (i == word.length() - 1 || dp[i - word.length()]) {
                    String curr = s.substring(i - word.length() + 1, i + 1);
                    if (curr.equals(word)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

    /**
     * Trie approach
     *
     * Given n as the length of s, m as the length of wordDict, and k as the average
     * length of the words in wordDict,
     *
     * Time complexity: O(n2+m⋅k)
     * Building the trie involves iterating over all characters of all words. This costs O(m⋅k)
     * Once we build the trie, we calculate dp. For each i, we iterate over all the indices after i.
     * We have a basic nested for loop which costs O(n^2) to handle all dp[i].
     *
     * Space complexity: O(n+m⋅k)
     * The dp array takes O(n) space. The trie can have up to m⋅k nodes in it.
     */
    static class TrieNode {
        boolean isWord;
        Map<Character, TrieNode> children;
        TrieNode() {
            this.children = new HashMap<>();
        }
    }
    public static boolean wordBreakTrie(String s, List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
            }
            curr.isWord = true;
        }

        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            if (i == 0 || dp[i - 1]) {
                TrieNode curr = root;
                for (int j = i; j < s.length(); ++j) {
                    char c = s.charAt(j);
                    if (!curr.children.containsKey(c)) {
                        // no words exist
                        break;
                    }
                    curr = curr.children.get(c);
                    if (curr.isWord) {
                        dp[j] = true;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        boolean isWordBreakPossible = wordBreak("leetcode", Arrays.asList("leet", "code"));
        if (isWordBreakPossible) {
            System.out.println("Possible ");
        } else {
            System.out.println("Not possible");
        }

        isWordBreakPossible = wordBreakBfs("applepenapplea", Arrays.asList("a", "apple", "pen"));
        if (isWordBreakPossible) {
            System.out.println("Possible.");
        } else {
            System.out.println("Not possible.");
        }

        isWordBreakPossible = wordBreakBfs("applepenappla", Arrays.asList("a", "apple", "pen"));
        if (isWordBreakPossible) {
            System.out.println("Possible.");
        } else {
            System.out.println("Not possible.");
        }

        isWordBreakPossible = wordBreakTopDown("leetcode", Arrays.asList("leet", "code"));
        if (isWordBreakPossible) {
            System.out.println("Possible ");
        } else {
            System.out.println("Not possible");
        }

        isWordBreakPossible = wordBreakTopDownO("leetcode", Arrays.asList("leet", "code"));
        if (isWordBreakPossible) {
            System.out.println("Possible ");
        } else {
            System.out.println("Not possible");
        }

        isWordBreakPossible = wordBreakBU("leetcode", Arrays.asList("leet", "code"));
        if (isWordBreakPossible) {
            System.out.println("Possible ");
        } else {
            System.out.println("Not possible");
        }

        isWordBreakPossible = wordBreakTrie("leetcode", Arrays.asList("leet", "code"));
        if (isWordBreakPossible) {
            System.out.println("Possible ");
        } else {
            System.out.println("Not possible");
        }
    }
}
