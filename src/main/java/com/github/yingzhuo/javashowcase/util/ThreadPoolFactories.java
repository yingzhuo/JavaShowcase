package com.github.yingzhuo.javashowcase.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ThreadPoolFactories {

    private ThreadPoolFactories() {
        super();
    }

    public static ThreadPoolExecutor createDefaults() {
        return new ThreadPoolExecutor(
                15,
                30,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(60),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
