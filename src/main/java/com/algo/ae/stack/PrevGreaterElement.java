package com.algo.ae.stack;


import java.util.Arrays;
import java.util.Stack;

public class PrevGreaterElement {
    public static int[] prevGreaterElement(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int [] arr = {10,2,30,40,5,7,20,50};
        int[] res = prevGreaterElement(arr);
        System.out.println("Result array : " + Arrays.toString(res));
    }
}
