package com.github.yingzhuo.javashowcase.jvm.classloading;

import java.util.Optional;

public class ClassLoaderTreeDemo {

    public static void main(String[] args) {

        // 系统类加载器 / 应用类加载器
        var systemClassLoader = ClassLoaderTreeDemo.class.getClassLoader();
        System.out.println(systemClassLoader.getClass().getName());

        // 扩展类加载器 / 平台类加载器
        var extensionClassLoader = systemClassLoader.getParent();
        System.out.println(extensionClassLoader.getClass().getName());

        // 引导类加载器
        var bootstrapClassLoader = extensionClassLoader.getParent();
        Optional.ofNullable(bootstrapClassLoader).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("没有找到")
        );
    }

}
