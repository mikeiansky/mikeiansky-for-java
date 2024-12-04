package com.winson.jdkapi.reflect.v1;

import com.winson.jdkapi.annotation.v1.User;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/10/5
 **/
public class AnnotationUtils {

    public static void getAllAnnotation(Class clazz) {
        Annotation[] annotations = clazz.getAnnotations();
        System.out.println(clazz);
        System.out.println(Arrays.stream(clazz.getAnnotatedInterfaces()).collect(Collectors.toList()));
        System.out.println(Arrays.stream(clazz.getAnnotations()).collect(Collectors.toList()));
        System.out.println(clazz.getComponentType());
        System.out.println(clazz.getAnnotatedSuperclass());
        System.out.println(clazz.getCanonicalName());
        System.out.println(Arrays.stream(clazz.getDeclaredAnnotations()).collect(Collectors.toList()));
        System.out.println("---------");
        for (Annotation annotation : annotations) {
            getAnnotation(annotation);
        }
    }

    public static void getAnnotation(Annotation annotation) {
        if(annotation.annotationType().getName().startsWith("java.lang")){
            return;
        }
        System.out.println("==== find one annotation ==== ");
        System.out.println(annotation.annotationType());
        System.out.println(annotation);
        Annotation[] annotations = annotation.annotationType().getAnnotations();
        for (Annotation an : annotations) {
            getAnnotation(an);
        }

    }

    public static void main(String[] args) {
        getAllAnnotation(User.class);

//        System.out.println(User.class.isAnnotationPresent(Super.class));
//        System.out.println(User.class.getDeclaredAnnotation(Super.class));
//        System.out.println(Arrays.stream(User.class.getDeclaredAnnotationsByType(Super.class)).collect(Collectors.toList()));


    }

}
