package com.github.yingzhuo.javashowcase.jvm.stringtable;

/**
 * 演示常量池基本理念
 *
 * @author 应卓
 * @see String#intern()
 */
public class StringTableDemo {

    public static void main(String[] args) {

        var java = "java";
        var s0 = java + " 17"; // 通过jclasslib等工具可知，字符串拼接实际由 StringBuild 完成

        var s1 = new String("123"); // 堆
        var s2 = "123"; // 常量池
        System.out.println(s1 == s2); // false 一个在堆，一个在常量池

        var s3 = "abc";
        var s4 = "abc";
        System.out.println(s3 == s4); // true 都在常量池

        var s5 = "a" + "b" + "c"; // 前端编译器直接得到结论 "abc" 等同于字面变量放入常量池
        var s6 = "abc";
        System.out.println(s5 == s6); // true

        var s7 = "bcd";
        var s8 = s7 + "";
        System.out.println(s7 == s8); // false
    }

}
