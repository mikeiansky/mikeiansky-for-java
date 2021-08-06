package com.winson.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2021/8/6
 **/
public class ReflectDemoV1 {

    public static class Test{
        public static void target(int i) throws Exception {
//            if(1 == 1){
//                throw new Exception("test exception");
//            }
//            System.out.println("this is test target method!");
        }
        public static void target2(int i){

        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("main start ... ");

        Class clazz = Test.class;
        Method method = clazz.getMethod("target", int.class);
//        method.setAccessible(true);
        Method method1 = clazz.getMethod("target", int.class);
        Method method2 = clazz.getMethod("target2", int.class);

        for (int i = 0; i < 20000; i++) {
            method1.invoke(null, 1);
            method2.invoke(null, 1);
        }

        Test test = new Test();
//        method.invoke(null, null);
        long start = System.currentTimeMillis();
        Object[] obj = new Object[]{121};


        int size = 100_000_000;
        for (int i = 0; i < size; i++) {
            try {
//                method.invoke(test, 127);
                method.invoke(test, 128);
//                method.invoke(test, obj);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();

        System.out.println("main end ... time : " + (end - start));
    }

}
