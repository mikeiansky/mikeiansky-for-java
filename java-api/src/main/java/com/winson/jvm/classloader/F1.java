package com.winson.jvm.classloader;

/**
 * @author Mike Ian
 * @date 2023/4/25
 **/
public class F1 {

    static {
        System.out.println("F1111 static block");
    }

    public static void create(){
        Class c = F2.class;
    }

}
