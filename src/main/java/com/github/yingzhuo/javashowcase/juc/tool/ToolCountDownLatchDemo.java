package com.github.yingzhuo.javashowcase.juc.tool;

import com.github.yingzhuo.javashowcase.util.SleepUtils;
import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * {@link CountDownLatch} 使用案例
 *
 * @author 应卓
 */
public class ToolCountDownLatchDemo {

    private static final int WORKER_THREAD_COUNT = 15;

    public static void main(String[] args) {
        var threadPool = ThreadPoolFactories.createDefaults();
        var countDownLatch = new CountDownLatch(WORKER_THREAD_COUNT);

        threadPool.execute(new FinalAction(countDownLatch));
        for (int i = 0; i < WORKER_THREAD_COUNT; i++) {
            threadPool.execute(new WorkerAction(countDownLatch));
        }

        threadPool.shutdown();
    }

    private record WorkerAction(CountDownLatch countDownLatch) implements Runnable {
        @Override
        public void run() {
            SleepUtils.sleep(Duration.ofSeconds(2));
            System.out.printf("%s线程工作结束了%n", Thread.currentThread().getName());
            countDownLatch.countDown();
        }
    }

    private record FinalAction(CountDownLatch countDownLatch) implements Runnable {

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException ignored) {
            }

            System.out.println("其他的线程都结束工作了");
        }
    }

}
