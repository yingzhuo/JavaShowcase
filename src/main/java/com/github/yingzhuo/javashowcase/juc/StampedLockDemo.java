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
            // 写操作只能是悲观锁
            var stamp = lock.writeLock();

            try {
                n++;
            } finally {
                lock.unlock(stamp);
            }
        }

        public int getN() {
            // 首先尝试乐观模式
            var stamp = lock.tryOptimisticRead();
            int result = this.n;

            if (!lock.validate(stamp)) {
                // 乐观不成功转使用悲观锁
                stamp = lock.readLock();

                try {
                    result = this.n;
                    return result;
                } finally {
                    lock.unlock(stamp);
                }
            }

            return result;
        }
    }

}
