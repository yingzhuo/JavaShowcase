package com.github.yingzhuo.javashowcase.redis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.UUID;

/**
 * 为Redis生成测试用BigSet
 *
 * @author 应卓
 */
@SuppressWarnings("DuplicatedCode")
public class VeryBigSetScriptGen {

    private static final int MAX = 500_0000;
    private static final File OUTPUT = new File(String.format("/Users/yingzhuo/临时/redis-bigset-%d.txt", MAX));
    private static final String KEY = "bigset";

    public static void main(String[] args) throws IOException {
        var outputStream = new FileOutputStream(OUTPUT);
        var printerStream = new PrintStream(outputStream);

        for (int i = 0; i < MAX; i++) {
            printerStream.printf("SADD %s %s%n", KEY, UUID.randomUUID());
        }

        printerStream.close();
        outputStream.close();

        System.out.println("执行以下指令可以导入到Redis");
        System.out.printf("cat /Users/yingzhuo/临时/redis-bigset-%d.txt | redis-cli --raw -c --no-auth-warning -a <你的密码> --pipe %n", MAX);
    }

}
