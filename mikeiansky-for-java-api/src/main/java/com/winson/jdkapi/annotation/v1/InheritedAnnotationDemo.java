package com.winson.jdkapi.annotation.v1;

/**
 * @author winson
 * @date 2021/10/4
 **/
public class InheritedAnnotationDemo {



    @UserGroup
    public static class Animal{

    }

    public static class Cat extends Animal{

    }

    public static void main(String[] args) {

        System.out.println(Cat.class.isAnnotationPresent(UserGroup.class));
        System.out.println(Cat.class.isAnnotationPresent(Super.class));
        System.out.println(Cat.class.getAnnotation(UserGroup.class));
        System.out.println(Cat.class.getAnnotation(UserGroup.class).getClass());
        System.out.println(Cat.class.getAnnotation(UserGroup.class).getClass().isAnnotationPresent(Super.class));
        System.out.println(Cat.class.getAnnotation(UserGroup.class).annotationType().isAnnotationPresent(Super.class));
        System.out.println(UserGroup.class);
        System.out.println(UserGroup.class.isAnnotationPresent(Super.class));
        System.out.println(UserGroup.class.getAnnotation(Super.class));
        System.out.println(Animal.class.isAnnotationPresent(UserGroup.class));
        System.out.println(Animal.class.isAnnotationPresent(Super.class));

    }

}
