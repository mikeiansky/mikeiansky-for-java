package com.winson.opensource.canal;

import com.winson.lib.two.LibTwoManager;

/**
 * @author winson
 * @date 2022/4/19
 **/
public class ThirdDemo {

    public static void main(String[] args) {

        LibTwoManager libTwoManager = new LibTwoManager();
        System.out.println("two manager : "+libTwoManager);
        libTwoManager.doAction();
        System.out.println("two manager end : "+libTwoManager);

    }

}
