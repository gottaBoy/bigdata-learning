package com.my.concurrent;

public class Profiler {
    private static final ThreadLocal<Long> TIME_TL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final long begin() {
        long beginTime = System.currentTimeMillis();
        TIME_TL.set(beginTime);
        return beginTime;
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_TL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("Profiler [ BeginTime ] = %s", Profiler.begin()));
        Thread.sleep(1);
        System.out.println(String.format("Profiler [ EndTime - BeginTime ] = %s", Profiler.end()));
    }
}
