package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.SleepUtils;
import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.time.Duration;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * {@link CyclicBarrier} 使用案例
 */
public class ToolCyclicBarrierDemo {

    private static final int CYCLIC_BARRIER_PARTIES = 5;
    private static final int WORKER_THREAD_COUNT = CYCLIC_BARRIER_PARTIES * 3;

    public static void main(String[] args) {
        var threadPool = ThreadPoolFactories.createDefaults();
        var cyclicBarrier = new CyclicBarrier(CYCLIC_BARRIER_PARTIES, new FinalAction());

        for (int i = 0; i < WORKER_THREAD_COUNT; i++) {
            threadPool.execute(new WorkerAction(cyclicBarrier));
        }

        threadPool.shutdown();
    }

    private record WorkerAction(CyclicBarrier cyclicBarrier) implements Runnable {

        @Override
        public void run() {
            SleepUtils.sleep(Duration.ofSeconds(2L));

            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                // ignored
            }
        }
    }

    private static class FinalAction implements Runnable {
        @Override
        public void run() {
            System.out.println("FinalAction回调 " + Thread.currentThread().getName());
        }
    }

}
