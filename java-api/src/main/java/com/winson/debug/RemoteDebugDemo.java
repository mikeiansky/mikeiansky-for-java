package com.winson.debug;

import java.util.Scanner;

/**
 * @author winson
 * @date 2021/9/27
 **/
public class RemoteDebugDemo {

    public static void main(String[] args) {

        // -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9527
        System.out.println("Hello World");

        Scanner scanner = new Scanner(System.in);
        System.out.println("1111");
        System.out.println("2222");
        String input = scanner.next();
        while (!input.equals("quit")) {
            int a = 102;
            int b = 103;
            System.out.println("your input is : " + input);
            int c = 213;
            input = scanner.next();
            System.out.println("5555");
        }


    }

}
