package com.algo.ae.stack;

import java.util.ArrayList;

/**
 * Sort Stack
 *
 * Write a function that takes in an array of integers representing a Stack,
 * recursively sorts a stack in place(i.e., doesn't create a brand new array),
 * and returns it.
 *
 * The array must be treated as a stack, with the end of the array as the top
 * of the stack. Therefore, you're only allowed to
 * - Pop elements from the top of the stack by removing elements from the end
 *   of the array using the built-in .pop() method in your programming language.
 * - Push elements to the top of the stack by appending elements to the end of
 *   the array using the built-in .append() method in your programming
 *   language of choice.
 * - Peek at the element on top of the stack by accessing the last element in
 *   the array.
 *
 * You are not allowed to perform any other operations on the input array,
 * including accessing elements (except for the last element), moving
 * elements, etc.. You're also not allowed to use any other data structure,
 * and your solution must be recursive.
 *
 * Sample Input: stack = [-5, 2, -2, 4, 3, 1]
 *
 * Sample Output: [-5, -2, 1, 2, 3, 4]
 */
public class SortStack {
    public ArrayList<Integer> sortStack(ArrayList<Integer> stack) {
        // Write your code here.
        if (stack.isEmpty()) {
            return stack;
        }
        int lastIndex = stack.size() - 1;
        int currItem = stack.get(lastIndex);
        stack.remove(lastIndex);
        sortStack(stack);
        insertStack(currItem, stack);

        return stack;
    }

    public void insertStack(int e, ArrayList<Integer> stack) {
        int lastIndex = stack.size() - 1;
        if (stack.isEmpty() || e >= stack.get(lastIndex)) {
            stack.add(e);
        } else {
            int top = stack.get(lastIndex);
            stack.remove(lastIndex);
            insertStack(e, stack);
            stack.add(top);
        }
    }
}
