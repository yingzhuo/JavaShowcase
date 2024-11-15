package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.SleepUtils;

import java.util.concurrent.Executors;

/**
 * 本例子展示死锁
 *
 * @author 应卓
 */
public class DeadlockDemo {

    private static final Object MONITOR_1 = new Object();
    private static final Object MONITOR_2 = new Object();

    public static void main(String[] args) {

        var threadPool = Executors.newFixedThreadPool(2);

        var r1 = (Runnable) () -> {
            synchronized (MONITOR_1) {
                SleepUtils.sleepInRandomSeconds(3);
                synchronized (MONITOR_2) {
                    System.out.println(Thread.currentThread() + " working...");
                }
            }
        };

        var r2 = (Runnable) () -> {
            synchronized (MONITOR_2) {
                SleepUtils.sleepInRandomSeconds(3);
                synchronized (MONITOR_1) {
                    System.out.println(Thread.currentThread() + " working...");
                }
            }
        };

        threadPool.execute(r1);
        threadPool.execute(r2);

        threadPool.shutdown();
    }

}
