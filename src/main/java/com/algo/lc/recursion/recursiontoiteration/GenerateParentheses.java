package com.algo.lc.recursion.recursiontoiteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 */
public class GenerateParentheses {
    /**
     *
     * @param n
     * @return List of valid parentheses in string
     *
     * Brute Force approach:
     * Complexity Analysis
     * Time complexity: O(n * 2^2n)
     * We are generating all possible strings of length 2n. At each character, we have two choices:
     * choosing ( or ), which means there are a total of 2^{2n} unique strings.
     *
     * For each string of length 2n, we need to iterate through each character to verify it is a
     * valid combination of parentheses, which takes an average of O(n) time.
     *
     * Space complexity: O(n * 2^{2n})
     * While we don't count the answer as part of the space complexity, for those interested, it is the
     * nth Catalan number, which is asymptotically bounded by {4^n}/{n * sqrt{n}}.
     * Thus answer takes {4^n}/{sqrt{n}} space.
     */
    public List<String> generateParentheses(int n) {
        List<String> answer = new ArrayList<>();
        Queue<String> queue = new LinkedList<>(Arrays.asList(""));

        System.out.println("len : " + queue.size());
        while (!queue.isEmpty()) {
            String currStr = queue.poll();

            // If the length of currStr is 2*n and if it is valid
            // then add it to the answer
            if (currStr.length() == 2 * n) {
                if (isValid(currStr)) {
                    answer.add(currStr);
                }
                continue;
            }
            queue.offer(currStr + ")");
            queue.offer(currStr + "(");
            System.out.println("len2 : " + queue.size());
        }
        return answer;
    }

    private boolean isValid(String str) {
        int leftCount = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                ++leftCount;
            } else {
                --leftCount;
            }
            if (leftCount < 0) {
                return false;
            }
        }
        return leftCount == 0;
    }

    /**
     *
     * @param n
     * @return List of valid parentheses in string
     *
     * Time complexity: O({4^n}/{sqrt{n}})
     * We only track the valid prefixes during the backtracking procedure. As explained
     * in the approach 1 time complexity analysis, the total number of valid parentheses
     * strings is O({4^n}/{n * sqrt{n}})
     * When considering each valid string, it is important to note that we use a mutable instance
     * (StringBuilder in Java, list in Python etc.). As a result, in order to add each instance
     * of a valid string to answer, we must first convert it to a string. This process brings
     * an additional nnn factor in the time complexity.
     *
     * Space complexity: O(n)
     *
     * The space complexity of a recursive call depends on the maximum depth of the recursive
     * call stack, which is 2n. As each recursive call either adds a left parenthesis or a
     * right parenthesis, and the total number of parentheses is 2n. Therefore, at most O(n)
     * levels of recursion will be created, and each level consumes a constant amount of space.
     */
    public List<String> generateParenthesesE(int n) {
        List<String> answer = new ArrayList<>();
        backtracking(answer, new StringBuilder(), 0, 0, n);
        return answer;
    }

    private void backtracking(List<String> answer,
                              StringBuilder currStr,
                              int leftCount,
                              int rightCount,
                              int n) {
        if (currStr.length() == 2 * n) {
            answer.add(currStr.toString());
            return;
        }
        if (leftCount < n) {
            currStr.append("(");
            backtracking(answer, currStr, leftCount + 1, rightCount, n);
            currStr.deleteCharAt(currStr.length() - 1);
        }
        if (leftCount > rightCount) {
            currStr.append(")");
            backtracking(answer, currStr, leftCount, rightCount + 1, n);
            currStr.deleteCharAt(currStr.length() - 1);
        }
    }
    public static void main(String[] args) {
        GenerateParentheses obj = new GenerateParentheses();
        List<String> result = obj.generateParenthesesE(3);
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println();
    }
}
