package com.github.yingzhuo.javashowcase.juc.future;

import com.github.yingzhuo.javashowcase.util.SleepUtils;
import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 演示 {@link CompletableFuture#applyToEither(CompletionStage, Function)} 的用法
 *
 * @author 应卓
 */
public class CompletableFutureDemo2 {

    public static void main(String[] args) {
        var threadPool = ThreadPoolFactories.createDefaults();

        var f1 = CompletableFuture.supplyAsync(new Worker(), threadPool);
        var f2 = CompletableFuture.supplyAsync(new Worker(), threadPool);
        var result = f1.applyToEither(f2, s -> s).join();

        System.out.println(result);
        threadPool.shutdown();
    }

    private static class Worker implements Callable<String>, Supplier<String> {

        @Override
        public String get() {
            return call();
        }

        @Override
        public String call() {
            SleepUtils.sleepRandomSeconds(7);
            var currentThread = Thread.currentThread();
            return String.format("%s (id: %d)", currentThread.getName(), currentThread.getId());
        }
    }

}
