package com.github.yingzhuo.javashowcase.jvm.stack_and_heap;

/**
 * 演示栈溢出异常 <br>
 * -XX:ThreadMaxStack=144k
 *
 * @author 应卓
 * @see StackOverflowError
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        try {
            badMethod();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void badMethod() {
        badMethod();
    }

}
