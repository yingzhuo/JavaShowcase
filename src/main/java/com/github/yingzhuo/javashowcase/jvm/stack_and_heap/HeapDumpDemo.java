package com.github.yingzhuo.javashowcase.jvm.stack_and_heap;

import com.github.yingzhuo.javashowcase.util.BigData;
import com.github.yingzhuo.javashowcase.util.SleepUtils;

import java.time.Duration;
import java.util.HashSet;

/**
 * HeapDump 分析工具练习 <br>
 * <ul>
 *     <li>VisualVM</li>
 *     <li>Eclipse MAT</li>
 * </ul>
 *
 * <ul>
 *     <li>-XX:InitialHeapSize=60m</li>
 *     <li>-XX:MaxHeapSize=60m</li>
 *     <li>-XX:+HeapDumpOnOutOfMemoryError</li>
 *     <li>-XX:HeapDumpPath=./Java-HeapDump.hprof</li>
 * </ul>
 *
 * @author 应卓
 */
public class HeapDumpDemo {

    public static void main(String[] args) {

        var bigSet = new HashSet<BigData>();

        try {
            while (true) {
                SleepUtils.sleep(Duration.ofMillis(20L));
                bigSet.add(BigData.newInstance());
            }
        } catch (OutOfMemoryError e) {
            System.out.println("内存溢出");
            System.out.println("Count = " + bigSet.size());
            throw e;
        }
    }

}
