package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.SleepUtils;

import java.time.Duration;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * ForkJoin框架使用
 *
 * @author 应卓
 */
public class ForkJoinDemo {

    private static final int CPU_CORE = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        var forkJoinPool = new ForkJoinPool(CPU_CORE);

        var a = forkJoinPool.submit(new LongAdder(1, 1000));
        System.out.println(a.join());

        forkJoinPool.shutdown();
    }

    private static class LongAdder extends RecursiveTask<Long> {

        private final long left;
        private final long right;

        public LongAdder(long left, long right) {
            this.left = left;
            this.right = right;
        }

        @Override
        protected Long compute() {
            if (right - left <= 100) {
                SleepUtils.sleep(Duration.ofMillis(1000L));
                return LongStream.rangeClosed(left, right).reduce(0L, Long::sum);
            }

            long middle = (left + right) / 2;
            var leftAdder = new LongAdder(left, middle);
            var rightAdder = new LongAdder(middle + 1, right);

            leftAdder.fork();
            rightAdder.fork();

            return leftAdder.join() + rightAdder.join();
        }
    }

}
