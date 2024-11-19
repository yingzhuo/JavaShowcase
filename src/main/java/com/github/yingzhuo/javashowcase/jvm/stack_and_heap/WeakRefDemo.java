package com.github.yingzhuo.javashowcase.jvm.stack_and_heap;

import com.github.yingzhuo.javashowcase.util.BigData;

import java.lang.ref.WeakReference;
import java.util.HashSet;

/**
 * 弱引用的用例
 *
 * @author 应卓
 * @see WeakReference
 */
public class WeakRefDemo {

    public static void main(String[] args) {

        var set = new HashSet<WeakReference<BigData>>();

        while (true) {
            // 实际不会导致OOM
            set.add(new WeakReference<>(BigData.newInstance()));
        }
    }

}
