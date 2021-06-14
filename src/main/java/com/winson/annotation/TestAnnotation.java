package com.winson.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2021/6/14
 **/
public class TestAnnotation {

    @AllTargetAnnotation
    public static class Person {

    }

    public static class Student extends Person {

        @TypeAnnotation
        public Student() {

        }

        public void getParamAnnotation(@AllTargetAnnotation(address = "beijing") String name) {
            System.out.println("student test ---> " + name);

            @TypeAnnotation
            String address = "winson";

        }

    }

    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println("test annotation start ... ");
        AllTargetAnnotation allTargetAnnotation = Person.class.getAnnotation(AllTargetAnnotation.class);
        System.out.println(Person.class.isAnnotationPresent(AllTargetAnnotation.class));
        System.out.println(allTargetAnnotation);
        Annotation[] annotations = AllTargetAnnotation.class.getAnnotations();
//        System.out.println(annotations.length);
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        TypeAnnotation typeAnnotation = AllTargetAnnotation.class.getAnnotation(TypeAnnotation.class);
        System.out.println(typeAnnotation.value());

        System.out.println("-------- student --------");
        AllTargetAnnotation studentAllTargetAnnotation = Student.class.getAnnotation(AllTargetAnnotation.class);
        System.out.println(studentAllTargetAnnotation);
        System.out.println(studentAllTargetAnnotation == allTargetAnnotation);
        Student student = new Student();
        student.getParamAnnotation("zwx");

        Method method = Student.class.getMethod("getParamAnnotation", String.class);
        System.out.println(method);
        Annotation[][] paramAnnotation = method.getParameterAnnotations();
        for (Annotation[] pa : paramAnnotation) {
            for (Annotation annotation : pa) {
                System.out.println(annotation);
                if (annotation instanceof AllTargetAnnotation) {
                    System.out.println(((AllTargetAnnotation) annotation).address());
                    System.out.println(((AllTargetAnnotation) annotation).value());
                }
            }
        }

        System.out.println("test annotation stop ... ");
    }

}
