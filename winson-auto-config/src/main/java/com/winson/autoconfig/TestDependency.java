package com.winson.autoconfig;

import com.winson.lib.two.LibTwoManager;

/**
 * @author winson
 * @date 2022/4/24
 **/
public class TestDependency {

    public static void doAction(){
        LibTwoManager libTwoManager = new LibTwoManager();
        libTwoManager.doAction();
    }

}
