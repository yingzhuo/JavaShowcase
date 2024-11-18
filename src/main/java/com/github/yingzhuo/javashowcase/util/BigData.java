package com.github.yingzhuo.javashowcase.util;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 一个超大对象
 *
 * @author 应卓
 */
public final class BigData implements Serializable {

    public static BigData newInstance() {
        return new BigData();
    }

    private static final int SIZE = 1024 * 1024; // 1m
    private static final SecureRandom RANDOM = new SecureRandom();

    private final byte[] data = new byte[SIZE];

    private BigData() {
        RANDOM.nextBytes(this.data);
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigData bigData = (BigData) o;

        return Arrays.equals(data, bigData.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
