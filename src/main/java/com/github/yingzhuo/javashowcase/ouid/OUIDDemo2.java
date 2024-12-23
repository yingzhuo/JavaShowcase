package com.github.yingzhuo.javashowcase.ouid;

import com.github.f4b6a3.uuid.UuidCreator;

public class OUIDDemo2 {

    // https://www.baeldung.com/java-generating-time-based-uuids

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        System.out.println(UuidCreator.getTimeBased());
                    }
                }
            }).start();
        }
    }

}
