package com.winson.base;

/**
 * @author winson
 * @date 2022/1/27
 **/
public class LoopDemo {

    public static void main(String[] args) {
        int i = 0;
        while (i < 10) {
            System.out.println("cc");
            int c = 1;
            System.out.println("nnn");
            if(i> 5){
                return;
            }
            i++;
        }
        System.out.println("app end ");

    }

}
