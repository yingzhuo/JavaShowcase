package com.github.yingzhuo.javashowcase.jvm.stringtable;

/**
 * 在Java6，Java8，Java17下分别运行
 *
 * @author 应卓
 */
public class StringInternDemo {

    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        String s3 = new String("1") + new String("1");
        String s4 = "11";
        String s5 = s3.intern();
        System.out.println(s3 == s4);
        System.out.println(s4 == s5);
    }

    private static void method2() {
        String s3 = new String("1") + new String("1");
        String s5 = s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        System.out.println(s4 == s5);
    }

}
