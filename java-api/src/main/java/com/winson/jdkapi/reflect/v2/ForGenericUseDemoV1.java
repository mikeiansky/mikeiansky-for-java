package com.winson.jdkapi.reflect.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2022/4/11
 **/
public class ForGenericUseDemoV1 {

    public static void main(String[] args) {

//        TargetObj<MyFlagTwo, MyFlagThree> targetObj = new TargetObj<MyFlagTwo, MyFlagThree>();
        TargetObj targetObj = new TargetObj();
        // 如果是 TargetObj<H,K>，下面的就可以
//        targetObj.createAV1(new Object());
        targetObj.createAV1(new MyFlagThree());
//        targetObj.createAV2(new Object());
        targetObj.createAV2(new MyFlagThree());
//        targetObj.consumeBV1(new Object());
        targetObj.consumeBV1(new MyFlagTwo());
        // 抽象类时有这个方法的，但是不能用
//        targetObj.consumeBV2(new Object());
        targetObj.consumeBV2(new MyFlagTwo());
        // 这里能反射出来
//        targetObj.make(new Object());
        targetObj.make(new MyFlagOne());

        TargetInterfaceObj<MyFlagOne> targetInterfaceObj = new TargetInterfaceObj<MyFlagOne>() {
            @Override
            public void make(MyFlagOne myFlagOne) {

            }
        };
        // 如果是接口的话，是没有这个方法的
//        targetInterfaceObj.make(new Object());
        targetInterfaceObj.make(new MyFlagOne());
        List<String> list = new ArrayList<>();

    }

}
