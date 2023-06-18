package com.my.concurrent;

import java.util.concurrent.TimeUnit;

public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous), "JoinThread-" + i);
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(String.format("Thread Name = %s ", Thread.currentThread().getName()));
    }

    static class Domino implements Runnable {
        private Thread thread;

        Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Thread Name = %s ", Thread.currentThread().getName()));
        }
    }
}
