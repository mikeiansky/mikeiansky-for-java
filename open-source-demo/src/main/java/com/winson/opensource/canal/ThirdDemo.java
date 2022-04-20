package com.winson.opensource.canal;

import com.winson.lib.one.LibOneManager;
import com.winson.lib.two.LibTwoManager;

import java.lang.reflect.Field;

/**
 * @author winson
 * @date 2022/4/19
 **/
@MyAnnotation(LibOneManager.class)
public class ThirdDemo {

    @MyAnnotation(LibOneManager.class)
    public void test() {

    }

    @MyAnnotation(LibOneManager.class)
    public Field target;

    @MyAnnotation(LibOneManager.class)
    public ThirdDemo(@MyAnnotation(LibOneManager.class) int a) {

    }

    @MyAnnotation(LibOneManager.class)
    public static String name;

    @MyAnnotation(LibOneManager.class)
    public static void change(@MyAnnotation(LibOneManager.class) int c) {

    }

    public static void main(String[] args) {

//        LibTwoManager libTwoManager = new LibTwoManager();
//        System.out.println("two manager : "+libTwoManager);
//        libTwoManager.doAction();
//        System.out.println("two manager end : "+libTwoManager);

        System.out.println("start");
        ThirdDemo demo = new ThirdDemo(1);
        System.out.println("demo : " + demo);
//        demo.test();
        ThirdDemo.change(11);
        System.out.println("end");
//        System.out.println(LibOneManager.class);
        MyAnnotation myAnnotation = ThirdDemo.class.getDeclaredAnnotation(MyAnnotation.class);
        System.out.println(myAnnotation);
        System.out.println(myAnnotation.value());

    }

}
