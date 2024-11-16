package com.github.yingzhuo.javashowcase.juc.future;

import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.concurrent.CompletableFuture;

/**
 * 演示{@link java.util.concurrent.CompletableFuture} 串行运行多个线程
 *
 * @author 应卓
 */
public class CompletableFutureDemo3 {

    public static void main(String[] args) {
        var threadPool = ThreadPoolFactories.createDefaults();

        CompletableFuture.supplyAsync(() -> "一条活鱼", threadPool)
                .thenApply(x -> "一条去除内脏和鱼鳞的鱼")
                .thenApply(x -> "一条被蒸熟的鱼")
                .thenApply(x -> "一条放了调料的清蒸鱼")
                .thenAccept(System.out::println);

        threadPool.shutdown();
    }

}
