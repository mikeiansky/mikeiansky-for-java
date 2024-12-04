package com.winson.jvm.classloader;

/**
 * @author Mike Ian
 * @date 2023/4/25
 **/
public class F2 {

    static {
        System.out.println("F2 static block");
    }

    public void create(){
        Class c = F3.class;
    }

}
