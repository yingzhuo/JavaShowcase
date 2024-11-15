package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示线程之间互相通信
 */
public class SignalDemo1 {
    private static final int ASCII_COUNT = 26;

    public static void main(String[] args) {
        var threadPool = ThreadPoolFactories.createDefaults();
        var printer = new SharedPrinter();

        for (int i = 0; i < ASCII_COUNT; i++) {
            final var index = i;
            threadPool.execute(() -> printer.print(index));
        }

        threadPool.shutdown();
    }

    private static class SharedPrinter {
        private final Lock lock = new ReentrantLock();
        private final List<Condition> conditions;
        private int currentIndex = 0;

        public SharedPrinter() {
            var list = new ArrayList<Condition>();
            for (int i = 0; i < ASCII_COUNT; i++) {
                list.add(lock.newCondition());
            }
            this.conditions = Collections.unmodifiableList(list);
        }

        public void print(int index) {
            if (index < 0 || index >= 26) {
                return;
            }

            lock.lock();

            try {
                var currentCondition = getCurrentCondition(index);
                var nextCondition = getNextCondition(index);

                while (this.currentIndex != index) {
                    try {
                        currentCondition.await();
                    } catch (InterruptedException ignored) {
                    }
                }

                System.out.println((char) ('a' + index));

                incrCurrentIndex();
                nextCondition.signalAll();

            } finally {
                lock.unlock();
            }
        }

        private Condition getCurrentCondition(int index) {
            return this.conditions.get(index % conditions.size());
        }

        private Condition getNextCondition(int index) {
            return this.conditions.get((index + 1) % conditions.size());
        }

        private void incrCurrentIndex() {
            this.currentIndex = (this.currentIndex + 1) % ASCII_COUNT;
        }
    }

}
