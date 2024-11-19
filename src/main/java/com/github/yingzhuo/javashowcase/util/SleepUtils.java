package com.github.yingzhuo.javashowcase.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.github.yingzhuo.javashowcase.util.RandomDurationUtils.randomSeconds;

/**
 * 当前线程睡眠工具
 *
 * @author 应卓
 */
public abstract class SleepUtils {

    public static void sleep(Duration time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time.toMillis());
        } catch (InterruptedException ignored) {
        }
    }

    public static void sleepRandomSeconds(int max) {
        if (max <= 0) {
            return;
        }
        sleep(randomSeconds(max));
    }

}
