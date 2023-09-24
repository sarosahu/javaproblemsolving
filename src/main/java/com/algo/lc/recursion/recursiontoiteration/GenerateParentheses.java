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

    public static void main(String[] args) {
        GenerateParentheses obj = new GenerateParentheses();
        List<String> result = obj.generateParentheses(4);
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println();
    }
}
