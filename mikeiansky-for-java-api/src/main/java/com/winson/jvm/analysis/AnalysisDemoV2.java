package com.winson.jvm.analysis;

import java.lang.reflect.Field;

/**
 * @author winson
 * @date 2021/9/1
 **/
public class AnalysisDemoV2 {

    public static class Human {
        public String motion = "human motion";
    }

    public interface Motion {
//        String motion = "motion";
        int motion = 99;
        default void race(){
            System.out.println("motion race " );
        }
    }

    public static class Person extends Human implements Motion{

    }

    public static void main(String[] args) {
        Person person = new Person();
        person.race();
        // 这里编译错误，提示有歧义
//        System.out.println(person.motion);

        Class clazz = Person.class;
        try {
            Field field = clazz.getField("motion");
            // 这里找到的是接口的motion
            System.out.println("motion field : " + field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println("analysis demo v2 run finish ... ");

    }

}
