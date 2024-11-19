package com.github.yingzhuo.javashowcase.jvm.stack_and_heap;

import com.github.yingzhuo.javashowcase.util.BigData;

import java.lang.ref.SoftReference;
import java.util.HashSet;

/**
 * 软引用的用例
 *
 * @author 应卓
 * @see SoftReference
 */
public class SoftRefDemo {

    public static void main(String[] args) {

        var set = new HashSet<SoftReference<BigData>>();

        while (true) {
            // 实际不会导致OOM
            set.add(new SoftReference<>(BigData.newInstance()));
        }
    }

}
