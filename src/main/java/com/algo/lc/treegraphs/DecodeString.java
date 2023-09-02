package com.algo.lc.treegraphs;

import java.util.Stack;

public class DecodeString {
    public static String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> charStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                if (numStack.isEmpty()) {
                    numStack.push(c - '0');
                } else if (i != 0 && Character.isDigit(s.charAt(i - 1))) {
                    int currDigit = numStack.pop();
                    int newDigit = currDigit*10 + (c - '0');
                    numStack.push(newDigit);
                } else {
                    numStack.push(c - '0');
                }
            } else if (c == '[') {
                charStack.push(c);
            } else if (c == ']') {
                StringBuilder sb = strStack.pop();
                charStack.pop();
                int n = numStack.pop();
                String str2 = sb.toString();
                for (int j = 1; j < n; ++j) {
                    sb.append(str2);
                }
                if (strStack.isEmpty()) {
                    result.append(sb);
                } else {
                    strStack.peek().append(sb);
                }
            } else {
                if (strStack.isEmpty()) {
                    if (charStack.isEmpty()) {
                        result.append(c);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(c);
                        strStack.push(sb);
                    }
                } else {
                    if (charStack.size() > strStack.size()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(c);
                        strStack.push(sb);
                    } else {
                        strStack.peek().append(c);
                    }
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        //String s = "3[a]2[bc]";
        //String s = "3[a2[c]]";
        String s = "10[leetcode]";
        String decodedString = decodeString(s);
        System.out.println("Decoded string : " + decodedString);
    }
}
