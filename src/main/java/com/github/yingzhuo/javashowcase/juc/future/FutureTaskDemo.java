package com.github.yingzhuo.javashowcase.juc.future;

import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
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

        var future1 = new FutureTask<>(new SumAction(0, 100));
        var future2 = new FutureTask<>(new SumAction(101, 200));
        var future3 = new FutureTask<>(new SumAction(201, 300));

        threadPool.execute(future1);
        threadPool.execute(future2);
        threadPool.execute(future3);

        System.out.println(
                future1.get() + future2.get() + future3.get()
        );

        threadPool.shutdown();
    }

    private record SumAction(long from, long to) implements Callable<Long>, Supplier<Long> {

        @Override
        public Long call() {
            return get();
        }

        @Override
        public Long get() {
            return LongStream.rangeClosed(from, to).reduce(0L, Long::sum);
        }
    }

}
