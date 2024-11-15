package com.github.yingzhuo.javashowcase.util;

import java.time.Duration;
import java.util.Random;

public final class RandomDurationUtils {

    private static final Random RANDOM = new Random();

    private RandomDurationUtils() {
        super();
    }

    public static Duration randomSeconds(int upperBound) {
        var sec = RANDOM.nextInt(upperBound) + 1;
        return Duration.ofSeconds(sec);
    }

}
