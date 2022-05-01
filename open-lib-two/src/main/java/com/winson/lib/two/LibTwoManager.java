package com.winson.lib.two;

//import com.winson.lib.one.LibOneManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2022/4/19
 **/
//@MyCondition(LibOneManager.class)
public class LibTwoManager {

    public void doSomething(){
        System.out.println("do some thing ... ");
    }

    public void doFour(){
        System.out.println("doFour ===========> ");
    }

    public void doAction(){
//        LibOneManager libOneManager = new LibOneManager();
//        System.out.println("doaction .... ");
//        libOneManager.say("do @ two");

//        System.out.println(LibOneManager.class);

        System.out.println(LibTwoManager.class.getDeclaredAnnotations());

        System.out.println("lib two do action ... ");

//        MyCondition myCondition = LibTwoManager.class.getAnnotation(MyCondition.class);
//        System.out.println("my condition : " + myCondition);
//        Method valueMethod = null;
//        for (Method method : MyCondition.class.getMethods()) {
////            System.out.println("method : " + method.getName());
//            if(method.getName().endsWith("value")){
//                valueMethod = method;
//                break;
//            }
//        }
//        System.out.println("value method : " + valueMethod);
////        System.out.println("myCondition.value() " + myCondition.value());
//        try {
//            System.out.println("myCondition.value() " + valueMethod.invoke(myCondition, new Object[]{}));
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        System.out.println("lib two do action end ... ");

    }

}
