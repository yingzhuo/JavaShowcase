package com.github.yingzhuo.javashowcase.ouid;

import com.fasterxml.uuid.Generators;

public class OUIDDemo1 {

    // https://www.baeldung.com/java-generating-time-based-uuids

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            var ouid = Generators.timeBasedReorderedGenerator().generate();
            System.out.println(ouid);
        }
    }

}
