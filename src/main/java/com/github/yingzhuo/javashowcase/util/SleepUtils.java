package com.github.yingzhuo.javashowcase.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public final class SleepUtils {

    private SleepUtils() {
        super();
    }

    public static void sleep(Duration time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time.toMillis());
        } catch (InterruptedException ignored) {
        }
    }

}
