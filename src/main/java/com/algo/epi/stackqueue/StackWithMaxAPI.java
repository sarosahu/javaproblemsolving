package com.algo.epi.stackqueue;

import com.algo.util.Pair;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * EPI. Stacks and Queues
 * 9.1 Implement a stack with max API
 */
public class StackWithMaxAPI {
    private Stack<Integer> stk = new Stack<>();
    private Stack<Pair<Integer, Integer>> maxWithFreqStack = new Stack<>();

    public int peek() {
        if (!stk.isEmpty()) {
            return stk.peek();
        }
        throw new EmptyStackException();
    }

    public int max() {
        if (!stk.isEmpty()) {
            return maxWithFreqStack.peek().getKey();
        }
        throw new EmptyStackException();
    }

    public int pop() {
        int curr = stk.pop();
        if (curr == maxWithFreqStack.peek().getKey()) {
            int currMaxFreq = maxWithFreqStack.peek().getValue();
            currMaxFreq -= 1;
            maxWithFreqStack.pop();
            if (currMaxFreq != 0) {
                maxWithFreqStack.push(new Pair<>(curr, currMaxFreq));
            }
        }
        return curr;
    }

    public void push(int e) {
        stk.push(e);
        if (maxWithFreqStack.isEmpty()) {
            maxWithFreqStack.push(new Pair<>(e, 1));
        } else {
            int currMax = maxWithFreqStack.peek().getKey();
            if (e > currMax) {
                maxWithFreqStack.push(new Pair<>(e, 1));
            } else if (e == currMax) {
                int currMaxFreq = maxWithFreqStack.peek().getValue();
                maxWithFreqStack.pop();
                maxWithFreqStack.push(new Pair<>(e, currMaxFreq + 1));
            }
        }
    }

    public static void main(String[] args) {
        StackWithMaxAPI stk = new StackWithMaxAPI();
        stk.push(5);
        System.out.println("max : " + stk.max());
        stk.push(10);
        System.out.println("max : " + stk.max());
        stk.push(8);
        System.out.println("max : " + stk.max());
        stk.push(10);
        System.out.println("max : " + stk.max());
        stk.push(7);
        System.out.println("max : " + stk.max());
        stk.push(11);
        System.out.println("max : " + stk.max());
        stk.push(12);
        System.out.println("max : " + stk.max());

        stk.pop();
        System.out.println("max : " + stk.max());
        stk.pop();
        System.out.println("max : " + stk.max());
        stk.pop();
        System.out.println("max : " + stk.max());
        stk.pop();
        System.out.println("max : " + stk.max());
        stk.pop();
        System.out.println("max : " + stk.max());
        stk.pop();
        System.out.println("max : " + stk.max());

        // This should throw an exception.
        stk.pop();
        System.out.println("max : " + stk.max());
    }
}
