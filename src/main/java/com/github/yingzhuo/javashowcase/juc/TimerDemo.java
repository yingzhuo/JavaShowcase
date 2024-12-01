package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.SleepUtils;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer} 用例
 */
public class TimerDemo {

    public static void main(String[] args) {
        var timer = new Timer("自动刷新", true);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("自动刷新");
            }
        }, 1000 / 3);

        SleepUtils.sleep(Duration.ofSeconds(2L));
    }

}
