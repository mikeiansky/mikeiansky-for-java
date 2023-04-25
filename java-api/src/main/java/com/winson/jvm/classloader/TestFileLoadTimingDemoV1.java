package com.winson.jvm.classloader;

import java.util.Scanner;

/**
 * @author Mike Ian
 * @date 2023/4/25
 **/
public class TestFileLoadTimingDemoV1 {

    public static void main(String[] args) throws ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        // 这里阻塞之后，删除对应的F1, 和F2文件会报错
        String r = scanner.next();
        System.out.println("rrrr : " + r);

        F1 f1 = new F1();       // 删除这个文件，会报错, NoClassDefFoundError

        Class.forName("com.winson.jvm.classloader.F33");        // ClassNotFoundException
        System.out.println("complete");

    }

}
