package com.github.yingzhuo.javashowcase.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池创建工具
 *
 * @author 应卓
 */
public abstract class ThreadPoolFactories {

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
