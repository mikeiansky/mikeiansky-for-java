package com.winson.lib.two;

import com.winson.lib.one.LibOneManager;

/**
 * @author winson
 * @date 2022/4/19
 **/
public class LibTwoManager {

    public void doAction(){
        LibOneManager libOneManager = new LibOneManager();
        System.out.println("doaction .... ");
        libOneManager.say("do @ two");
    }

}
