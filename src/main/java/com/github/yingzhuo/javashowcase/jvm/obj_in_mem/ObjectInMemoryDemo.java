package com.github.yingzhuo.javashowcase.jvm.obj_in_mem;

import lombok.*;
import org.openjdk.jol.info.ClassLayout;

import java.io.Serializable;

/**
 * 演示对象在内存中的结构
 *
 * @author 应卓
 */
public class ObjectInMemoryDemo {

    public static void main(String[] args) {
        var layout = ClassLayout.parseInstance(new Point(0D, 0D));
        System.out.println(layout.toPrintable());
    }

    private static class AbstractPoint implements Serializable {
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private static class Point extends AbstractPoint implements Serializable {
        private double x;
        private double y;
    }

}
