package com.github.yingzhuo.javashowcase.juc;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * 演示 volatile 关键字的用法
 *
 * @author 应卓
 */
public class VolatileDemo {

    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {

        var adder = new IntAdder();
        var countDownLatch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    adder.incr();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println(adder.get());
    }

    private static class IntAdder implements Serializable {

        private volatile int value;

        public IntAdder() {
            this(0);
        }

        public IntAdder(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }

        public synchronized void add(int n) {
            this.value += n;
        }

        public void incr() {
            add(1);
        }
    }

}
