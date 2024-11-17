package com.github.yingzhuo.javashowcase.juc.future;

import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.LongStream;

/**
 * {@link FutureTask} 用例
 *
 * @author 应卓
 * @see CompletableFuture
 * @deprecated 直接使用 {@link CompletableFuture} 更舒服
 */
@Deprecated
public class FutureTaskDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var threadPool = ThreadPoolFactories.createDefaults();
        var countDownLatch = new CountDownLatch(3);

        var future1 = new FutureTask<>(new SumAction(0, 10000, countDownLatch));
        var future2 = new FutureTask<>(new SumAction(10001, 20000, countDownLatch));
        var future3 = new FutureTask<>(new SumAction(20001, 30000, countDownLatch));

        threadPool.execute(future1);
        threadPool.execute(future2);
        threadPool.execute(future3);

        countDownLatch.await();
        System.out.println(
                future1.get() + future2.get() + future3.get()
        );

        threadPool.shutdown();
    }

    private record SumAction(long from, long to,
                             CountDownLatch countDownLatch) implements Callable<Long>, Supplier<Long> {

        @Override
        public Long call() {
            return get();
        }

        @Override
        public Long get() {
            try {
                return LongStream.rangeClosed(from, to).reduce(0L, Long::sum);
            } finally {
                countDownLatch.countDown();
            }
        }
    }

}
