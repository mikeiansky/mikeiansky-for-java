package com.winson.jvm.initialize;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2022/1/23
 **/
public class InterfaceInitializeDemo {

    interface Hello{

    }

    public static void test(){
        try {
            Class clazz = Class.forName(String.valueOf(Hello.class));
            Method method = clazz.getMethod("hello",null);
            method.invoke(new Object(),null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
