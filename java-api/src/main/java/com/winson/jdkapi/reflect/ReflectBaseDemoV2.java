package com.winson.jdkapi.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author winson
 * @date 2021/10/4
 **/
@ReflectAnnotation("class-annotation")
public class ReflectBaseDemoV2 {

    public static final int finalFlagField = 12;

    public static volatile transient int muchFlagField;

    @ReflectAnnotation("private-field")
    private String privateField;

    public String publicField = "good";

    protected String protectedField;

    String defaultField;

    @ReflectAnnotation("static-private-field")
    private static String staticPrivateField;

    public static String staticPublicField = "winson";

    protected static String staticProtectedField;

    static String staticDefaultField;

    @ReflectAnnotation("public-method")
    public String publicMethod(int arg1, @ReflectAnnotation("public-method-arg2") String arg2) {
        return null;
    }

    private String privateMethod(int arg1, String arg2) {
        return null;
    }

    protected String protectedMethod(int arg1, String arg2) {
        return null;
    }

    String defaultMethod(int arg1, String arg2) {
        return null;
    }

    @ReflectAnnotation("static-public-method")
    public static String staticPublicMethod(int arg1, @ReflectAnnotation("static-public-method-arg2") String arg2) {
        return null;
    }

    private static String staticPrivateMethod(int arg1, String arg2) {
        return null;
    }

    protected static String staticProtectedMethod(int arg1, String arg2) {
        return null;
    }

    static String staticDefaultMethod(int arg1, String arg2) {
        return null;
    }

    public static void main(String[] args) {

        Class clazz = ReflectBaseDemoV2.class;
        System.out.println("============ FIELDS ============");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
//            System.out.println(field.getName());
            displayField(field);
        }
        System.out.println("============ DECLARED FIELDS ============");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
//            System.out.println(field.getName());
            displayField(field);
        }
        System.out.println("============ METHODS ============");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            displayMethod(method);
        }
        System.out.println("============ DECLARED METHODS ============");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            displayMethod(method);
        }
    }

    public static void displayField(Field field) {
        Annotation annotation = field.getAnnotation(ReflectAnnotation.class);
        if (annotation != null) {
            System.out.println(" ==> annotation : " + annotation);
            System.out.println(" ==> " + annotation.getClass().getAnnotation(RootAnnotationWithInherited.class));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("name : " + field.getName())
                .append(" , return type : " + field.getType())
                .append(" , modifiers : " + field.getModifiers())
                .append(" , annotation : " + annotation);

        System.out.println(sb);
    }

    public static void displayMethod(Method method) {
        Annotation annotation = method.getAnnotation(ReflectAnnotation.class);
        Parameter[] ps = method.getParameters();
        StringBuilder sbPs = new StringBuilder();
        sbPs.append("{");
        for (Parameter p : ps) {
            sbPs.append("[ name : ")
                    .append(p.getName())
                    .append(", type : ")
                    .append(p.getType())
                    .append(", annotation : ")
                    .append(p.getAnnotation(ReflectAnnotation.class))
                    .append("],");
        }
        sbPs.append("}");

        StringBuilder sb = new StringBuilder();
        sb.append("name : " + method.getName())
                .append(" , return type : " + method.getReturnType())
                .append(" , parameters : " + sbPs)
                .append(" , modifiers : " + method.getModifiers())
                .append(" , annotation : " + annotation);

        System.out.println(sb);
    }

}
