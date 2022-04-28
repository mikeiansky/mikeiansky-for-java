package com.winson.jvm.classloader;

/**
 * @author winson
 * @date 2022/4/28
 **/
public class Hello {

    public static String msg = "external-001";

    public static void sayHello(){
        System.out.println("Hello echo msg : " + msg);
    }

    public static void main(String[] args) {
        Hello.sayHello();
    }

}
