package com.algo.ae.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Write a function that takes in a string made up of brackets
 * ( (, [, {, ), ], } ) and other optional characters. The
 * function should return a boolean representing whether the
 * string  is balanced with regards to brackets.
 *
 * A string is said to be balanced if it has as many opening
 * brackets of a certain type as it has closing brackets of that
 * type and if no bracket is unmatched. Note that an opening
 * bracket can't match a corresponding closing bracket that comes
 * before it, and similarly, a closing bracket can't match a
 * corresponding opening bracket that comes after it. Also
 * brackets can't overlap each other as in [(]).
 *
 * Sample input:
 * String = "([])(){}(())()()"
 *
 * Sample output: true // it's balanced
 */
public class BalancedBrackets {
    static Map<Character, Character> pairs = new HashMap<>();

    public static boolean balancedBrackets(String str) {
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');
        if (str.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char lastPushedChar = stack.peek();
                if (lastPushedChar != pairs.get(c)) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean balancedBrackets2(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.add(c);
            } else {
                if (c == ')' || c == '}' || c == ']') {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (c == ')' && stack.peek() != '(' ||
                            c == '}' && stack.peek() != '{' ||
                            c == ']' && stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}