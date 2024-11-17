package com.github.yingzhuo.javashowcase.jvm;

/**
 * 演示常量池基本理念
 *
 * @author 应卓
 */
public class StringTableDemo {

    public static void main(String[] args) {
        var s1 = new String("123");
        var s2 = "123";
        System.out.println(s1 == s2);

        var s3 = "abc";
        var s4 = "abc";
        System.out.println(s3 == s4);
    }

}
