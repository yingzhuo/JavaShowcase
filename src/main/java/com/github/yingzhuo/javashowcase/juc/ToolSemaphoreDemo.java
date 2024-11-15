package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.RandomDurationUtils;
import com.github.yingzhuo.javashowcase.util.SleepUtils;
import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.concurrent.Semaphore;

/**
 * {@link Semaphore} 使用案例
 */
public class ToolSemaphoreDemo {

    private static final int CAR_COUNT = 10;
    private static final int PARKING_SPACE_COUNT = 4;

    public static void main(String[] args) {
        var threadPool = ThreadPoolFactories.createDefaults();

        var semaphore = new Semaphore(PARKING_SPACE_COUNT);

        for (int i = 0; i < CAR_COUNT; i++) {
            var carNumber = i + 1;
            threadPool.execute(new CarAction(semaphore, carNumber));
        }

        threadPool.shutdown();
    }

    private record CarAction(Semaphore semaphore, int carNumber) implements Runnable {

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(carNumber + "号车停入车位");
                SleepUtils.sleep(RandomDurationUtils.randomSeconds(5));
                System.out.println(carNumber + "号车离开车位");
            } catch (InterruptedException ignored) {
            } finally {
                semaphore.release();
            }
        }
    }

}
