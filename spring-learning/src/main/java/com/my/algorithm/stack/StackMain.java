package com.my.algorithm.stack;

public class StackMain {
    public static void main(String[] args) {
//        getStackMin();
//        stackQueue();

    }

    private static void stackQueue() {
        StackQueue stackQueue = new StackQueue();
        int[] arr = new int[]{4, 5, 2, 7, 3, 9, 6, 8, 1};
        for (int i = 0; i < arr.length; i++) {
            stackQueue.add(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(String.format("第 %s 轮, 出队值：%s", i,  stackQueue.poll()));
        }
    }

    // TODO 待优化
    private static void getStackMin() {
        int[] arr = new int[]{4, 5, 2, 7, 3, 9, 6, 8, 1};
        StackMin stackMin = new StackMin();
        for (int i = 0; i < arr.length; i++) {
            stackMin.push(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(String.format("第 %s 轮, 最小值: %s, 出栈值：%s", i, stackMin.getMin(), stackMin.pop()));
        }
    }


}
