package com.winson.syntax.jvm;

/**
 * @author winson
 * @date 2021/5/21
 **/
public class TestEscape {

    public static void main(String[] args) {

        Object obj = new Object();
        synchronized (obj){
            System.out.println("Hello World!");
        }

    }

}
