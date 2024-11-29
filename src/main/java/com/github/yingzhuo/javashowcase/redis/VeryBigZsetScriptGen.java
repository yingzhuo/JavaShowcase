package com.github.yingzhuo.javashowcase.redis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.UUID;

/**
 * 为Redis生成测试用BigZset
 *
 * @author 应卓
 */
@SuppressWarnings("DuplicatedCode")
public class VeryBigZsetScriptGen {

    private static final Random RANDOM = new Random();

    private static final int MAX = 500_0000;
    private static final File OUTPUT = new File(String.format("/Users/yingzhuo/临时/redis-bigzset-%d.txt", MAX));
    private static final String KEY = "bigzset";

    public static void main(String[] args) throws IOException {
        var outputStream = new FileOutputStream(OUTPUT);
        var printerStream = new PrintStream(outputStream);

        for (int i = 0; i < MAX; i++) {
            printerStream.printf("ZADD %s %s %s%n", KEY, RANDOM.nextDouble(0.1, 10.0), UUID.randomUUID());
        }

        printerStream.close();
        outputStream.close();

        System.out.println("执行以下指令可以导入到Redis");
        System.out.printf("cat /Users/yingzhuo/临时/redis-bigzset-%d.txt | redis-cli --raw -c --no-auth-warning -a <你的密码> --pipe %n", MAX);
    }

}
