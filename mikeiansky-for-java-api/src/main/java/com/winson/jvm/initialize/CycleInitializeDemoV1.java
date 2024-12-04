package com.winson.jvm.initialize;

/**
 * @author winson
 * @date 2022/5/1
 **/
public class CycleInitializeDemoV1 {

    private int a = 0;
    // error
//    private int a = b;
    private int b = a;

    public static void main(String[] args) {

    }

}
