package com.github.yingzhuo.javashowcase.juc.tool;

import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

/**
 * {@link LongAdder} 用例 <br>
 * {@link LongAdder} 在超高竞争之下，效率要远高于 {@link java.util.concurrent.atomic.AtomicLong}
 *
 * @author 应卓
 */
public class ToolLongAdderDemo {

    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        var threadPool = ThreadPoolFactories.createDefaults();
        var adder = new LongAdder();
        var countDownLatch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                adder.add(1L);
                countDownLatch.countDown();
            });

        }

        countDownLatch.await();

        System.out.println(adder.sum());
        threadPool.shutdown();
    }

}
