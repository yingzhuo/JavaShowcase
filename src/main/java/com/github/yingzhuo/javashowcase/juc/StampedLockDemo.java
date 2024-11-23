package com.github.yingzhuo.javashowcase.juc;

import java.util.concurrent.locks.StampedLock;

/**
 * 演示StampedLock的用法
 *
 * @author 应卓
 * @see StampedLock
 */
public class StampedLockDemo {

    public static void main(String[] args) {
        var lock = new StampedLock();
        var adder = new Adder(lock);

        for (int i = 0; i < 10; i++) {
            new Thread(adder::incr).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(adder.getN());
            }).start();
        }
    }


    private static class Adder {
        private final StampedLock lock;
        private int n = 0;

        public Adder(StampedLock lock) {
            this.lock = lock;
        }

        public void incr() {
            var stamp = lock.writeLock();

            try {
                n++;
            } finally {
                lock.unlockWrite(stamp);
            }
        }

        public int getN() {
            var stamp = lock.tryOptimisticRead();

            if (!lock.validate(stamp)) {
                stamp = lock.readLock();

                try {
                    return n;
                } finally {
                    lock.unlockRead(stamp);
                }
            }

            return n;
        }
    }

}
