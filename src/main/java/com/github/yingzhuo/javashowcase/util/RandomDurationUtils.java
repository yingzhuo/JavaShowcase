package com.github.yingzhuo.javashowcase.util;

import java.time.Duration;
import java.util.Random;

/**
 * 内部工具
 *
 * @author 应卓
 */
public abstract class RandomDurationUtils {

    private static final Random RANDOM = new Random();

    public static Duration randomSeconds(int upperBound) {
        var sec = RANDOM.nextInt(upperBound) + 1;
        return Duration.ofSeconds(sec);
    }

}
