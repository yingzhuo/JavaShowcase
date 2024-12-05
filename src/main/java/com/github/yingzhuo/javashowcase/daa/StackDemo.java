package com.github.yingzhuo.javashowcase.daa;

import java.util.Stack;

public class StackDemo {

    public static void main(String[] args) {
        var stack = new Stack<String>();
        stack.push("hello");

        System.out.println(stack.pop());
        System.out.println(stack.size());
    }

}
