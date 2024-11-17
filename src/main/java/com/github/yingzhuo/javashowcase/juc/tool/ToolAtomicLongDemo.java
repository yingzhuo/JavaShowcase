package com.github.yingzhuo.javashowcase.juc.tool;

import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * {@link AtomicLong} 用例
 * {@link LongAdder} 在超高竞争之下，效率要远高于 {@link java.util.concurrent.atomic.AtomicLong}
 *
 * @author 应卓
 */
public class ToolAtomicLongDemo {

    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        var threadPool = ThreadPoolFactories.createDefaults();
        var adder = new AtomicLong(0L);
        var countDownLatch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                adder.addAndGet(1);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        System.out.println(adder.get());
        threadPool.shutdown();
    }

}
