package com.algo.lc.backtrackordp;

import java.util.ArrayList;
import java.util.List;

public class GetAllPalindromicPartitioning {
    public static
    List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        dfs(s, 0, new ArrayList<String>(), result);
        return result;
    }

    public static void dfs(String s,
                           int start,
                           List<String> currStringList,
                           List<List<String>> result) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(currStringList));
        }
        for (int end = start; end < s.length(); ++end) {
            if (isPalindrom(s, start, end)) {
                currStringList.add(s.substring(start, end + 1));
                dfs(s, end + 1, currStringList, result);
                currStringList.remove(currStringList.size() - 1);
            }
        }
    }

    public static boolean
    isPalindrom(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            } else {
                ++start;
                --end;
            }
        }
        return true;
    }

    public static List<List<String>>
    partitionE(String s) {
        List<List<String>> result = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        dfsE(s, dp, 0, new ArrayList<String>(), result);
        return result;
    }

    public static void dfsE(String s,
                            boolean[][] dp,
                            int start,
                            List<String> currStrList,
                            List<List<String>> result) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(currStrList));
        }
        for (int end = start; end < s.length(); ++end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (startChar == endChar && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                currStrList.add(s.substring(start, end + 1));
                dfsE(s, dp, end + 1, currStrList, result);
                currStrList.remove(currStrList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String s = "abba";
        List<List<String>> result = partitionE(s);
    }
}
