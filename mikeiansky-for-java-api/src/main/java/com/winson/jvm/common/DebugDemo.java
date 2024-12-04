package com.winson.jvm.common;

/**
 * @author winson
 * @date 2021/7/18
 **/
public class DebugDemo {

    public static void outMethod(){
        System.out.println("out method 1");
        innerMethod();
        System.out.println("out method 2");
        innerMethod();
        System.out.println("out method 3");
        innerMethod();
        System.out.println("out method 4");
        innerMethod();
    }

    public static void innerMethod(){
        System.out.println("inner method 1");
        System.out.println("inner method 2");
        System.out.println("inner method 3");
        System.out.println("inner method 4");
    }


    public static void main(String[] args) {
        outMethod();
        outMethod();
        outMethod();
        outMethod();



    }

}
