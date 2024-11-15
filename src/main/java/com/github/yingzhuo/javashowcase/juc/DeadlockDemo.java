package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.SleepUtils;

import java.time.Duration;

/**
 * 本例子展示死锁
 */
public class DeadlockDemo {

    private static final Object MONITOR1 = new Object();
    private static final Object MONITOR2 = new Object();
    private static final Duration SLEEP_3S = Duration.ofSeconds(3L);

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (MONITOR1) {
                SleepUtils.sleep(SLEEP_3S);
                synchronized (MONITOR2) {
                    System.out.println(Thread.currentThread() + " working...");
                }
            }
        }, "T1").start();

        new Thread(() -> {
            synchronized (MONITOR2) {
                SleepUtils.sleep(SLEEP_3S);
                synchronized (MONITOR1) {
                    System.out.println(Thread.currentThread() + " working...");
                }
            }
        }, "T2").start();
    }

}
