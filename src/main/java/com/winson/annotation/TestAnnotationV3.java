package com.winson.annotation;

/**
 * @author winson
 * @date 2021/6/20
 **/
public class TestAnnotationV3 {

    public static void main(String[] args) {

        System.out.println(User.class.isAnnotationPresent(Super.class));
        System.out.println(User.class.isAnnotationPresent(UserGroup.class));
        System.out.println(UserGroup.class.isAnnotationPresent(Super.class));
        System.out.println(User.class.getAnnotation(UserGroup.class));
        System.out.println(UserGroup.class.getAnnotation(Super.class));
        System.out.println(User.class.getAnnotation(Super.class));

    }

}
