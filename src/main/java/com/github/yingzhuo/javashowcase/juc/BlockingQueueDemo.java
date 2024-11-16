package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 展示阻塞队列的用法
 *
 * @author 应卓
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        var threadPool = ThreadPoolFactories.createDefaults();
        var queue = new LinkedBlockingQueue<String>();

        threadPool.execute(new Producer(queue));
        threadPool.execute(new Consumer(queue));
        threadPool.shutdown();
    }

    private record Producer(BlockingQueue<String> queue) implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200L);
                    queue.put(UUID.randomUUID().toString());
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    private record Consumer(BlockingQueue<String> queue) implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200L);
                    var uuid = queue.take();
                    System.out.println(uuid);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

}
