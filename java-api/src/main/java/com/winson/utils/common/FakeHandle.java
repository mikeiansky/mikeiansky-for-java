package com.winson.utils.common;

import java.lang.invoke.MethodHandles;

/**
 * @author winson
 * @date 2021/9/2
 **/
public class FakeHandle {

    public static MethodHandles.Lookup lookup(){
        return MethodHandles.lookup();
    }

    public void regular(){
        System.out.println("fake handle virtual regular ... ");
    }

    private void special(){
        System.out.println("fake handle special special ... ");
    }

}
