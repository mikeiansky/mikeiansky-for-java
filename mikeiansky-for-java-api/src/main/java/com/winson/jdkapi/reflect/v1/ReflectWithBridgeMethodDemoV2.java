package com.winson.jdkapi.reflect.v1;

import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2022/2/24
 **/
public class ReflectWithBridgeMethodDemoV2 {

    public static class Flag {

    }

    public static class SecondFlag extends Flag{

    }

    public static class High {
        public void sayFlag(SecondFlag flag){
            System.out.println("high sayFlag (SecondFlag): " + flag);
        }
    }

    public static class Low extends High{
//        public void sayFlag(SecondFlag flag){
//            System.out.println("Low sayFlag (SecondFlag): " + flag);
//        }

        public void sayFlag(Flag flag){
            System.out.println("Low sayFlag (Flag): " + flag);
        }

    }

    public static void main(String[] args) {
        // 这里是不会存在bridge方法的
        Class clazz = Low.class;
        for (Method method : clazz.getMethods()) {
            System.out.println(method.getName() + " , is Bridge : " + method.isBridge());
        }

        System.out.println(" ===== ");
        Low low = new Low();
        low.sayFlag(new SecondFlag());
        low.sayFlag(new Flag());

    }

}
