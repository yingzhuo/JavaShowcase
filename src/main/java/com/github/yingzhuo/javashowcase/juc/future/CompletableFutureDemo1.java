package com.github.yingzhuo.javashowcase.juc.future;

import com.github.yingzhuo.javashowcase.util.SleepUtils;
import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

/**
 * 演示 {@link CompletableFuture} 的一般性用法
 *
 * @author 应卓
 * @see CountDownLatch
 */
public class CompletableFutureDemo1 {

    public static void main(String[] args) {

        final var threadPool = ThreadPoolFactories.createDefaults();
        final var countDownLatch = new CountDownLatch(3);

        var f1 = CompletableFuture.supplyAsync(new Slave("饺子"))
                .whenComplete((s, x) -> countDownLatch.countDown());

        var f2 = CompletableFuture.supplyAsync(new Slave("醋"))
                .whenComplete((s, x) -> countDownLatch.countDown());

        var f3 = CompletableFuture.supplyAsync(new Slave("蒜泥"))
                .whenComplete((s, x) -> countDownLatch.countDown());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // noop
        }

        System.out.println(f1.join());
        System.out.println(f2.join());
        System.out.println(f3.join());

        System.out.println("主线程吃饺子");
        threadPool.shutdown();
    }

    private record Slave(String target) implements Supplier<String> {

        @Override
        public String get() {
            SleepUtils.sleepRandomSeconds(5);
            return this.target;
        }
    }

}
