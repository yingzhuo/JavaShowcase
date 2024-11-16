package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.SleepUtils;

import java.time.Duration;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link java.util.concurrent.locks.LockSupport} 使用案例
 *
 * @author 应卓
 */
public class LockSupportDemo {

    public static void main(String[] args) {

        var lock = new ReentrantLock();

        var t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.printf("进入%s线程%n", Thread.currentThread().getName());
                LockSupport.park();
                System.out.printf("退出%s线程%n", Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }, "t1");

        t1.start();

        SleepUtils.sleep(Duration.ofSeconds(3L));

        var t2 = new Thread(() -> LockSupport.unpark(t1), "t2");
        t2.start();
    }

}
