package com.winson.jvm.common;

/**
 * @author winson
 * @date 2021/5/21
 **/
public class TestGC {

    public static void main(String[] args) {
        boolean flag = true;
        Object obj1 = null;
        while (flag){
            obj1 = new Object();
        }
        obj1.toString();
    }

}
