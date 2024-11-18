package com.github.yingzhuo.javashowcase.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadPoolFactoriesTestCase {

    @Test
    @DisplayName("测试线程池生成工具")
    public void test1() {
        var pool = ThreadPoolFactories.createDefaults();
        Assertions.assertNotNull(pool);
    }

}
