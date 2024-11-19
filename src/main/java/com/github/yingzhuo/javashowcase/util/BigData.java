package com.github.yingzhuo.javashowcase.util;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 超大对象
 *
 * @author 应卓
 */
public final class BigData implements Serializable {

    public static BigData newInstance() {
        return new BigData();
    }

    private static final SecureRandom RANDOM = new SecureRandom();

    private final byte[] data = new byte[1024 * 1024];

    /**
     * 私有构造方法
     */
    private BigData() {
        RANDOM.nextBytes(this.data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigData bigData = (BigData) o;

        return Arrays.equals(data, bigData.data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

}
