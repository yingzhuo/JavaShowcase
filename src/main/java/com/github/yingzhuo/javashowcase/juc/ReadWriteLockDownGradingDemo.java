package com.github.yingzhuo.javashowcase.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示读写锁的降级
 *
 * @author 应卓
 */
public class ReadWriteLockDownGradingDemo {

    public static void main(String[] args) {
        var rwLock = new ReentrantReadWriteLock();
        var readLock = rwLock.readLock();
        var writeLock = rwLock.writeLock();

        writeLock.lock();
        readLock.lock();

        System.out.println("此区域为写锁");

        writeLock.unlock();
        System.out.println("此区域降级为读锁");
        readLock.lock();
    }
}
