package com.algo.ae.stack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Shorten Path
 *
 * Write a function that takes a non-empty string representing a valid unix-shell path
 * and returns a shortened version of that path.
 *
 * A path is a notation that represents the location of a file or directory in a file
 * system.
 *
 * A path can be an absolute, meaning that it starts at the root directory in a file
 * system, or a relative path, meaning it starts at the current directory in a file
 * system.
 *
 * In a unix-like operating system, a path is bound by the following rules:
 * - The root directory is represented by a /. This means that if a path starts with
 *   / , it's absolute path; if it doesn't, it's a relative path.
 * - The symbol / otherwise represents the directory separator. This means that path
 *   /foo/bar is the location of the directory bar inside the directory foo, which is
 *   itself located inside the root directory.
 * - The symbol .. represents the parent directory. This means that accessing files or
 *   directories in /foo/bar/.. is equivalent to accessing the files or directories in
 *   /foo .
 * - They symbol . represents the current directory. This means that accessing files or
 *   directories in /foo/bar/. is equivalent to accessing files or directories in
 *   /foo/bar .
 * - The symbols / and . can be repeated sequentially without consequence; the symbol ..
 *   can't, however, because repeating it sequentially means going further up in parent
 *   directories. For example, /foo/bar/baz/././. and /foo/bar/baz are equivalent path,
 *   but /foo/bar/baz/../../../ and /foo/bar/baz definitely are not. The only exception
 *   is with the root directory: /../../.. and / are equivalent, because root directory
 *   has no parent directory, which means that repeatedly accessing parent directories
 *   does nothing.
 *
 * Note that the shortened version of the path must be equivalent to the original path.
 * In other words, it must point to the same file or directory as the original path.
 *
 * Sample Input:
 * path = "/foo/../test/../test/-../foo//bar/./baz"
 *
 * Sample Output: "/foo/bar/baz"  // This path is equivalent to the input path.
 *
 */
public class ShortenPath {
    public static String shortenPath(String path) {

        String[] tokens = path.split("/");
        Stack<String> stk = new Stack<>();
        for (int i = 0; i < tokens.length; ++i) {
            String currString = tokens[i];
            if (currString.equals(".")) {
                continue;
            }
            if (currString.isEmpty()) {
                if (stk.isEmpty()) {
                    stk.push("");
                }
                continue;
            }
            if (currString.equals("..")) {
                if (stk.isEmpty()) {
                    stk.push(currString);
                } else if (stk.peek().isEmpty()) {
                    continue;
                } else if (stk.peek().equals("..")) {
                    stk.push(currString);
                } else {
                    stk.pop();
                }
            } else {
                stk.push(currString);
            }
        }
        if ((stk.size() == 1 && stk.peek().isEmpty()) || stk.isEmpty()) {
            return "/";
        }
        List<String> list = new ArrayList<>();
        while (!stk.isEmpty()) {
            list.add(stk.peek());
            stk.pop();
        }
        Collections.reverse(list);

        return String.join("/", list);
    }

    public static String shortenPath2(String path) {
        boolean isAbsolutePath = path.charAt(0) == '/';

        String[] tokens = path.split("/");
        List<String> tokenList = Arrays.asList(tokens);
        List<String> filteredTokens =
                tokenList.stream()
                        .filter(token -> isImportantToken(token))
                        .collect(Collectors.toList());

        Stack<String> stack = new Stack<>();
        if (isAbsolutePath) {
            stack.add("");
        }

        for (String token : filteredTokens) {
            if (token.equals("..")) {
                if (stack.isEmpty() || stack.peek().equals("..")) {
                    stack.add(token);
                } else if (!stack.peek().isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.add(token);
            }
        }
        if (stack.size() == 1 && stack.peek().isEmpty()) {
            return "/";
        }
        return String.join("/", stack);
    }

    public static boolean isImportantToken(String token) {
        return token.length() > 0 && !token.equals(".");
    }

    public static void main(String[] args) {
        String shortendPath = shortenPath2("/foo/../test/../test/../foo//bar/./baz");
        System.out.println("Shorted path : " + shortendPath);
    }
}
