package com.my.algorithm.stack;

import java.util.Stack;

public class StackMin {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public StackMin() {
        this.dataStack = new Stack<Integer>();
        this.minStack = new Stack<Integer>();
    }

    public void push(int data) {
        if (minStack.isEmpty()) {
            minStack.push(data);
        } else {
            int min = this.minStack.peek() > data ? data : this.minStack.peek();
            this.minStack.push(min);
        }
        this.dataStack.push(data);
    }

    public int pop() {
        if (this.dataStack.isEmpty()) {
            throw new RuntimeException("the stack is empty");
        }
        this.minStack.pop();
        return this.dataStack.pop();
    }

    public int getMin() {
        if (this.minStack.isEmpty()) {
            throw new RuntimeException("the stack is empty");
        }
        return this.minStack.peek();
    }
}
