package com.my.concurrent;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

/**
 * https://zhuanlan.zhihu.com/p/471109617
 * https://blog.csdn.net/weixin_52851967/article/details/126033053
 * https://blog.csdn.net/xianweijian/article/details/120995889
 *
 */
public class MultiThread {
    public static void main(String[] args) {
        threadMXBean();
        new Thread(new TimeWaiting(), "TimeWaiting").start();
        new Thread(new Waiting(), "Waiting").start();
        new Thread(new Blocked(), "Blocked-1").start();
        new Thread(new Blocked(), "Blocked-2").start();
    }

    static class Blocked implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Blocked.class) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void threadMXBean() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(
                    String.format("ID = [ %s ], Name= [ %s ], LockName = [ %s ] ",
                            threadInfo.getThreadId(), threadInfo.getThreadName(), threadInfo.getLockName()));
        }
    }
}
