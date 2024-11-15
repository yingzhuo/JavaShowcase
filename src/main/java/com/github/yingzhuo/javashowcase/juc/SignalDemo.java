package com.github.yingzhuo.javashowcase.juc;

import com.github.yingzhuo.javashowcase.util.ThreadPoolFactories;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示线程之间互相通信 <br>
 * 启动26个线程，线程依次打印一个英文字母。
 *
 * @author 应卓
 */
public class SignalDemo {

    private static final int ASCII_COUNT = 26;
    private static final char FIRST_ASCII = 'a';

    public static void main(String[] args) {
        var threadPool = ThreadPoolFactories.createDefaults();

        var printer = new SharedPrinter();
        for (int i = 0; i < ASCII_COUNT; i++) {
            threadPool.execute(new SharedPrinterAction(printer, i));
        }

        threadPool.shutdown();
    }

    private record SharedPrinterAction(SharedPrinter printer, int index) implements Runnable {
        @Override
        public void run() {
            printer.print(index);
        }
    }

    private static class SharedPrinter {
        private final Lock lock = new ReentrantLock();
        private final Condition[] conditions = new Condition[ASCII_COUNT];
        private int currentIndex = 0;

        {
            for (int i = 0; i < ASCII_COUNT; i++) {
                this.conditions[i] = lock.newCondition();
            }
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

                System.out.println(getAsciiToPrint(index));

                incrCurrentIndex();
                nextCondition.signalAll();

            } finally {
                lock.unlock();
            }
        }

        private char getAsciiToPrint(int index) {
            return (char) (FIRST_ASCII + index);
        }

        private Condition getCurrentCondition(int index) {
            return this.conditions[index % ASCII_COUNT];
        }

        private Condition getNextCondition(int index) {
            return this.conditions[(index + 1) % ASCII_COUNT];
        }

        private void incrCurrentIndex() {
            this.currentIndex = (this.currentIndex + 1) % ASCII_COUNT;
        }
    }

}
