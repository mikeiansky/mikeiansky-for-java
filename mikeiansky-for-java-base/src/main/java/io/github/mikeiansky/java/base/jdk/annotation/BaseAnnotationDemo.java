package io.github.mikeiansky.java.base.jdk.annotation;

import java.lang.annotation.*;

/**
 * @author mike ian
 * @date 2024/12/13
 * @desc
 **/
public class BaseAnnotationDemo {

    @Inherited
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface GrandFatherAnnotation {
        String gfvalue() default "grandFather";
    }

    @Inherited
    @GrandFatherAnnotation(gfvalue = "customize-grand-father")
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FatherAnnotation {
        String fvalue() default "father";
    }

    @FatherAnnotation(fvalue = "customize-father")
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SonAnnotation {
        String svalue() default "son";
    }

    @SonAnnotation(svalue = "customize-son")
    public static class One {

    }

    public static void main(String[] args) {
        Class<One> clazz = One.class;
        System.out.println("test is present");
        System.out.println(clazz.isAnnotationPresent(SonAnnotation.class));
        System.out.println(clazz.isAnnotationPresent(FatherAnnotation.class));
        System.out.println(clazz.isAnnotationPresent(GrandFatherAnnotation.class));

        System.out.println("get annotation ");
        SonAnnotation sonAnnotation = clazz.getAnnotation(SonAnnotation.class);
        System.out.println(sonAnnotation);
        FatherAnnotation fatherAnnotation = clazz.getAnnotation(FatherAnnotation.class);
        System.out.println(fatherAnnotation);
        GrandFatherAnnotation grandFatherAnnotation = clazz.getAnnotation(GrandFatherAnnotation.class);
        System.out.println(grandFatherAnnotation);
        System.out.println(sonAnnotation instanceof SonAnnotation);
        System.out.println(sonAnnotation instanceof FatherAnnotation);
        System.out.println(sonAnnotation instanceof GrandFatherAnnotation);
    }

}
