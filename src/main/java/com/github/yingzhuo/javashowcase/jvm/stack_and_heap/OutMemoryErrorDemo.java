package com.github.yingzhuo.javashowcase.jvm.stack_and_heap;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 抛出OOM，用VirtualVM工具观察老年代用量 <br>
 * 注意设置JVM参数
 *
 * @author 应卓
 * @see OutOfMemoryError
 */
public class OutMemoryErrorDemo {

    /*
     * 栈起始尺寸
     * -XX:InitialHeapSize=600m
     * 栈最大尺寸
     * -XX:MaxHeapSize=600m
     *
     * 栈新生代和老年代比例 (默认值是2)
     * -XX:NewRatio
     *
     * 伊甸园区与S0，S1的尺寸比例 (默认值是8)
     * -XX:SurvivorRatio
     */

    public static void main(String[] args) {
        var list = new ArrayList<byte[]>();

        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                list.add(new byte[1024 * 1024]);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (OutOfMemoryError oom) {
                System.out.println("内存不足");
                break;
            }
        }

        list.forEach(System.out::println);
        list.clear();
    }

}
