package com.algo.ae.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Min Max Stack Construction
 *
 * Write a MinMaxStack class for a Min Max Stack. The class should support:
 * - Pushing and popping values on and off the stack.
 * - Peeking at the value at the top of the stack.
 * - Getting both the minimum and the maximum values in the stack
 *   at any given point in time.
 *
 * All class methods, when considered independently, should run in constant
 * time and with constant space.
 *
 * Sample Usage:
 *
 * // All operations below are performed sequentially.
 * MinMaxStack(): - instantiate a MinMaxStack
 * push(5): -
 * getMin(): 5
 * getMax(): 5
 * peek(): 5
 * push(7): -
 * getMin(): - 5
 * getMax(): - 7
 * peek(): 7
 * push(2): -
 * getMin(): - 2
 * getMax(): - 7
 * peek(): 2
 * pop(): 2
 * pop(): 7
 * getMin(): 5
 * getMax(): 5
 * peek(): 5
 */
public class MinMaxStackConstruction {
    static class NumCount {
        int num;
        int count;
        NumCount(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    static class MinMaxStack {
        private Stack<NumCount> minStack = new Stack<>();
        private Stack<NumCount> maxStack = new Stack<>();
        private Stack<Integer> stack = new Stack<>();

        public int peek() {
            return stack.peek();
        }

        public int pop() {
            int top = stack.peek();
            stack.pop();
            if (top == minStack.peek().num) {
                minStack.peek().count -= 1;
                if (minStack.peek().count == 0) {
                    minStack.pop();
                }
            }
            if (top == maxStack.peek().num) {
                maxStack.peek().count -= 1;
                if (maxStack.peek().count == 0) {
                    maxStack.pop();
                }
            }
            return top;
        }

        public void push(Integer number) {
            if (stack.isEmpty()) {
                minStack.push(new NumCount(number, 1));
                maxStack.push(new NumCount(number, 1));
            } else {
                if (number > maxStack.peek().num) {
                    maxStack.push(new NumCount(number, 1));
                } else if (number == maxStack.peek().num) {
                    maxStack.peek().count += 1;
                }

                if (number < minStack.peek().num) {
                    minStack.push(new NumCount(number, 1));
                } else if (number == minStack.peek().num) {
                    minStack.peek().count += 1;
                }
            }
            stack.push(number);
        }

        public int getMin() {
            return minStack.peek().num;
        }

        public int getMax() {
            return maxStack.peek().num;
        }
    }

    static class MinMaxStack2 {
        private List<Integer> minStack = new ArrayList<>();
        private List<Integer> maxStack = new ArrayList<>();
        private List<Integer> stack = new ArrayList<>();

        public int peek() {
            return stack.get(stack.size() - 1);
        }

        public int pop() {
            if (maxStack.get(maxStack.size() - 1) == peek()) {
                maxStack.remove(maxStack.size() - 1);
            }

            if (minStack.get(minStack.size() - 1) == peek()) {
                minStack.remove(minStack.size() - 1);
            }

            return stack.remove(stack.size() - 1);
        }

        public void push(Integer number) {
            if (stack.isEmpty() || number >= maxStack.get(maxStack.size() - 1)) {
                maxStack.add(number);
            }

            if (stack.isEmpty() || number <= minStack.get(minStack.size() - 1)) {
                minStack.add(number);
            }
            stack.add(number);
        }

        public int getMin() {
            return minStack.get(minStack.size() - 1);
        }

        public int getMax() {
            return maxStack.get(maxStack.size() - 1);
        }
    }

    public static void main(String[] args) {

        MinMaxStack minMaxStack = new MinMaxStack();
        minMaxStack.push(5);
        System.out.println("peek() : " + minMaxStack.peek());
        System.out.println("min() : " + minMaxStack.getMin());
        System.out.println("max() : " + minMaxStack.getMax());
        minMaxStack.push(2);
        System.out.println();
        System.out.println("peek() : " + minMaxStack.peek());
        System.out.println("min() : " + minMaxStack.getMin());
        System.out.println("max() : " + minMaxStack.getMax());
        System.out.println("pop() : " + minMaxStack.pop());
        System.out.println();
        MinMaxStack2 minMaxStack2 = new MinMaxStack2();
        minMaxStack2.push(5);
        System.out.println("peek() : " + minMaxStack2.peek());
        System.out.println("min() : " + minMaxStack2.getMin());
        System.out.println("max() : " + minMaxStack2.getMax());
        System.out.println();
        minMaxStack2.push(2);
        System.out.println("peek() : " + minMaxStack2.peek());
        System.out.println("min() : " + minMaxStack2.getMin());
        System.out.println("max() : " + minMaxStack2.getMax());
        System.out.println("pop() : " + minMaxStack2.pop());
    }
}
