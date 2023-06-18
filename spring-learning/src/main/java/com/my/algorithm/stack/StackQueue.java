package com.my.algorithm.stack;

import java.util.Queue;
import java.util.Stack;

public class StackQueue {
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public StackQueue () {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void add(int data) {
        this.pushStack.add(data);
        pushToPop();
    }

    private void pushToPop() {
        if (this.popStack.isEmpty()) {
            while (!this.pushStack.isEmpty()) {
                this.popStack.push(this.pushStack.pop());
            }
        }
    }

    public Integer poll() {
        if (this.pushStack.isEmpty() && this.popStack.isEmpty()) {
            throw new RuntimeException("the stack is empty");
        }
        pushToPop();
        return this.popStack.pop();
    }
}
