package com.algo.ae.string;

import java.util.Stack;

/**
 * Longest Balanced Substring.
 *
 * Write a function that takes in a string made up of parentheses ('(' and ')'). The function
 * should return an integer representing the length of the longest balanced substring with
 * regards to parentheses.
 *
 * A string is said to be balanced if it has as many opening parentheses as it has closing
 * parentheses and if no parentheses is unmatched. Note that an opening parentheses can't
 * match a closing parentheses that comes before it, and similarly, a closing parentheses
 * can't match an opening parentheses that comes after it.
 *
 * Sample INput:
 * string = "(()))("
 *
 * Sample Output: 4 // The longest balanced substring is "(())".
 *
 */
public class LongestBalancedSubstring {
    // { Brute-force apprach
    // O(n^3) time complexity and O(n) space complexity
    public int longestBalancedSubstring(String string) {
        int maxLen = 0;

        for (int i = 0; i < string.length(); ++i) {
            for (int j = i + 2; j <= string.length(); j += 2) {
                String currSubstring = string.substring(i, j);
                if (isBalanced(currSubstring)) {
                    int currLength = j - i;
                    maxLen = Math.max(maxLen, currLength);
                }
            }
        }
        return maxLen;
    }

    public boolean isBalanced(String string) {
        Stack<Character> openParensStack = new Stack();

        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (c == '(') {
                openParensStack.push('(');
            } else if (openParensStack.size() > 0) {
                openParensStack.pop();
            } else {
                return false;
            }
        }

        return openParensStack.size() == 0;
    }
    // }

    // { O(n) time and O(n) space
    public int longestBalancedSubstring2(String string) {
        // Write your code here.
        int maxLen = 0;
        Stack<Integer> indexStack = new Stack();
        indexStack.push(-1);

        for (int i = 0; i < string.length(); ++i) {
            char currChar = string.charAt(i);
            if (currChar == '(') {
                indexStack.push(i);
            } else {
                indexStack.pop();
                if (indexStack.size() == 0) {
                    indexStack.push(i);
                } else {
                    int balancedSubstringStartIdx = indexStack.peek();
                    int currLen = i - balancedSubstringStartIdx;
                    maxLen = Math.max(maxLen, currLen);
                }
            }
        }
        return maxLen;
    }
    // }
    // { O(n) time and O(1) space
    public int longestBalancedSubstringE(String string) {
        // Write your code here.
        int maxLen = 0;
        int openingCount = 0;
        int closingCount = 0;

        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);

            if (c == '(') {
                openingCount += 1;
            } else {
                closingCount += 1;
            }

            if (openingCount == closingCount) {
                maxLen = Math.max(maxLen, closingCount * 2);
            } else if (closingCount > openingCount) {
                openingCount = 0;
                closingCount = 0;
            }
        }

        openingCount = 0;
        closingCount = 0;

        for (int i = string.length() - 1; i >= 0; --i) {
            char c = string.charAt(i);

            if (c == '(') {
                openingCount += 1;
            } else {
                closingCount += 1;
            }

            if (openingCount == closingCount) {
                maxLen = Math.max(maxLen, openingCount * 2);
            } else if (openingCount > closingCount) {
                openingCount = 0;
                closingCount = 0;
            }
        }
        return maxLen;
    }
    // }
}
