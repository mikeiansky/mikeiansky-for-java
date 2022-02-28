package com.winson.jdkapi.annotation;

import java.util.Arrays;

/**
 * @author winson
 * @date 2021/10/10
 **/
public class InheritAnnotationDemoV1 {

    public static void main(String[] args) {

        System.out.println("AnnotationUser.class.getAnnotations() : " + Arrays.asList(AnnotationUser.class.getAnnotations()));
        System.out.println("AnnotationUser.class.getDeclaredAnnotations() : " + Arrays.asList(AnnotationUser.class.getDeclaredAnnotations()));
        System.out.println(AnnotationUser.class.getAnnotatedSuperclass().getType());
        System.out.println(AnnotationUser.class.getAnnotatedInterfaces().length);
    }

}
