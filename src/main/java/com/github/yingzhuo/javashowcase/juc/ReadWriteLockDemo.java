package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.SleepUtils;
import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁使用案例
 *
 * @author 应卓
 * @see java.util.concurrent.locks.ReadWriteLock
 * @see java.util.concurrent.locks.ReentrantReadWriteLock
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        var threadPool = ThreadPoolFactories.createDefaults();

        var adder = new IntAdder(new ReentrantReadWriteLock(false));

        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                SleepUtils.sleep(Duration.ofMillis(20L));
                adder.incr();
            });
        }

        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                SleepUtils.sleep(Duration.ofMillis(10L));
                System.out.println(adder.getN());
            });
        }

        threadPool.shutdown();
    }

    private static class IntAdder {
        private final ReadWriteLock readWriteLock;
        private final Lock writeLock;
        private final Lock readLock;
        private int n = 0;

        public IntAdder(ReadWriteLock readWriteLock) {
            this.readWriteLock = readWriteLock;
            this.writeLock = readWriteLock.writeLock();
            this.readLock = readWriteLock.readLock();
        }

        public void incr() {
            writeLock.lock();
            try {
                n++;
            } finally {
                writeLock.unlock();
            }
        }

        public int getN() {
            readLock.lock();
            try {
                return n;
            } finally {
                readLock.unlock();
            }
        }
    }

}
