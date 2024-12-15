package io.github.mikeiansky.java.base.jdk.annotation;

import java.lang.annotation.*;

/**
 * @author mike ian
 * @date 2024/12/13
 * @desc
 **/
public class InheritAnnotationDemo {

    @Inherited
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InheritAnnotation {

    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NoInheritAnnotation {

    }

    @InheritAnnotation
    public static class Father {

    }

    public static class Son extends Father {

    }

    public static void main(String[] args) {
        Class<Son> clazz = Son.class;
        InheritAnnotation inheritAnnotation = clazz.getAnnotation(InheritAnnotation.class);
        InheritAnnotation inheritAnnotationDeclared = clazz.getDeclaredAnnotation(InheritAnnotation.class);
        NoInheritAnnotation noInheritAnnotation = clazz.getAnnotation(NoInheritAnnotation.class);
        NoInheritAnnotation noInheritAnnotationDeclared = clazz.getDeclaredAnnotation(NoInheritAnnotation.class);
        System.out.println(inheritAnnotation);
        System.out.println(inheritAnnotationDeclared);
        System.out.println(noInheritAnnotation);
        System.out.println(noInheritAnnotationDeclared);
    }

}
